package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
@Table(name = "familia")
@Entity
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_familia")
    private Integer idFamilia;
    
    private String codigo;
    private String descripcion;
    
    
    @ManyToOne
    @JoinColumn(name = "id_padre", referencedColumnName = "id_familia")
    @JsonBackReference // Anotación para evitar recursión al serializar el padre
    private Familia familia;

    @OneToMany(mappedBy = "familia")
    @JsonManagedReference // Anotación para controlar la serialización de los hijos
    private List<Familia> familiaHijo;

    @Transient
    private String descripcionCompleta;
    
    
    private boolean vigencia=true;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    @OneToMany(mappedBy = "familia",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VariableFamilia> variableFamilia = new HashSet<>();
    
    
    
    public Integer getIdFamilia() {
        return idFamilia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<VariableFamilia> getVariableFamilia() {
        return variableFamilia;
    }

    public void setVariableFamilia(Set<VariableFamilia> variableFamilia) {
        this.variableFamilia = variableFamilia;
    }

    public Familia getFamilia() {
        return familia;
    }

    public List<Familia> getFamiliaHijo() {
        return familiaHijo;
    }

    public String getDescripcionCompleta() {
        return descripcionCompleta;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public void setFamiliaHijo(List<Familia> familiaHijo) {
        this.familiaHijo = familiaHijo;
    }

    public void setDescripcionCompleta(String descripcionCompleta) {
        this.descripcionCompleta = descripcionCompleta;
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


