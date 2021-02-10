/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleanarc.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author halo_
 */
@Entity
@Table(name = "direccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d"),
    @NamedQuery(name = "Direccion.findByIdDireccion", query = "SELECT d FROM Direccion d WHERE d.idDireccion = :idDireccion"),
    @NamedQuery(name = "Direccion.findBySector", query = "SELECT d FROM Direccion d WHERE d.sector = :sector"),
    @NamedQuery(name = "Direccion.findByBarrio", query = "SELECT d FROM Direccion d WHERE d.barrio = :barrio"),
    @NamedQuery(name = "Direccion.findByCalleP", query = "SELECT d FROM Direccion d WHERE d.calleP = :calleP"),
    @NamedQuery(name = "Direccion.findByCalleS", query = "SELECT d FROM Direccion d WHERE d.calleS = :calleS"),
    @NamedQuery(name = "Direccion.findByReferencia", query = "SELECT d FROM Direccion d WHERE d.referencia = :referencia")})
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDireccion")
    private Integer idDireccion;
    @Column(name = "sector")
    private String sector;
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "calleP")
    private String calleP;
    @Column(name = "calleS")
    private String calleS;
    @Column(name = "referencia")
    private String referencia;
    @JoinColumn(name = "idBeneficiario", referencedColumnName = "idBeneficiario")
    @ManyToOne(optional = false)
    private Beneficiario idBeneficiario;

    public Direccion() {
    }

    public Direccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
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

    public String getCalleP() {
        return calleP;
    }

    public void setCalleP(String calleP) {
        this.calleP = calleP;
    }

    public String getCalleS() {
        return calleS;
    }

    public void setCalleS(String calleS) {
        this.calleS = calleS;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Beneficiario getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Beneficiario idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccion != null ? idDireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.idDireccion == null && other.idDireccion != null) || (this.idDireccion != null && !this.idDireccion.equals(other.idDireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cleanarc.persistencia.Direccion[ idDireccion=" + idDireccion + " ]";
    }
    
}
