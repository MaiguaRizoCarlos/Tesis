/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;

import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface GrupoInvestigacionService <T>{
    public List<T> findAll();
    public T save(T objeto);
    public boolean verificarVigenciaInvestigadorEnProyectoInvestigacion(Integer idUsuario, Integer idProyectoInvestigacion);
    public T obtenerUsuarioConProyectoInvestigacion(Integer idUsuario, Integer idProyectoInvestigacion);
    public List<T> obtenerInvestigadoresEnProyectosInvestigacion(Integer id);
    public List<T> obtenerInvestigacionesInvestigador(Integer id);
    public List<T> obtenerInvestigacionesDeInvestigadorVigentes(Integer id);
    public List<T> obtenerInvestigacionesVigentesDirector(Integer id);
    public List<T> obtenerDirectorEnProyectosInvestigacion(Integer id);
    public List<T> obtenerInvestigacionesEliminadosDirector(Integer id);
    List<Object[]> obtenerProyectosDeDirector( Integer idUsuario);
    
    
}
