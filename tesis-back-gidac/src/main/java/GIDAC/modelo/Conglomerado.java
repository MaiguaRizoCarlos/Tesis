package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "conglomerado")
@Entity
public class Conglomerado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConglomerado;
    
    private String codigoConglomerado;
    private String nombreConglomerado;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private String sector;
    private boolean vigencia=true;
    
    @OneToMany(mappedBy = "conglomerado",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Parcela> parcela = new HashSet<>();
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto")
    private ProyectoInvestigacion proyectoInvestigacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_altura")
    private Altura altura;
    
    private boolean editable=true;

    public boolean isEditable() {
        return editable;
    }
    
     public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Integer getIdConglomerado() {
        return idConglomerado;
    }

    public Set<Parcela> getParcela() {
        return parcela;
    }

    public String getNombreConglomerado() {
        return nombreConglomerado;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setIdConglomerado(Integer idConglomerado) {
        this.idConglomerado = idConglomerado;
    }

    public void setParcela(Set<Parcela> parcela) {
        this.parcela = parcela;
    }

    public void setNombreConglomerado(String nombreConglomerado) {
        this.nombreConglomerado = nombreConglomerado;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public Altura getAltura() {
        return altura;
    }

    public void setAltura(Altura altura) {
        this.altura = altura;
    }

    public void setCodigoConglomerado(String codigoConglomerado) {
        this.codigoConglomerado = codigoConglomerado;
    }

    public String getCodigoConglomerado() {
        return codigoConglomerado;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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
