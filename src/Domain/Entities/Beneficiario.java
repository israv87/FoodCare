package Domain.Entities;




public class Beneficiario {

    public String nombreApellidos;
    public int numeroIntegrantes;
    public String celular;
    public Direccion direccion;
    public kit kit;
    Direccion objDireccion ;

    public Beneficiario() {
    }

    public Beneficiario(String nombreApellidos, int numeroIntegrantes, String celular, Direccion direccion, kit kit, Direccion objDireccion) {
        this.nombreApellidos = nombreApellidos;
        this.numeroIntegrantes = numeroIntegrantes;
        this.celular = celular;
        this.direccion = direccion;
        this.kit = kit;
        this.objDireccion = objDireccion;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public int getNumeroIntegrantes() {
        return numeroIntegrantes;
    }

    public void setNumeroIntegrantes(int numeroIntegrantes) {
        this.numeroIntegrantes = numeroIntegrantes;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public kit getKit() {
        return kit;
    }

    public void setKit(kit kit) {
        this.kit = kit;
    }

    public Direccion getObjDireccion() {
        return objDireccion;
    }

    public void setObjDireccion(Direccion objDireccion) {
        this.objDireccion = objDireccion;
    }

    @Override
    public String toString() {
        return "Beneficiario{" + "nombreApellidos=" + nombreApellidos + ", numeroIntegrantes=" + numeroIntegrantes + ", celular=" + celular + ", direccion=" + direccion + ", kit=" + kit + ", objDireccion=" + objDireccion + '}';
    }
    
    
    
    public void AgregarDireccion(final String sector, final String callePrincipal, final String calleSecundaria, final String barrio, final String referencia) {
    
    }

}
