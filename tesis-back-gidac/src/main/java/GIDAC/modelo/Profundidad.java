package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "profundidad")
@Entity
public class Profundidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfundidad;
    
    @OneToMany(mappedBy = "profundidad",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProfundidadParcela> profunidadParcela = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;
    
    private Double profundidadMinima;
    
    private Double profundidadMaxima;
    
    private boolean vigencia=true;
    
    private boolean editable=true;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    public Integer getIdProfundidad() {
        return idProfundidad;
    }

    public Set<ProfundidadParcela> getProfunidadParcela() {
        return profunidadParcela;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setIdProfundidad(Integer idProfundidad) {
        this.idProfundidad = idProfundidad;
    }

    public void setProfunidadParcela(Set<ProfundidadParcela> profunidadParcela) {
        this.profunidadParcela = profunidadParcela;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public Double getProfundidadMinima() {
        return profundidadMinima;
    }

    public Double getProfundidadMaxima() {
        return profundidadMaxima;
    }

    public void setProfundidadMinima(Double profundidadMinima) {
        this.profundidadMinima = profundidadMinima;
    }

    public void setProfundidadMaxima(Double profundidadMaxima) {
        this.profundidadMaxima = profundidadMaxima;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
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
