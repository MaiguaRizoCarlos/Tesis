package GIDAC.modelo;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "visitante")
public class Visitantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String  ip;
    private Date fecha;

    

    public Integer getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

 

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Visitantes() {
    }
}
