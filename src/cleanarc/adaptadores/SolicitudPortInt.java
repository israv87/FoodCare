package cleanarc.adaptadores;

import cleanarc.Domain.Entities.Beneficiario;
import cleanarc.Domain.Entities.Direccion;
import cleanarc.Domain.ISolicitar;
import cleanarc.persistencia.IOperationDB;
import cleanarc.persistencia.RepositorioBeneficiario;



public class SolicitudPortInt {

   private ISolicitar solicitar;
   private IOperationDB ioperactiondb;
   RepositorioBeneficiario objBenRep = new RepositorioBeneficiario();
    public SolicitudPortInt(ISolicitar solicitar, IOperationDB ioperactiondb) {
        this.solicitar = solicitar;
        this.ioperactiondb = ioperactiondb;
    }

   
    public int SolicitarPortInt(Beneficiario benficiario) {
      //  objBenRep.InsertarBeneficiario(benficiario);
        solicitar.registrar(benficiario);
        return 1;
    }

}
