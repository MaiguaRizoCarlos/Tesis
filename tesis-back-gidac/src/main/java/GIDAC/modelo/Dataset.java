package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "dataset")
public class Dataset {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDataset;
    private Integer codigoDataset;
    @Temporal(TemporalType.DATE)
    private Date fechaInicioDataset;
    @Temporal(TemporalType.DATE)
    private Date fechaFinDataset;
    @Temporal(TemporalType.DATE)
    private Date fechaSalidaCampo;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
    
    @OneToMany(mappedBy = "dataset",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DatoRecolectado> datoRecolectado = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto")
    private ProyectoInvestigacion proyectoInvestigacion;
    
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_profundidad", referencedColumnName = "id_profundidad"),
        @JoinColumn(name = "id_parcela", referencedColumnName = "id_parcela")
    })
    private ProfundidadParcela profundidadParcela;
    
    

    public Integer getIdDataset() {
        return idDataset;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public Set<DatoRecolectado> getDatoRecolectado() {
        return datoRecolectado;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public ProfundidadParcela getProfundidadParcela() {
        return profundidadParcela;
    }

    public void setIdDataset(Integer idDataset) {
        this.idDataset = idDataset;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public void setDatoRecolectado(Set<DatoRecolectado> datoRecolectado) {
        this.datoRecolectado = datoRecolectado;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public void setProfundidadParcela(ProfundidadParcela profundidadParcela) {
        this.profundidadParcela = profundidadParcela;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Integer getCodigoDataset() {
        return codigoDataset;
    }

    public void setCodigoDataset(Integer codigoDataset) {
        this.codigoDataset = codigoDataset;
    }

    public Date getFechaSalidaCampo() {
        return fechaSalidaCampo;
    }

    public void setFechaSalidaCampo(Date fechaSalidaCampo) {
        this.fechaSalidaCampo = fechaSalidaCampo;
    }

    public Date getFechaInicioDataset() {
        return fechaInicioDataset;
    }

    public void setFechaInicioDataset(Date fechaInicioDataset) {
        this.fechaInicioDataset = fechaInicioDataset;
    }

    public Date getFechaFinDataset() {
        return fechaFinDataset;
    }

    public void setFechaFinDataset(Date fechaFinDataset) {
        this.fechaFinDataset = fechaFinDataset;
    }
    
    
}
