package Domain.UseCase;

import Domain.Entities.Beneficiario;
import Domain.Entities.Direccion;
import Domain.ISolicitar;
import Persistencia.IOperationDB;
import Persistencia.RepositorioBeneficiario.BeneficiarioRepo;




public class SolicitarKit implements ISolicitar {
 private IOperationDB repository;

    public SolicitarKit(IOperationDB repository) {
        this.repository = repository;
    }
 
 

    public void registrar(Beneficiario beneficiario,BeneficiarioRepo beneficiarioRepo) {
     Beneficiario objBen = new Beneficiario();
     Direccion objDir = new Direccion();
     BeneficiarioRepo objBenRep = new BeneficiarioRepo(); 
     
     objBenRep.setNombreApeliido(objBen.getNombreApellidos());
     objBenRep.setIntegrantes(objBen.getNumeroIntegrantes());
     objBenRep.setCelular(objBen.getCelular());
     objBenRep.setIdBeneficiario(1);
     repository.insertar(objBenRep);
    }

    @Override
    public int registrar(Beneficiario beneficiario) {
        Beneficiario objBen = new Beneficiario();
     Direccion objDir = new Direccion();
     BeneficiarioRepo objBenRep = new BeneficiarioRepo(); 
     
     objBenRep.setNombreApeliido(objBen.getNombreApellidos());
     objBenRep.setIntegrantes(objBen.getNumeroIntegrantes());
     objBenRep.setCelular(objBen.getCelular());
     objBenRep.setIdBeneficiario(1);
     repository.insertar(objBenRep);
     return 1;
    }
    

}
