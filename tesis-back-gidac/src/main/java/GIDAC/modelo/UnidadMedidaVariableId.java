/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import java.util.Objects;

public class UnidadMedidaVariableId implements Serializable {
    
    
    private String idVariable;
    private Integer idUnidadMedida;

    public UnidadMedidaVariableId() {
        // Constructor sin argumentos
    }

    public UnidadMedidaVariableId(String idVariable, Integer idUnidadMedida) {
        this.idVariable = idVariable;
        this.idUnidadMedida = idUnidadMedida;
    }

    
    public String getIdVariable() {
        return idVariable;
    }

    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    
    

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadMedidaVariableId that = (UnidadMedidaVariableId) o;
        return Objects.equals(idVariable, that.idVariable) &&
                Objects.equals(idUnidadMedida, that.idUnidadMedida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVariable, idUnidadMedida);
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