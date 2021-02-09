package Adapatadores;

import Domain.Entities.Beneficiario;
import Domain.Entities.Direccion;
import Domain.ISolicitar;
import Persistencia.IOperationDB;
import Persistencia.RepositorioBeneficiario.BeneficiarioRepo;
import Persistencia.RepositorioBeneficiario.RepositorioBeneficiario;



public class SolicitudPortInt {

   private ISolicitar solicitar;
   private IOperationDB ioperactiondb;
   RepositorioBeneficiario objBenRep = new RepositorioBeneficiario();
    public SolicitudPortInt(ISolicitar solicitar, IOperationDB ioperactiondb) {
        this.solicitar = solicitar;
        this.ioperactiondb = ioperactiondb;
    }

   
    public int SolicitarPortInt(Beneficiario benficiario) {
        objBenRep.InsertarBeneficiario(benficiario);
        solicitar.registrar(benficiario);
        return 1;
    }

}
