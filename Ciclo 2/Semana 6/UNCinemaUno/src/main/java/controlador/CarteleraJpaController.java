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
import modelo.Cartelera;
import modelo.CarteleraPK;
import modelo.Cinema;
import modelo.Pelicula;

/**
 *
 * @author user
 */
public class CarteleraJpaController implements Serializable {

    public CarteleraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cartelera cartelera) throws PreexistingEntityException, Exception {
        if (cartelera.getCarteleraPK() == null) {
            cartelera.setCarteleraPK(new CarteleraPK());
        }
        cartelera.getCarteleraPK().setPeliculaidPelicula(cartelera.getPelicula().getIdPelicula());
        cartelera.getCarteleraPK().setCinemaidCinema(cartelera.getCinema().getIdCinema());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cinema cinema = cartelera.getCinema();
            if (cinema != null) {
                cinema = em.getReference(cinema.getClass(), cinema.getIdCinema());
                cartelera.setCinema(cinema);
            }
            Pelicula pelicula = cartelera.getPelicula();
            if (pelicula != null) {
                pelicula = em.getReference(pelicula.getClass(), pelicula.getIdPelicula());
                cartelera.setPelicula(pelicula);
            }
            em.persist(cartelera);
            if (cinema != null) {
                cinema.getCarteleraCollection().add(cartelera);
                cinema = em.merge(cinema);
            }
            if (pelicula != null) {
                pelicula.getCarteleraCollection().add(cartelera);
                pelicula = em.merge(pelicula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCartelera(cartelera.getCarteleraPK()) != null) {
                throw new PreexistingEntityException("Cartelera " + cartelera + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cartelera cartelera) throws NonexistentEntityException, Exception {
        cartelera.getCarteleraPK().setPeliculaidPelicula(cartelera.getPelicula().getIdPelicula());
        cartelera.getCarteleraPK().setCinemaidCinema(cartelera.getCinema().getIdCinema());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartelera persistentCartelera = em.find(Cartelera.class, cartelera.getCarteleraPK());
            Cinema cinemaOld = persistentCartelera.getCinema();
            Cinema cinemaNew = cartelera.getCinema();
            Pelicula peliculaOld = persistentCartelera.getPelicula();
            Pelicula peliculaNew = cartelera.getPelicula();
            if (cinemaNew != null) {
                cinemaNew = em.getReference(cinemaNew.getClass(), cinemaNew.getIdCinema());
                cartelera.setCinema(cinemaNew);
            }
            if (peliculaNew != null) {
                peliculaNew = em.getReference(peliculaNew.getClass(), peliculaNew.getIdPelicula());
                cartelera.setPelicula(peliculaNew);
            }
            cartelera = em.merge(cartelera);
            if (cinemaOld != null && !cinemaOld.equals(cinemaNew)) {
                cinemaOld.getCarteleraCollection().remove(cartelera);
                cinemaOld = em.merge(cinemaOld);
            }
            if (cinemaNew != null && !cinemaNew.equals(cinemaOld)) {
                cinemaNew.getCarteleraCollection().add(cartelera);
                cinemaNew = em.merge(cinemaNew);
            }
            if (peliculaOld != null && !peliculaOld.equals(peliculaNew)) {
                peliculaOld.getCarteleraCollection().remove(cartelera);
                peliculaOld = em.merge(peliculaOld);
            }
            if (peliculaNew != null && !peliculaNew.equals(peliculaOld)) {
                peliculaNew.getCarteleraCollection().add(cartelera);
                peliculaNew = em.merge(peliculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CarteleraPK id = cartelera.getCarteleraPK();
                if (findCartelera(id) == null) {
                    throw new NonexistentEntityException("The cartelera with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CarteleraPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartelera cartelera;
            try {
                cartelera = em.getReference(Cartelera.class, id);
                cartelera.getCarteleraPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cartelera with id " + id + " no longer exists.", enfe);
            }
            Cinema cinema = cartelera.getCinema();
            if (cinema != null) {
                cinema.getCarteleraCollection().remove(cartelera);
                cinema = em.merge(cinema);
            }
            Pelicula pelicula = cartelera.getPelicula();
            if (pelicula != null) {
                pelicula.getCarteleraCollection().remove(cartelera);
                pelicula = em.merge(pelicula);
            }
            em.remove(cartelera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cartelera> findCarteleraEntities() {
        return findCarteleraEntities(true, -1, -1);
    }

    public List<Cartelera> findCarteleraEntities(int maxResults, int firstResult) {
        return findCarteleraEntities(false, maxResults, firstResult);
    }

    private List<Cartelera> findCarteleraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cartelera.class));
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

    public Cartelera findCartelera(CarteleraPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cartelera.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarteleraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cartelera> rt = cq.from(Cartelera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
