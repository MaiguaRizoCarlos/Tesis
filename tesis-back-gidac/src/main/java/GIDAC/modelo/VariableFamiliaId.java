/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import java.util.Objects;

public class VariableFamiliaId implements Serializable {
    
    
    private Integer idVariable;
    private Integer idFamilia;

    public VariableFamiliaId() {
        
    }

    public VariableFamiliaId(Integer idVariable, Integer idFamilia) {
        this.idVariable = idVariable;
        this.idFamilia = idFamilia;
    }

    
    public Integer getIdVariable() {
        return idVariable;
    }


    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }
    
    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableFamiliaId that = (VariableFamiliaId) o;
        return Objects.equals(idVariable, that.idVariable) &&
                Objects.equals(idFamilia, that.idFamilia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVariable, idFamilia);
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