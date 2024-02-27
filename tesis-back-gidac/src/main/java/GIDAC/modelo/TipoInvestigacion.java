package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "tipo_investigacion")
@Entity
public class TipoInvestigacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoInvestigacion;

    private String nombreTipoInvestigacion;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;


    @OneToMany(mappedBy = "tipoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProyectoInvestigacion> proyectoInvestigacion = new HashSet<>();

    public Integer getIdTipoInvestigacion() {
        return idTipoInvestigacion;
    }

    public String getNombreTipoInvestigacion() {
        return nombreTipoInvestigacion;
    }

    public Set<ProyectoInvestigacion> getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setIdTipoInvestigacion(Integer idTipoInvestigacion) {
        this.idTipoInvestigacion = idTipoInvestigacion;
    }

    public void setNombreTipoInvestigacion(String nombreTipoInvestigacion) {
        this.nombreTipoInvestigacion = nombreTipoInvestigacion;
    }

    public void setProyectoInvestigacion(Set<ProyectoInvestigacion> proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
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
