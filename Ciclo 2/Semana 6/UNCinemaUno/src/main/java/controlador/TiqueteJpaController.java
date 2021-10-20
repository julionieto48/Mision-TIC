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
import modelo.Horario;
import modelo.Silla;
import modelo.Tiquete;
import modelo.TiquetePK;

/**
 *
 * @author user
 */
public class TiqueteJpaController implements Serializable {

    public TiqueteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiquete tiquete) throws PreexistingEntityException, Exception {
        if (tiquete.getTiquetePK() == null) {
            tiquete.setTiquetePK(new TiquetePK());
        }
        tiquete.getTiquetePK().setHorarioPeliculaidPelicula(tiquete.getHorario().getHorarioPK().getPeliculaidPelicula());
        tiquete.getTiquetePK().setSillaidSilla(tiquete.getSilla().getIdSilla());
        tiquete.getTiquetePK().setHorarioSalaidSala(tiquete.getHorario().getHorarioPK().getSalaidSala());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario horario = tiquete.getHorario();
            if (horario != null) {
                horario = em.getReference(horario.getClass(), horario.getHorarioPK());
                tiquete.setHorario(horario);
            }
            Silla silla = tiquete.getSilla();
            if (silla != null) {
                silla = em.getReference(silla.getClass(), silla.getIdSilla());
                tiquete.setSilla(silla);
            }
            em.persist(tiquete);
            if (horario != null) {
                horario.getTiqueteCollection().add(tiquete);
                horario = em.merge(horario);
            }
            if (silla != null) {
                silla.getTiqueteCollection().add(tiquete);
                silla = em.merge(silla);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiquete(tiquete.getTiquetePK()) != null) {
                throw new PreexistingEntityException("Tiquete " + tiquete + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiquete tiquete) throws NonexistentEntityException, Exception {
        tiquete.getTiquetePK().setHorarioPeliculaidPelicula(tiquete.getHorario().getHorarioPK().getPeliculaidPelicula());
        tiquete.getTiquetePK().setSillaidSilla(tiquete.getSilla().getIdSilla());
        tiquete.getTiquetePK().setHorarioSalaidSala(tiquete.getHorario().getHorarioPK().getSalaidSala());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiquete persistentTiquete = em.find(Tiquete.class, tiquete.getTiquetePK());
            Horario horarioOld = persistentTiquete.getHorario();
            Horario horarioNew = tiquete.getHorario();
            Silla sillaOld = persistentTiquete.getSilla();
            Silla sillaNew = tiquete.getSilla();
            if (horarioNew != null) {
                horarioNew = em.getReference(horarioNew.getClass(), horarioNew.getHorarioPK());
                tiquete.setHorario(horarioNew);
            }
            if (sillaNew != null) {
                sillaNew = em.getReference(sillaNew.getClass(), sillaNew.getIdSilla());
                tiquete.setSilla(sillaNew);
            }
            tiquete = em.merge(tiquete);
            if (horarioOld != null && !horarioOld.equals(horarioNew)) {
                horarioOld.getTiqueteCollection().remove(tiquete);
                horarioOld = em.merge(horarioOld);
            }
            if (horarioNew != null && !horarioNew.equals(horarioOld)) {
                horarioNew.getTiqueteCollection().add(tiquete);
                horarioNew = em.merge(horarioNew);
            }
            if (sillaOld != null && !sillaOld.equals(sillaNew)) {
                sillaOld.getTiqueteCollection().remove(tiquete);
                sillaOld = em.merge(sillaOld);
            }
            if (sillaNew != null && !sillaNew.equals(sillaOld)) {
                sillaNew.getTiqueteCollection().add(tiquete);
                sillaNew = em.merge(sillaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TiquetePK id = tiquete.getTiquetePK();
                if (findTiquete(id) == null) {
                    throw new NonexistentEntityException("The tiquete with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TiquetePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiquete tiquete;
            try {
                tiquete = em.getReference(Tiquete.class, id);
                tiquete.getTiquetePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiquete with id " + id + " no longer exists.", enfe);
            }
            Horario horario = tiquete.getHorario();
            if (horario != null) {
                horario.getTiqueteCollection().remove(tiquete);
                horario = em.merge(horario);
            }
            Silla silla = tiquete.getSilla();
            if (silla != null) {
                silla.getTiqueteCollection().remove(tiquete);
                silla = em.merge(silla);
            }
            em.remove(tiquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiquete> findTiqueteEntities() {
        return findTiqueteEntities(true, -1, -1);
    }

    public List<Tiquete> findTiqueteEntities(int maxResults, int firstResult) {
        return findTiqueteEntities(false, maxResults, firstResult);
    }

    private List<Tiquete> findTiqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiquete.class));
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

    public Tiquete findTiquete(TiquetePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiquete> rt = cq.from(Tiquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
