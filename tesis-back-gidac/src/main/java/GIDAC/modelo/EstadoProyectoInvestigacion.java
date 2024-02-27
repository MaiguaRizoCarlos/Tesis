package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "estado_proyecto_investigacion")
@Entity
public class EstadoProyectoInvestigacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstadoProyecto;

    private String nombreEstadoProyecto;


    @OneToMany(mappedBy = "estadoProyectoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProyectoInvestigacion> proyectoInvestigacion = new HashSet<>();
    
    public Integer getIdEstadoProyecto() {
        return idEstadoProyecto;
    }

    public String getNombreEstadoProyecto() {
        return nombreEstadoProyecto;
    }

    public void setIdEstadoProyecto(Integer idEstadoProyecto) {
        this.idEstadoProyecto = idEstadoProyecto;
    }

    public void setNombreEstadoProyecto(String nombreEstadoProyecto) {
        this.nombreEstadoProyecto = nombreEstadoProyecto;
    }


    
    

    public Set<ProyectoInvestigacion> getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setProyectoInvestigacion(Set<ProyectoInvestigacion> proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    
    
    
    

}
