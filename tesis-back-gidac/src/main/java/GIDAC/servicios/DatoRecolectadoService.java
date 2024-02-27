/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.DatoRecolectado;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface DatoRecolectadoService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public List<T> buscarPorVigenciaAndVigenciaDatasetAndIdParcela(Boolean vigenciaAux ,Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaDataset(Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaProfundidadParcela(Boolean vigencia, Integer idProfunididad, Integer idParcela);
    public List<T> buscarPorVigenciaProyecto(Boolean vigencia, Integer id);
//    public List<T> buscarPorVigenciaVariable(Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaVariableUnidadMedida(Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaVariableUnidadMedidaAndProyecto(Boolean vigencia, Integer id, Integer idProyecto);
    public List<T> buscarPorVigenciaVariableUnidadMedidaAndCodigoDatasetAndProyecto(Boolean vigencia, Integer id, Integer codigoDataset, Integer idProyecto);
    public List<T> buscarPorEditable();
    
    
    public List<Object[]> listarTodosLosDatosNumerico();
    public List<Object[]> listarTodosLosDatosNominal();
    
    public List<Object[]> listarTodosLosDatosProyectoNumerico(Integer idProyecto);
    public List<Object[]> listarTodosLosDatosProyectoNominal(Integer idProyecto);
    
    public List<Object[]> listarTodosLosDatosProyectoNumericoDataset(Integer idProyecto, Integer codigoDataset);
    public List<Object[]> listarTodosLosDatosProyectoNominalDataset(Integer idProyecto, Integer codigoDataset);
    
    public List<Object[]> listarTodosLosDatosNumericoVariable(Integer idVariable);
    public List<Object[]> listarTodosLosDatosNominalVariable(Integer idVariable);
    
    public List<Object[]> listarTodosLosDatosProyectoNumericoVariable(Integer idProyecto, Integer idVariable);
    public List<Object[]> listarTodosLosDatosProyectoNominalVariable(Integer idProyecto, Integer idVariable);
    
    public List<Object[]> listarTodosLosDatosProyectoNumericoVariableDataset(Integer idProyecto, Integer idVariable, Integer codigoDataset);
    public List<Object[]> listarTodosLosDatosProyectoNominalVariableDataset(Integer idProyecto, Integer idVariable, Integer codigoDataset);
    
    
    public List<Object[]> listarTodosLosDatosVariableNumerico(Integer idVariable);
    public List<Object[]> listarTodosLosDatosProyectoVariableNumerico(Integer idProyecto, Integer idVariable);
    
    
    public List<Object[]> listarTodosLosDatosVariableNominal(Integer idVariable);
    public List<Object[]> listarTodosLosDatosProyectoVariableNominal(Integer idProyecto, Integer idVariable);
    public List<Object[]> obtenerAlturasMasUsuadas();
    public List<Object[]> obtenerUnidadesDeMedidaMasUsuadas();
    public List<Object[]> obteneProfundidadesMasUsuadas();
    public List<Object[]> obteneValorPromedioVariablesNumericas();
    public List<Object[]> obteneCantidadDatosPorVariable();
    public List<Object[]> obtenerCantidadProyectosPorAreaInvestigacion();
    public List<Object[]> obtenerCantidadProyectosPorSectorImpacto();
    public List<Object[]> obtenerCantidadProyectosPorLineaInvestigacion();
    public List<Object[]> obtenerCantidadProyectosPorTipoProyecto();
    public List<Object[]> obtenerCantidadProyectosPorTipoInvestigacion();
    public List<Object[]> obtenerCantidadProyectosPorEstadoProyecto();
    public List<Object[]> obtenerCantidadProyectosPorDirector();
    
    public List<Object[]> obtenerCantidadProyectosPorAreaInvestigacionNulos();
    public List<Object[]> obtenerCantidadProyectosPorSectorImpactoNulos();
    public List<Object[]> obtenerCantidadProyectosPorLineaInvestigacionNulos();
    public List<Object[]> obtenerCantidadProyectosPorTipoProyectoNulos();
    public List<Object[]> obtenerCantidadProyectosPorTipoInvestigacionNulos();
    public List<Object[]> obtenerCantidadProyectosPorEstadoProyectoNulos();
    public List<Object[]> obtenerCantidadProyectosPorDirectorNulos();
    
    
    public BigInteger obtenerCantidadProyectosPorEstadoPublico(Integer idUsuario);
    public BigInteger obtenerCantidadProyectosPorEstadoPrivado(Integer idUsuario);
    public BigInteger obtenerCantidadProyectosVigentes(Integer idUsuario);
    public BigInteger obtenerCantidadProyectosEliminados(Integer idUsuario);
    public BigInteger obtenerCantidadProyectosPorEstadoPublicoAdminDatos();
    public BigInteger obtenerCantidadProyectosPorEstadoPrivadoAdminDatos();
    public BigInteger obtenerCantidadProyectosVigentesAdminDatos();
    public BigInteger obtenerCantidadProyectosEliminadosAdminDatos();
    public BigInteger obtenerCantidadSolicitudesDescarga(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesDescargaSolicitado(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesDescargaAprobadas(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesDescargaRechazadas(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizar(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizarSolicitado(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizarAceptado(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesRechazado(Integer idUsuario);
    
    public List<Object[]> obtenerGraficaSolicitudesPorAnio(Integer idUsuario);
    public List<Object[]> obtenerGraficaSolicitudesPorMes(Integer idUsuario);
    public List<Object[]> obtenerGraficaSolicitudesPorDia(Integer idUsuario);
    
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorAnio(Integer idUsuario);
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorMes(Integer idUsuario);
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorDia(Integer idUsuario);
    
    public List<Object[]> obtenerGraficaAccesoPorAnio();
    public List<Object[]> obtenerGraficaAccesoPorMes();
    public List<Object[]> obtenerGraficaAccesoPorDia();
    
    public List<Object[]> obtenerGraficaUsuariosPorRol();
    
    
    public BigInteger obtenerCantidadSolicitudesActualizarInvestigador(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizarAcesptadoInvestigador(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizarRechazadoInvestigador(Integer idUsuario);
    
    public List<DatoRecolectado> findByDatasetProfundidadParcelaParcelaConglomeradoAlturaAlturaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaUnidadMedidaAbreviaturaAndDatasetProfundidadParcelaParcelaConglomeradoCodigoConglomeradoAndDatasetProfundidadParcelaParcelaConglomeradoVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoProyectoInvestigacionIdProyectoAndDatasetProfundidadParcelaParcelaCodigoParcelaAndDatasetProfundidadParcelaParcelaVigenciaAndDatasetProfundidadParcelaProfundidadProfundidadMinimaAndDatasetProfundidadParcelaProfundidadProfundidadMaximaAndDatasetProfundidadParcelaProfundidadVigenciaAndDatasetProfundidadParcelaProfundidadUnidadMedidaAbreviaturaAndDatasetFechaSalidaCampoAndVariableUnidadMedidaIdVariableUnidadMedidaAndValorAndVigencia(
            Double altura, 
            Boolean vigenciaAltura,
            String abreAltrua,
            String codigoConglomerado,
            Boolean vigenciaConglo,
            Integer idProyConglomerado,
            String CodigoParcela,
            Boolean vigenciaParcela,
            Double profundidadMinima, 
            Double profundidadMaxima,
            Boolean vigenciaProf,
            String abreProfundidad,
            Date fechaSalidaCampo,
            Integer idVUM,
            String valor,
            Boolean vigencia);
    
    public List<Object[]> obtenerDatoRepetido( Double altura,
             String abreAltrua,
             String codigoConglomerado,
             Integer idProyConglomerado,
             String codigoParcela,
             Double profundidadMinima, 
             Double profundidadMaxima,
             String abreProfundidad,
             Date fechaSalidaCampo,
             Integer idVUM,
             String valor);
    
}

