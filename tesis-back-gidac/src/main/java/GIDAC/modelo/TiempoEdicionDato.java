package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "tiempo_edidcion_dato")
@Entity
public class TiempoEdicionDato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTiempoEdicionDato;
    private Double tiempo;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties("tiempoEdidcionDato")
    private Usuario usuario;

    public TiempoEdicionDato() {
    }

    public Integer getIdTiempoEdicionDato() {
        return idTiempoEdicionDato;
    }

    public void setIdTiempoEdicionDato(Integer idTiempoEdicionDato) {
        this.idTiempoEdicionDato = idTiempoEdicionDato;
    }

    public Double getTiempo() {
        return tiempo;
    }

    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
