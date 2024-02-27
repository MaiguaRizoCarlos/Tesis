package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "respuesta_solicitud_actualizar")
@Entity
public class RespuestaSolicitudActualizar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRespuestaActualizar;

    private String respuesta;
    private Date fechaRespuesta;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_actualizar")
    private SolicitudActualizarDato solicitudActualizarDato;
    
    public RespuestaSolicitudActualizar() {
    }

    public Integer getIdRespuestaActualizar() {
        return idRespuestaActualizar;
    }

    public void setIdRespuestaActualizar(Integer idRespuestaActualizar) {
        this.idRespuestaActualizar = idRespuestaActualizar;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public SolicitudActualizarDato getSolicitudActualizarDato() {
        return solicitudActualizarDato;
    }

    public void setSolicitudActualizarDato(SolicitudActualizarDato solicitudActualizarDato) {
        this.solicitudActualizarDato = solicitudActualizarDato;
    }

    
}
