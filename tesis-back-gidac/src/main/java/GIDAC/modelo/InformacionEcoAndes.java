package GIDAC.modelo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "informacion_ecoandes")
public class InformacionEcoAndes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInformacionEcoAndes;
    
    
    private String  descripcion;
    private String  condicionesUso;
    private String  licenciaUso;
    private String  derechoReservado;
    private Date fechaCreacion;
    private boolean vigencia=true;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties("informacionEcoandes")
    private Usuario usuario;

    public Integer getIdInformacionEcoAndes() {
        return idInformacionEcoAndes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCondicionesUso() {
        return condicionesUso;
    }

    public String getLicenciaUso() {
        return licenciaUso;
    }


    public boolean isVigencia() {
        return vigencia;
    }

    public void setIdInformacionEcoAndes(Integer idInformacionEcoAndes) {
        this.idInformacionEcoAndes = idInformacionEcoAndes;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCondicionesUso(String condicionesUso) {
        this.condicionesUso = condicionesUso;
    }

    public void setLicenciaUso(String licenciaUso) {
        this.licenciaUso = licenciaUso;
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

    public String getDerechoReservado() {
        return derechoReservado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setDerechoReservado(String derechoReservado) {
        this.derechoReservado = derechoReservado;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public InformacionEcoAndes() {
    }
}
