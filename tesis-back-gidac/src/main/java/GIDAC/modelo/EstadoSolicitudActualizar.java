package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "estado_solicitud_actualizar")
@Entity
public class EstadoSolicitudActualizar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstadoSolicitud;

    private String nombreEstadoSolicitud;

    
    @OneToMany(mappedBy = "estadoSolicitudActualizar",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SolicitudActualizarDato> solicitudActualizarDato = new HashSet<>();

    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public String getNombreEstadoSolicitud() {
        return nombreEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public void setNombreEstadoSolicitud(String nombreEstadoSolicitud) {
        this.nombreEstadoSolicitud = nombreEstadoSolicitud;
    }

    public Set<SolicitudActualizarDato> getSolicitudActualizarDato() {
        return solicitudActualizarDato;
    }

    public void setSolicitudActualizarDato(Set<SolicitudActualizarDato> solicitudActualizarDato) {
        this.solicitudActualizarDato = solicitudActualizarDato;
    }

    
    
    
    
    

}
