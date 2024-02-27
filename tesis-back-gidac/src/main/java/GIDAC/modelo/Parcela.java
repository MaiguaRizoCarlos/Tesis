package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "parcela")
@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParcela;
    
    private String codigoParcela;
    private String nombreParcela;
    private String coordenadaX;
    private String coordenadaY;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
    //private String Altitud;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_conglomerado")
    private Conglomerado conglomerado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_area")
    private Area area;
    
    @OneToMany(mappedBy = "parcela",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProfundidadParcela> profunidadParcela = new HashSet<>();
    
    
    private boolean editable=true;
    
    public boolean isEditable() {
        return editable;
    }
    
     public void setEditable(boolean editable) {
        this.editable = editable;
    }
    

    public void setIdParcela(Integer idParcela) {
        this.idParcela = idParcela;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public void setConglomerado(Conglomerado conglomerado) {
        this.conglomerado = conglomerado;
    }

    public void setProfunidadParcela(Set<ProfundidadParcela> profunidadParcela) {
        this.profunidadParcela = profunidadParcela;
    }

    public Integer getIdParcela() {
        return idParcela;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public Conglomerado getConglomerado() {
        return conglomerado;
    }

    public Set<ProfundidadParcela> getProfunidadParcela() {
        return profunidadParcela;
    }

    public void setCodigoParcela(String codigoParcela) {
        this.codigoParcela = codigoParcela;
    }

    public void setNombreParcela(String nombreParcela) {
        this.nombreParcela = nombreParcela;
    }


    public String getCodigoParcela() {
        return codigoParcela;
    }

    public String getNombreParcela() {
        return nombreParcela;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
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
