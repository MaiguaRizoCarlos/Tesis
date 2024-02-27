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
public interface LocalizacionProyectoService<T>{
    public T guardar(T objeto);
    public List<T> buscarTodos();
    public List<T> buscarPorProyecto(Integer id);
    public T buscarPorProyectoAndLocalizacion(Integer idProyecto, Integer idLocalizacion);
    public T buscarPorEliminadoProyectoAndLocalizacion(Integer idProyecto, Integer idLocalizacion);
    public void eliminar(Integer idProyecto, Integer idLocalizacion);
    
    
}

