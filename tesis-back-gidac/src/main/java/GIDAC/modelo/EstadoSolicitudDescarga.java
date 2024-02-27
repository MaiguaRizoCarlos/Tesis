package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "estado_solicitud_descarga")
@Entity
public class EstadoSolicitudDescarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstadoDescarga;

    private String nombreEstadoDescarga;

    
    @OneToMany(mappedBy = "estadoSolicitudDescarga",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SolicitudDescarga> solicitudDescargas = new HashSet<>();

    public Integer getIdEstadoDescarga() {
        return idEstadoDescarga;
    }

    public String getNombreEstadoDescarga() {
        return nombreEstadoDescarga;
    }

    public void setIdEstadoDescarga(Integer idEstadoDescarga) {
        this.idEstadoDescarga = idEstadoDescarga;
    }

    public void setNombreEstadoDescarga(String nombreEstadoDescarga) {
        this.nombreEstadoDescarga = nombreEstadoDescarga;
    }

    public Set<SolicitudDescarga> getSolicitudDescargas() {
        return solicitudDescargas;
    }

    public void setSolicitudDescargas(Set<SolicitudDescarga> solicitudDescargas) {
        this.solicitudDescargas = solicitudDescargas;
    }
}
