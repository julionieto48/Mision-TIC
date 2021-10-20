/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Pasajero;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Equipaje;

/**
 *
 * @author user
 */
public class EquipajeJpaController implements Serializable {

    public EquipajeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equipaje equipaje) throws PreexistingEntityException, Exception {
        if (equipaje.getPasajeroCollection() == null) {
            equipaje.setPasajeroCollection(new ArrayList<Pasajero>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pasajero> attachedPasajeroCollection = new ArrayList<Pasajero>();
            for (Pasajero pasajeroCollectionPasajeroToAttach : equipaje.getPasajeroCollection()) {
                pasajeroCollectionPasajeroToAttach = em.getReference(pasajeroCollectionPasajeroToAttach.getClass(), pasajeroCollectionPasajeroToAttach.getPasajeroPK());
                attachedPasajeroCollection.add(pasajeroCollectionPasajeroToAttach);
            }
            equipaje.setPasajeroCollection(attachedPasajeroCollection);
            em.persist(equipaje);
            for (Pasajero pasajeroCollectionPasajero : equipaje.getPasajeroCollection()) {
                Equipaje oldEquipajeidEquipajeOfPasajeroCollectionPasajero = pasajeroCollectionPasajero.getEquipajeidEquipaje();
                pasajeroCollectionPasajero.setEquipajeidEquipaje(equipaje);
                pasajeroCollectionPasajero = em.merge(pasajeroCollectionPasajero);
                if (oldEquipajeidEquipajeOfPasajeroCollectionPasajero != null) {
                    oldEquipajeidEquipajeOfPasajeroCollectionPasajero.getPasajeroCollection().remove(pasajeroCollectionPasajero);
                    oldEquipajeidEquipajeOfPasajeroCollectionPasajero = em.merge(oldEquipajeidEquipajeOfPasajeroCollectionPasajero);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquipaje(equipaje.getIdEquipajeeq()) != null) {
                throw new PreexistingEntityException("Equipaje " + equipaje + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipaje equipaje) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipaje persistentEquipaje = em.find(Equipaje.class, equipaje.getIdEquipajeeq());
            Collection<Pasajero> pasajeroCollectionOld = persistentEquipaje.getPasajeroCollection();
            Collection<Pasajero> pasajeroCollectionNew = equipaje.getPasajeroCollection();
            Collection<Pasajero> attachedPasajeroCollectionNew = new ArrayList<Pasajero>();
            for (Pasajero pasajeroCollectionNewPasajeroToAttach : pasajeroCollectionNew) {
                pasajeroCollectionNewPasajeroToAttach = em.getReference(pasajeroCollectionNewPasajeroToAttach.getClass(), pasajeroCollectionNewPasajeroToAttach.getPasajeroPK());
                attachedPasajeroCollectionNew.add(pasajeroCollectionNewPasajeroToAttach);
            }
            pasajeroCollectionNew = attachedPasajeroCollectionNew;
            equipaje.setPasajeroCollection(pasajeroCollectionNew);
            equipaje = em.merge(equipaje);
            for (Pasajero pasajeroCollectionOldPasajero : pasajeroCollectionOld) {
                if (!pasajeroCollectionNew.contains(pasajeroCollectionOldPasajero)) {
                    pasajeroCollectionOldPasajero.setEquipajeidEquipaje(null);
                    pasajeroCollectionOldPasajero = em.merge(pasajeroCollectionOldPasajero);
                }
            }
            for (Pasajero pasajeroCollectionNewPasajero : pasajeroCollectionNew) {
                if (!pasajeroCollectionOld.contains(pasajeroCollectionNewPasajero)) {
                    Equipaje oldEquipajeidEquipajeOfPasajeroCollectionNewPasajero = pasajeroCollectionNewPasajero.getEquipajeidEquipaje();
                    pasajeroCollectionNewPasajero.setEquipajeidEquipaje(equipaje);
                    pasajeroCollectionNewPasajero = em.merge(pasajeroCollectionNewPasajero);
                    if (oldEquipajeidEquipajeOfPasajeroCollectionNewPasajero != null && !oldEquipajeidEquipajeOfPasajeroCollectionNewPasajero.equals(equipaje)) {
                        oldEquipajeidEquipajeOfPasajeroCollectionNewPasajero.getPasajeroCollection().remove(pasajeroCollectionNewPasajero);
                        oldEquipajeidEquipajeOfPasajeroCollectionNewPasajero = em.merge(oldEquipajeidEquipajeOfPasajeroCollectionNewPasajero);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = equipaje.getIdEquipajeeq();
                if (findEquipaje(id) == null) {
                    throw new NonexistentEntityException("The equipaje with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipaje equipaje;
            try {
                equipaje = em.getReference(Equipaje.class, id);
                equipaje.getIdEquipajeeq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipaje with id " + id + " no longer exists.", enfe);
            }
            Collection<Pasajero> pasajeroCollection = equipaje.getPasajeroCollection();
            for (Pasajero pasajeroCollectionPasajero : pasajeroCollection) {
                pasajeroCollectionPasajero.setEquipajeidEquipaje(null);
                pasajeroCollectionPasajero = em.merge(pasajeroCollectionPasajero);
            }
            em.remove(equipaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipaje> findEquipajeEntities() {
        return findEquipajeEntities(true, -1, -1);
    }

    public List<Equipaje> findEquipajeEntities(int maxResults, int firstResult) {
        return findEquipajeEntities(false, maxResults, firstResult);
    }

    private List<Equipaje> findEquipajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipaje.class));
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

    public Equipaje findEquipaje(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipaje> rt = cq.from(Equipaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
