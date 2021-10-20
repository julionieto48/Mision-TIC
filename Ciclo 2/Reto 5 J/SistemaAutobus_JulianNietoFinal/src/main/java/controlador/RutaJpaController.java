/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Autobus;
import modelo.Terminal;
import modelo.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Ruta;
import modelo.RutaPK;

/**
 *
 * @author user
 */
public class RutaJpaController implements Serializable {

    public RutaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ruta ruta) throws PreexistingEntityException, Exception {
        if (ruta.getRutaPK() == null) {
            ruta.setRutaPK(new RutaPK());
        }
        if (ruta.getTicketCollection() == null) {
            ruta.setTicketCollection(new ArrayList<Ticket>());
        }
        ruta.getRutaPK().setTerminalidTerminal(ruta.getTerminal().getIdTerminalterm());
        ruta.getRutaPK().setAutobusidAutobus(ruta.getAutobus().getIdAutobus());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autobus autobus = ruta.getAutobus();
            if (autobus != null) {
                autobus = em.getReference(autobus.getClass(), autobus.getIdAutobus());
                ruta.setAutobus(autobus);
            }
            Terminal terminal = ruta.getTerminal();
            if (terminal != null) {
                terminal = em.getReference(terminal.getClass(), terminal.getIdTerminalterm());
                ruta.setTerminal(terminal);
            }
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : ruta.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getTicketPK());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            ruta.setTicketCollection(attachedTicketCollection);
            em.persist(ruta);
            if (autobus != null) {
                autobus.getRutaCollection().add(ruta);
                autobus = em.merge(autobus);
            }
            if (terminal != null) {
                terminal.getRutaCollection().add(ruta);
                terminal = em.merge(terminal);
            }
            for (Ticket ticketCollectionTicket : ruta.getTicketCollection()) {
                Ruta oldRutaOfTicketCollectionTicket = ticketCollectionTicket.getRuta();
                ticketCollectionTicket.setRuta(ruta);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldRutaOfTicketCollectionTicket != null) {
                    oldRutaOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldRutaOfTicketCollectionTicket = em.merge(oldRutaOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRuta(ruta.getRutaPK()) != null) {
                throw new PreexistingEntityException("Ruta " + ruta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ruta ruta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        ruta.getRutaPK().setTerminalidTerminal(ruta.getTerminal().getIdTerminalterm());
        ruta.getRutaPK().setAutobusidAutobus(ruta.getAutobus().getIdAutobus());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruta persistentRuta = em.find(Ruta.class, ruta.getRutaPK());
            Autobus autobusOld = persistentRuta.getAutobus();
            Autobus autobusNew = ruta.getAutobus();
            Terminal terminalOld = persistentRuta.getTerminal();
            Terminal terminalNew = ruta.getTerminal();
            Collection<Ticket> ticketCollectionOld = persistentRuta.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = ruta.getTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its ruta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (autobusNew != null) {
                autobusNew = em.getReference(autobusNew.getClass(), autobusNew.getIdAutobus());
                ruta.setAutobus(autobusNew);
            }
            if (terminalNew != null) {
                terminalNew = em.getReference(terminalNew.getClass(), terminalNew.getIdTerminalterm());
                ruta.setTerminal(terminalNew);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getTicketPK());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            ruta.setTicketCollection(ticketCollectionNew);
            ruta = em.merge(ruta);
            if (autobusOld != null && !autobusOld.equals(autobusNew)) {
                autobusOld.getRutaCollection().remove(ruta);
                autobusOld = em.merge(autobusOld);
            }
            if (autobusNew != null && !autobusNew.equals(autobusOld)) {
                autobusNew.getRutaCollection().add(ruta);
                autobusNew = em.merge(autobusNew);
            }
            if (terminalOld != null && !terminalOld.equals(terminalNew)) {
                terminalOld.getRutaCollection().remove(ruta);
                terminalOld = em.merge(terminalOld);
            }
            if (terminalNew != null && !terminalNew.equals(terminalOld)) {
                terminalNew.getRutaCollection().add(ruta);
                terminalNew = em.merge(terminalNew);
            }
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    Ruta oldRutaOfTicketCollectionNewTicket = ticketCollectionNewTicket.getRuta();
                    ticketCollectionNewTicket.setRuta(ruta);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldRutaOfTicketCollectionNewTicket != null && !oldRutaOfTicketCollectionNewTicket.equals(ruta)) {
                        oldRutaOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldRutaOfTicketCollectionNewTicket = em.merge(oldRutaOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RutaPK id = ruta.getRutaPK();
                if (findRuta(id) == null) {
                    throw new NonexistentEntityException("The ruta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RutaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruta ruta;
            try {
                ruta = em.getReference(Ruta.class, id);
                ruta.getRutaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ruta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = ruta.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ruta (" + ruta + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable ruta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Autobus autobus = ruta.getAutobus();
            if (autobus != null) {
                autobus.getRutaCollection().remove(ruta);
                autobus = em.merge(autobus);
            }
            Terminal terminal = ruta.getTerminal();
            if (terminal != null) {
                terminal.getRutaCollection().remove(ruta);
                terminal = em.merge(terminal);
            }
            em.remove(ruta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ruta> findRutaEntities() {
        return findRutaEntities(true, -1, -1);
    }

    public List<Ruta> findRutaEntities(int maxResults, int firstResult) {
        return findRutaEntities(false, maxResults, firstResult);
    }

    private List<Ruta> findRutaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ruta.class));
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

    public Ruta findRuta(RutaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ruta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRutaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ruta> rt = cq.from(Ruta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
