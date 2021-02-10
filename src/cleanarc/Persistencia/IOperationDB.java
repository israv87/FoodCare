package Persistencia;

import Domain.Entities.Beneficiario;
import Domain.Entities.Direccion;
import Persistencia.RepositorioBeneficiario.BeneficiarioRepo;



public interface IOperationDB {

    void insertar();


    void getAll();


    void actualizar();

    public void insertar(BeneficiarioRepo objBenRep);

    public void InsertarBeneficiario(Beneficiario objBen);

    public void InsertarDireccionBeneficiario(Direccion objDir);
    
}
