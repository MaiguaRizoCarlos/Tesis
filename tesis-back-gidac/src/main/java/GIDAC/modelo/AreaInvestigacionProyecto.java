package GIDAC.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "area_investigacion_proyecto")
@IdClass(AreaInvestigacionProyectoId.class)
public class AreaInvestigacionProyecto{
    
    @Id
    @Column(name = "id_area_investigacion")
    private Integer idAreaInvestigacion;
    
    @Id
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
            
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_area_investigacion", insertable = false, updatable = false)
    private AreaInvestigacion areaInvestigacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto", insertable = false, updatable = false)
    private ProyectoInvestigacion proyectoInvestigacion;


    public AreaInvestigacionProyecto() {
    }

    public Integer getIdAreaInvestigacion() {
        return idAreaInvestigacion;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public AreaInvestigacion getAreaInvestigacion() {
        return areaInvestigacion;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setIdAreaInvestigacion(Integer idAreaInvestigacion) {
        this.idAreaInvestigacion = idAreaInvestigacion;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public void setAreaInvestigacion(AreaInvestigacion areaInvestigacion) {
        this.areaInvestigacion = areaInvestigacion;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    
    
    
}
