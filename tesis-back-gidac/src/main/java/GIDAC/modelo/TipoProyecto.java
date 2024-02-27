package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "tipo_proyecto")
@Entity
public class TipoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoProyecto;

    private String nombreTipoProyecto;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;


    @OneToMany(mappedBy = "tipoProyecto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProyectoInvestigacion> proyectoInvestigacion = new HashSet<>();

    public Integer getIdTipoProyecto() {
        return idTipoProyecto;
    }

    public String getNombreTipoProyecto() {
        return nombreTipoProyecto;
    }

    public void setIdTipoProyecto(Integer idTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
    }

    public void setNombreTipoProyecto(String nombreTipoProyecto) {
        this.nombreTipoProyecto = nombreTipoProyecto;
    }
    
    public Set<ProyectoInvestigacion> getProyectoInvestigacion() {
        return proyectoInvestigacion;
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
