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
import modelo.Horario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Cartelera;
import modelo.Pelicula;

/**
 *
 * @author user
 */
public class PeliculaJpaController implements Serializable {

    public PeliculaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pelicula pelicula) throws PreexistingEntityException, Exception {
        if (pelicula.getHorarioCollection() == null) {
            pelicula.setHorarioCollection(new ArrayList<Horario>());
        }
        if (pelicula.getCarteleraCollection() == null) {
            pelicula.setCarteleraCollection(new ArrayList<Cartelera>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Horario> attachedHorarioCollection = new ArrayList<Horario>();
            for (Horario horarioCollectionHorarioToAttach : pelicula.getHorarioCollection()) {
                horarioCollectionHorarioToAttach = em.getReference(horarioCollectionHorarioToAttach.getClass(), horarioCollectionHorarioToAttach.getHorarioPK());
                attachedHorarioCollection.add(horarioCollectionHorarioToAttach);
            }
            pelicula.setHorarioCollection(attachedHorarioCollection);
            Collection<Cartelera> attachedCarteleraCollection = new ArrayList<Cartelera>();
            for (Cartelera carteleraCollectionCarteleraToAttach : pelicula.getCarteleraCollection()) {
                carteleraCollectionCarteleraToAttach = em.getReference(carteleraCollectionCarteleraToAttach.getClass(), carteleraCollectionCarteleraToAttach.getCarteleraPK());
                attachedCarteleraCollection.add(carteleraCollectionCarteleraToAttach);
            }
            pelicula.setCarteleraCollection(attachedCarteleraCollection);
            em.persist(pelicula);
            for (Horario horarioCollectionHorario : pelicula.getHorarioCollection()) {
                Pelicula oldPeliculaOfHorarioCollectionHorario = horarioCollectionHorario.getPelicula();
                horarioCollectionHorario.setPelicula(pelicula);
                horarioCollectionHorario = em.merge(horarioCollectionHorario);
                if (oldPeliculaOfHorarioCollectionHorario != null) {
                    oldPeliculaOfHorarioCollectionHorario.getHorarioCollection().remove(horarioCollectionHorario);
                    oldPeliculaOfHorarioCollectionHorario = em.merge(oldPeliculaOfHorarioCollectionHorario);
                }
            }
            for (Cartelera carteleraCollectionCartelera : pelicula.getCarteleraCollection()) {
                Pelicula oldPeliculaOfCarteleraCollectionCartelera = carteleraCollectionCartelera.getPelicula();
                carteleraCollectionCartelera.setPelicula(pelicula);
                carteleraCollectionCartelera = em.merge(carteleraCollectionCartelera);
                if (oldPeliculaOfCarteleraCollectionCartelera != null) {
                    oldPeliculaOfCarteleraCollectionCartelera.getCarteleraCollection().remove(carteleraCollectionCartelera);
                    oldPeliculaOfCarteleraCollectionCartelera = em.merge(oldPeliculaOfCarteleraCollectionCartelera);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPelicula(pelicula.getIdPelicula()) != null) {
                throw new PreexistingEntityException("Pelicula " + pelicula + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pelicula pelicula) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pelicula persistentPelicula = em.find(Pelicula.class, pelicula.getIdPelicula());
            Collection<Horario> horarioCollectionOld = persistentPelicula.getHorarioCollection();
            Collection<Horario> horarioCollectionNew = pelicula.getHorarioCollection();
            Collection<Cartelera> carteleraCollectionOld = persistentPelicula.getCarteleraCollection();
            Collection<Cartelera> carteleraCollectionNew = pelicula.getCarteleraCollection();
            List<String> illegalOrphanMessages = null;
            for (Horario horarioCollectionOldHorario : horarioCollectionOld) {
                if (!horarioCollectionNew.contains(horarioCollectionOldHorario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Horario " + horarioCollectionOldHorario + " since its pelicula field is not nullable.");
                }
            }
            for (Cartelera carteleraCollectionOldCartelera : carteleraCollectionOld) {
                if (!carteleraCollectionNew.contains(carteleraCollectionOldCartelera)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cartelera " + carteleraCollectionOldCartelera + " since its pelicula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Horario> attachedHorarioCollectionNew = new ArrayList<Horario>();
            for (Horario horarioCollectionNewHorarioToAttach : horarioCollectionNew) {
                horarioCollectionNewHorarioToAttach = em.getReference(horarioCollectionNewHorarioToAttach.getClass(), horarioCollectionNewHorarioToAttach.getHorarioPK());
                attachedHorarioCollectionNew.add(horarioCollectionNewHorarioToAttach);
            }
            horarioCollectionNew = attachedHorarioCollectionNew;
            pelicula.setHorarioCollection(horarioCollectionNew);
            Collection<Cartelera> attachedCarteleraCollectionNew = new ArrayList<Cartelera>();
            for (Cartelera carteleraCollectionNewCarteleraToAttach : carteleraCollectionNew) {
                carteleraCollectionNewCarteleraToAttach = em.getReference(carteleraCollectionNewCarteleraToAttach.getClass(), carteleraCollectionNewCarteleraToAttach.getCarteleraPK());
                attachedCarteleraCollectionNew.add(carteleraCollectionNewCarteleraToAttach);
            }
            carteleraCollectionNew = attachedCarteleraCollectionNew;
            pelicula.setCarteleraCollection(carteleraCollectionNew);
            pelicula = em.merge(pelicula);
            for (Horario horarioCollectionNewHorario : horarioCollectionNew) {
                if (!horarioCollectionOld.contains(horarioCollectionNewHorario)) {
                    Pelicula oldPeliculaOfHorarioCollectionNewHorario = horarioCollectionNewHorario.getPelicula();
                    horarioCollectionNewHorario.setPelicula(pelicula);
                    horarioCollectionNewHorario = em.merge(horarioCollectionNewHorario);
                    if (oldPeliculaOfHorarioCollectionNewHorario != null && !oldPeliculaOfHorarioCollectionNewHorario.equals(pelicula)) {
                        oldPeliculaOfHorarioCollectionNewHorario.getHorarioCollection().remove(horarioCollectionNewHorario);
                        oldPeliculaOfHorarioCollectionNewHorario = em.merge(oldPeliculaOfHorarioCollectionNewHorario);
                    }
                }
            }
            for (Cartelera carteleraCollectionNewCartelera : carteleraCollectionNew) {
                if (!carteleraCollectionOld.contains(carteleraCollectionNewCartelera)) {
                    Pelicula oldPeliculaOfCarteleraCollectionNewCartelera = carteleraCollectionNewCartelera.getPelicula();
                    carteleraCollectionNewCartelera.setPelicula(pelicula);
                    carteleraCollectionNewCartelera = em.merge(carteleraCollectionNewCartelera);
                    if (oldPeliculaOfCarteleraCollectionNewCartelera != null && !oldPeliculaOfCarteleraCollectionNewCartelera.equals(pelicula)) {
                        oldPeliculaOfCarteleraCollectionNewCartelera.getCarteleraCollection().remove(carteleraCollectionNewCartelera);
                        oldPeliculaOfCarteleraCollectionNewCartelera = em.merge(oldPeliculaOfCarteleraCollectionNewCartelera);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pelicula.getIdPelicula();
                if (findPelicula(id) == null) {
                    throw new NonexistentEntityException("The pelicula with id " + id + " no longer exists.");
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
            Pelicula pelicula;
            try {
                pelicula = em.getReference(Pelicula.class, id);
                pelicula.getIdPelicula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pelicula with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Horario> horarioCollectionOrphanCheck = pelicula.getHorarioCollection();
            for (Horario horarioCollectionOrphanCheckHorario : horarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pelicula (" + pelicula + ") cannot be destroyed since the Horario " + horarioCollectionOrphanCheckHorario + " in its horarioCollection field has a non-nullable pelicula field.");
            }
            Collection<Cartelera> carteleraCollectionOrphanCheck = pelicula.getCarteleraCollection();
            for (Cartelera carteleraCollectionOrphanCheckCartelera : carteleraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pelicula (" + pelicula + ") cannot be destroyed since the Cartelera " + carteleraCollectionOrphanCheckCartelera + " in its carteleraCollection field has a non-nullable pelicula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pelicula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pelicula> findPeliculaEntities() {
        return findPeliculaEntities(true, -1, -1);
    }

    public List<Pelicula> findPeliculaEntities(int maxResults, int firstResult) {
        return findPeliculaEntities(false, maxResults, firstResult);
    }

    private List<Pelicula> findPeliculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pelicula.class));
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

    public Pelicula findPelicula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pelicula.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pelicula> rt = cq.from(Pelicula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
