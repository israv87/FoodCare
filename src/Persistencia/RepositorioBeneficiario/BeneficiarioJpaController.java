/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.RepositorioBeneficiario;

import Adapatadores.exceptions.IllegalOrphanException;
import Adapatadores.exceptions.NonexistentEntityException;
import Persistencia.RepositorioBeneficiario.BeneficiarioRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.RepositorioBeneficiario.DireccionRepo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public Connection con;
ResultSet rs;
PreparedStatement ps = null;
public Connection getConnection () throws ClassNotFoundException, SQLException { 
          String driver = "com.mysql.jdbc.Driver";
          String url = "jdbc:mysql://localhost:3306/FoodCare";
          //System.out.println("llego");
          Class.forName(driver);
          return DriverManager.getConnection(url,"root","");
}
public Connection OpenConection() throws ClassNotFoundException, SQLException{
          con = getConnection();
          return con;
}
public void CloseConection() throws SQLException{
         con.close();
}
 
public boolean InsertBeneficiario(BeneficiarioRepo objBenRep) {
      String sql = "INSERT INTO beneficiario (NombreApeliido, integrantes, celular)  "
                + "VALUES (?,?,?)";
        try {
            ps = OpenConection().prepareStatement(sql);
            ps.setString(1, objBenRep.getNombreApeliido());
            ps.setString(2, String.valueOf(objBenRep.getIntegrantes()));
            ps.setString(3, objBenRep.getCelular());
            ps.execute();
            System.out.println("exe beneficiario");
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                CloseConection();
            } catch (SQLException e) {
                System.err.println(3);
            }
        }
    }

    public BeneficiarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BeneficiarioRepo beneficiario) {
        if (beneficiario.getDireccionCollection() == null) {
            beneficiario.setDireccionCollection(new ArrayList<DireccionRepo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DireccionRepo> attachedDireccionCollection = new ArrayList<DireccionRepo>();
            for (DireccionRepo direccionCollectionDireccionToAttach : beneficiario.getDireccionCollection()) {
                direccionCollectionDireccionToAttach = em.getReference(direccionCollectionDireccionToAttach.getClass(), direccionCollectionDireccionToAttach.getIdDireccion());
                attachedDireccionCollection.add(direccionCollectionDireccionToAttach);
            }
            beneficiario.setDireccionCollection(attachedDireccionCollection);
            em.persist(beneficiario);
            for (DireccionRepo direccionCollectionDireccion : beneficiario.getDireccionCollection()) {
                BeneficiarioRepo oldIdBeneficiarioOfDireccionCollectionDireccion = direccionCollectionDireccion.getIdBeneficiario();
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

    public void edit(BeneficiarioRepo beneficiario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BeneficiarioRepo persistentBeneficiario = em.find(BeneficiarioRepo.class, beneficiario.getIdBeneficiario());
            Collection<DireccionRepo> direccionCollectionOld = persistentBeneficiario.getDireccionCollection();
            Collection<DireccionRepo> direccionCollectionNew = beneficiario.getDireccionCollection();
            List<String> illegalOrphanMessages = null;
            for (DireccionRepo direccionCollectionOldDireccion : direccionCollectionOld) {
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
            Collection<DireccionRepo> attachedDireccionCollectionNew = new ArrayList<DireccionRepo>();
            for (DireccionRepo direccionCollectionNewDireccionToAttach : direccionCollectionNew) {
                direccionCollectionNewDireccionToAttach = em.getReference(direccionCollectionNewDireccionToAttach.getClass(), direccionCollectionNewDireccionToAttach.getIdDireccion());
                attachedDireccionCollectionNew.add(direccionCollectionNewDireccionToAttach);
            }
            direccionCollectionNew = attachedDireccionCollectionNew;
            beneficiario.setDireccionCollection(direccionCollectionNew);
            beneficiario = em.merge(beneficiario);
            for (DireccionRepo direccionCollectionNewDireccion : direccionCollectionNew) {
                if (!direccionCollectionOld.contains(direccionCollectionNewDireccion)) {
                    BeneficiarioRepo oldIdBeneficiarioOfDireccionCollectionNewDireccion = direccionCollectionNewDireccion.getIdBeneficiario();
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
            BeneficiarioRepo beneficiario;
            try {
                beneficiario = em.getReference(BeneficiarioRepo.class, id);
                beneficiario.getIdBeneficiario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The beneficiario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DireccionRepo> direccionCollectionOrphanCheck = beneficiario.getDireccionCollection();
            for (DireccionRepo direccionCollectionOrphanCheckDireccion : direccionCollectionOrphanCheck) {
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

    public List<BeneficiarioRepo> findBeneficiarioEntities() {
        return findBeneficiarioEntities(true, -1, -1);
    }

    public List<BeneficiarioRepo> findBeneficiarioEntities(int maxResults, int firstResult) {
        return findBeneficiarioEntities(false, maxResults, firstResult);
    }

    private List<BeneficiarioRepo> findBeneficiarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BeneficiarioRepo.class));
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

    public BeneficiarioRepo findBeneficiario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BeneficiarioRepo.class, id);
        } finally {
            em.close();
        }
    }

    public int getBeneficiarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BeneficiarioRepo> rt = cq.from(BeneficiarioRepo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
