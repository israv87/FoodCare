package cleanarc.persistencia;

import cleanarc.Domain.Entities.Beneficiario;
import cleanarc.Domain.Entities.Direccion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;



public class RepositorioBeneficiario implements IOperationDB {


    public Beneficiario beneficiario;
    

    public void insertar() {
    }


    public void getAll() {
    }


    public void actualizar() {
    }



    public void insertarBeneficiario(Beneficiario objBen) {
        
    }
/*
    public void InsertarBeneficiario(Beneficiario objBen) {
        Beneficiario objBenRep = new Beneficiario();
       objBenRep.setNombreApeliido(objBen.getNombreApellidos());
       objBenRep.setIntegrantes(objBen.getNumeroIntegrantes());
       objBenRep.setCelular(objBen.getCelular());
       
       
       Direccion objDirRep = new Direccion();
       objDirRep.setSector(objBen.getDireccion().getSector());
       objDirRep.setBarrio(objBen.getDireccion().getBarrio());
       objDirRep.setCalleP(objBen.getDireccion().getCallePrincipal());
       objDirRep.setCalleS(objBen.getDireccion().getCalleSecundaria());
       objDirRep.setReferencia(objBen.getDireccion().getReferencia());
       
        BeneficiarioJpaController.InsertBeneficiario(objBenRep);
       DireccionJpaController.InsertDireccion(objDirRep);
    }
*/
    public void InsertarDireccionBeneficiario(Direccion objDir) {
    }

    @Override
    public int insertar(cleanarc.persistencia.Beneficiario objBeneficiario) {
         try {
            BeneficiarioJpaController jpaObject = new BeneficiarioJpaController(Persistence.createEntityManagerFactory("FoodCarePU"));
            jpaObject.create(objBeneficiario);
            
        } catch (Exception ex) {
            Logger.getLogger(RepositorioBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objBeneficiario.getIdBeneficiario();
    }


}
