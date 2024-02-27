package GIDAC.repositorios;

import GIDAC.modelo.Dataset;
import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableUnidadMedida;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DatoRecolectadoRepository extends JpaRepository<DatoRecolectado,Integer> {
    List<DatoRecolectado> findByVigenciaAndDatasetVigenciaAndDatasetProfundidadParcelaIdParcela(Boolean vigenciaAux ,Boolean vigencia, Integer idParcela);
    List<DatoRecolectado> findByVigenciaAndDataset(Boolean vigencia,Dataset dataset);
    List<DatoRecolectado> findByVigenciaAndDatasetProfundidadParcelaProfundidadIdProfundidadAndDatasetProfundidadParcelaParcelaIdParcela(Boolean vigencia,Integer idProfundidad, Integer idParcela);
    //List<DatoRecolectado> findByVigenciaAndVariable(Boolean vigencia, Variable variable);
    
    List<DatoRecolectado> findByDatasetProfundidadParcelaParcelaConglomeradoAlturaAlturaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaUnidadMedidaAbreviaturaAndDatasetProfundidadParcelaParcelaConglomeradoCodigoConglomeradoAndDatasetProfundidadParcelaParcelaConglomeradoVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoProyectoInvestigacionIdProyectoAndDatasetProfundidadParcelaParcelaCodigoParcelaAndDatasetProfundidadParcelaParcelaVigenciaAndDatasetProfundidadParcelaProfundidadProfundidadMinimaAndDatasetProfundidadParcelaProfundidadProfundidadMaximaAndDatasetProfundidadParcelaProfundidadVigenciaAndDatasetProfundidadParcelaProfundidadUnidadMedidaAbreviaturaAndDatasetFechaSalidaCampoAndVariableUnidadMedidaIdVariableUnidadMedidaAndValorAndVigencia(
            Double altura, 
            Boolean vigenciaAltura,
            String abreAltrua,
            String codigoConglomerado,
            Boolean vigenciaConglo,
            Integer idProyConglomerado,
            String CodigoParcela,
            Boolean vigenciaPar,
            Double profundidadMinima, 
            Double profundidadMaxima,
            Boolean vigenciaProf,
            String abreProfundidad,
            Date fechaSalidaCampo,
            Integer idVUM,
            String valor,
            Boolean vigencia);
    
    List<DatoRecolectado> findByDatasetProfundidadParcelaParcelaConglomeradoAlturaIdAlturaAndDatasetProfundidadParcelaParcelaConglomeradoCodigoConglomeradoAndDatasetProfundidadParcelaParcelaConglomeradoProyectoInvestigacionIdProyectoAndDatasetProfundidadParcelaParcelaCodigoParcelaAndDatasetProfundidadParcelaProfundidadProfundidadMinimaAndDatasetProfundidadParcelaProfundidadProfundidadMaximaAndDatasetProfundidadParcelaProfundidadUnidadMedidaAbreviaturaAndDatasetFechaSalidaCampoAndVariableUnidadMedidaIdVariableUnidadMedidaAndValorAndVigencia(Integer idAltura, String codigoConglomerado,Integer idProyConglomerado,String codigoParcela,Double profundidadMinima, Double profundidadMaxima,String abreProfundidad,Date fechaSalidaCampo,Integer idVUM,String valor,Boolean vigencia);
            
    
    List<DatoRecolectado> findByVigenciaAndVariableUnidadMedida(Boolean vigencia, VariableUnidadMedida variableUnidadMedida);
    List<DatoRecolectado> findByVigenciaAndVariableUnidadMedidaAndDatasetProyectoInvestigacionIdProyecto(Boolean vigencia, VariableUnidadMedida variableUnidadMedida, Integer idProyecto);
    List<DatoRecolectado> findByVigenciaAndVariableUnidadMedidaAndDatasetCodigoDatasetAndDatasetProyectoInvestigacionIdProyecto(Boolean vigencia, VariableUnidadMedida variableUnidadMedida, Integer codigoDataset, Integer idProyecto);
    List<DatoRecolectado> findByVigenciaAndDatasetProyectoInvestigacionIdProyecto(Boolean vigencia, Integer idProyecto);
    List<DatoRecolectado> findByEditable(Boolean editable);
    
//    @Query(value="SELECT p.coordenadaX, p.coordenadaY,   pr.profundidad_maxima, pr.profundidad_minima,m.abreviatura, v.nombre_variable, AVG(d.valor) AS promedioValor" +
//            " FROM parcela p" +
//            " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
//            " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
//            " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
//            " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
//            " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto"+
//            " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto"+
//            " JOIN variable v" +
//            " JOIN dato_recolectado d ON (d.id_dataset = ds.id_dataset AND d.id_variable=v.id_variable)" +
//            " WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2" +
//            " GROUP BY p.coordenadaX, p.coordenadaY,  pr.profundidad_maxima,pr.profundidad_minima, m.abreviatura, v.nombre_variable"+
//            " ORDER BY 3 ASC",
//            nativeQuery=true)
//    List<Object[]> obtenerPromedioValores();
    
    @Query(value="SELECT dr.id_dato_recolectado, dr.vigencia" +
            "    FROM unidad_medida uma JOIN altura a ON a.id_unidad_medida = uma.id_unidad_medida" +
            "	 JOIN conglomerado c ON c.id_altura = a.id_altura" +
            "	 JOIN parcela p ON p.id_conglomerado = c.id_conglomerado" +
            "	 JOIN profundidad_parcela pp ON pp.id_parcela = p.id_parcela" +
            "	 JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "	 JOIN unidad_medida ump ON ump.id_unidad_medida = pr.id_unidad_medida" +
            "	 JOIN dataset ds ON (ds.id_parcela = pp.id_parcela and ds.id_profundidad=pp.id_profundidad)" +
            "    JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "	 JOIN dato_recolectado dr ON dr.id_dataset = ds.id_dataset" +
            "	 join variable_unidad_medida vum ON vum.id_variable_unidad_medida = dr.id_variable_unidad_medida"+
            "    WHERE a.altura=:altura AND uma.abreviatura=':abreAltrua' AND c.codigo_conglomerado=':codigoConglomerado' AND pi.id_proyecto=:idProyConglomerado AND p.codigo_parcela=':codigoParcela' AND pr.profundidad_minima=:profundidadMinima AND pr.profundidad_maxima=:profundidadMaxima AND ump.abreviatura=':abreProfundidad' AND ds.fecha_salida_campo=:fechaSalidaCampo AND vum.id_variable_unidad_medida=:idVUM AND dr.valor=':valor'",
            nativeQuery=true)
    List<Object[]> obtenerDatoRepetido(@Param("altura") Double altura, 
            @Param("abreAltrua") String abreAltrua,
            @Param("codigoConglomerado") String codigoConglomerado,
            @Param("idProyConglomerado") Integer idProyConglomerado,
            @Param("codigoParcela") String codigoParcela,
            @Param("profundidadMinima") Double profundidadMinima, 
            @Param("profundidadMaxima") Double profundidadMaxima,
            @Param("abreProfundidad") String abreProfundidad,
            @Param("fechaSalidaCampo") Date fechaSalidaCampo,
            @Param("idVUM") Integer idVUM,
            @Param("valor") String valor);
    
//    @Query(value="SELECT dr.id_dato_recolectado, dr.vigencia " +
//            "FROM unidad_medida uma JOIN altura a ON a.id_unidad_medida = uma.id_unidad_medida " +
//            "JOIN conglomerado c ON c.id_altura = a.id_altura " +
//            "JOIN parcela p ON p.id_conglomerado = c.id_conglomerado " +
//            "JOIN profundidad_parcela pp ON pp.id_parcela = p.id_parcela " +
//            "JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad " +
//            "JOIN unidad_medida ump ON ump.id_unidad_medida = pr.id_unidad_medida " +
//            "JOIN dataset ds ON (ds.id_parcela = pp.id_parcela and ds.id_profundidad = pp.id_profundidad) " +
//            "JOIN proyecto_investigacion pi ON pi.id_proyecto = ds.id_proyecto " +
//            "JOIN dato_recolectado dr ON dr.id_dataset = ds.id_dataset " +
//            "JOIN variable_unidad_medida vum ON vum.id_variable_unidad_medida = dr.id_variable_unidad_medida " +
//            "WHERE a.altura_minima = :alturaMinima AND a.altura_maxima = :alturaMaxima " +
//            "AND uma.abreviatura = :abreAltrua AND c.codigo_conglomerado = :codigoConglomerado " +
//            "AND pi.id_proyecto = :idProyConglomerado AND p.codigo_parcela = :codigoParcela " +
//            "AND pr.profundidad_minima = :profundidadMinima AND pr.profundidad_maxima = :profundidadMaxima " +
//            "AND ump.abreviatura = :abreProfundidad AND ds.fecha_salida_campo = :fechaSalidaCampo " +
//            "AND vum.id_variable_unidad_medida = :idVUM AND dr.valor = :valor",
//            nativeQuery=true)
//    List<Object[]> obtenerDatoRepetido(@Param("alturaMinima") float alturaMinima, 
//            @Param("alturaMaxima") float alturaMaxima,
//            @Param("abreAltrua") String abreAltrua,
//            @Param("codigoConglomerado") String codigoConglomerado,
//            @Param("idProyConglomerado") Integer idProyConglomerado,
//            @Param("codigoParcela") String codigoParcela,
//            @Param("profundidadMinima") Double profundidadMinima, 
//            @Param("profundidadMaxima") Double profundidadMaxima,
//            @Param("abreProfundidad") String abreProfundidad,
//            @Param("fechaSalidaCampo") Date fechaSalidaCampo,
//            @Param("idVUM") Integer idVUM,
//            @Param("valor") String valor);
    
    //    variables 
     @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, um.abreviatura as abreviaturaVariable, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable, AVG(CAST(d.valor AS DOUBLE PRECISION)) AS promedioValor" +
            "            FROM parcela p" +
            "             JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            "             JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "             JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            "             JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            "             JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "             JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN unidad_medida um ON um.id_unidad_medida=vum.id_unidad_medida"+
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            "             WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND v.id_tipo_variable=1 AND v.vigencia=true" +
            "             GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima,pr.profundidad_minima, um.abreviatura, m.abreviatura, v.nombre_variable" +
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresNumerico();
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable,  d.valor AS promedioValor" +
                " FROM parcela p" +
                " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
                " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
                " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
                " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
                " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
                " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            
                " WHERE v.id_tipo_variable=2 AND d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND v.vigencia=true" +
                " GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, d.valor" +
                " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresNominal();
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, um.abreviatura as abreviaturaVariable, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable, AVG(CAST(d.valor AS DOUBLE PRECISION)) AS promedioValor" +
            "            FROM parcela p" +
            "             JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            "             JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "             JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            "             JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            "             JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "             JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN unidad_medida um ON um.id_unidad_medida=vum.id_unidad_medida"+
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            "             WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_tipo_variable=1 AND v.vigencia=true" +
            "             GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima,pr.profundidad_minima,um.abreviatura, m.abreviatura, v.nombre_variable" +
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoNumerico(@Param("idProy") Integer idProy);
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, um.abreviatura as abreviaturaVariable, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable, AVG(CAST(d.valor AS DOUBLE PRECISION)) AS promedioValor" +
            "            FROM parcela p" +
            "             JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            "             JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "             JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            "             JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            "             JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "             JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN unidad_medida um ON um.id_unidad_medida=vum.id_unidad_medida"+
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            "             WHERE d.vigencia=true AND pi.vigencia=true AND ds.codigo_dataset=:codigoDataset AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_tipo_variable=1 AND v.vigencia=true" +
            "             GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima,pr.profundidad_minima,um.abreviatura, m.abreviatura, v.nombre_variable" +
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoNumericoDataset(@Param("idProy") Integer idProy, @Param("codigoDataset") Integer codigoDataset);
                   
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable,  d.valor AS promedioValor" +
                " FROM parcela p" +
                " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
                " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
                " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
                " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
                " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
                " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
                "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            
                " WHERE v.id_tipo_variable=2 AND d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.vigencia=true" +
                " GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, d.valor" +
                " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoNominal(@Param("idProy") Integer idProy);
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable,  d.valor AS promedioValor" +
                " FROM parcela p" +
                " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
                " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
                " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
                " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
                " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
                " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
                "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            
                " WHERE v.id_tipo_variable=2 AND d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.vigencia=true AND ds.codigo_dataset=:codigoDataset" +
                " GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, d.valor" +
                " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoNominalDataset(@Param("idProy") Integer idProy, @Param("codigoDataset") Integer codigoDataset);
    
    
    
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, um.abreviatura as abreviaturaVariable, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable, AVG(CAST(d.valor AS DOUBLE PRECISION)) AS promedioValor" +
            "            FROM parcela p" +
            "             JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            "             JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "             JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            "             JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            "             JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "             JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN unidad_medida um ON um.id_unidad_medida=vum.id_unidad_medida"+
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            "             WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND v.id_tipo_variable=1 AND v.id_variable=:idVariable AND v.vigencia=true" +
            "             GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima,pr.profundidad_minima, um.abreviatura, m.abreviatura, v.nombre_variable" +
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresNumericoVariable(@Param("idVariable") Integer idVariable);
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable,  d.valor AS promedioValor" +
                " FROM parcela p" +
                " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
                " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
                " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
                " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
                " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
                " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
                "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            
                " WHERE v.id_tipo_variable=2 AND d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND v.id_variable=:idVariable AND v.vigencia=true" +
                " GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, d.valor" +
                " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresNominalVariable(@Param("idVariable") Integer idVariable);
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, um.abreviatura as abreviaturaVariable, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable, AVG(CAST(d.valor AS DOUBLE PRECISION)) AS promedioValor" +
            "            FROM parcela p" +
            "             JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            "             JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "             JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            "             JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            "             JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "             JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN unidad_medida um ON um.id_unidad_medida=vum.id_unidad_medida"+
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            "             WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_tipo_variable=1 AND v.id_variable=:idVariable AND v.vigencia=true" +
            "             GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima,pr.profundidad_minima,um.abreviatura, m.abreviatura, v.nombre_variable" +
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoNumericoVariable(@Param("idProy") Integer idProy, @Param("idVariable") Integer idVariable);
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, um.abreviatura as abreviaturaVariable, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable, AVG(CAST(d.valor AS DOUBLE PRECISION)) AS promedioValor" +
            "            FROM parcela p" +
            "             JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            "             JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "             JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            "             JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            "             JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "             JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN unidad_medida um ON um.id_unidad_medida=vum.id_unidad_medida"+
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            "             WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_tipo_variable=1 AND v.id_variable=:idVariable AND v.vigencia=true AND ds.codigo_dataset=:codigoDataset" +
            "             GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima,pr.profundidad_minima,um.abreviatura, m.abreviatura, v.nombre_variable" +
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoNumericoVariableDataset(@Param("idProy") Integer idProy, @Param("idVariable") Integer idVariable, @Param("codigoDataset") Integer codigoDataset);
                   
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable,  d.valor AS promedioValor" +
                " FROM parcela p" +
                " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
                " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
                " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
                " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
                " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
                " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
                "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            
                " WHERE v.id_tipo_variable=2 AND d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_variable=:idVariable AND v.vigencia=true" +
                " GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, d.valor" +
                " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoNominalVariable(@Param("idProy") Integer idProy, @Param("idVariable") Integer idVariable);
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable,  d.valor AS promedioValor" +
                " FROM parcela p" +
                " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
                " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
                " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
                " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
                " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
                " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
                "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            
                " WHERE v.id_tipo_variable=2 AND d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_variable=:idVariable AND v.vigencia=true AND ds.codigo_dataset=:codigoDataset" +
                " GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, d.valor" +
                " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoNominalVariableDataset(@Param("idProy") Integer idProy, @Param("idVariable") Integer idVariable, @Param("codigoDataset") Integer codigoDataset);
    
    //-------------------------------------------------------------------------------------
    
//     @Query(value="SELECT p.coordenadaX, p.coordenadaY, pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, AVG(d.valor) AS promedioValor" +
//            " FROM parcela p" +
//            " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
//            " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
//            " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
//            " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
//            " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto"+
//            " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto"+
//            " JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset" +
//            " JOIN variable v on v.id_variable= d.id_variable"+
//            " WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND v.id_variable = :idVar" +
//            " GROUP BY p.coordenadaX, p.coordenadaY, pr.profundidad_maxima,pr.profundidad_minima, m.abreviatura, v.nombre_variable"+
//             " ORDER BY 3",
//            nativeQuery=true)
//    List<Object[]> obtenerPromedioValoresCatalogo(@Param("idVar") String idvariable);
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, um.abreviatura as abreviaturaVariable, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable, AVG(CAST(d.valor AS DOUBLE PRECISION)) AS promedioValor" +
            "            FROM parcela p" +
            "             JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            "             JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "             JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            "             JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            "             JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "             JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN unidad_medida um ON um.id_unidad_medida=vum.id_unidad_medida"+
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            "             WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND v.id_variable = :idVar AND v.id_tipo_variable=1 AND v.vigencia=true" +
            "             GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima,pr.profundidad_minima,um.abreviatura, m.abreviatura, v.nombre_variable" +
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresCatalogoNumerico(@Param("idVar") Integer idvariable);
    
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable,  d.valor AS promedioValor" +
                " FROM parcela p" +
                " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
                " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
                " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
                " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
                " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
                " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
                "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            
                " WHERE v.id_tipo_variable=2 AND d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND v.id_variable = :idVar AND v.vigencia=true" +
                " GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, d.valor" +
                " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresCatalogoNominal(@Param("idVar") Integer idvariable);
    
//    @Query(value="SELECT p.coordenadaX, p.coordenadaY, pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, AVG(d.valor) AS promedioValor" +
//            " FROM parcela p" +
//            " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
//            " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
//            " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
//            " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
//            " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto"+
//            " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto"+
//            " JOIN variable v" +
//            " JOIN dato_recolectado d ON (d.id_dataset = ds.id_dataset AND d.id_variable=v.id_variable)" +
//            " WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_variable = :idVar" +
//            " GROUP BY p.coordenadaX, p.coordenadaY, pr.profundidad_maxima,pr.profundidad_minima, m.abreviatura, v.nombre_variable"+
//            " ORDER BY 3",
//            nativeQuery=true)
//    List<Object[]> obtenerPromedioValoresProyectoVariable(@Param("idProy") Integer idProy, @Param("idVar") String idVariable);
    
    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, um.abreviatura as abreviaturaVariable, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable, AVG(CAST(d.valor AS DOUBLE PRECISION)) AS promedioValor" +
            "            FROM parcela p" +
            "             JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            "             JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            "             JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            "             JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            "             JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
            "             JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
            "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN unidad_medida um ON um.id_unidad_medida=vum.id_unidad_medida"+
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            "             WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_variable = :idVar AND v.id_tipo_variable=1 AND v.vigencia=true" +
            "             GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima,pr.profundidad_minima,um.abreviatura, m.abreviatura, v.nombre_variable" +
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoVariableNumerico(@Param("idProy") Integer idProy, @Param("idVar") Integer idVariable);

    @Query(value="SELECT p.coordenadax as coordenadaX, p.coordenaday as coordenadaY, pr.profundidad_maxima as profundidad_maxima, pr.profundidad_minima as profundidad_minima, m.abreviatura as abreviatura, v.nombre_variable as nombre_variable,  d.valor AS promedioValor" +
                " FROM parcela p" +
                " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
                " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
                " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
                " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
                " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto" +
                " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto" +
                "             JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset"+
            "             JOIN variable_unidad_medida vum on d.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "             JOIN variable v ON v.id_variable=vum.id_variable"+
            
                " WHERE v.id_tipo_variable=2 AND d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy AND v.id_variable = :idVar AND v.vigencia=true" +
                " GROUP BY p.coordenadax, p.coordenaday,  pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, d.valor" +
                " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyectoVariableNominal(@Param("idProy") Integer idProy, @Param("idVar") Integer idVariable);

    //---------------------------------------------------------------------------------------------
    //DASH INVESTIGADOR
    
     @Query(value="SELECT a.altura, um.abreviatura, COUNT(a.id_altura)" +
                    "   FROM altura a JOIN unidad_medida um on um.id_unidad_medida=a.id_unidad_medida" +
                    "   JOIN conglomerado c on c.id_altura=a.id_altura" +
                    "   WHERE um.vigencia=true" +
                    "   GROUP BY a.altura, um.abreviatura" +
                    "   ORDER BY 3 DESC" +
                    "   LIMIT 5",
            nativeQuery=true)
    List<Object[]> obtenerAlturasMasUsuadas();
    
    
    
    @Query(value="SELECT um.unidad_medida, um.abreviatura, COUNT(um.id_unidad_medida)" +
                    "   FROM dato_recolectado dr JOIN variable_unidad_medida vum on vum.id_variable_unidad_medida=dr.id_variable_unidad_medida" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "   JOIN variable v ON v.id_variable=vum.id_variable"+
                    "   WHERE um.vigencia=true AND v.id_tipo_variable=1" +
                    "   GROUP BY um.unidad_medida, um.abreviatura" +
                    "   ORDER BY 3 DESC" +
                    "   LIMIT 5",
            nativeQuery=true)
    List<Object[]> obtenerUnidadesDeMedidaMasUsuadas();
    
    
    
    @Query(value="SELECT p.profundidad_minima,p.profundidad_maxima, um.abreviatura, COUNT(dt.id_dataset)" +
                "   FROM dataset dt JOIN profundidad_parcela pp on (pp.id_parcela=dt.id_parcela AND pp.id_profundidad=dt.id_profundidad)" +
                "	JOIN profundidad p on p.id_profundidad=pp.id_profundidad" +
                "	JOIN unidad_medida um on um.id_unidad_medida=p.id_unidad_medida" +
                "	JOIN parcela pa on pa.id_parcela=pp.id_parcela" +
                "   WHERE um.vigencia=true" +
                "   GROUP BY p.profundidad_minima,p.profundidad_maxima, um.abreviatura" +
                "   ORDER BY 4 DESC" +
                "   LIMIT 5",
            nativeQuery=true)
    List<Object[]> obteneProfundidadesMasUsuadas();
    
    @Query(value="SELECT v.nombre_variable,um.abreviatura, AVG(CAST(dr.valor AS DOUBLE PRECISION))" +
                "   FROM dato_recolectado dr JOIN variable_unidad_medida vum on vum.id_variable_unidad_medida=dr.id_variable_unidad_medida" +
                "    JOIN variable v on v.id_variable=vum.id_variable" +
                "   JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida"+
                "   WHERE v.id_tipo_variable=1" +
                "   GROUP BY  v.nombre_variable,  um.abreviatura" +
                "   ORDER BY 2 DESC",
            nativeQuery=true)
    List<Object[]> obteneValorPromedioVariablesNumericas();
    
    @Query(value="SELECT v.nombre_variable, COUNT(dr.id_dato_recolectado)" +
                "   FROM dato_recolectado dr JOIN variable_unidad_medida vum on vum.id_variable_unidad_medida=dr.id_variable_unidad_medida" +
                "    JOIN variable v on v.id_variable=vum.id_variable" +
                "   JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida"+
                "   WHERE v.id_tipo_variable=1" +
                "   GROUP BY  v.nombre_variable,dr.id_variable_unidad_medida" +
                "   ORDER BY 2 DESC",
            nativeQuery=true)
    List<Object[]> obteneCantidadDatosPorVariable();

    
    //---------------------------------------------------------------------------------------------
    //DASH ADMIN
    
    
    @Query(value="SELECT a.nombre_area_investigacion, COUNT(pi.id_proyecto)" +
                "   FROM area_investigacion a JOIN area_investigacion_proyecto ai on ai.id_area_investigacion=a.id_area_investigacion" +
                "	JOIN proyecto_investigacion pi on pi.id_proyecto=ai.id_proyecto" +
                "   WHERE a.vigencia=true AND pi.vigencia=true AND ai.vigencia=true" +
                "   GROUP BY a.nombre_area_investigacion" +
                "   ORDER BY 2 DESC",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorAreaInvestigacion();
    
    @Query(value="SELECT ai.nombre_area_investigacion" +
                "   FROM area_investigacion ai" +
                "   WHERE ai.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorAreaInvestigacionNulos();
    
    
    
    @Query(value="SELECT s.nombre_sector_impacto, COUNT(pi.id_proyecto)" +
                "   FROM sector_impacto s JOIN sector_impacto_proyecto si on si.id_sector_impacto=s.id_sector_impacto" +
                "	JOIN proyecto_investigacion pi on pi.id_proyecto=si.id_proyecto" +
                "   WHERE s.vigencia=true AND pi.vigencia=true" +
                "   GROUP BY s.nombre_sector_impacto" +
                "   ORDER BY 2 DESC",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorSectorImpacto();
    
    @Query(value="SELECT si.nombre_sector_impacto" +
                "   FROM sector_impacto si" +
                "   WHERE si.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorSectorImpactoNulos();
    
    
    
    
    @Query(value="SELECT l.nombre_linea_investigacion, COUNT(pi.id_proyecto)" +
                "   FROM linea_investigacion l JOIN linea_investigacion_proyecto li on li.id_linea_investigacion=l.id_linea_investigacion" +
                "	JOIN proyecto_investigacion pi on pi.id_proyecto=li.id_proyecto" +
                "   WHERE l.vigencia=true AND pi.vigencia=true AND li.vigencia=true" +
                "   GROUP BY l.nombre_linea_investigacion" +
                "   ORDER BY 2 DESC",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorLineaInvestigacion();
    
    @Query(value="SELECT l.nombre_linea_investigacion" +
                "   FROM linea_investigacion l" +
                "   WHERE l.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorLineaInvestigacionNulos();
    
    
    
    @Query(value="SELECT t.nombre_tipo_proyecto, COUNT(pi.id_proyecto)" +
                "   FROM tipo_proyecto t JOIN proyecto_investigacion pi on pi.id_tipo_proyecto=t.id_tipo_proyecto" +
                "   WHERE t.vigencia=true AND pi.vigencia=true" +
                "   GROUP BY t.nombre_tipo_proyecto" +
                "   ORDER BY 2 DESC",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorTipoProyecto();
    
    @Query(value="SELECT t.nombre_tipo_proyecto" +
                "   FROM tipo_proyecto t" +
                "    WHERE t.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorTipoProyectoNulos();
    
    
    
    @Query(value="SELECT t.nombre_tipo_investigacion, COUNT(pi.id_proyecto)" +
                "   FROM tipo_investigacion t JOIN proyecto_investigacion pi on pi.id_tipo_investigacion=t.id_tipo_investigacion" +
                "   WHERE t.vigencia=true AND pi.vigencia=true" +
                "   GROUP BY t.nombre_tipo_investigacion" +
                "   ORDER BY 2 DESC",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorTipoInvestigacion();
    
     @Query(value="SELECT t.nombre_tipo_investigacion" +
                "   FROM tipo_investigacion t" +
                "   WHERE t.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorTipoInvestigacionNulos();
    
    
    
    @Query(value="SELECT e.nombre_estado_proyecto, COUNT(pi.id_proyecto)" +
                "   FROM estado_proyecto_investigacion e JOIN proyecto_investigacion pi on pi.id_estado_proyecto=e.id_estado_proyecto" +
                "   GROUP BY e.nombre_estado_proyecto" +
                "   ORDER BY 2 DESC",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorEstadoProyecto();
    
    @Query(value="SELECT e.nombre_estado_proyecto" +
                "   FROM estado_proyecto_investigacion e",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorEstadoProyectoNulos();
    
    
    
    
    @Query(value="SELECT u.nombre_usuario, u.apellido_usuario, COUNT(pi.id_proyecto)" +
                " FROM usuario u JOIN grupo_investigacion gi on gi.id_usuario=u.id_usuario" +
                "    JOIN proyecto_investigacion pi on pi.id_proyecto = gi.id_proyecto" +
                " WHERE u.id_rol=2 and u.vigencia=true AND pi.vigencia=true AND gi.vigencia=true" +
                " GROUP BY  u.nombre_usuario, u.apellido_usuario" +
                " ORDER BY 3 DESC;",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorDirector();
    
    @Query(value="SELECT u.nombre_usuario, u.apellido_usuario" +
                "   FROM usuario u" +
                "   WHERE u.id_rol=2 and u.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerCantidadProyectosPorDirectorNulos();
    
    
    //Director
    
    @Query(value="SELECT COUNT(pi.id_proyecto)" +
                "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto=epi.id_estado_proyecto" +
                "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
                "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
                "   WHERE pi.vigencia=true AND u.id_usuario = :idUsuario AND pi.id_estado_proyecto=1",
                nativeQuery=true)
    BigInteger obtenerCantidadProyectosPorEstadoPrivado(@Param("idUsuario") Integer idUsuario);
    
    @Query(value="SELECT COUNT(pi.id_proyecto)" +
                "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto=epi.id_estado_proyecto" +
                "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
                "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
                "   WHERE pi.vigencia=true AND u.id_usuario = :idUsuario AND pi.id_estado_proyecto=2",
            nativeQuery=true)
    BigInteger obtenerCantidadProyectosPorEstadoPublico(@Param("idUsuario") Integer idUsuario);
    
    
    @Query(value="SELECT COUNT(pi.id_proyecto)" +
                "   FROM proyecto_investigacion pi JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
                "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
                "   WHERE pi.vigencia=true AND u.id_usuario=:idUsuario",
            nativeQuery=true)
    BigInteger obtenerCantidadProyectosVigentes(@Param("idUsuario") Integer idUsuario);
    
    @Query(value="SELECT COUNT(pi.id_proyecto)" +
                "   FROM proyecto_investigacion pi JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
                "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
                "   WHERE pi.vigencia=false AND u.id_usuario=:idUsuario",
            nativeQuery=true)
    BigInteger obtenerCantidadProyectosEliminados(@Param("idUsuario") Integer idUsuario);
    
    //-------------------------------------------------------------
    //Admin datos
    @Query(value="SELECT COUNT(pi.id_proyecto)" +
                "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto=epi.id_estado_proyecto" +
                "   WHERE pi.vigencia=true AND pi.id_estado_proyecto=1",
                nativeQuery=true)
    BigInteger obtenerCantidadProyectosPorEstadoPrivadoAdminDatos();
    
    @Query(value="SELECT COUNT(pi.id_proyecto)" +
                "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto=epi.id_estado_proyecto" +
                "   WHERE pi.vigencia=true AND pi.id_estado_proyecto=2",
            nativeQuery=true)
    BigInteger obtenerCantidadProyectosPorEstadoPublicoAdminDatos();
    
    
    @Query(value="SELECT COUNT(pi.id_proyecto)" +
                "   FROM proyecto_investigacion pi" +
                "   WHERE pi.vigencia=true",
            nativeQuery=true)
    BigInteger obtenerCantidadProyectosVigentesAdminDatos();
    
    @Query(value="SELECT COUNT(pi.id_proyecto)" +
                "   FROM proyecto_investigacion pi" +
                "   WHERE pi.vigencia=false",
            nativeQuery=true)
    BigInteger obtenerCantidadProyectosEliminadosAdminDatos();
    
    //-------------------------------------------------------------
    
    
    
    
    
    @Query(value="SELECT COUNT(sd.id_solicitud_descarga)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on 	pi.id_estado_proyecto=epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_descarga sd on sd.id_proyecto=pi.id_proyecto" +
            "   WHERE u.id_usuario = :idUsuario",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesDescarga(@Param("idUsuario") Integer idUsuario);
    
    
    @Query(value="SELECT COUNT(sd.id_solicitud_descarga)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on 	pi.id_estado_proyecto=epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_descarga sd on sd.id_proyecto=pi.id_proyecto" +
            "   WHERE u.id_usuario = :idUsuario AND sd.id_estado_descarga=1",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesDescargaSolicitada(@Param("idUsuario") Integer idUsuario);
    
    
    @Query(value="SELECT COUNT(sd.id_solicitud_descarga)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on 	pi.id_estado_proyecto=epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_descarga sd on sd.id_proyecto=pi.id_proyecto" +
            "   WHERE u.id_usuario = :idUsuario AND sd.id_estado_descarga=2",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesDescargaAprobadas(@Param("idUsuario") Integer idUsuario);
    
    
    
    
    @Query(value="SELECT COUNT(sd.id_solicitud_descarga)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on 	pi.id_estado_proyecto=epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_descarga sd on sd.id_proyecto=pi.id_proyecto" +
            "   WHERE u.id_usuario =:idUsuario AND sd.id_estado_descarga=3",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesDescargaRechazadas(@Param("idUsuario") Integer idUsuario);
    
    
    
    
    @Query(value="SELECT COUNT(sa.id_solicitud_actualizar)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto = epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_actualizar_dato sa ON sa.id_proyecto=gi.id_proyecto" +
            "   WHERE u.id_usuario=:idUsuario",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesActualizar(@Param("idUsuario") Integer idUsuario);
    
    
    @Query(value="SELECT COUNT(sa.id_solicitud_actualizar)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto = epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_actualizar_dato sa ON sa.id_proyecto=gi.id_proyecto" +
            "   WHERE u.id_usuario=:idUsuario AND sa.id_estado_solictud_actualizar=1",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesActualizarSolicitadas(@Param("idUsuario") Integer idUsuario);
    
    @Query(value="SELECT COUNT(sa.id_solicitud_actualizar)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto = epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_actualizar_dato sa ON sa.id_proyecto=gi.id_proyecto" +
            "   WHERE u.id_usuario=:idUsuario AND sa.id_estado_solictud_actualizar=2",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesActualizarAceptado(@Param("idUsuario") Integer idUsuario);
    
    
    
    @Query(value="SELECT COUNT(sa.id_solicitud_actualizar)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto = epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_actualizar_dato sa ON sa.id_proyecto=gi.id_proyecto" +
            "   WHERE u.id_usuario=:idUsuario AND sa.id_estado_solictud_actualizar=3",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesRechazado(@Param("idUsuario") Integer idUsuario);
    
    //grafica solictudes por mes
    
    @Query(value="SELECT EXTRACT(YEAR FROM sd.fecha_envio_solicitud) AS anio, "+ 
            " COUNT(sd.id_solicitud_descarga) AS total_registros" +
            "   FROM solicitud_descarga sd JOIN proyecto_investigacion pi ON pi.id_proyecto=sd.id_proyecto" +
            "	JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "    JOIN usuario u ON u.id_usuario=gi.id_usuario" +
            "   WHERE u.id_usuario=:idUsuario" +
            "   GROUP BY EXTRACT(YEAR FROM sd.fecha_envio_solicitud)" +
            "   ORDER BY 1",
            nativeQuery=true)
    List<Object[]> obtenerGraficaSolicitudesPorAnio(@Param("idUsuario") Integer idUsuario);
    
     @Query(value="SELECT EXTRACT(YEAR FROM sd.fecha_envio_solicitud) AS anio, "+ 
             "  EXTRACT(MONTH FROM sd.fecha_envio_solicitud)," +
             "  CASE EXTRACT(MONTH FROM sd.fecha_envio_solicitud)" +
                "        WHEN 1 THEN 'Enero'" +
                "        WHEN 2 THEN 'Febrero'" +
                "        WHEN 3 THEN 'Marzo'" +
                "        WHEN 4 THEN 'Abril'" +
                "        WHEN 5 THEN 'Mayo'" +
                "        WHEN 6 THEN 'Junio'" +
                "        WHEN 7 THEN 'Julio'" +
                "        WHEN 8 THEN 'Agosto'" +
                "        WHEN 9 THEN 'Septiembre'" +
                "        WHEN 10 THEN 'Octubre'" +
                "        WHEN 11 THEN 'Noviembre'" +
                "        WHEN 12 THEN 'Diciembre'" +
                "    END AS mes, " + 
            " COUNT(sd.id_solicitud_descarga) AS total_registros" +
            "   FROM solicitud_descarga sd JOIN proyecto_investigacion pi ON pi.id_proyecto=sd.id_proyecto" +
            "	JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "    JOIN usuario u ON u.id_usuario=gi.id_usuario" +
            "   WHERE u.id_usuario=:idUsuario" +
            "   GROUP BY EXTRACT(YEAR FROM sd.fecha_envio_solicitud), EXTRACT(MONTH FROM sd.fecha_envio_solicitud)" +
            "   ORDER BY 1,2",
            nativeQuery=true)
    List<Object[]> obtenerGraficaSolicitudesPorMes(@Param("idUsuario") Integer idUsuario);
    
    @Query(value="SELECT EXTRACT(YEAR FROM sd.fecha_envio_solicitud) AS anio, "+ 
            "  EXTRACT(MONTH FROM sd.fecha_envio_solicitud)," +
             "  CASE EXTRACT(MONTH FROM sd.fecha_envio_solicitud)" +
                "        WHEN 1 THEN 'Enero'" +
                "        WHEN 2 THEN 'Febrero'" +
                "        WHEN 3 THEN 'Marzo'" +
                "        WHEN 4 THEN 'Abril'" +
                "        WHEN 5 THEN 'Mayo'" +
                "        WHEN 6 THEN 'Junio'" +
                "        WHEN 7 THEN 'Julio'" +
                "        WHEN 8 THEN 'Agosto'" +
                "        WHEN 9 THEN 'Septiembre'" +
                "        WHEN 10 THEN 'Octubre'" +
                "        WHEN 11 THEN 'Noviembre'" +
                "        WHEN 12 THEN 'Diciembre'" +
                "    END AS mes, " + 
            " EXTRACT(DAY FROM sd.fecha_envio_solicitud) AS dia,"+
            " COUNT(sd.id_solicitud_descarga) AS total_registros" +
            "   FROM solicitud_descarga sd JOIN proyecto_investigacion pi ON pi.id_proyecto=sd.id_proyecto" +
            "	JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "    JOIN usuario u ON u.id_usuario=gi.id_usuario" +
            "   WHERE u.id_usuario=:idUsuario" +
            "   GROUP BY EXTRACT(YEAR FROM sd.fecha_envio_solicitud), EXTRACT(MONTH FROM sd.fecha_envio_solicitud), EXTRACT(DAY FROM sd.fecha_envio_solicitud)"+
            "   ORDER BY 1,2, 4",
            nativeQuery=true)
    List<Object[]> obtenerGraficaSolicitudesPorDia(@Param("idUsuario") Integer idUsuario);
    
    
    //solicitud actualizar
    
    @Query(value="SELECT EXTRACT(YEAR FROM sa.fecha_envio_solicitud) AS anio, " +
            "   COUNT(sa.id_solicitud_actualizar) AS total_registros" +
            "   FROM solicitud_actualizar_dato sa JOIN grupo_investigacion gi on gi.id_proyecto=sa.id_proyecto" +
            "   JOIN proyecto_investigacion pi ON pi.id_proyecto=gi.id_proyecto" +
            "   JOIN usuario u ON u.id_usuario=gi.id_usuario" +
            "   WHERE u.id_usuario=:idUsuario" +
            "   GROUP BY EXTRACT(YEAR FROM sa.fecha_envio_solicitud)" +
            "   ORDER BY 1,2",
            nativeQuery=true)
    List<Object[]> obtenerGraficaSolicitudesActualizarPorAnio(@Param("idUsuario") Integer idUsuario);
    
    @Query(value="SELECT EXTRACT(YEAR FROM sa.fecha_envio_solicitud)  AS anio, " +
            "  EXTRACT(MONTH FROM sa.fecha_envio_solicitud)," +
            "  CASE EXTRACT(MONTH FROM sa.fecha_envio_solicitud)" +
                "        WHEN 1 THEN 'Enero'" +
                "        WHEN 2 THEN 'Febrero'" +
                "        WHEN 3 THEN 'Marzo'" +
                "        WHEN 4 THEN 'Abril'" +
                "        WHEN 5 THEN 'Mayo'" +
                "        WHEN 6 THEN 'Junio'" +
                "        WHEN 7 THEN 'Julio'" +
                "        WHEN 8 THEN 'Agosto'" +
                "        WHEN 9 THEN 'Septiembre'" +
                "        WHEN 10 THEN 'Octubre'" +
                "        WHEN 11 THEN 'Noviembre'" +
                "        WHEN 12 THEN 'Diciembre'" +
                "    END AS mes, " + 
            "   COUNT(sa.id_solicitud_actualizar) AS total_registros" +
            "   FROM solicitud_actualizar_dato sa JOIN grupo_investigacion gi on gi.id_proyecto=sa.id_proyecto" +
            "   JOIN proyecto_investigacion pi ON pi.id_proyecto=gi.id_proyecto" +
            "   JOIN usuario u ON u.id_usuario=gi.id_usuario" +
            "   WHERE u.id_usuario=:idUsuario" +
            "   GROUP BY EXTRACT(YEAR FROM sa.fecha_envio_solicitud), EXTRACT(MONTH FROM sa.fecha_envio_solicitud)" +
            "   ORDER BY 1,2",
            nativeQuery=true)
    List<Object[]> obtenerGraficaSolicitudesActualizarPorMes(@Param("idUsuario") Integer idUsuario);
    
    @Query(value="SELECT EXTRACT(YEAR FROM sa.fecha_envio_solicitud) AS anio, " +
            "  EXTRACT(MONTH FROM sa.fecha_envio_solicitud)," +
            "  CASE EXTRACT(MONTH FROM sa.fecha_envio_solicitud)" +
                "        WHEN 1 THEN 'Enero'" +
                "        WHEN 2 THEN 'Febrero'" +
                "        WHEN 3 THEN 'Marzo'" +
                "        WHEN 4 THEN 'Abril'" +
                "        WHEN 5 THEN 'Mayo'" +
                "        WHEN 6 THEN 'Junio'" +
                "        WHEN 7 THEN 'Julio'" +
                "        WHEN 8 THEN 'Agosto'" +
                "        WHEN 9 THEN 'Septiembre'" +
                "        WHEN 10 THEN 'Octubre'" +
                "        WHEN 11 THEN 'Noviembre'" +
                "        WHEN 12 THEN 'Diciembre'" +
                "    END AS mes, " + 
            " EXTRACT(DAY FROM sa.fecha_envio_solicitud) AS dia,"+
            "   COUNT(sa.id_solicitud_actualizar) AS total_registros" +
            "   FROM solicitud_actualizar_dato sa JOIN grupo_investigacion gi on gi.id_proyecto=sa.id_proyecto" +
            "   JOIN proyecto_investigacion pi ON pi.id_proyecto=gi.id_proyecto" +
            "   JOIN usuario u ON u.id_usuario=gi.id_usuario" +
            "   WHERE u.id_usuario=:idUsuario" +
            "   GROUP BY EXTRACT(YEAR FROM sa.fecha_envio_solicitud), EXTRACT(MONTH FROM sa.fecha_envio_solicitud), EXTRACT(DAY FROM sa.fecha_envio_solicitud)" +
            "   ORDER BY 1,2,4",
            nativeQuery=true)
    List<Object[]> obtenerGraficaSolicitudesActualizarPorDia(@Param("idUsuario") Integer idUsuario);
    
    
   
     @Query(value="SELECT EXTRACT(YEAR FROM a.fecha_acceso) AS anio, " +
            "   COUNT(a.id_acceso) AS total_registros" +
            "   FROM acceso a JOIN usuario u ON u.id_usuario=a.id_usuario" +
            "   GROUP BY EXTRACT(YEAR FROM a.fecha_acceso)" +
            "   ORDER BY 1",
            nativeQuery=true)
    List<Object[]> obtenerGraficaAccesoPorAnio();
    
    @Query(value="SELECT EXTRACT(YEAR FROM a.fecha_acceso) AS anio, " +
            "  EXTRACT(MONTH FROM a.fecha_acceso)," +
            "  CASE EXTRACT(MONTH FROM a.fecha_acceso)" +
                "        WHEN 1 THEN 'Enero'" +
                "        WHEN 2 THEN 'Febrero'" +
                "        WHEN 3 THEN 'Marzo'" +
                "        WHEN 4 THEN 'Abril'" +
                "        WHEN 5 THEN 'Mayo'" +
                "        WHEN 6 THEN 'Junio'" +
                "        WHEN 7 THEN 'Julio'" +
                "        WHEN 8 THEN 'Agosto'" +
                "        WHEN 9 THEN 'Septiembre'" +
                "        WHEN 10 THEN 'Octubre'" +
                "        WHEN 11 THEN 'Noviembre'" +
                "        WHEN 12 THEN 'Diciembre'" +
                "    END AS mes, " + 
            "   COUNT(a.id_acceso) AS total_registros" +
            "   FROM acceso a JOIN usuario u ON u.id_usuario=a.id_usuario" +
            "   GROUP BY EXTRACT(YEAR FROM a.fecha_acceso),EXTRACT(MONTH FROM a.fecha_acceso)" +
            "   ORDER BY 1,2",
            nativeQuery=true)
    List<Object[]> obtenerGraficaAccesoPorMes();
    
    @Query(value="SELECT EXTRACT(YEAR FROM a.fecha_acceso) AS anio, " +
            "  EXTRACT(MONTH FROM a.fecha_acceso)," +
            "  CASE EXTRACT(MONTH FROM a.fecha_acceso)" +
                "        WHEN 1 THEN 'Enero'" +
                "        WHEN 2 THEN 'Febrero'" +
                "        WHEN 3 THEN 'Marzo'" +
                "        WHEN 4 THEN 'Abril'" +
                "        WHEN 5 THEN 'Mayo'" +
                "        WHEN 6 THEN 'Junio'" +
                "        WHEN 7 THEN 'Julio'" +
                "        WHEN 8 THEN 'Agosto'" +
                "        WHEN 9 THEN 'Septiembre'" +
                "        WHEN 10 THEN 'Octubre'" +
                "        WHEN 11 THEN 'Noviembre'" +
                "        WHEN 12 THEN 'Diciembre'" +
                "    END AS mes, " + 
            "   EXTRACT(DAY FROM a.fecha_acceso) AS dia,"+
            "   COUNT(a.id_acceso) AS total_registros" +
            "   FROM acceso a JOIN usuario u ON u.id_usuario=a.id_usuario" +
            "   GROUP BY EXTRACT(YEAR FROM a.fecha_acceso), EXTRACT(MONTH FROM a.fecha_acceso),  EXTRACT(DAY FROM a.fecha_acceso)" +
            "   ORDER BY 1,2,4",
            nativeQuery=true)
    List<Object[]> obtenerGraficaAccesoPorDia();
    
    
    @Query(value="SELECT r.nombre_rol, COUNT(u.id_usuario)" +
            "   FROM usuario u JOIN rol r on r.id_rol=u.id_rol" +
            "   WHERE u.vigencia=true" +
            "   GROUP BY r.nombre_rol",
            nativeQuery=true)
    List<Object[]> obtenerGraficaUsuariosPorRol();
    
    
    // usuario solicitudes
    
    
    @Query(value="SELECT COUNT(sa.id_solicitud_actualizar)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto = epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_actualizar_dato sa ON (sa.id_proyecto=gi.id_proyecto AND gi.id_usuario=sa.id_usuario)" +
            "   WHERE u.id_usuario=:idUsuario",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesActualizarInvestigador(@Param("idUsuario") Integer idUsuario);
    
    
    @Query(value="SELECT COUNT(sa.id_solicitud_actualizar)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto = epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_actualizar_dato sa ON (sa.id_proyecto=gi.id_proyecto AND gi.id_usuario=sa.id_usuario)" +
            "   WHERE u.id_usuario=:idUsuario AND sa.id_estado_solictud_actualizar=2",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesActualizarAcesptadoInvestigador(@Param("idUsuario") Integer idUsuario);
    
    @Query(value="SELECT COUNT(sa.id_solicitud_actualizar)" +
            "   FROM estado_proyecto_investigacion epi JOIN proyecto_investigacion pi on pi.id_estado_proyecto = epi.id_estado_proyecto" +
            "   JOIN grupo_investigacion gi on gi.id_proyecto=pi.id_proyecto" +
            "   JOIN usuario u on u.id_usuario=gi.id_usuario" +
            "   JOIN solicitud_actualizar_dato sa ON (sa.id_proyecto=gi.id_proyecto AND gi.id_usuario=sa.id_usuario)" +
            "   WHERE u.id_usuario=:idUsuario AND sa.id_estado_solictud_actualizar=3",
            nativeQuery=true)
    BigInteger obtenerCantidadSolicitudesActualizarRechazadoInvestigador(@Param("idUsuario") Integer idUsuario);
   
    
}
