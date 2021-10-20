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
import modelo.Ruta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Autobus;

/**
 *
 * @author user
 */
public class AutobusJpaController implements Serializable {

    public AutobusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autobus autobus) throws PreexistingEntityException, Exception {
        if (autobus.getRutaCollection() == null) {
            autobus.setRutaCollection(new ArrayList<Ruta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ruta> attachedRutaCollection = new ArrayList<Ruta>();
            for (Ruta rutaCollectionRutaToAttach : autobus.getRutaCollection()) {
                rutaCollectionRutaToAttach = em.getReference(rutaCollectionRutaToAttach.getClass(), rutaCollectionRutaToAttach.getRutaPK());
                attachedRutaCollection.add(rutaCollectionRutaToAttach);
            }
            autobus.setRutaCollection(attachedRutaCollection);
            em.persist(autobus);
            for (Ruta rutaCollectionRuta : autobus.getRutaCollection()) {
                Autobus oldAutobusOfRutaCollectionRuta = rutaCollectionRuta.getAutobus();
                rutaCollectionRuta.setAutobus(autobus);
                rutaCollectionRuta = em.merge(rutaCollectionRuta);
                if (oldAutobusOfRutaCollectionRuta != null) {
                    oldAutobusOfRutaCollectionRuta.getRutaCollection().remove(rutaCollectionRuta);
                    oldAutobusOfRutaCollectionRuta = em.merge(oldAutobusOfRutaCollectionRuta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutobus(autobus.getIdAutobus()) != null) {
                throw new PreexistingEntityException("Autobus " + autobus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autobus autobus) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autobus persistentAutobus = em.find(Autobus.class, autobus.getIdAutobus());
            Collection<Ruta> rutaCollectionOld = persistentAutobus.getRutaCollection();
            Collection<Ruta> rutaCollectionNew = autobus.getRutaCollection();
            List<String> illegalOrphanMessages = null;
            for (Ruta rutaCollectionOldRuta : rutaCollectionOld) {
                if (!rutaCollectionNew.contains(rutaCollectionOldRuta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruta " + rutaCollectionOldRuta + " since its autobus field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ruta> attachedRutaCollectionNew = new ArrayList<Ruta>();
            for (Ruta rutaCollectionNewRutaToAttach : rutaCollectionNew) {
                rutaCollectionNewRutaToAttach = em.getReference(rutaCollectionNewRutaToAttach.getClass(), rutaCollectionNewRutaToAttach.getRutaPK());
                attachedRutaCollectionNew.add(rutaCollectionNewRutaToAttach);
            }
            rutaCollectionNew = attachedRutaCollectionNew;
            autobus.setRutaCollection(rutaCollectionNew);
            autobus = em.merge(autobus);
            for (Ruta rutaCollectionNewRuta : rutaCollectionNew) {
                if (!rutaCollectionOld.contains(rutaCollectionNewRuta)) {
                    Autobus oldAutobusOfRutaCollectionNewRuta = rutaCollectionNewRuta.getAutobus();
                    rutaCollectionNewRuta.setAutobus(autobus);
                    rutaCollectionNewRuta = em.merge(rutaCollectionNewRuta);
                    if (oldAutobusOfRutaCollectionNewRuta != null && !oldAutobusOfRutaCollectionNewRuta.equals(autobus)) {
                        oldAutobusOfRutaCollectionNewRuta.getRutaCollection().remove(rutaCollectionNewRuta);
                        oldAutobusOfRutaCollectionNewRuta = em.merge(oldAutobusOfRutaCollectionNewRuta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = autobus.getIdAutobus();
                if (findAutobus(id) == null) {
                    throw new NonexistentEntityException("The autobus with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autobus autobus;
            try {
                autobus = em.getReference(Autobus.class, id);
                autobus.getIdAutobus();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autobus with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ruta> rutaCollectionOrphanCheck = autobus.getRutaCollection();
            for (Ruta rutaCollectionOrphanCheckRuta : rutaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Autobus (" + autobus + ") cannot be destroyed since the Ruta " + rutaCollectionOrphanCheckRuta + " in its rutaCollection field has a non-nullable autobus field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(autobus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autobus> findAutobusEntities() {
        return findAutobusEntities(true, -1, -1);
    }

    public List<Autobus> findAutobusEntities(int maxResults, int firstResult) {
        return findAutobusEntities(false, maxResults, firstResult);
    }

    private List<Autobus> findAutobusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autobus.class));
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

    public Autobus findAutobus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autobus.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutobusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autobus> rt = cq.from(Autobus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
