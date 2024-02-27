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
public interface ValorPermitidoService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T> buscarTodos();
    public List<T> obtenerPorVariableUnidadMedida(Integer id);
    public T obtenerPorVariableAndUnidadMedida(Integer idVariable, Integer idMedida);
    public T obtenerPorMaxMinPerVariableUnidadMedida(Float max, Float min, String per, Integer idVariable, Integer idMedida);
    public T obtenerPorPerVariableUnidadMedida(String per, Integer idVariable, Integer idMedida);
    public List<T> listarPorVariable(Integer id);
    public List<T> listarPorVigenciaVariableUnidadMedida(Boolean vigencia, Integer idVariable, Integer idUnidadMedida);
    public void eliminar(Integer id);
}
