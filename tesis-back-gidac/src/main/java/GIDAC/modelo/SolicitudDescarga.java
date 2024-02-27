package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "solicitud_descarga")
public class SolicitudDescarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitudDescarga;

    private String nombre;
    private String apellido;
    private String email;
    private String institucion;
    private String motivo;
    private Date fechaEnvioSolicitud;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto")
    private ProyectoInvestigacion proyectoInvestigacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_descarga")
    private EstadoSolicitudDescarga estadoSolicitudDescarga;
    
    @OneToMany(mappedBy = "solicitudDescarga",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RespuestaSolicitudDescarga> respuestaSolicitudDescarga = new HashSet<>();

    public void setIdSolicitudDescarga(Integer idSolicitudDescarga) {
        this.idSolicitudDescarga = idSolicitudDescarga;
    }

    public Integer getIdSolicitudDescarga() {
        return idSolicitudDescarga;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    public String getInstitucion() {
        return institucion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoSolicitudDescarga getEstadoSolicitudDescarga() {
        return estadoSolicitudDescarga;
    }

    public void setEstadoSolicitudDescarga(EstadoSolicitudDescarga estadoSolicitudDescarga) {
        this.estadoSolicitudDescarga = estadoSolicitudDescarga;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public Set<RespuestaSolicitudDescarga> getRespuestaSolicitudDescarga() {
        return respuestaSolicitudDescarga;
    }

    public void setRespuestaSolicitudDescarga(Set<RespuestaSolicitudDescarga> respuestaSolicitudDescarga) {
        this.respuestaSolicitudDescarga = respuestaSolicitudDescarga;
    }

    public Date getFechaEnvioSolicitud() {
        return fechaEnvioSolicitud;
    }

    public void setFechaEnvioSolicitud(Date fechaEnvioSolicitud) {
        this.fechaEnvioSolicitud = fechaEnvioSolicitud;
    }
    
    
    public SolicitudDescarga(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
