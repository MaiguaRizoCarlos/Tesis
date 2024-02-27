package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "variable_unidad_medida")
public class VariableUnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variable_unidad_medida")
    private Integer idVariableUnidadMedida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_variable")
    private Variable variable;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    
    private boolean vigencia=true;
    
    @OneToMany(mappedBy = "variableUnidadMedida",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DatoRecolectado> datoRecolectado = new HashSet<>();
    
    @OneToMany(mappedBy = "variableUnidadMedida",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ValorPermitido> valorPermitido = new HashSet<>();
    

    public VariableUnidadMedida() {
    }
    
    public Variable getVariable() {
        return variable;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getIdVariableUnidadMedida() {
        return idVariableUnidadMedida;
    }

    public void setIdVariableUnidadMedida(Integer idVariableUnidadMedida) {
        this.idVariableUnidadMedida = idVariableUnidadMedida;
    }

    public Set<DatoRecolectado> getDatoRecolectado() {
        return datoRecolectado;
    }

    public void setDatoRecolectado(Set<DatoRecolectado> datoRecolectado) {
        this.datoRecolectado = datoRecolectado;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public Set<ValorPermitido> getValorPermitido() {
        return valorPermitido;
    }

    public void setValorPermitido(Set<ValorPermitido> valorPermitido) {
        this.valorPermitido = valorPermitido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    
    
    
}
