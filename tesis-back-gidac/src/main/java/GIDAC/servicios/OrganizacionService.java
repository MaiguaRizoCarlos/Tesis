/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.CatalogoOrganizacion;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface OrganizacionService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T>  buscarPorVigencia(Boolean vigencia);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public T findByVigenciaAndIdOrganizacion(Boolean vigencia, Integer idOrganizacion);
}
