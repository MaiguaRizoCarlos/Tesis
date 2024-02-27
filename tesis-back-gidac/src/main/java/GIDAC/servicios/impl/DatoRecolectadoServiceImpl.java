
package GIDAC.servicios.impl;


import GIDAC.modelo.Dataset;
import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.repositorios.DatoRecolectadoRepository;
import GIDAC.servicios.DatoRecolectadoService;
import java.math.BigInteger;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DatoRecolectadoServiceImpl implements DatoRecolectadoService {

    @Autowired
    private DatoRecolectadoRepository repository;

    @Override
    public DatoRecolectado guardar(Object objeto) {
        DatoRecolectado oA=(DatoRecolectado) objeto;
        return repository.save(oA);
    }

    @Override
    public DatoRecolectado buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
    
    @Override
    public List buscarPorVigenciaDataset(Boolean vigencia,Integer id) {
        Dataset dataset=new Dataset();
        dataset.setIdDataset(id);
        return repository.findByVigenciaAndDataset(vigencia, dataset);
    }
    
    @Override
    public List buscarPorVigenciaProfundidadParcela(Boolean vigencia,Integer idProfunididad, Integer idParcela) {
        return repository.findByVigenciaAndDatasetProfundidadParcelaProfundidadIdProfundidadAndDatasetProfundidadParcelaParcelaIdParcela(vigencia,idProfunididad, idParcela);
    }
    
    @Override
    public List buscarPorVigenciaProyecto(Boolean vigencia, Integer id) {
        return repository.findByVigenciaAndDatasetProyectoInvestigacionIdProyecto(vigencia, id);
    }
    
//    @Override
//    public List buscarPorVigenciaVariable(Boolean vigencia, Integer id) {
//        Variable variable=new Variable();
//        variable.setIdVariable(id);
//        return repository.findByVigenciaAndVariable(vigencia,variable);
//    }
//    
    @Override
    public List buscarPorVigenciaVariableUnidadMedida(Boolean vigencia, Integer id) {
        VariableUnidadMedida variable=new VariableUnidadMedida();
        variable.setIdVariableUnidadMedida(id);
        return repository.findByVigenciaAndVariableUnidadMedida(vigencia,variable);
    }
    
    @Override
    public List buscarPorVigenciaVariableUnidadMedidaAndProyecto(Boolean vigencia, Integer id, Integer idProyecto) {
        VariableUnidadMedida variable=new VariableUnidadMedida();
        variable.setIdVariableUnidadMedida(id);
        return repository.findByVigenciaAndVariableUnidadMedidaAndDatasetProyectoInvestigacionIdProyecto(vigencia,variable, idProyecto);
    }
    
    public List buscarPorVigenciaVariableUnidadMedidaAndCodigoDatasetAndProyecto(Boolean vigencia, Integer id, Integer codigoDataset, Integer idProyecto) {
        VariableUnidadMedida variable=new VariableUnidadMedida();
        variable.setIdVariableUnidadMedida(id);
        return repository.findByVigenciaAndVariableUnidadMedidaAndDatasetCodigoDatasetAndDatasetProyectoInvestigacionIdProyecto(vigencia,variable, codigoDataset, idProyecto);
    }

    @Override
    public List buscarPorEditable() {
        return repository.findByEditable(true);
    }

    
    //        variables
    @Override
    public List<Object[]> listarTodosLosDatosNumerico() {
        return repository.obtenerPromedioValoresNumerico();
    }
    
    @Override
    public List<Object[]> listarTodosLosDatosNominal() {
        return repository.obtenerPromedioValoresNominal();
    }

    @Override
    public List<Object[]> listarTodosLosDatosProyectoNumerico(Integer idProyecto) {
        return repository.obtenerPromedioValoresProyectoNumerico(idProyecto);
    }
    
    @Override
    public List<Object[]> listarTodosLosDatosProyectoNominal(Integer idProyecto) {
        return repository.obtenerPromedioValoresProyectoNominal(idProyecto);
    }
    
    
    @Override
    public List<Object[]> listarTodosLosDatosNumericoVariable(Integer idVariable) {
        return repository.obtenerPromedioValoresNumericoVariable(idVariable);
    }
    
    @Override
    public List<Object[]> listarTodosLosDatosNominalVariable(Integer idVariable) {
        return repository.obtenerPromedioValoresNominalVariable(idVariable);
    }

    @Override
    public List<Object[]> listarTodosLosDatosProyectoNumericoVariable(Integer idProyecto, Integer idVariable) {
        return repository.obtenerPromedioValoresProyectoNumericoVariable(idProyecto,idVariable);
    }
    
    @Override
    public List<Object[]> listarTodosLosDatosProyectoNominalVariable(Integer idProyecto, Integer idVariable) {
        return repository.obtenerPromedioValoresProyectoNominalVariable(idProyecto,idVariable);
    }

    //-------------------------------------------------
    
    @Override
    public List listarTodosLosDatosVariableNumerico(Integer idVariable) {
        return repository.obtenerPromedioValoresCatalogoNumerico(idVariable);
    }
    
    @Override
    public List listarTodosLosDatosProyectoVariableNumerico(Integer idProyecto, Integer idVariable) {
        return repository.obtenerPromedioValoresProyectoVariableNumerico(idProyecto, idVariable);
    }
    
    

    @Override
    public List listarTodosLosDatosVariableNominal(Integer idVariable) {
        return repository.obtenerPromedioValoresCatalogoNominal(idVariable);
    }
    
    @Override
    public List listarTodosLosDatosProyectoVariableNominal(Integer idProyecto, Integer idVariable) {
        return repository.obtenerPromedioValoresProyectoVariableNominal(idProyecto, idVariable);
    }

    //investigador
    @Override
    public List<Object[]> obtenerAlturasMasUsuadas() {
        return repository.obtenerAlturasMasUsuadas();
    }

    @Override
    public List<Object[]> obtenerUnidadesDeMedidaMasUsuadas() {
        return repository.obtenerUnidadesDeMedidaMasUsuadas();
    }

    @Override
    public List<Object[]> obteneProfundidadesMasUsuadas() {
        return repository.obteneProfundidadesMasUsuadas();
    }

    @Override
    public List<Object[]> obteneValorPromedioVariablesNumericas() {
        return repository.obteneValorPromedioVariablesNumericas();
    }

    @Override
    public List<Object[]> obteneCantidadDatosPorVariable() {
        return repository.obteneCantidadDatosPorVariable();
    }

    
    //administrador
    @Override
    public List<Object[]> obtenerCantidadProyectosPorAreaInvestigacion() {
        return repository.obtenerCantidadProyectosPorAreaInvestigacion();
    }

    @Override
    public List<Object[]> obtenerCantidadProyectosPorSectorImpacto() {
        return repository.obtenerCantidadProyectosPorSectorImpacto();
    }

    @Override
    public List<Object[]> obtenerCantidadProyectosPorLineaInvestigacion() {
        return repository.obtenerCantidadProyectosPorLineaInvestigacion();
    }

    @Override
    public List<Object[]> obtenerCantidadProyectosPorTipoProyecto() {
        return repository.obtenerCantidadProyectosPorTipoProyecto();
    }

    @Override
    public List<Object[]> obtenerCantidadProyectosPorTipoInvestigacion() {
        return repository.obtenerCantidadProyectosPorTipoInvestigacion();
    }

    @Override
    public List<Object[]> obtenerCantidadProyectosPorEstadoProyecto() {
        return repository.obtenerCantidadProyectosPorEstadoProyecto();
    }

    @Override
    public List<Object[]> obtenerCantidadProyectosPorDirector() {
        return repository.obtenerCantidadProyectosPorDirector();
    }
    
    //nulos
    
    @Override
    public List<Object[]> obtenerCantidadProyectosPorAreaInvestigacionNulos() {
        return repository.obtenerCantidadProyectosPorAreaInvestigacionNulos();
    }

    @Override
    public List<Object[]> obtenerCantidadProyectosPorSectorImpactoNulos() {
        return repository.obtenerCantidadProyectosPorSectorImpactoNulos();
    }

    @Override
    public List obtenerCantidadProyectosPorLineaInvestigacionNulos() {
        return repository.obtenerCantidadProyectosPorLineaInvestigacionNulos();
    }

    @Override
    public List obtenerCantidadProyectosPorTipoProyectoNulos() {
        return repository.obtenerCantidadProyectosPorTipoProyectoNulos();
    }

    @Override
    public List obtenerCantidadProyectosPorTipoInvestigacionNulos() {
        return repository.obtenerCantidadProyectosPorTipoInvestigacionNulos();
    }

    @Override
    public List obtenerCantidadProyectosPorEstadoProyectoNulos() {
        return repository.obtenerCantidadProyectosPorEstadoProyectoNulos();
    }

    @Override
    public List obtenerCantidadProyectosPorDirectorNulos() {
        return repository.obtenerCantidadProyectosPorDirectorNulos();
    }

    
    //director

    @Override
    public BigInteger obtenerCantidadProyectosPorEstadoPublico(Integer idUsuario) {
        return repository.obtenerCantidadProyectosPorEstadoPublico(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadProyectosPorEstadoPrivado(Integer idUsuario) {
        return repository.obtenerCantidadProyectosPorEstadoPrivado(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadProyectosVigentes(Integer idUsuario) {
        return repository.obtenerCantidadProyectosVigentes(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadProyectosEliminados(Integer idUsuario) {
        return repository.obtenerCantidadProyectosEliminados(idUsuario);
    }
    
    //Admin datos
    
    @Override
    public BigInteger obtenerCantidadProyectosPorEstadoPublicoAdminDatos() {
        return repository.obtenerCantidadProyectosPorEstadoPublicoAdminDatos();
    }

    @Override
    public BigInteger obtenerCantidadProyectosPorEstadoPrivadoAdminDatos() {
        return repository.obtenerCantidadProyectosPorEstadoPrivadoAdminDatos();
    }

    @Override
    public BigInteger obtenerCantidadProyectosVigentesAdminDatos() {
        return repository.obtenerCantidadProyectosVigentesAdminDatos();
    }

    @Override
    public BigInteger obtenerCantidadProyectosEliminadosAdminDatos() {
        return repository.obtenerCantidadProyectosEliminadosAdminDatos();
    }

    @Override
    public BigInteger obtenerCantidadSolicitudesDescarga(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesDescarga(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadSolicitudesDescargaSolicitado(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesDescargaSolicitada(idUsuario);
    }
    
    @Override
    public BigInteger obtenerCantidadSolicitudesDescargaAprobadas(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesDescargaAprobadas(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadSolicitudesDescargaRechazadas(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesDescargaRechazadas(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadSolicitudesActualizar(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesActualizar(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadSolicitudesActualizarSolicitado(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesActualizarSolicitadas(idUsuario);
    }
    
    @Override
    public BigInteger obtenerCantidadSolicitudesActualizarAceptado(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesActualizarAceptado(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadSolicitudesRechazado(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesRechazado(idUsuario);
    }

    @Override
    public List<Object[]> obtenerGraficaSolicitudesPorAnio(Integer idUsuario) {
        return repository.obtenerGraficaSolicitudesPorAnio(idUsuario);
    }
    
    @Override
    public List<Object[]> obtenerGraficaSolicitudesPorMes(Integer idUsuario) {
        return repository.obtenerGraficaSolicitudesPorMes(idUsuario);
    }
    
    @Override
    public List<Object[]> obtenerGraficaSolicitudesPorDia(Integer idUsuario) {
        return repository.obtenerGraficaSolicitudesPorDia(idUsuario);
    }

    @Override
    public List<Object[]>  obtenerGraficaSolicitudesActualizarPorAnio(Integer idUsuario) {
        return repository.obtenerGraficaSolicitudesActualizarPorAnio(idUsuario);
    }

    @Override
    public List<Object[]>  obtenerGraficaSolicitudesActualizarPorMes(Integer idUsuario) {
        return repository.obtenerGraficaSolicitudesActualizarPorMes(idUsuario);
    }

    @Override
    public List<Object[]>  obtenerGraficaSolicitudesActualizarPorDia(Integer idUsuario) {
        return repository.obtenerGraficaSolicitudesActualizarPorDia(idUsuario);
    }
    
    @Override
    public List<Object[]>  obtenerGraficaAccesoPorAnio() {
        return repository.obtenerGraficaAccesoPorAnio();
    }
    
    @Override
    public List<Object[]>  obtenerGraficaAccesoPorMes() {
        return repository.obtenerGraficaAccesoPorMes();
    }
    
    @Override
    public List<Object[]>  obtenerGraficaAccesoPorDia() {
        return repository.obtenerGraficaAccesoPorDia();
    }
    
    
    @Override
    public List<Object[]>  obtenerGraficaUsuariosPorRol() {
        return repository.obtenerGraficaUsuariosPorRol();
    }
    
    @Override
    public BigInteger  obtenerCantidadSolicitudesActualizarInvestigador(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesActualizarInvestigador(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadSolicitudesActualizarAcesptadoInvestigador(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesActualizarAcesptadoInvestigador(idUsuario);
    }

    @Override
    public BigInteger obtenerCantidadSolicitudesActualizarRechazadoInvestigador(Integer idUsuario) {
        return repository.obtenerCantidadSolicitudesActualizarRechazadoInvestigador(idUsuario);
    }

    @Override
    public List<DatoRecolectado> buscarPorVigenciaAndVigenciaDatasetAndIdParcela(Boolean vigenciaAux, Boolean vigencia, Integer id) {
        return repository.findByVigenciaAndDatasetVigenciaAndDatasetProfundidadParcelaIdParcela(vigenciaAux, vigencia, id);
    }

    @Override
    public List<Object[]>  listarTodosLosDatosProyectoNumericoDataset(Integer idProyecto, Integer codigoDataset) {
        return repository.obtenerPromedioValoresProyectoNumericoDataset(idProyecto, codigoDataset);
    }

    @Override
    public List<Object[]>  listarTodosLosDatosProyectoNominalDataset(Integer idProyecto, Integer codigoDataset) {
        return repository.obtenerPromedioValoresProyectoNominalDataset(idProyecto, codigoDataset);
    }

    @Override
    public List<Object[]>  listarTodosLosDatosProyectoNumericoVariableDataset(Integer idProyecto, Integer idVariable, Integer codigoDataset) {
        return repository.obtenerPromedioValoresProyectoNumericoVariableDataset(idProyecto, idVariable, codigoDataset);
    }

    @Override
    public List<Object[]>  listarTodosLosDatosProyectoNominalVariableDataset(Integer idProyecto, Integer idVariable, Integer codigoDataset) {
        return repository.obtenerPromedioValoresProyectoNominalVariableDataset(idProyecto, idVariable, codigoDataset);
    }

  

    
    @Override
    public List<Object[]> obtenerDatoRepetido(Double altura, String abreAltrua, String codigoConglomerado, Integer idProyConglomerado, String codigoParcela, Double profundidadMinima, Double profundidadMaxima, String abreProfundidad, Date fechaSalidaCampo, Integer idVUM, String valor) {
        return repository.obtenerDatoRepetido( altura,
             abreAltrua,
             codigoConglomerado,
             idProyConglomerado,
             codigoParcela,
             profundidadMinima, 
             profundidadMaxima,
             abreProfundidad,
             fechaSalidaCampo,
             idVUM,
             valor);
    }

    @Override
    public List findByDatasetProfundidadParcelaParcelaConglomeradoAlturaAlturaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaUnidadMedidaAbreviaturaAndDatasetProfundidadParcelaParcelaConglomeradoCodigoConglomeradoAndDatasetProfundidadParcelaParcelaConglomeradoVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoProyectoInvestigacionIdProyectoAndDatasetProfundidadParcelaParcelaCodigoParcelaAndDatasetProfundidadParcelaParcelaVigenciaAndDatasetProfundidadParcelaProfundidadProfundidadMinimaAndDatasetProfundidadParcelaProfundidadProfundidadMaximaAndDatasetProfundidadParcelaProfundidadVigenciaAndDatasetProfundidadParcelaProfundidadUnidadMedidaAbreviaturaAndDatasetFechaSalidaCampoAndVariableUnidadMedidaIdVariableUnidadMedidaAndValorAndVigencia(Double altura, Boolean vigenciaAltura, String abreAltrua, String codigoConglomerado, Boolean vigenciaConglo, Integer idProyConglomerado, String CodigoParcela, Boolean vigenciaParcela, Double profundidadMinima, Double profundidadMaxima, Boolean vigenciaProf, String abreProfundidad, Date fechaSalidaCampo, Integer idVUM, String valor, Boolean vigencia) {
        return repository.findByDatasetProfundidadParcelaParcelaConglomeradoAlturaAlturaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaUnidadMedidaAbreviaturaAndDatasetProfundidadParcelaParcelaConglomeradoCodigoConglomeradoAndDatasetProfundidadParcelaParcelaConglomeradoVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoProyectoInvestigacionIdProyectoAndDatasetProfundidadParcelaParcelaCodigoParcelaAndDatasetProfundidadParcelaParcelaVigenciaAndDatasetProfundidadParcelaProfundidadProfundidadMinimaAndDatasetProfundidadParcelaProfundidadProfundidadMaximaAndDatasetProfundidadParcelaProfundidadVigenciaAndDatasetProfundidadParcelaProfundidadUnidadMedidaAbreviaturaAndDatasetFechaSalidaCampoAndVariableUnidadMedidaIdVariableUnidadMedidaAndValorAndVigencia(altura,
             vigenciaAltura,
             abreAltrua,
             codigoConglomerado,
             vigenciaConglo,
             idProyConglomerado,
             CodigoParcela,
             vigenciaParcela,
             profundidadMinima, 
             profundidadMaxima,
             vigenciaProf,
             abreProfundidad,
             fechaSalidaCampo,
             idVUM,
             valor,
             vigencia);
    }

   
    

}
