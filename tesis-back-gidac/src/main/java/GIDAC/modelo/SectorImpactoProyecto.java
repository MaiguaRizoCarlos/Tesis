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
@Table(name = "sector_impacto_proyecto")
@IdClass(SectorImpactoProyectoId.class)
public class SectorImpactoProyecto{
    
    @Id
    @Column(name = "id_sector_impacto")
    private Integer idSectorImpacto;
    
    @Id
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
            
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sector_impacto", insertable = false, updatable = false)
    private SectorImpacto sectorImpacto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto", insertable = false, updatable = false)
    private ProyectoInvestigacion proyectoInvestigacion;


    public SectorImpactoProyecto() {
    }

    public Integer getIdSectorImpacto() {
        return idSectorImpacto;
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

    public SectorImpacto getSectorImpacto() {
        return sectorImpacto;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setIdSectorImpacto(Integer idSectorImpacto) {
        this.idSectorImpacto = idSectorImpacto;
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

    public void setSectorImpacto(SectorImpacto sectorImpacto) {
        this.sectorImpacto = sectorImpacto;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }
    
    
    
}
