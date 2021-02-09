/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapatadores;

import Adapatadores.exceptions.IllegalOrphanException;
import Adapatadores.exceptions.NonexistentEntityException;
import Persistencia.RepositorioBeneficiario.Beneficiario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.RepositorioBeneficiario.Direccion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Isra
 */
public class BeneficiarioJpaController implements Serializable {

    public BeneficiarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Beneficiario beneficiario) {
        if (beneficiario.getDireccionCollection() == null) {
            beneficiario.setDireccionCollection(new ArrayList<Direccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Direccion> attachedDireccionCollection = new ArrayList<Direccion>();
            for (Direccion direccionCollectionDireccionToAttach : beneficiario.getDireccionCollection()) {
                direccionCollectionDireccionToAttach = em.getReference(direccionCollectionDireccionToAttach.getClass(), direccionCollectionDireccionToAttach.getIdDireccion());
                attachedDireccionCollection.add(direccionCollectionDireccionToAttach);
            }
            beneficiario.setDireccionCollection(attachedDireccionCollection);
            em.persist(beneficiario);
            for (Direccion direccionCollectionDireccion : beneficiario.getDireccionCollection()) {
                Beneficiario oldIdBeneficiarioOfDireccionCollectionDireccion = direccionCollectionDireccion.getIdBeneficiario();
                direccionCollectionDireccion.setIdBeneficiario(beneficiario);
                direccionCollectionDireccion = em.merge(direccionCollectionDireccion);
                if (oldIdBeneficiarioOfDireccionCollectionDireccion != null) {
                    oldIdBeneficiarioOfDireccionCollectionDireccion.getDireccionCollection().remove(direccionCollectionDireccion);
                    oldIdBeneficiarioOfDireccionCollectionDireccion = em.merge(oldIdBeneficiarioOfDireccionCollectionDireccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Beneficiario beneficiario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Beneficiario persistentBeneficiario = em.find(Beneficiario.class, beneficiario.getIdBeneficiario());
            Collection<Direccion> direccionCollectionOld = persistentBeneficiario.getDireccionCollection();
            Collection<Direccion> direccionCollectionNew = beneficiario.getDireccionCollection();
            List<String> illegalOrphanMessages = null;
            for (Direccion direccionCollectionOldDireccion : direccionCollectionOld) {
                if (!direccionCollectionNew.contains(direccionCollectionOldDireccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Direccion " + direccionCollectionOldDireccion + " since its idBeneficiario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Direccion> attachedDireccionCollectionNew = new ArrayList<Direccion>();
            for (Direccion direccionCollectionNewDireccionToAttach : direccionCollectionNew) {
                direccionCollectionNewDireccionToAttach = em.getReference(direccionCollectionNewDireccionToAttach.getClass(), direccionCollectionNewDireccionToAttach.getIdDireccion());
                attachedDireccionCollectionNew.add(direccionCollectionNewDireccionToAttach);
            }
            direccionCollectionNew = attachedDireccionCollectionNew;
            beneficiario.setDireccionCollection(direccionCollectionNew);
            beneficiario = em.merge(beneficiario);
            for (Direccion direccionCollectionNewDireccion : direccionCollectionNew) {
                if (!direccionCollectionOld.contains(direccionCollectionNewDireccion)) {
                    Beneficiario oldIdBeneficiarioOfDireccionCollectionNewDireccion = direccionCollectionNewDireccion.getIdBeneficiario();
                    direccionCollectionNewDireccion.setIdBeneficiario(beneficiario);
                    direccionCollectionNewDireccion = em.merge(direccionCollectionNewDireccion);
                    if (oldIdBeneficiarioOfDireccionCollectionNewDireccion != null && !oldIdBeneficiarioOfDireccionCollectionNewDireccion.equals(beneficiario)) {
                        oldIdBeneficiarioOfDireccionCollectionNewDireccion.getDireccionCollection().remove(direccionCollectionNewDireccion);
                        oldIdBeneficiarioOfDireccionCollectionNewDireccion = em.merge(oldIdBeneficiarioOfDireccionCollectionNewDireccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = beneficiario.getIdBeneficiario();
                if (findBeneficiario(id) == null) {
                    throw new NonexistentEntityException("The beneficiario with id " + id + " no longer exists.");
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
            Beneficiario beneficiario;
            try {
                beneficiario = em.getReference(Beneficiario.class, id);
                beneficiario.getIdBeneficiario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The beneficiario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Direccion> direccionCollectionOrphanCheck = beneficiario.getDireccionCollection();
            for (Direccion direccionCollectionOrphanCheckDireccion : direccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Beneficiario (" + beneficiario + ") cannot be destroyed since the Direccion " + direccionCollectionOrphanCheckDireccion + " in its direccionCollection field has a non-nullable idBeneficiario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(beneficiario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Beneficiario> findBeneficiarioEntities() {
        return findBeneficiarioEntities(true, -1, -1);
    }

    public List<Beneficiario> findBeneficiarioEntities(int maxResults, int firstResult) {
        return findBeneficiarioEntities(false, maxResults, firstResult);
    }

    private List<Beneficiario> findBeneficiarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Beneficiario.class));
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

    public Beneficiario findBeneficiario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Beneficiario.class, id);
        } finally {
            em.close();
        }
    }

    public int getBeneficiarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Beneficiario> rt = cq.from(Beneficiario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
