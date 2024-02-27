/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.Variable;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface VariableService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T> buscarTodos();
    public List<Object[]> litsarVairbalesCompletas();
    public List<Object[]> litsarVairbalesCompletasInvestigador();
    public List<Object[]> litsarVairbalesConDatosSinFamilia();
    public List<Object[]> litsarVairbalesConDatosConFiltroFamilia(Integer idFamilia);
    public List<Object[]> litsarVairbalesConDatosSinFamiliaOrganizacion(Integer idOrganizacion);
    public List<Object[]> litsarVairbalesConDatosConFiltroFamiliaOrganizacion(Integer idFamilia, Integer idOrganizacion);
    public List<Object[]> litsarVairbalesConDatosSinFamiliaInvestigador(Integer idProyecto);
    public List<Object[]> litsarVairbalesConDatosConFiltroFamiliaInvestigador(Integer idFamilia, Integer idProyecto);
    public List<Object[]> litsarVairbalesIncompletas();
    public void eliminar(Integer id);
    public void activar(Integer id);
    public List<Object[]> listarCatalogoParaPerfilado();
    public List<Object[]> listarCatalogoParaPerfiladoPorProyecto(Integer id);
    public List<Object[]> listarCatalogoParaPerfiladoPorProyectoCodigoDataset(Integer id, Integer codigoDataset);
    public List<Object[]> listarCatalogoParaPerfiladoPorProyectoOganizacion(Integer id, Integer idOrganizacion);
    public List<Object[]> listarCatalogoParaPerfiladoPorProyectoOganizacionCodigoDataset(Integer id, Integer idOrganizacion, Integer codigoDataset);
    public List<T> buscarPorVigencia(Boolean vigencia);
    public List<T> buscarPorVigenciaAndCodigoVariable(Boolean vigencia, String codigoVariable);
    public T findByVigenciaAndIdVariableAndCodigoVariable(Boolean vigencia, Integer idVariable,String codigoVariable);
    public byte[] dowloadPdf();
    public byte[] dowloadExcel();
}