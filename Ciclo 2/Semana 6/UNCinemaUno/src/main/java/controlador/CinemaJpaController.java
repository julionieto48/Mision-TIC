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
import modelo.Cartelera;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Cinema;

/**
 *
 * @author user
 */
public class CinemaJpaController implements Serializable {

    public CinemaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cinema cinema) throws PreexistingEntityException, Exception {
        if (cinema.getCarteleraCollection() == null) {
            cinema.setCarteleraCollection(new ArrayList<Cartelera>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Cartelera> attachedCarteleraCollection = new ArrayList<Cartelera>();
            for (Cartelera carteleraCollectionCarteleraToAttach : cinema.getCarteleraCollection()) {
                carteleraCollectionCarteleraToAttach = em.getReference(carteleraCollectionCarteleraToAttach.getClass(), carteleraCollectionCarteleraToAttach.getCarteleraPK());
                attachedCarteleraCollection.add(carteleraCollectionCarteleraToAttach);
            }
            cinema.setCarteleraCollection(attachedCarteleraCollection);
            em.persist(cinema);
            for (Cartelera carteleraCollectionCartelera : cinema.getCarteleraCollection()) {
                Cinema oldCinemaOfCarteleraCollectionCartelera = carteleraCollectionCartelera.getCinema();
                carteleraCollectionCartelera.setCinema(cinema);
                carteleraCollectionCartelera = em.merge(carteleraCollectionCartelera);
                if (oldCinemaOfCarteleraCollectionCartelera != null) {
                    oldCinemaOfCarteleraCollectionCartelera.getCarteleraCollection().remove(carteleraCollectionCartelera);
                    oldCinemaOfCarteleraCollectionCartelera = em.merge(oldCinemaOfCarteleraCollectionCartelera);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCinema(cinema.getIdCinema()) != null) {
                throw new PreexistingEntityException("Cinema " + cinema + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cinema cinema) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cinema persistentCinema = em.find(Cinema.class, cinema.getIdCinema());
            Collection<Cartelera> carteleraCollectionOld = persistentCinema.getCarteleraCollection();
            Collection<Cartelera> carteleraCollectionNew = cinema.getCarteleraCollection();
            List<String> illegalOrphanMessages = null;
            for (Cartelera carteleraCollectionOldCartelera : carteleraCollectionOld) {
                if (!carteleraCollectionNew.contains(carteleraCollectionOldCartelera)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cartelera " + carteleraCollectionOldCartelera + " since its cinema field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Cartelera> attachedCarteleraCollectionNew = new ArrayList<Cartelera>();
            for (Cartelera carteleraCollectionNewCarteleraToAttach : carteleraCollectionNew) {
                carteleraCollectionNewCarteleraToAttach = em.getReference(carteleraCollectionNewCarteleraToAttach.getClass(), carteleraCollectionNewCarteleraToAttach.getCarteleraPK());
                attachedCarteleraCollectionNew.add(carteleraCollectionNewCarteleraToAttach);
            }
            carteleraCollectionNew = attachedCarteleraCollectionNew;
            cinema.setCarteleraCollection(carteleraCollectionNew);
            cinema = em.merge(cinema);
            for (Cartelera carteleraCollectionNewCartelera : carteleraCollectionNew) {
                if (!carteleraCollectionOld.contains(carteleraCollectionNewCartelera)) {
                    Cinema oldCinemaOfCarteleraCollectionNewCartelera = carteleraCollectionNewCartelera.getCinema();
                    carteleraCollectionNewCartelera.setCinema(cinema);
                    carteleraCollectionNewCartelera = em.merge(carteleraCollectionNewCartelera);
                    if (oldCinemaOfCarteleraCollectionNewCartelera != null && !oldCinemaOfCarteleraCollectionNewCartelera.equals(cinema)) {
                        oldCinemaOfCarteleraCollectionNewCartelera.getCarteleraCollection().remove(carteleraCollectionNewCartelera);
                        oldCinemaOfCarteleraCollectionNewCartelera = em.merge(oldCinemaOfCarteleraCollectionNewCartelera);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cinema.getIdCinema();
                if (findCinema(id) == null) {
                    throw new NonexistentEntityException("The cinema with id " + id + " no longer exists.");
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
            Cinema cinema;
            try {
                cinema = em.getReference(Cinema.class, id);
                cinema.getIdCinema();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cinema with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Cartelera> carteleraCollectionOrphanCheck = cinema.getCarteleraCollection();
            for (Cartelera carteleraCollectionOrphanCheckCartelera : carteleraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cinema (" + cinema + ") cannot be destroyed since the Cartelera " + carteleraCollectionOrphanCheckCartelera + " in its carteleraCollection field has a non-nullable cinema field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cinema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cinema> findCinemaEntities() {
        return findCinemaEntities(true, -1, -1);
    }

    public List<Cinema> findCinemaEntities(int maxResults, int firstResult) {
        return findCinemaEntities(false, maxResults, firstResult);
    }

    private List<Cinema> findCinemaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cinema.class));
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

    public Cinema findCinema(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cinema.class, id);
        } finally {
            em.close();
        }
    }

    public int getCinemaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cinema> rt = cq.from(Cinema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
