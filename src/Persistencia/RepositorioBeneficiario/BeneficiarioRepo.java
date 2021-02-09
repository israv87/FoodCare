/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.RepositorioBeneficiario;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Isra
 */
@Entity
@Table(name = "beneficiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beneficiario.findAll", query = "SELECT b FROM Beneficiario b"),
    @NamedQuery(name = "Beneficiario.findByIdBeneficiario", query = "SELECT b FROM Beneficiario b WHERE b.idBeneficiario = :idBeneficiario"),
    @NamedQuery(name = "Beneficiario.findByNombreApeliido", query = "SELECT b FROM Beneficiario b WHERE b.nombreApeliido = :nombreApeliido"),
    @NamedQuery(name = "Beneficiario.findByIntegrantes", query = "SELECT b FROM Beneficiario b WHERE b.integrantes = :integrantes"),
    @NamedQuery(name = "Beneficiario.findByCelular", query = "SELECT b FROM Beneficiario b WHERE b.celular = :celular")})
public class BeneficiarioRepo implements Serializable {

    private  final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBeneficiario")
    private Integer idBeneficiario;
    @Basic(optional = false)
    @Column(name = "NombreApeliido")
    private String nombreApeliido;
    @Basic(optional = false)
    @Column(name = "integrantes")
    private int integrantes;
    @Basic(optional = false)
    @Column(name = "celular")
    private String celular;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBeneficiario")
    private Collection<DireccionRepo> direccionCollection;

    public BeneficiarioRepo() {
    }

    public BeneficiarioRepo(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public BeneficiarioRepo(Integer idBeneficiario, String nombreApeliido, int integrantes, String celular) {
        this.idBeneficiario = idBeneficiario;
        this.nombreApeliido = nombreApeliido;
        this.integrantes = integrantes;
        this.celular = celular;
    }

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getNombreApeliido() {
        return nombreApeliido;
    }

    public void setNombreApeliido(String nombreApeliido) {
        this.nombreApeliido = nombreApeliido;
    }

    public int getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(int integrantes) {
        this.integrantes = integrantes;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @XmlTransient
    public Collection<DireccionRepo> getDireccionCollection() {
        return direccionCollection;
    }

    public void setDireccionCollection(Collection<DireccionRepo> direccionCollection) {
        this.direccionCollection = direccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBeneficiario != null ? idBeneficiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeneficiarioRepo)) {
            return false;
        }
        BeneficiarioRepo other = (BeneficiarioRepo) object;
        if ((this.idBeneficiario == null && other.idBeneficiario != null) || (this.idBeneficiario != null && !this.idBeneficiario.equals(other.idBeneficiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.RepositorioBeneficiario.Beneficiario[ idBeneficiario=" + idBeneficiario + " ]";
    }
    
}
