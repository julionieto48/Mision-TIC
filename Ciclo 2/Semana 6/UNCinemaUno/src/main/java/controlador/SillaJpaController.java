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
import modelo.Tiquete;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Espectador;
import modelo.Silla;

/**
 *
 * @author user
 */
public class SillaJpaController implements Serializable {

    public SillaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Silla silla) throws PreexistingEntityException, Exception {
        if (silla.getTiqueteCollection() == null) {
            silla.setTiqueteCollection(new ArrayList<Tiquete>());
        }
        if (silla.getEspectadorCollection() == null) {
            silla.setEspectadorCollection(new ArrayList<Espectador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tiquete> attachedTiqueteCollection = new ArrayList<Tiquete>();
            for (Tiquete tiqueteCollectionTiqueteToAttach : silla.getTiqueteCollection()) {
                tiqueteCollectionTiqueteToAttach = em.getReference(tiqueteCollectionTiqueteToAttach.getClass(), tiqueteCollectionTiqueteToAttach.getTiquetePK());
                attachedTiqueteCollection.add(tiqueteCollectionTiqueteToAttach);
            }
            silla.setTiqueteCollection(attachedTiqueteCollection);
            Collection<Espectador> attachedEspectadorCollection = new ArrayList<Espectador>();
            for (Espectador espectadorCollectionEspectadorToAttach : silla.getEspectadorCollection()) {
                espectadorCollectionEspectadorToAttach = em.getReference(espectadorCollectionEspectadorToAttach.getClass(), espectadorCollectionEspectadorToAttach.getEspectadorPK());
                attachedEspectadorCollection.add(espectadorCollectionEspectadorToAttach);
            }
            silla.setEspectadorCollection(attachedEspectadorCollection);
            em.persist(silla);
            for (Tiquete tiqueteCollectionTiquete : silla.getTiqueteCollection()) {
                Silla oldSillaOfTiqueteCollectionTiquete = tiqueteCollectionTiquete.getSilla();
                tiqueteCollectionTiquete.setSilla(silla);
                tiqueteCollectionTiquete = em.merge(tiqueteCollectionTiquete);
                if (oldSillaOfTiqueteCollectionTiquete != null) {
                    oldSillaOfTiqueteCollectionTiquete.getTiqueteCollection().remove(tiqueteCollectionTiquete);
                    oldSillaOfTiqueteCollectionTiquete = em.merge(oldSillaOfTiqueteCollectionTiquete);
                }
            }
            for (Espectador espectadorCollectionEspectador : silla.getEspectadorCollection()) {
                Silla oldSillaOfEspectadorCollectionEspectador = espectadorCollectionEspectador.getSilla();
                espectadorCollectionEspectador.setSilla(silla);
                espectadorCollectionEspectador = em.merge(espectadorCollectionEspectador);
                if (oldSillaOfEspectadorCollectionEspectador != null) {
                    oldSillaOfEspectadorCollectionEspectador.getEspectadorCollection().remove(espectadorCollectionEspectador);
                    oldSillaOfEspectadorCollectionEspectador = em.merge(oldSillaOfEspectadorCollectionEspectador);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSilla(silla.getIdSilla()) != null) {
                throw new PreexistingEntityException("Silla " + silla + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Silla silla) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Silla persistentSilla = em.find(Silla.class, silla.getIdSilla());
            Collection<Tiquete> tiqueteCollectionOld = persistentSilla.getTiqueteCollection();
            Collection<Tiquete> tiqueteCollectionNew = silla.getTiqueteCollection();
            Collection<Espectador> espectadorCollectionOld = persistentSilla.getEspectadorCollection();
            Collection<Espectador> espectadorCollectionNew = silla.getEspectadorCollection();
            List<String> illegalOrphanMessages = null;
            for (Tiquete tiqueteCollectionOldTiquete : tiqueteCollectionOld) {
                if (!tiqueteCollectionNew.contains(tiqueteCollectionOldTiquete)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tiquete " + tiqueteCollectionOldTiquete + " since its silla field is not nullable.");
                }
            }
            for (Espectador espectadorCollectionOldEspectador : espectadorCollectionOld) {
                if (!espectadorCollectionNew.contains(espectadorCollectionOldEspectador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Espectador " + espectadorCollectionOldEspectador + " since its silla field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tiquete> attachedTiqueteCollectionNew = new ArrayList<Tiquete>();
            for (Tiquete tiqueteCollectionNewTiqueteToAttach : tiqueteCollectionNew) {
                tiqueteCollectionNewTiqueteToAttach = em.getReference(tiqueteCollectionNewTiqueteToAttach.getClass(), tiqueteCollectionNewTiqueteToAttach.getTiquetePK());
                attachedTiqueteCollectionNew.add(tiqueteCollectionNewTiqueteToAttach);
            }
            tiqueteCollectionNew = attachedTiqueteCollectionNew;
            silla.setTiqueteCollection(tiqueteCollectionNew);
            Collection<Espectador> attachedEspectadorCollectionNew = new ArrayList<Espectador>();
            for (Espectador espectadorCollectionNewEspectadorToAttach : espectadorCollectionNew) {
                espectadorCollectionNewEspectadorToAttach = em.getReference(espectadorCollectionNewEspectadorToAttach.getClass(), espectadorCollectionNewEspectadorToAttach.getEspectadorPK());
                attachedEspectadorCollectionNew.add(espectadorCollectionNewEspectadorToAttach);
            }
            espectadorCollectionNew = attachedEspectadorCollectionNew;
            silla.setEspectadorCollection(espectadorCollectionNew);
            silla = em.merge(silla);
            for (Tiquete tiqueteCollectionNewTiquete : tiqueteCollectionNew) {
                if (!tiqueteCollectionOld.contains(tiqueteCollectionNewTiquete)) {
                    Silla oldSillaOfTiqueteCollectionNewTiquete = tiqueteCollectionNewTiquete.getSilla();
                    tiqueteCollectionNewTiquete.setSilla(silla);
                    tiqueteCollectionNewTiquete = em.merge(tiqueteCollectionNewTiquete);
                    if (oldSillaOfTiqueteCollectionNewTiquete != null && !oldSillaOfTiqueteCollectionNewTiquete.equals(silla)) {
                        oldSillaOfTiqueteCollectionNewTiquete.getTiqueteCollection().remove(tiqueteCollectionNewTiquete);
                        oldSillaOfTiqueteCollectionNewTiquete = em.merge(oldSillaOfTiqueteCollectionNewTiquete);
                    }
                }
            }
            for (Espectador espectadorCollectionNewEspectador : espectadorCollectionNew) {
                if (!espectadorCollectionOld.contains(espectadorCollectionNewEspectador)) {
                    Silla oldSillaOfEspectadorCollectionNewEspectador = espectadorCollectionNewEspectador.getSilla();
                    espectadorCollectionNewEspectador.setSilla(silla);
                    espectadorCollectionNewEspectador = em.merge(espectadorCollectionNewEspectador);
                    if (oldSillaOfEspectadorCollectionNewEspectador != null && !oldSillaOfEspectadorCollectionNewEspectador.equals(silla)) {
                        oldSillaOfEspectadorCollectionNewEspectador.getEspectadorCollection().remove(espectadorCollectionNewEspectador);
                        oldSillaOfEspectadorCollectionNewEspectador = em.merge(oldSillaOfEspectadorCollectionNewEspectador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = silla.getIdSilla();
                if (findSilla(id) == null) {
                    throw new NonexistentEntityException("The silla with id " + id + " no longer exists.");
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
            Silla silla;
            try {
                silla = em.getReference(Silla.class, id);
                silla.getIdSilla();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The silla with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tiquete> tiqueteCollectionOrphanCheck = silla.getTiqueteCollection();
            for (Tiquete tiqueteCollectionOrphanCheckTiquete : tiqueteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Silla (" + silla + ") cannot be destroyed since the Tiquete " + tiqueteCollectionOrphanCheckTiquete + " in its tiqueteCollection field has a non-nullable silla field.");
            }
            Collection<Espectador> espectadorCollectionOrphanCheck = silla.getEspectadorCollection();
            for (Espectador espectadorCollectionOrphanCheckEspectador : espectadorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Silla (" + silla + ") cannot be destroyed since the Espectador " + espectadorCollectionOrphanCheckEspectador + " in its espectadorCollection field has a non-nullable silla field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(silla);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Silla> findSillaEntities() {
        return findSillaEntities(true, -1, -1);
    }

    public List<Silla> findSillaEntities(int maxResults, int firstResult) {
        return findSillaEntities(false, maxResults, firstResult);
    }

    private List<Silla> findSillaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Silla.class));
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

    public Silla findSilla(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Silla.class, id);
        } finally {
            em.close();
        }
    }

    public int getSillaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Silla> rt = cq.from(Silla.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
