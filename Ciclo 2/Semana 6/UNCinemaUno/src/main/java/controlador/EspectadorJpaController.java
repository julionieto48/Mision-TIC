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
import modelo.Espectador;
import modelo.EspectadorPK;
import modelo.Silla;

/**
 *
 * @author user
 */
public class EspectadorJpaController implements Serializable {

    public EspectadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Espectador espectador) throws PreexistingEntityException, Exception {
        if (espectador.getEspectadorPK() == null) {
            espectador.setEspectadorPK(new EspectadorPK());
        }
        espectador.getEspectadorPK().setSillaidSilla(espectador.getSilla().getIdSilla());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Silla silla = espectador.getSilla();
            if (silla != null) {
                silla = em.getReference(silla.getClass(), silla.getIdSilla());
                espectador.setSilla(silla);
            }
            em.persist(espectador);
            if (silla != null) {
                silla.getEspectadorCollection().add(espectador);
                silla = em.merge(silla);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEspectador(espectador.getEspectadorPK()) != null) {
                throw new PreexistingEntityException("Espectador " + espectador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Espectador espectador) throws NonexistentEntityException, Exception {
        espectador.getEspectadorPK().setSillaidSilla(espectador.getSilla().getIdSilla());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Espectador persistentEspectador = em.find(Espectador.class, espectador.getEspectadorPK());
            Silla sillaOld = persistentEspectador.getSilla();
            Silla sillaNew = espectador.getSilla();
            if (sillaNew != null) {
                sillaNew = em.getReference(sillaNew.getClass(), sillaNew.getIdSilla());
                espectador.setSilla(sillaNew);
            }
            espectador = em.merge(espectador);
            if (sillaOld != null && !sillaOld.equals(sillaNew)) {
                sillaOld.getEspectadorCollection().remove(espectador);
                sillaOld = em.merge(sillaOld);
            }
            if (sillaNew != null && !sillaNew.equals(sillaOld)) {
                sillaNew.getEspectadorCollection().add(espectador);
                sillaNew = em.merge(sillaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EspectadorPK id = espectador.getEspectadorPK();
                if (findEspectador(id) == null) {
                    throw new NonexistentEntityException("The espectador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EspectadorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Espectador espectador;
            try {
                espectador = em.getReference(Espectador.class, id);
                espectador.getEspectadorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The espectador with id " + id + " no longer exists.", enfe);
            }
            Silla silla = espectador.getSilla();
            if (silla != null) {
                silla.getEspectadorCollection().remove(espectador);
                silla = em.merge(silla);
            }
            em.remove(espectador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Espectador> findEspectadorEntities() {
        return findEspectadorEntities(true, -1, -1);
    }

    public List<Espectador> findEspectadorEntities(int maxResults, int firstResult) {
        return findEspectadorEntities(false, maxResults, firstResult);
    }

    private List<Espectador> findEspectadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Espectador.class));
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

    public Espectador findEspectador(EspectadorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Espectador.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspectadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Espectador> rt = cq.from(Espectador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
