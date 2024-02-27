package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "unidad_medida")
@Entity
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnidadMedida;
    
    @OneToMany(mappedBy = "unidadMedida",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Profundidad> profundidad = new HashSet<>();
    
    @OneToMany(mappedBy = "unidadMedida",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Altura> altura = new HashSet<>();
    
    @OneToMany(mappedBy = "unidadMedida",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Area> area = new HashSet<>();
    
    @OneToMany(mappedBy = "unidadMedida",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VariableUnidadMedida> unidadMedidaVariable = new HashSet<>();

    private String unidadMedida;
    private String magnitud;
    private String abreviatura;
    private boolean editable=true;
    private boolean vigencia=true;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    public Set<Profundidad> getProfundidad() {
        return profundidad;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setProfundidad(Set<Profundidad> profundidad) {
        this.profundidad = profundidad;
    }

    
    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public Set<Altura> getAltura() {
        return altura;
    }

    public Set<Area> getArea() {
        return area;
    }


    public void setAltura(Set<Altura> altura) {
        this.altura = altura;
    }

    public void setArea(Set<Area> area) {
        this.area = area;
    }


    public Set<VariableUnidadMedida> getUnidadMedidaVariable() {
        return unidadMedidaVariable;
    }

    public void setUnidadMedidaVariable(Set<VariableUnidadMedida> unidadMedidaVariable) {
        this.unidadMedidaVariable = unidadMedidaVariable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
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
