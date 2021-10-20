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
import modelo.Empleado;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Ruta;
import modelo.Terminal;

/**
 *
 * @author user
 */
public class TerminalJpaController implements Serializable {

    public TerminalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Terminal terminal) throws PreexistingEntityException, Exception {
        if (terminal.getEmpleadoCollection() == null) {
            terminal.setEmpleadoCollection(new ArrayList<Empleado>());
        }
        if (terminal.getRutaCollection() == null) {
            terminal.setRutaCollection(new ArrayList<Ruta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Empleado> attachedEmpleadoCollection = new ArrayList<Empleado>();
            for (Empleado empleadoCollectionEmpleadoToAttach : terminal.getEmpleadoCollection()) {
                empleadoCollectionEmpleadoToAttach = em.getReference(empleadoCollectionEmpleadoToAttach.getClass(), empleadoCollectionEmpleadoToAttach.getEmpleadoPK());
                attachedEmpleadoCollection.add(empleadoCollectionEmpleadoToAttach);
            }
            terminal.setEmpleadoCollection(attachedEmpleadoCollection);
            Collection<Ruta> attachedRutaCollection = new ArrayList<Ruta>();
            for (Ruta rutaCollectionRutaToAttach : terminal.getRutaCollection()) {
                rutaCollectionRutaToAttach = em.getReference(rutaCollectionRutaToAttach.getClass(), rutaCollectionRutaToAttach.getRutaPK());
                attachedRutaCollection.add(rutaCollectionRutaToAttach);
            }
            terminal.setRutaCollection(attachedRutaCollection);
            em.persist(terminal);
            for (Empleado empleadoCollectionEmpleado : terminal.getEmpleadoCollection()) {
                Terminal oldTerminalOfEmpleadoCollectionEmpleado = empleadoCollectionEmpleado.getTerminal();
                empleadoCollectionEmpleado.setTerminal(terminal);
                empleadoCollectionEmpleado = em.merge(empleadoCollectionEmpleado);
                if (oldTerminalOfEmpleadoCollectionEmpleado != null) {
                    oldTerminalOfEmpleadoCollectionEmpleado.getEmpleadoCollection().remove(empleadoCollectionEmpleado);
                    oldTerminalOfEmpleadoCollectionEmpleado = em.merge(oldTerminalOfEmpleadoCollectionEmpleado);
                }
            }
            for (Ruta rutaCollectionRuta : terminal.getRutaCollection()) {
                Terminal oldTerminalOfRutaCollectionRuta = rutaCollectionRuta.getTerminal();
                rutaCollectionRuta.setTerminal(terminal);
                rutaCollectionRuta = em.merge(rutaCollectionRuta);
                if (oldTerminalOfRutaCollectionRuta != null) {
                    oldTerminalOfRutaCollectionRuta.getRutaCollection().remove(rutaCollectionRuta);
                    oldTerminalOfRutaCollectionRuta = em.merge(oldTerminalOfRutaCollectionRuta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTerminal(terminal.getIdTerminalterm()) != null) {
                throw new PreexistingEntityException("Terminal " + terminal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Terminal terminal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terminal persistentTerminal = em.find(Terminal.class, terminal.getIdTerminalterm());
            Collection<Empleado> empleadoCollectionOld = persistentTerminal.getEmpleadoCollection();
            Collection<Empleado> empleadoCollectionNew = terminal.getEmpleadoCollection();
            Collection<Ruta> rutaCollectionOld = persistentTerminal.getRutaCollection();
            Collection<Ruta> rutaCollectionNew = terminal.getRutaCollection();
            List<String> illegalOrphanMessages = null;
            for (Empleado empleadoCollectionOldEmpleado : empleadoCollectionOld) {
                if (!empleadoCollectionNew.contains(empleadoCollectionOldEmpleado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleado " + empleadoCollectionOldEmpleado + " since its terminal field is not nullable.");
                }
            }
            for (Ruta rutaCollectionOldRuta : rutaCollectionOld) {
                if (!rutaCollectionNew.contains(rutaCollectionOldRuta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruta " + rutaCollectionOldRuta + " since its terminal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Empleado> attachedEmpleadoCollectionNew = new ArrayList<Empleado>();
            for (Empleado empleadoCollectionNewEmpleadoToAttach : empleadoCollectionNew) {
                empleadoCollectionNewEmpleadoToAttach = em.getReference(empleadoCollectionNewEmpleadoToAttach.getClass(), empleadoCollectionNewEmpleadoToAttach.getEmpleadoPK());
                attachedEmpleadoCollectionNew.add(empleadoCollectionNewEmpleadoToAttach);
            }
            empleadoCollectionNew = attachedEmpleadoCollectionNew;
            terminal.setEmpleadoCollection(empleadoCollectionNew);
            Collection<Ruta> attachedRutaCollectionNew = new ArrayList<Ruta>();
            for (Ruta rutaCollectionNewRutaToAttach : rutaCollectionNew) {
                rutaCollectionNewRutaToAttach = em.getReference(rutaCollectionNewRutaToAttach.getClass(), rutaCollectionNewRutaToAttach.getRutaPK());
                attachedRutaCollectionNew.add(rutaCollectionNewRutaToAttach);
            }
            rutaCollectionNew = attachedRutaCollectionNew;
            terminal.setRutaCollection(rutaCollectionNew);
            terminal = em.merge(terminal);
            for (Empleado empleadoCollectionNewEmpleado : empleadoCollectionNew) {
                if (!empleadoCollectionOld.contains(empleadoCollectionNewEmpleado)) {
                    Terminal oldTerminalOfEmpleadoCollectionNewEmpleado = empleadoCollectionNewEmpleado.getTerminal();
                    empleadoCollectionNewEmpleado.setTerminal(terminal);
                    empleadoCollectionNewEmpleado = em.merge(empleadoCollectionNewEmpleado);
                    if (oldTerminalOfEmpleadoCollectionNewEmpleado != null && !oldTerminalOfEmpleadoCollectionNewEmpleado.equals(terminal)) {
                        oldTerminalOfEmpleadoCollectionNewEmpleado.getEmpleadoCollection().remove(empleadoCollectionNewEmpleado);
                        oldTerminalOfEmpleadoCollectionNewEmpleado = em.merge(oldTerminalOfEmpleadoCollectionNewEmpleado);
                    }
                }
            }
            for (Ruta rutaCollectionNewRuta : rutaCollectionNew) {
                if (!rutaCollectionOld.contains(rutaCollectionNewRuta)) {
                    Terminal oldTerminalOfRutaCollectionNewRuta = rutaCollectionNewRuta.getTerminal();
                    rutaCollectionNewRuta.setTerminal(terminal);
                    rutaCollectionNewRuta = em.merge(rutaCollectionNewRuta);
                    if (oldTerminalOfRutaCollectionNewRuta != null && !oldTerminalOfRutaCollectionNewRuta.equals(terminal)) {
                        oldTerminalOfRutaCollectionNewRuta.getRutaCollection().remove(rutaCollectionNewRuta);
                        oldTerminalOfRutaCollectionNewRuta = em.merge(oldTerminalOfRutaCollectionNewRuta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = terminal.getIdTerminalterm();
                if (findTerminal(id) == null) {
                    throw new NonexistentEntityException("The terminal with id " + id + " no longer exists.");
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
            Terminal terminal;
            try {
                terminal = em.getReference(Terminal.class, id);
                terminal.getIdTerminalterm();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The terminal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Empleado> empleadoCollectionOrphanCheck = terminal.getEmpleadoCollection();
            for (Empleado empleadoCollectionOrphanCheckEmpleado : empleadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Terminal (" + terminal + ") cannot be destroyed since the Empleado " + empleadoCollectionOrphanCheckEmpleado + " in its empleadoCollection field has a non-nullable terminal field.");
            }
            Collection<Ruta> rutaCollectionOrphanCheck = terminal.getRutaCollection();
            for (Ruta rutaCollectionOrphanCheckRuta : rutaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Terminal (" + terminal + ") cannot be destroyed since the Ruta " + rutaCollectionOrphanCheckRuta + " in its rutaCollection field has a non-nullable terminal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(terminal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Terminal> findTerminalEntities() {
        return findTerminalEntities(true, -1, -1);
    }

    public List<Terminal> findTerminalEntities(int maxResults, int firstResult) {
        return findTerminalEntities(false, maxResults, firstResult);
    }

    private List<Terminal> findTerminalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Terminal.class));
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

    public Terminal findTerminal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Terminal.class, id);
        } finally {
            em.close();
        }
    }

    public int getTerminalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Terminal> rt = cq.from(Terminal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
