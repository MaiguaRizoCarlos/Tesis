package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "localizacion")
@Entity
public class Localizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocalizacion;
    
    
    
    @OneToMany(mappedBy = "localizacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<LocalizacionProyecto> localizacionProyecto = new HashSet<>();
    
   

    private String codigoPais;
    private String nombrePais;
    private String codigoParroquia;
    private String nombreParroquia;
    private String codigoCanton;
    private String nombreCanton;
    private String codigoProvincia;
    private String nombreProvincia;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    
    private boolean vigencia=true;

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getCodigoParroquia() {
        return codigoParroquia;
    }

    public String getNombreParroquia() {
        return nombreParroquia;
    }

    public String getCodigoCanton() {
        return codigoCanton;
    }

    public String getNombreCanton() {
        return nombreCanton;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    


    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public void setCodigoParroquia(String codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    public void setNombreParroquia(String nombreParroquia) {
        this.nombreParroquia = nombreParroquia;
    }

    public void setCodigoCanton(String codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public void setNombreCanton(String nombreCanton) {
        this.nombreCanton = nombreCanton;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public Set<LocalizacionProyecto> getLocalizacionProyecto() {
        return localizacionProyecto;
    }

    public void setLocalizacionProyecto(Set<LocalizacionProyecto> localizacionProyecto) {
        this.localizacionProyecto = localizacionProyecto;
    }


    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    
   
    
}
