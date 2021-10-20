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
import modelo.Equipaje;
import modelo.Usuario;
import modelo.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Pasajero;
import modelo.PasajeroPK;

/**
 *
 * @author user
 */
public class PasajeroJpaController implements Serializable {

    public PasajeroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pasajero pasajero) throws PreexistingEntityException, Exception {
        if (pasajero.getPasajeroPK() == null) {
            pasajero.setPasajeroPK(new PasajeroPK());
        }
        if (pasajero.getTicketCollection() == null) {
            pasajero.setTicketCollection(new ArrayList<Ticket>());
        }
        pasajero.getPasajeroPK().setUsuarioNombreuser(pasajero.getUsuario().getNombreuser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipaje equipajeidEquipaje = pasajero.getEquipajeidEquipaje();
            if (equipajeidEquipaje != null) {
                equipajeidEquipaje = em.getReference(equipajeidEquipaje.getClass(), equipajeidEquipaje.getIdEquipajeeq());
                pasajero.setEquipajeidEquipaje(equipajeidEquipaje);
            }
            Usuario usuario = pasajero.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getNombreuser());
                pasajero.setUsuario(usuario);
            }
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : pasajero.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getTicketPK());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            pasajero.setTicketCollection(attachedTicketCollection);
            em.persist(pasajero);
            if (equipajeidEquipaje != null) {
                equipajeidEquipaje.getPasajeroCollection().add(pasajero);
                equipajeidEquipaje = em.merge(equipajeidEquipaje);
            }
            if (usuario != null) {
                usuario.getPasajeroCollection().add(pasajero);
                usuario = em.merge(usuario);
            }
            for (Ticket ticketCollectionTicket : pasajero.getTicketCollection()) {
                Pasajero oldPasajeroOfTicketCollectionTicket = ticketCollectionTicket.getPasajero();
                ticketCollectionTicket.setPasajero(pasajero);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldPasajeroOfTicketCollectionTicket != null) {
                    oldPasajeroOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldPasajeroOfTicketCollectionTicket = em.merge(oldPasajeroOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPasajero(pasajero.getPasajeroPK()) != null) {
                throw new PreexistingEntityException("Pasajero " + pasajero + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pasajero pasajero) throws IllegalOrphanException, NonexistentEntityException, Exception {
        pasajero.getPasajeroPK().setUsuarioNombreuser(pasajero.getUsuario().getNombreuser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasajero persistentPasajero = em.find(Pasajero.class, pasajero.getPasajeroPK());
            Equipaje equipajeidEquipajeOld = persistentPasajero.getEquipajeidEquipaje();
            Equipaje equipajeidEquipajeNew = pasajero.getEquipajeidEquipaje();
            Usuario usuarioOld = persistentPasajero.getUsuario();
            Usuario usuarioNew = pasajero.getUsuario();
            Collection<Ticket> ticketCollectionOld = persistentPasajero.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = pasajero.getTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its pasajero field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (equipajeidEquipajeNew != null) {
                equipajeidEquipajeNew = em.getReference(equipajeidEquipajeNew.getClass(), equipajeidEquipajeNew.getIdEquipajeeq());
                pasajero.setEquipajeidEquipaje(equipajeidEquipajeNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getNombreuser());
                pasajero.setUsuario(usuarioNew);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getTicketPK());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            pasajero.setTicketCollection(ticketCollectionNew);
            pasajero = em.merge(pasajero);
            if (equipajeidEquipajeOld != null && !equipajeidEquipajeOld.equals(equipajeidEquipajeNew)) {
                equipajeidEquipajeOld.getPasajeroCollection().remove(pasajero);
                equipajeidEquipajeOld = em.merge(equipajeidEquipajeOld);
            }
            if (equipajeidEquipajeNew != null && !equipajeidEquipajeNew.equals(equipajeidEquipajeOld)) {
                equipajeidEquipajeNew.getPasajeroCollection().add(pasajero);
                equipajeidEquipajeNew = em.merge(equipajeidEquipajeNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPasajeroCollection().remove(pasajero);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPasajeroCollection().add(pasajero);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    Pasajero oldPasajeroOfTicketCollectionNewTicket = ticketCollectionNewTicket.getPasajero();
                    ticketCollectionNewTicket.setPasajero(pasajero);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldPasajeroOfTicketCollectionNewTicket != null && !oldPasajeroOfTicketCollectionNewTicket.equals(pasajero)) {
                        oldPasajeroOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldPasajeroOfTicketCollectionNewTicket = em.merge(oldPasajeroOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PasajeroPK id = pasajero.getPasajeroPK();
                if (findPasajero(id) == null) {
                    throw new NonexistentEntityException("The pasajero with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PasajeroPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasajero pasajero;
            try {
                pasajero = em.getReference(Pasajero.class, id);
                pasajero.getPasajeroPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pasajero with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = pasajero.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pasajero (" + pasajero + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable pasajero field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Equipaje equipajeidEquipaje = pasajero.getEquipajeidEquipaje();
            if (equipajeidEquipaje != null) {
                equipajeidEquipaje.getPasajeroCollection().remove(pasajero);
                equipajeidEquipaje = em.merge(equipajeidEquipaje);
            }
            Usuario usuario = pasajero.getUsuario();
            if (usuario != null) {
                usuario.getPasajeroCollection().remove(pasajero);
                usuario = em.merge(usuario);
            }
            em.remove(pasajero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pasajero> findPasajeroEntities() {
        return findPasajeroEntities(true, -1, -1);
    }

    public List<Pasajero> findPasajeroEntities(int maxResults, int firstResult) {
        return findPasajeroEntities(false, maxResults, firstResult);
    }

    private List<Pasajero> findPasajeroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pasajero.class));
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

    public Pasajero findPasajero(PasajeroPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pasajero.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasajeroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pasajero> rt = cq.from(Pasajero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
