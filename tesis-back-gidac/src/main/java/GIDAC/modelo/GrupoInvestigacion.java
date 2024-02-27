package GIDAC.modelo;

import javax.persistence.*;
import java.util.Date;


//@Entity
//@Table(name = "grupo_investigacion")
//@IdClass(GrupoInvestigacionId.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//public class GrupoInvestigacion implements Serializable {
//
//    @Id
//    @JsonIdentityReference(alwaysAsId = true)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_usuario")
//    private Usuario usuario;
//
//    @Id
//    @JsonIdentityReference(alwaysAsId = true)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_proyecto_investigacion")
//    private ProyectoInvestigacion proyectoInvestigacion;

//@IdClass(GrupoInvestigacionId.class)
//@Table(name = "grupo_investigacion")
@Entity
@Table(name = "grupo_investigacion")
@IdClass(GrupoInvestigacionId.class)
public class GrupoInvestigacion{
    
    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Id
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
            
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto", insertable = false, updatable = false)
    private ProyectoInvestigacion proyectoInvestigacion;


    public GrupoInvestigacion() {
    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    
    
}
