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
public interface AreaService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public T buscarPorAreaAbreviatura(float aarea, String abreviatura);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    List<Object[]> obtenerAreasUsadas();
    public List<T> buscarPorVigencia(Boolean vigencia);
    
}
