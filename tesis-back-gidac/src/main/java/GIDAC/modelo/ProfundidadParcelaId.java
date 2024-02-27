/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import java.util.Objects;

public class ProfundidadParcelaId implements Serializable {
   
    private Integer idProfundidad;
    private Integer idParcela;
    
    public ProfundidadParcelaId() {
        // Constructor sin argumentos
    }

    public ProfundidadParcelaId(Integer idProfundidad, Integer idParcela) {
        this.idProfundidad = idProfundidad;
        this.idParcela = idParcela;
    }

    public Integer getIdProfundidad() {
        return idProfundidad;
    }

    public void setIdProfundidad(Integer idProfundidad) {
        this.idProfundidad = idProfundidad;
    }


    public Integer getIdParcela() {
        return idParcela;
    }


    public void setIdParcela(Integer idParcela) {
        this.idParcela = idParcela;
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfundidadParcelaId that = (ProfundidadParcelaId) o;
        return Objects.equals(idParcela, that.idParcela) &&
                Objects.equals(idProfundidad, that.idProfundidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParcela, idProfundidad);
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