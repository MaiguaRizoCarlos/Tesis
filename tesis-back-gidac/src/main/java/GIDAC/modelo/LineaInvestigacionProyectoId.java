/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import java.util.Objects;

public class LineaInvestigacionProyectoId implements Serializable {
    private Integer idLineaInvestigacion;
    private Integer idProyecto;

    public LineaInvestigacionProyectoId() {
        // Constructor sin argumentos
    }

    public LineaInvestigacionProyectoId(Integer idLineaInvestigacion, Integer idProyecto) {
        this.idLineaInvestigacion = idLineaInvestigacion;
        this.idProyecto = idProyecto;
    }

    public Integer getIdLineaInvestigacion() {
        return idLineaInvestigacion;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdLineaInvestigacion(Integer idLineaInvestigacion) {
        this.idLineaInvestigacion = idLineaInvestigacion;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

 

    
    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineaInvestigacionProyectoId that = (LineaInvestigacionProyectoId) o;
        return Objects.equals(idLineaInvestigacion, that.idLineaInvestigacion) &&
                Objects.equals(idProyecto, that.idProyecto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLineaInvestigacion, idProyecto);
    }
}

//public class GrupoInvestigacionId implements Serializable {
//    private ProyectoInvestigacion proyectoInvestigacion;
//    private Usuario usuario;
//
//    public GrupoInvestigacionId() {
//        // Constructor sin argumentos
//    }
//    
//    // Constructor
//    public GrupoInvestigacionId(ProyectoInvestigacion proyectoInvestigacion, Usuario usuario) {
//        this.proyectoInvestigacion = proyectoInvestigacion;
//        this.usuario = usuario;
//    }
//
//    // Getters y Setters
//    public ProyectoInvestigacion getProyectoInvestigacion() {
//        return proyectoInvestigacion;
//    }
//
//    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
//        this.proyectoInvestigacion = proyectoInvestigacion;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    // Equals y HashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        GrupoInvestigacionId that = (GrupoInvestigacionId) o;
//        return Objects.equals(proyectoInvestigacion, that.proyectoInvestigacion) &&
//                Objects.equals(usuario, that.usuario);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(proyectoInvestigacion, usuario);
//    }
//}