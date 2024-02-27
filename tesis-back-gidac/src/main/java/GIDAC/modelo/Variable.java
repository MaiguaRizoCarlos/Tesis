package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "variable")
public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVariable;
    
    private String codigoVariable;
    private String nombreVariable;
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CatalogoOrganizacion> catalogoOrganizacion = new HashSet<>();
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VariableFamilia> variableFamilia = new HashSet<>();
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VariableUnidadMedida> unidadMedidaVariable = new HashSet<>();

    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_variable")
    private TipoVariable tipoVariable;
    

    public Integer getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }


    public String getNombreVariable() {
        return nombreVariable;
    }

    public void setCodigoVariable(String codigoVariable) {
        this.codigoVariable = codigoVariable;
    }

    public String getCodigoVariable() {
        return codigoVariable;
    }

    
    
    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public TipoVariable getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(TipoVariable tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public Set<VariableUnidadMedida> getUnidadMedidaVariable() {
        return unidadMedidaVariable;
    }

    public void setUnidadMedidaVariable(Set<VariableUnidadMedida> unidadMedidaVariable) {
        this.unidadMedidaVariable = unidadMedidaVariable;
    }

    public Set<VariableFamilia> getVariableFamilia() {
        return variableFamilia;
    }

    public void setVariableFamilia(Set<VariableFamilia> variableFamilia) {
        this.variableFamilia = variableFamilia;
    }

    public Set<CatalogoOrganizacion> getCatalogoOrganizacion() {
        return catalogoOrganizacion;
    }

    public void setCatalogoOrganizacion(Set<CatalogoOrganizacion> catalogoOrganizacion) {
        this.catalogoOrganizacion = catalogoOrganizacion;
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
