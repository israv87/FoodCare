package cleanarc.Domain.Entities;




public class Direccion {

    public String sector;
    public String barrio;
    public String callePrincipal;
    public String calleSecundaria;
    public String referencia;

    public Direccion() {
    }

    public Direccion(String sector, String barrio, String callePrincipal, String calleSecundaria, String referencia) {
        this.sector = sector;
        this.barrio = barrio;
        this.callePrincipal = callePrincipal;
        this.calleSecundaria = calleSecundaria;
        this.referencia = referencia;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }

    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return "Direccion{" + "sector=" + sector + ", barrio=" + barrio + ", callePrincipal=" + callePrincipal + ", calleSecundaria=" + calleSecundaria + ", referencia=" + referencia + '}';
    }

    
}
