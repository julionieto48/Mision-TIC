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
import modelo.Empleado;
import modelo.EmpleadoPK;
import modelo.Terminal;

/**
 *
 * @author user
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) throws PreexistingEntityException, Exception {
        if (empleado.getEmpleadoPK() == null) {
            empleado.setEmpleadoPK(new EmpleadoPK());
        }
        empleado.getEmpleadoPK().setTerminalidTerminal(empleado.getTerminal().getIdTerminalterm());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terminal terminal = empleado.getTerminal();
            if (terminal != null) {
                terminal = em.getReference(terminal.getClass(), terminal.getIdTerminalterm());
                empleado.setTerminal(terminal);
            }
            em.persist(empleado);
            if (terminal != null) {
                terminal.getEmpleadoCollection().add(empleado);
                terminal = em.merge(terminal);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleado(empleado.getEmpleadoPK()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        empleado.getEmpleadoPK().setTerminalidTerminal(empleado.getTerminal().getIdTerminalterm());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getEmpleadoPK());
            Terminal terminalOld = persistentEmpleado.getTerminal();
            Terminal terminalNew = empleado.getTerminal();
            if (terminalNew != null) {
                terminalNew = em.getReference(terminalNew.getClass(), terminalNew.getIdTerminalterm());
                empleado.setTerminal(terminalNew);
            }
            empleado = em.merge(empleado);
            if (terminalOld != null && !terminalOld.equals(terminalNew)) {
                terminalOld.getEmpleadoCollection().remove(empleado);
                terminalOld = em.merge(terminalOld);
            }
            if (terminalNew != null && !terminalNew.equals(terminalOld)) {
                terminalNew.getEmpleadoCollection().add(empleado);
                terminalNew = em.merge(terminalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EmpleadoPK id = empleado.getEmpleadoPK();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EmpleadoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getEmpleadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            Terminal terminal = empleado.getTerminal();
            if (terminal != null) {
                terminal.getEmpleadoCollection().remove(empleado);
                terminal = em.merge(terminal);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(EmpleadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
