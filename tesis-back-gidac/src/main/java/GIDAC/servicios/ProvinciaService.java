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
public interface ProvinciaService<T>{
    public T guardar(T objeto);
    public T buscarPorId(String id);
    public T buscarPorPais(String codigo);
    public List<T> buscarTodos();
    public void eliminar(String id);
    
}
