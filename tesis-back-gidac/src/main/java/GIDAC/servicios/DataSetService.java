/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.Dataset;
import java.util.Date;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface DataSetService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T> buscarPorParcelaProfundidad(Integer idParcela, Integer idProfundidad);
    public List<T> buscarPorCodigoDatasetParcelaProfundidad(Integer codigoDataset, Integer idParcela, Integer idProfundidad);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public List<T> buscarPorProyecto(Integer id);
    public List<T> buscarPorParcela(Integer id);
    List<Object[]> obtenerDataSetUsados(Integer id);
    List<Object[]> obtenerDatasets(Integer id);
    List<Object[]> obtenerDatasetsAsc(Integer id);
    List<T> findByCodigoDatasetAndProyectoInvestigacionIdProyecto(Integer codigoDataset,Integer idProyecto);
    List<T> findByFechaSalidaCampo(Date fecha);
    
    
}
