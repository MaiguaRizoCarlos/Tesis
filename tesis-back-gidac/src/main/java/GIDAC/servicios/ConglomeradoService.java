/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.Conglomerado;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface ConglomeradoService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public T buscarPorCodigoConglomeradoProyectoInvestigacionAltura(String codigoConglomerado, Integer idProyecto, Integer idAltura);
    public T buscarPorCodigoConglomeradoProyectoInvestigacion(String codigoConglomerado, Integer idProyecto);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public List<T> buscarPorProyectoInvestigacion(Integer id);
    List<Object[]> obtenerConglomeradosUsados(Integer id);
}

