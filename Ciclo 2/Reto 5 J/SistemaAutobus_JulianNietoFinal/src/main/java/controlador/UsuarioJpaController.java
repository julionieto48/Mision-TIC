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
import modelo.Pasajero;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Usuario;

/**
 *
 * @author user
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getPasajeroCollection() == null) {
            usuario.setPasajeroCollection(new ArrayList<Pasajero>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pasajero> attachedPasajeroCollection = new ArrayList<Pasajero>();
            for (Pasajero pasajeroCollectionPasajeroToAttach : usuario.getPasajeroCollection()) {
                pasajeroCollectionPasajeroToAttach = em.getReference(pasajeroCollectionPasajeroToAttach.getClass(), pasajeroCollectionPasajeroToAttach.getPasajeroPK());
                attachedPasajeroCollection.add(pasajeroCollectionPasajeroToAttach);
            }
            usuario.setPasajeroCollection(attachedPasajeroCollection);
            em.persist(usuario);
            for (Pasajero pasajeroCollectionPasajero : usuario.getPasajeroCollection()) {
                Usuario oldUsuarioOfPasajeroCollectionPasajero = pasajeroCollectionPasajero.getUsuario();
                pasajeroCollectionPasajero.setUsuario(usuario);
                pasajeroCollectionPasajero = em.merge(pasajeroCollectionPasajero);
                if (oldUsuarioOfPasajeroCollectionPasajero != null) {
                    oldUsuarioOfPasajeroCollectionPasajero.getPasajeroCollection().remove(pasajeroCollectionPasajero);
                    oldUsuarioOfPasajeroCollectionPasajero = em.merge(oldUsuarioOfPasajeroCollectionPasajero);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getNombreuser()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getNombreuser());
            Collection<Pasajero> pasajeroCollectionOld = persistentUsuario.getPasajeroCollection();
            Collection<Pasajero> pasajeroCollectionNew = usuario.getPasajeroCollection();
            List<String> illegalOrphanMessages = null;
            for (Pasajero pasajeroCollectionOldPasajero : pasajeroCollectionOld) {
                if (!pasajeroCollectionNew.contains(pasajeroCollectionOldPasajero)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pasajero " + pasajeroCollectionOldPasajero + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Pasajero> attachedPasajeroCollectionNew = new ArrayList<Pasajero>();
            for (Pasajero pasajeroCollectionNewPasajeroToAttach : pasajeroCollectionNew) {
                pasajeroCollectionNewPasajeroToAttach = em.getReference(pasajeroCollectionNewPasajeroToAttach.getClass(), pasajeroCollectionNewPasajeroToAttach.getPasajeroPK());
                attachedPasajeroCollectionNew.add(pasajeroCollectionNewPasajeroToAttach);
            }
            pasajeroCollectionNew = attachedPasajeroCollectionNew;
            usuario.setPasajeroCollection(pasajeroCollectionNew);
            usuario = em.merge(usuario);
            for (Pasajero pasajeroCollectionNewPasajero : pasajeroCollectionNew) {
                if (!pasajeroCollectionOld.contains(pasajeroCollectionNewPasajero)) {
                    Usuario oldUsuarioOfPasajeroCollectionNewPasajero = pasajeroCollectionNewPasajero.getUsuario();
                    pasajeroCollectionNewPasajero.setUsuario(usuario);
                    pasajeroCollectionNewPasajero = em.merge(pasajeroCollectionNewPasajero);
                    if (oldUsuarioOfPasajeroCollectionNewPasajero != null && !oldUsuarioOfPasajeroCollectionNewPasajero.equals(usuario)) {
                        oldUsuarioOfPasajeroCollectionNewPasajero.getPasajeroCollection().remove(pasajeroCollectionNewPasajero);
                        oldUsuarioOfPasajeroCollectionNewPasajero = em.merge(oldUsuarioOfPasajeroCollectionNewPasajero);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getNombreuser();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getNombreuser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pasajero> pasajeroCollectionOrphanCheck = usuario.getPasajeroCollection();
            for (Pasajero pasajeroCollectionOrphanCheckPasajero : pasajeroCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Pasajero " + pasajeroCollectionOrphanCheckPasajero + " in its pasajeroCollection field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
