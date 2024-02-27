package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "valor_permitido")
@Entity
public class ValorPermitido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idValorPermitido;
    
    private float valorMaximo;
    private float valorMinimo;
    private String valorPermitido;
    private boolean vigencia=true;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_variable_unidad_medida")
    private VariableUnidadMedida variableUnidadMedida;
    
    

    public float getValorMaximo() {
        return valorMaximo;
    }

    public float getValorMinimo() {
        return valorMinimo;
    }

    public String getValorPermitido() {
        return valorPermitido;
    }


    public Integer getIdValorPermitido() {
        return idValorPermitido;
    }

    public void setIdValorPermitido(Integer idValorPermitido) {
        this.idValorPermitido = idValorPermitido;
    }

   

    public void setValorMaximo(float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public void setValorPermitido(String valorPermitido) {
        this.valorPermitido = valorPermitido;
    }


    public VariableUnidadMedida getVariableUnidadMedida() {
        return variableUnidadMedida;
    }

    public void setVariableUnidadMedida(VariableUnidadMedida variableUnidadMedida) {
        this.variableUnidadMedida = variableUnidadMedida;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
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
