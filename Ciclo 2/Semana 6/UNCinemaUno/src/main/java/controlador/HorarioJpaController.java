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
import modelo.Pelicula;
import modelo.Sala;
import modelo.Tiquete;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Horario;
import modelo.HorarioPK;

/**
 *
 * @author user
 */
public class HorarioJpaController implements Serializable {

    public HorarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Horario horario) throws PreexistingEntityException, Exception {
        if (horario.getHorarioPK() == null) {
            horario.setHorarioPK(new HorarioPK());
        }
        if (horario.getTiqueteCollection() == null) {
            horario.setTiqueteCollection(new ArrayList<Tiquete>());
        }
        horario.getHorarioPK().setSalaidSala(horario.getSala().getIdSala());
        horario.getHorarioPK().setPeliculaidPelicula(horario.getPelicula().getIdPelicula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pelicula pelicula = horario.getPelicula();
            if (pelicula != null) {
                pelicula = em.getReference(pelicula.getClass(), pelicula.getIdPelicula());
                horario.setPelicula(pelicula);
            }
            Sala sala = horario.getSala();
            if (sala != null) {
                sala = em.getReference(sala.getClass(), sala.getIdSala());
                horario.setSala(sala);
            }
            Collection<Tiquete> attachedTiqueteCollection = new ArrayList<Tiquete>();
            for (Tiquete tiqueteCollectionTiqueteToAttach : horario.getTiqueteCollection()) {
                tiqueteCollectionTiqueteToAttach = em.getReference(tiqueteCollectionTiqueteToAttach.getClass(), tiqueteCollectionTiqueteToAttach.getTiquetePK());
                attachedTiqueteCollection.add(tiqueteCollectionTiqueteToAttach);
            }
            horario.setTiqueteCollection(attachedTiqueteCollection);
            em.persist(horario);
            if (pelicula != null) {
                pelicula.getHorarioCollection().add(horario);
                pelicula = em.merge(pelicula);
            }
            if (sala != null) {
                sala.getHorarioCollection().add(horario);
                sala = em.merge(sala);
            }
            for (Tiquete tiqueteCollectionTiquete : horario.getTiqueteCollection()) {
                Horario oldHorarioOfTiqueteCollectionTiquete = tiqueteCollectionTiquete.getHorario();
                tiqueteCollectionTiquete.setHorario(horario);
                tiqueteCollectionTiquete = em.merge(tiqueteCollectionTiquete);
                if (oldHorarioOfTiqueteCollectionTiquete != null) {
                    oldHorarioOfTiqueteCollectionTiquete.getTiqueteCollection().remove(tiqueteCollectionTiquete);
                    oldHorarioOfTiqueteCollectionTiquete = em.merge(oldHorarioOfTiqueteCollectionTiquete);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHorario(horario.getHorarioPK()) != null) {
                throw new PreexistingEntityException("Horario " + horario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Horario horario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        horario.getHorarioPK().setSalaidSala(horario.getSala().getIdSala());
        horario.getHorarioPK().setPeliculaidPelicula(horario.getPelicula().getIdPelicula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario persistentHorario = em.find(Horario.class, horario.getHorarioPK());
            Pelicula peliculaOld = persistentHorario.getPelicula();
            Pelicula peliculaNew = horario.getPelicula();
            Sala salaOld = persistentHorario.getSala();
            Sala salaNew = horario.getSala();
            Collection<Tiquete> tiqueteCollectionOld = persistentHorario.getTiqueteCollection();
            Collection<Tiquete> tiqueteCollectionNew = horario.getTiqueteCollection();
            List<String> illegalOrphanMessages = null;
            for (Tiquete tiqueteCollectionOldTiquete : tiqueteCollectionOld) {
                if (!tiqueteCollectionNew.contains(tiqueteCollectionOldTiquete)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tiquete " + tiqueteCollectionOldTiquete + " since its horario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (peliculaNew != null) {
                peliculaNew = em.getReference(peliculaNew.getClass(), peliculaNew.getIdPelicula());
                horario.setPelicula(peliculaNew);
            }
            if (salaNew != null) {
                salaNew = em.getReference(salaNew.getClass(), salaNew.getIdSala());
                horario.setSala(salaNew);
            }
            Collection<Tiquete> attachedTiqueteCollectionNew = new ArrayList<Tiquete>();
            for (Tiquete tiqueteCollectionNewTiqueteToAttach : tiqueteCollectionNew) {
                tiqueteCollectionNewTiqueteToAttach = em.getReference(tiqueteCollectionNewTiqueteToAttach.getClass(), tiqueteCollectionNewTiqueteToAttach.getTiquetePK());
                attachedTiqueteCollectionNew.add(tiqueteCollectionNewTiqueteToAttach);
            }
            tiqueteCollectionNew = attachedTiqueteCollectionNew;
            horario.setTiqueteCollection(tiqueteCollectionNew);
            horario = em.merge(horario);
            if (peliculaOld != null && !peliculaOld.equals(peliculaNew)) {
                peliculaOld.getHorarioCollection().remove(horario);
                peliculaOld = em.merge(peliculaOld);
            }
            if (peliculaNew != null && !peliculaNew.equals(peliculaOld)) {
                peliculaNew.getHorarioCollection().add(horario);
                peliculaNew = em.merge(peliculaNew);
            }
            if (salaOld != null && !salaOld.equals(salaNew)) {
                salaOld.getHorarioCollection().remove(horario);
                salaOld = em.merge(salaOld);
            }
            if (salaNew != null && !salaNew.equals(salaOld)) {
                salaNew.getHorarioCollection().add(horario);
                salaNew = em.merge(salaNew);
            }
            for (Tiquete tiqueteCollectionNewTiquete : tiqueteCollectionNew) {
                if (!tiqueteCollectionOld.contains(tiqueteCollectionNewTiquete)) {
                    Horario oldHorarioOfTiqueteCollectionNewTiquete = tiqueteCollectionNewTiquete.getHorario();
                    tiqueteCollectionNewTiquete.setHorario(horario);
                    tiqueteCollectionNewTiquete = em.merge(tiqueteCollectionNewTiquete);
                    if (oldHorarioOfTiqueteCollectionNewTiquete != null && !oldHorarioOfTiqueteCollectionNewTiquete.equals(horario)) {
                        oldHorarioOfTiqueteCollectionNewTiquete.getTiqueteCollection().remove(tiqueteCollectionNewTiquete);
                        oldHorarioOfTiqueteCollectionNewTiquete = em.merge(oldHorarioOfTiqueteCollectionNewTiquete);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HorarioPK id = horario.getHorarioPK();
                if (findHorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HorarioPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario horario;
            try {
                horario = em.getReference(Horario.class, id);
                horario.getHorarioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tiquete> tiqueteCollectionOrphanCheck = horario.getTiqueteCollection();
            for (Tiquete tiqueteCollectionOrphanCheckTiquete : tiqueteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Tiquete " + tiqueteCollectionOrphanCheckTiquete + " in its tiqueteCollection field has a non-nullable horario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pelicula pelicula = horario.getPelicula();
            if (pelicula != null) {
                pelicula.getHorarioCollection().remove(horario);
                pelicula = em.merge(pelicula);
            }
            Sala sala = horario.getSala();
            if (sala != null) {
                sala.getHorarioCollection().remove(horario);
                sala = em.merge(sala);
            }
            em.remove(horario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Horario> findHorarioEntities() {
        return findHorarioEntities(true, -1, -1);
    }

    public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
        return findHorarioEntities(false, maxResults, firstResult);
    }

    private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Horario.class));
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

    public Horario findHorario(HorarioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }

    public int getHorarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Horario> rt = cq.from(Horario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
