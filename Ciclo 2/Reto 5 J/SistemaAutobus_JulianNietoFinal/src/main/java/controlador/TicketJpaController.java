/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Pasajero;
import modelo.Ruta;
import modelo.Ticket;
import modelo.TicketPK;

/**
 *
 * @author user
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) throws PreexistingEntityException, Exception {
        if (ticket.getTicketPK() == null) {
            ticket.setTicketPK(new TicketPK());
        }
        ticket.getTicketPK().setRutaTerminalidTerminal(ticket.getRuta().getRutaPK().getTerminalidTerminal());
        ticket.getTicketPK().setPasajeroidPasajero(ticket.getPasajero().getPasajeroPK().getIdPasajero());
        ticket.getTicketPK().setRutaidRuta(ticket.getRuta().getRutaPK().getIdRutarut());
        ticket.getTicketPK().setRutaAutobusidAutobus(ticket.getRuta().getRutaPK().getAutobusidAutobus());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasajero pasajero = ticket.getPasajero();
            if (pasajero != null) {
                pasajero = em.getReference(pasajero.getClass(), pasajero.getPasajeroPK());
                ticket.setPasajero(pasajero);
            }
            Ruta ruta = ticket.getRuta();
            if (ruta != null) {
                ruta = em.getReference(ruta.getClass(), ruta.getRutaPK());
                ticket.setRuta(ruta);
            }
            em.persist(ticket);
            if (pasajero != null) {
                pasajero.getTicketCollection().add(ticket);
                pasajero = em.merge(pasajero);
            }
            if (ruta != null) {
                ruta.getTicketCollection().add(ticket);
                ruta = em.merge(ruta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTicket(ticket.getTicketPK()) != null) {
                throw new PreexistingEntityException("Ticket " + ticket + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws NonexistentEntityException, Exception {
        ticket.getTicketPK().setRutaTerminalidTerminal(ticket.getRuta().getRutaPK().getTerminalidTerminal());
        ticket.getTicketPK().setPasajeroidPasajero(ticket.getPasajero().getPasajeroPK().getIdPasajero());
        ticket.getTicketPK().setRutaidRuta(ticket.getRuta().getRutaPK().getIdRutarut());
        ticket.getTicketPK().setRutaAutobusidAutobus(ticket.getRuta().getRutaPK().getAutobusidAutobus());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getTicketPK());
            Pasajero pasajeroOld = persistentTicket.getPasajero();
            Pasajero pasajeroNew = ticket.getPasajero();
            Ruta rutaOld = persistentTicket.getRuta();
            Ruta rutaNew = ticket.getRuta();
            if (pasajeroNew != null) {
                pasajeroNew = em.getReference(pasajeroNew.getClass(), pasajeroNew.getPasajeroPK());
                ticket.setPasajero(pasajeroNew);
            }
            if (rutaNew != null) {
                rutaNew = em.getReference(rutaNew.getClass(), rutaNew.getRutaPK());
                ticket.setRuta(rutaNew);
            }
            ticket = em.merge(ticket);
            if (pasajeroOld != null && !pasajeroOld.equals(pasajeroNew)) {
                pasajeroOld.getTicketCollection().remove(ticket);
                pasajeroOld = em.merge(pasajeroOld);
            }
            if (pasajeroNew != null && !pasajeroNew.equals(pasajeroOld)) {
                pasajeroNew.getTicketCollection().add(ticket);
                pasajeroNew = em.merge(pasajeroNew);
            }
            if (rutaOld != null && !rutaOld.equals(rutaNew)) {
                rutaOld.getTicketCollection().remove(ticket);
                rutaOld = em.merge(rutaOld);
            }
            if (rutaNew != null && !rutaNew.equals(rutaOld)) {
                rutaNew.getTicketCollection().add(ticket);
                rutaNew = em.merge(rutaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TicketPK id = ticket.getTicketPK();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TicketPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getTicketPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            Pasajero pasajero = ticket.getPasajero();
            if (pasajero != null) {
                pasajero.getTicketCollection().remove(ticket);
                pasajero = em.merge(pasajero);
            }
            Ruta ruta = ticket.getRuta();
            if (ruta != null) {
                ruta.getTicketCollection().remove(ticket);
                ruta = em.merge(ruta);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ticket findTicket(TicketPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
