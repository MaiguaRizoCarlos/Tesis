package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "tipo_variable")
@Entity
public class TipoVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoVariable;
    
    private String nombreTipoVariable;
    
    @OneToMany(mappedBy = "tipoVariable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Variable> variable = new HashSet<>();

    public Integer getIdTipoVariable() {
        return idTipoVariable;
    }

    public String getNombreTipoVariable() {
        return nombreTipoVariable;
    }

    public Set<Variable> getVariable() {
        return variable;
    }

    public void setIdTipoVariable(Integer idTipoVariable) {
        this.idTipoVariable = idTipoVariable;
    }

    public void setNombreTipoVariable(String nombreTipoVariable) {
        this.nombreTipoVariable = nombreTipoVariable;
    }

    public void setVariable(Set<Variable> variable) {
        this.variable = variable;
    }
    
    
    
}
