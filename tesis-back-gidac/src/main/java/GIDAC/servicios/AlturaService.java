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
public interface AlturaService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    //public T buscarPorAlturaMinimaAlturaMaximaAbreviatura(float alturaMinima, float alturaMaxima, String abreviatura);
    public List<T> buscarPorVigenciaAlturaAbreviatura(Boolean vigencia, Double altura, String abreviatura);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    List<Object[]> obtenerAlturasUsadas();
    public List<T> buscarPorVigencia(Boolean vigencia);
    
}
