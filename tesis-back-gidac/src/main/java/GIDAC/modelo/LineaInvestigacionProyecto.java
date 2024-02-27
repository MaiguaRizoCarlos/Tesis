package GIDAC.modelo;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "linea_investigacion_proyecto")
@IdClass(LineaInvestigacionProyectoId.class)
public class LineaInvestigacionProyecto{
    
    @Id
    @Column(name = "id_linea_investigacion")
    private Integer idLineaInvestigacion;
    
    @Id
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
            
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_linea_investigacion", insertable = false, updatable = false)
    private LineaInvestigacion lineaInvestigacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto", insertable = false, updatable = false)
    private ProyectoInvestigacion proyectoInvestigacion;


    public LineaInvestigacionProyecto() {
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

    public Integer getIdLineaInvestigacion() {
        return idLineaInvestigacion;
    }

    public void setIdLineaInvestigacion(Integer idLineaInvestigacion) {
        this.idLineaInvestigacion = idLineaInvestigacion;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
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

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public LineaInvestigacion getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public void setLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.lineaInvestigacion = lineaInvestigacion;
    }
    
    
    
    
}
