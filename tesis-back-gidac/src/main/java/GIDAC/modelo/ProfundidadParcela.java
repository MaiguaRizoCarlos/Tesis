package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "profundidad_parcela")
@IdClass(ProfundidadParcelaId.class)
public class ProfundidadParcela {
    
    @Id
    @Column(name = "id_profundidad")
    private Integer idProfundidad;
    
    @Id
    @Column(name = "id_parcela")
    private Integer idParcela;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profundidad", insertable = false, updatable = false)
    private Profundidad profundidad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parcela", insertable = false, updatable = false)
    private Parcela parcela;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
    private boolean editable=true;

    public Integer getIdProfundidad() {
        return idProfundidad;
    }

    public void setIdProfundidad(Integer idProfundidad) {
        this.idProfundidad = idProfundidad;
    }

    public void setIdParcela(Integer idParcela) {
        this.idParcela = idParcela;
    }

    public void setProfundidad(Profundidad profundidad) {
        this.profundidad = profundidad;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public Integer getIdParcela() {
        return idParcela;
    }

    public Profundidad getProfundidad() {
        return profundidad;
    }

    public Parcela getParcela() {
        return parcela;
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

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
    
}
