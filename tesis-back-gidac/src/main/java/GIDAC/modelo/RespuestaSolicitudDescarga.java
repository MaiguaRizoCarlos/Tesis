package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "respuesta_solicitud_descarga")
@Entity
public class RespuestaSolicitudDescarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRespuestaDescarga;

    private String respuesta;
    private Date fechaRespuesta;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_descarga")
    private SolicitudDescarga solicitudDescarga;
    
    public RespuestaSolicitudDescarga() {
    }

    public Integer getIdRespuestaDescarga() {
        return idRespuestaDescarga;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setIdRespuestaDescarga(Integer idRespuestaDescarga) {
        this.idRespuestaDescarga = idRespuestaDescarga;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public SolicitudDescarga getSolicitudDescarga() {
        return solicitudDescarga;
    }

    public void setSolicitudDescarga(SolicitudDescarga solicitudDescarga) {
        this.solicitudDescarga = solicitudDescarga;
    }
    
    
    
   
    
}
