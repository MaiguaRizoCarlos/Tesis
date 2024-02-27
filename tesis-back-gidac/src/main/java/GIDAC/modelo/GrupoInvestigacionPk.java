/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class GrupoInvestigacionPk{
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto_investigacion")
    private ProyectoInvestigacion proyectoInvestigacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public GrupoInvestigacionPk() {
        // Constructor sin argumentos
    }
    
    // Constructor
    public GrupoInvestigacionPk(ProyectoInvestigacion proyectoInvestigacion, Usuario usuario) {
        this.proyectoInvestigacion = proyectoInvestigacion;
        this.usuario = usuario;
    }

    // Getters y Setters
    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}