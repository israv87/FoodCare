package Persistencia.RepositorioBeneficiario;

import Domain.Entities.Beneficiario;
import Domain.Entities.Direccion;
import Persistencia.IOperationDB;


public class RepositorioBeneficiario implements IOperationDB {


    public Beneficiario beneficiario;
    

    public void insertar() {
    }


    public void getAll() {
    }


    public void actualizar() {
    }

    public void insertar(BeneficiarioRepo objBenRep) {
       
    }

    public void insertarBeneficiario(Beneficiario objBen) {
        
    }

    @Override
    public void InsertarBeneficiario(Beneficiario objBen) {
        BeneficiarioRepo objBenRep = new BeneficiarioRepo();
       objBenRep.setNombreApeliido(objBen.getNombreApellidos());
       objBenRep.setIntegrantes(objBen.getNumeroIntegrantes());
       objBenRep.setCelular(objBen.getCelular());
       
       
       DireccionRepo objDirRep = new DireccionRepo();
       objDirRep.setSector(objBen.getDireccion().getSector());
       objDirRep.setBarrio(objBen.getDireccion().getBarrio());
       objDirRep.setCalleP(objBen.getDireccion().getCallePrincipal());
       objDirRep.setCalleS(objBen.getDireccion().getCalleSecundaria());
       objDirRep.setReferencia(objBen.getDireccion().getReferencia());
       
        BeneficiarioJpaController.InsertBeneficiario(objBenRep);
       DireccionJpaController.InsertDireccion(objDirRep);
    }

    @Override
    public void InsertarDireccionBeneficiario(Direccion objDir) {
    }

}
