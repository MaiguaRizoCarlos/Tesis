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
public interface VariableFamiliaService<T>{
    public T guardar(T objeto);
    public T buscarPorId(String idVariable, Integer idFamilia, Boolean vigencia);
    public List<T> buscarPorVariable(Integer idVariable, Boolean vigencia);
    public T buscarPorVariableFamilia(Integer idVariable, Integer idFamilia, Boolean vigencia);
    public T buscarPorVariableFamilia(Integer idVariable, Integer idFamilia);
    public List<T> buscarTodos(Boolean vigencia);
    public void eliminar(Integer idVariable, Integer idFamilia);
    public List<Object[]> listarFamiliasDifusion();
}

