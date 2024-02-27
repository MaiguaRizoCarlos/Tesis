package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "area")
@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArea;
    
    private float area;
    
    @OneToMany(mappedBy = "area",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Parcela> parcela = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;
    
    private boolean editable=true;
    private boolean vigencia=true;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public boolean isEditable() {
        return editable;
    }
    
     public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isVigencia() {
        return vigencia;
    }
    
    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public float getArea() {
        return area;
    }

    public Set<Parcela> getParcela() {
        return parcela;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setParcela(Set<Parcela> parcela) {
        this.parcela = parcela;
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
