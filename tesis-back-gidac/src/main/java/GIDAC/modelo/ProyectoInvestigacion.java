package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proyecto_investigacion")
public class ProyectoInvestigacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    
    private String nombreProyecto;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    
    private boolean vigencia=true;

    
    
    @OneToMany(mappedBy = "proyectoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SolicitudDescarga> solicitudAcceso = new HashSet<>();
    
    @OneToMany(mappedBy = "proyectoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<GrupoInvestigacion> grupoInvestigacion = new HashSet<>();
    
    @OneToMany(mappedBy = "proyectoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Conglomerado> conglomerado = new HashSet<>();
    
    @OneToMany(mappedBy = "proyectoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<LocalizacionProyecto> localizacionProyecto = new HashSet<>();
    
    @OneToMany(mappedBy = "proyectoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AreaInvestigacionProyecto> areaInvestigacionProyecto = new HashSet<>();
   
    @OneToMany(mappedBy = "proyectoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SectorImpactoProyecto> sectorImpactoProyecto = new HashSet<>();
    
    @OneToMany(mappedBy = "proyectoInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<LineaInvestigacionProyecto> lineaInvestigacionProyecto = new HashSet<>();
    
    
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_proyecto")
    private EstadoProyectoInvestigacion estadoProyectoInvestigacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_proyecto")
    private TipoProyecto tipoProyecto;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_investigacion")
    private TipoInvestigacion tipoInvestigacion;
    
    
    public ProyectoInvestigacion(){

    }

    public ProyectoInvestigacion(Integer idProyectoInvestigacion, String nombreProyectoInvestigacion, String descripcion, Date fechaInicio, Date fechaFin) {
        this.idProyecto = idProyectoInvestigacion;
        this.nombreProyecto = nombreProyectoInvestigacion;
        this.descripcion=descripcion;
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
    }

   

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public Set<SolicitudDescarga> getSolicitudAcceso() {
        return solicitudAcceso;
    }

    public void setSolicitudAcceso(Set<SolicitudDescarga> solicitudAcceso) {
        this.solicitudAcceso = solicitudAcceso;
    }
    

    public EstadoProyectoInvestigacion getEstadoProyectoInvestigacion() {
        return estadoProyectoInvestigacion;
    }

    public void setEstadoProyectoInvestigacion(EstadoProyectoInvestigacion estadoProyectoInvestigacion) {
        this.estadoProyectoInvestigacion = estadoProyectoInvestigacion;
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

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setGrupoInvestigacion(Set<GrupoInvestigacion> grupoInvestigacion) {
        this.grupoInvestigacion = grupoInvestigacion;
    }
    public Set<GrupoInvestigacion> getGrupoInvestigacion() {
        return grupoInvestigacion;
    }

    public void setConglomerado(Set<Conglomerado> conglomerado) {
        this.conglomerado = conglomerado;
    }

    public Set<Conglomerado> getConglomerado() {
        return conglomerado;
    }

    public void setLocalizacionProyecto(Set<LocalizacionProyecto> localizacionProyecto) {
        this.localizacionProyecto = localizacionProyecto;
    }

    public void setTipoProyecto(TipoProyecto tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public Set<LocalizacionProyecto> getLocalizacionProyecto() {
        return localizacionProyecto;
    }

    public TipoProyecto getTipoProyecto() {
        return tipoProyecto;
    }

    public TipoInvestigacion getTipoInvestigacion() {
        return tipoInvestigacion;
    }

    public void setTipoInvestigacion(TipoInvestigacion tipoInvestigacion) {
        this.tipoInvestigacion = tipoInvestigacion;
    }

    public Set<AreaInvestigacionProyecto> getAreaInvestigacionProyecto() {
        return areaInvestigacionProyecto;
    }



    public Set<SectorImpactoProyecto> getSectorImpactoProyecto() {
        return sectorImpactoProyecto;
    }

    public Set<LineaInvestigacionProyecto> getLineaInvestigacionProyecto() {
        return lineaInvestigacionProyecto;
    }

    public void setAreaInvestigacionProyecto(Set<AreaInvestigacionProyecto> areaInvestigacionProyecto) {
        this.areaInvestigacionProyecto = areaInvestigacionProyecto;
    }

    public void setSectorImpactoProyecto(Set<SectorImpactoProyecto> sectorImpactoProyecto) {
        this.sectorImpactoProyecto = sectorImpactoProyecto;
    }

    public void setLineaInvestigacionProyecto(Set<LineaInvestigacionProyecto> lineaInvestigacionProyecto) {
        this.lineaInvestigacionProyecto = lineaInvestigacionProyecto;
    }
    
    
    
}
