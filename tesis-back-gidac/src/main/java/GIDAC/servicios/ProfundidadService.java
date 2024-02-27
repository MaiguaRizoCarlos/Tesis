/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.Profundidad;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface ProfundidadService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public T buscarPorProfundidadMinimaProfundidadMaximaAbreviatura(Double profundidadMinima, Double profundidadMaxima, String abreviatura);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public List<T> buscarPorMedida(Integer id);
    List<Object[]> obtenerProfundidadesUsadas();
    public List<T> buscarPorVigencia(Boolean vigencia);
}
