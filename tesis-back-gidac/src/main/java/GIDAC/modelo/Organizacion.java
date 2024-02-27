package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "organizacion")
@Entity
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrganizacion;
    
    @OneToMany(mappedBy = "organizacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CatalogoOrganizacion> catalogoOrganizacion = new HashSet<>();
    
    private String nombreOrganizacion;
    private String siglas;
    private String descripcion;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    
    private boolean vigencia=true;

    public void setIdOrganizacion(Integer idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public void setCatalogoOrganizacion(Set<CatalogoOrganizacion> catalogoOrganizacion) {
        this.catalogoOrganizacion = catalogoOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public Integer getIdOrganizacion() {
        return idOrganizacion;
    }

    public Set<CatalogoOrganizacion> getCatalogoOrganizacion() {
        return catalogoOrganizacion;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public String getSiglas() {
        return siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    
}
