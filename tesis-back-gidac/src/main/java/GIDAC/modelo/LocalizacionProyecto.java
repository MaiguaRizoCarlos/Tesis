package GIDAC.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "localizacion_proyecto")
@IdClass(LocalizacionProyectoId.class)
public class LocalizacionProyecto{
    
    @Id
    @Column(name = "id_localizacion")
    private Integer idLocalizacion;
    
    @Id
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    
    
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
            
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_localizacion", insertable = false, updatable = false)
    private Localizacion localizacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto", insertable = false, updatable = false)
    private ProyectoInvestigacion proyectoInvestigacion;


    public LocalizacionProyecto() {
    }

    

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }
    
    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
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

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    
    
    
    
}
