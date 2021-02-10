package cleanarc.persistencia;

import cleanarc.Domain.Entities.Beneficiario;
import cleanarc.Domain.Entities.Direccion;


public interface IOperationDB {

    void insertar();


    void getAll();


    void actualizar();

    public int insertar(cleanarc.persistencia.Beneficiario objBeneficiario);
   

}
