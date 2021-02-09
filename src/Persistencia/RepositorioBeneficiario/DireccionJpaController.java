/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.RepositorioBeneficiario;

import Adapatadores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.RepositorioBeneficiario.BeneficiarioRepo;
import Persistencia.RepositorioBeneficiario.DireccionRepo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Isra
 */
public class DireccionJpaController implements Serializable {

    static void InsertDireccion(DireccionRepo objDirRep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DireccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DireccionRepo direccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BeneficiarioRepo idBeneficiario = direccion.getIdBeneficiario();
            if (idBeneficiario != null) {
                idBeneficiario = em.getReference(idBeneficiario.getClass(), idBeneficiario.getIdBeneficiario());
                direccion.setIdBeneficiario(idBeneficiario);
            }
            em.persist(direccion);
            if (idBeneficiario != null) {
                idBeneficiario.getDireccionCollection().add(direccion);
                idBeneficiario = em.merge(idBeneficiario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DireccionRepo direccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DireccionRepo persistentDireccion = em.find(DireccionRepo.class, direccion.getIdDireccion());
            BeneficiarioRepo idBeneficiarioOld = persistentDireccion.getIdBeneficiario();
            BeneficiarioRepo idBeneficiarioNew = direccion.getIdBeneficiario();
            if (idBeneficiarioNew != null) {
                idBeneficiarioNew = em.getReference(idBeneficiarioNew.getClass(), idBeneficiarioNew.getIdBeneficiario());
                direccion.setIdBeneficiario(idBeneficiarioNew);
            }
            direccion = em.merge(direccion);
            if (idBeneficiarioOld != null && !idBeneficiarioOld.equals(idBeneficiarioNew)) {
                idBeneficiarioOld.getDireccionCollection().remove(direccion);
                idBeneficiarioOld = em.merge(idBeneficiarioOld);
            }
            if (idBeneficiarioNew != null && !idBeneficiarioNew.equals(idBeneficiarioOld)) {
                idBeneficiarioNew.getDireccionCollection().add(direccion);
                idBeneficiarioNew = em.merge(idBeneficiarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = direccion.getIdDireccion();
                if (findDireccion(id) == null) {
                    throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DireccionRepo direccion;
            try {
                direccion = em.getReference(DireccionRepo.class, id);
                direccion.getIdDireccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.", enfe);
            }
            BeneficiarioRepo idBeneficiario = direccion.getIdBeneficiario();
            if (idBeneficiario != null) {
                idBeneficiario.getDireccionCollection().remove(direccion);
                idBeneficiario = em.merge(idBeneficiario);
            }
            em.remove(direccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DireccionRepo> findDireccionEntities() {
        return findDireccionEntities(true, -1, -1);
    }

    public List<DireccionRepo> findDireccionEntities(int maxResults, int firstResult) {
        return findDireccionEntities(false, maxResults, firstResult);
    }

    private List<DireccionRepo> findDireccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DireccionRepo.class));
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

    public DireccionRepo findDireccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DireccionRepo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDireccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DireccionRepo> rt = cq.from(DireccionRepo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
