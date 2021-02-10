package cleanarc.Domain.Usecase;

import cleanarc.Domain.Entities.Beneficiario;
import cleanarc.Domain.Entities.Direccion;
import cleanarc.Domain.ISolicitar;
import cleanarc.persistencia.IOperationDB;




public class SolicitarKit implements ISolicitar {
 private IOperationDB repository;

    public SolicitarKit(IOperationDB repository) {
        this.repository = repository;
    }
 
 

    public void registrar(Beneficiario beneficiario,Beneficiario beneficiarioRepo) {
     Beneficiario objBen = new Beneficiario();
     Direccion objDir = new Direccion();
     cleanarc.persistencia.Beneficiario objBeneficiario = new cleanarc.persistencia.Beneficiario();
     
     objBeneficiario.setNombreApeliido(objBen.getNombreApellidos());
     objBeneficiario.setIntegrantes(objBen.getNumeroIntegrantes());
     objBeneficiario.setCelular(objBen.getCelular());
     objBeneficiario.setIdBeneficiario(1);
     repository.insertar(objBeneficiario);
    }

    @Override
    public int registrar(Beneficiario beneficiario) {
        Beneficiario objBen = new Beneficiario();
     Direccion objDir = new Direccion();
     cleanarc.persistencia.Beneficiario objBeneficiario = new cleanarc.persistencia.Beneficiario();
     
     objBeneficiario.setNombreApeliido(objBen.getNombreApellidos());
     objBeneficiario.setIntegrantes(objBen.getNumeroIntegrantes());
     objBeneficiario.setCelular(objBen.getCelular());
     objBeneficiario.setIdBeneficiario(1);
     repository.insertar(objBeneficiario);
     return 1;
    }

    

}
