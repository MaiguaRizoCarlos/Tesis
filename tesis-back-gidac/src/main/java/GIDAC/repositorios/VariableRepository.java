
package GIDAC.repositorios;

import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableView;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VariableRepository extends JpaRepository<Variable,Integer> {
    
    public List<Variable> findByVigencia(Boolean vigencia);
    public List<Variable> findByVigenciaAndCodigoVariable(Boolean vigencia, String codigoVariable);
    public Variable findByVigenciaAndIdVariableAndCodigoVariable(Boolean vigencia, Integer idVariable, String codigoVariable);
    
    @Query(value="SELECT DISTINCT co.codigo_variable_organizacion as idVariable, co.nombre_variable_organizacion as nombreVariable, o.siglas as siglas" +
            "   FROM variable v LEFT OUTER JOIN variable_unidad_medida vum on vum.id_variable=v.id_variable" +
            "	LEFT OUTER JOIN valor_permitido vp ON vp.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "	JOIN equivalencia_variable eq ON v.id_variable=eq.id_variable" +
            "	JOIN catalogo_organizacion co ON co.codigo_variable_organizacion=eq.codigo_variable_organizacion" +
            "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion" +
            "   WHERE vum.id_variable IS null and vp.id_variable_unidad_medida IS null",
            nativeQuery=true)
    List<Object[]> obtenerVariablesImcompletas();
    
    @Query(value="SELECT DISTINCT  v.id_variable,v.nombre_variable, co.codigo_variable_organizacion, co.nombre_variable_organizacion, o.siglas, tv.nombre_tipo_variable" +
            "   FROM variable v JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable"+
            "   JOIN variable_familia vf ON vf.id_variable=v.id_variable" +
            "	JOIN catalogo_organizacion co ON co.id_variable=v.id_variable" +
            "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion"+
            "   WHERE o.vigencia=true AND v.vigencia=true AND co.vigencia=true"+
            "   order by 2",
            nativeQuery=true)
    List<Object[]> obtenerVariablesCompletasInvestigador();
    
    @Query(value="SELECT DISTINCT  v.id_variable, co.codigo_variable_organizacion as idVariableOrganizacion, co.nombre_variable_organizacion as nombreVariable, o.siglas as siglas, tv.nombre_tipo_variable, um.abreviatura" +
            "   FROM variable v JOIN variable_unidad_medida vum on vum.id_variable=v.id_variable" +
            "   JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida"+
            "	JOIN valor_permitido vp ON vp.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "   JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable"+
            "   JOIN variable_familia vf ON vf.id_variable=v.id_variable" +
            "	JOIN catalogo_organizacion co ON co.id_variable=v.id_variable" +
            "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion"+
            "   WHERE co.codigo_variable_organizacion=v.codigo_variable",
            nativeQuery=true)
    List<Object[]> obtenerVariablesCompletas();
    
    @Query(value="SELECT DISTINCT v.id_variable, co.nombre_variable_organizacion, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi INNER JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	INNER JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	INNER JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	INNER JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	INNER JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    INNER JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    INNER JOIN familia f on f.id_familia=vf.id_familia" +
                    "    INNER JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                    "    INNER JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                    " WHERE  v.vigencia=true AND v.codigo_variable=co.codigo_variable_organizacion AND pi.vigencia=true AND pi.id_estado_proyecto=2",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosSinFamilia();
    
    @Query(value="SELECT DISTINCT v.id_variable, co.nombre_variable_organizacion, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                    " WHERE v.vigencia=true AND co.id_organizacion= :idOrganizacion AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND co.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosSinFamiliaOrganizacion(@Param("idOrganizacion") Integer idOrganizacion);
    
    @Query(value="SELECT DISTINCT v.id_variable, co.nombre_variable_organizacion, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                " WHERE v.vigencia=true AND  v.codigo_variable=co.codigo_variable_organizacion AND f.id_familia = :idFamilia AND pi.vigencia=true AND pi.id_estado_proyecto=2",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosFiltradoPorFamilia(@Param("idFamilia") Integer idFamilia);
    
       @Query(value="SELECT DISTINCT v.id_variable, co.nombre_variable_organizacion, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                " WHERE v.vigencia=true AND co.id_organizacion= :idOrganizacion AND f.id_familia = :idFamilia AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND co.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosFiltradoPorFamiliaOrganizacion(@Param("idFamilia") Integer idFamilia, @Param("idOrganizacion") Integer idOrganizacion);
    
    
    
    @Query(value="SELECT DISTINCT v.id_variable, co.nombre_variable_organizacion, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                    " WHERE v.vigencia=true AND  pi.vigencia=true AND pi.id_proyecto = :idProyecto AND co.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosSinFamiliaInvestigador(@Param("idProyecto") Integer idProyecto);
    
    @Query(value="SELECT DISTINCT v.id_variable, co.nombre_variable_organizacion, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                " WHERE v.vigencia=true AND f.id_familia = :idFamilia AND pi.vigencia=true AND pi.id_proyecto = :idProyecto AND co.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosFiltradoPorFamiliaInvestigador(@Param("idFamilia") Integer idFamilia, @Param("idProyecto") Integer idProyecto);
    
    
    //variable de equivalencia
    @Query(value="SELECT DISTINCT vum.id_variable_unidad_medida, v.id_variable,v.codigo_variable, v.nombre_variable, o.siglas, co.nombre_variable_organizacion, tv.nombre_tipo_variable, um.abreviatura" +
"                    FROM variable_unidad_medida vum JOIN unidad_medida um ON vum.id_unidad_medida=um.id_unidad_medida" +
"                    JOIN variable v ON v.id_variable=vum.id_variable" +
"                    JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable" +
"                    JOIN catalogo_organizacion co ON co.id_variable=v.id_variable" +
"                    JOIN organizacion o ON o.id_organizacion=co.id_organizacion" +
"                    WHERE v.vigencia=true AND vum.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerVariablesParaCatalogo();
    
    @Query(value="SELECT DISTINCT vum.id_variable_unidad_medida, v.id_variable, v.nombre_variable, o.siglas, tv.nombre_tipo_variable, um.abreviatura"+
                "   FROM proyecto_investigacion pi JOIN dataset dt ON dt.id_proyecto=pi.id_proyecto" +
                "   JOIN dato_recolectado dr ON dt.id_dataset=dr.id_dataset" +
                "   JOIN variable_unidad_medida vum ON dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                "   JOIN unidad_medida um ON vum.id_unidad_medida=um.id_unidad_medida" +
                "   JOIN variable v ON v.id_variable=vum.id_variable" +
                "   JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable" +
                "   JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion" +
                "   WHERE v.vigencia=true AND v.codigo_variable=co.codigo_variable_organizacion AND vum.vigencia=true AND pi.vigencia=true AND dr.vigencia=true AND pi.id_proyecto= :idProy",
            nativeQuery=true)
    List<Object[]> obtenerVariablesParaCatalogoProProyeyecto(@Param("idProy") Integer idProy);
    
    @Query(value="SELECT DISTINCT vum.id_variable_unidad_medida, v.id_variable, v.nombre_variable, o.siglas, tv.nombre_tipo_variable, um.abreviatura"+
                "   FROM proyecto_investigacion pi JOIN dataset dt ON dt.id_proyecto=pi.id_proyecto" +
                "   JOIN dato_recolectado dr ON dt.id_dataset=dr.id_dataset" +
                "   JOIN variable_unidad_medida vum ON dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                "   JOIN unidad_medida um ON vum.id_unidad_medida=um.id_unidad_medida" +
                "   JOIN variable v ON v.id_variable=vum.id_variable" +
                "   JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable" +
                "   JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion" +
                "   WHERE v.vigencia=true AND v.codigo_variable=co.codigo_variable_organizacion AND vum.vigencia=true AND pi.vigencia=true AND dr.vigencia=true AND pi.id_proyecto= :idProy AND dt.codigo_dataset=:codigoDataset",
            nativeQuery=true)
    List<Object[]> obtenerVariablesParaCatalogoProProyeyectoCodigoDataset(@Param("idProy") Integer idProy, @Param("codigoDataset") Integer codigoDataset);
    
    
    @Query(value="SELECT DISTINCT vum.id_variable_unidad_medida, v.id_variable, co.nombre_variable_organizacion, o.siglas, tv.nombre_tipo_variable, um.abreviatura"+
                "   FROM proyecto_investigacion pi JOIN dataset dt ON dt.id_proyecto=pi.id_proyecto" +
                "   JOIN dato_recolectado dr ON dt.id_dataset=dr.id_dataset" +
                "   JOIN variable_unidad_medida vum ON dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                "   JOIN unidad_medida um ON vum.id_unidad_medida=um.id_unidad_medida" +
                "   JOIN variable v ON v.id_variable=vum.id_variable" +
                "   JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable" +
                "   JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion" +
                "   WHERE v.vigencia=true AND co.id_organizacion= :idOrganizacion AND pi.id_proyecto= :idProy AND vum.vigencia=true AND pi.vigencia=true AND dr.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerVariablesParaCatalogoProProyeyectoOrganizacion(@Param("idProy") Integer idProy,@Param("idOrganizacion") Integer idOrganizacion);
    
    
    @Query(value="SELECT DISTINCT vum.id_variable_unidad_medida, v.id_variable, co.nombre_variable_organizacion, o.siglas, tv.nombre_tipo_variable, um.abreviatura"+
                "   FROM proyecto_investigacion pi JOIN dataset dt ON dt.id_proyecto=pi.id_proyecto" +
                "   JOIN dato_recolectado dr ON dt.id_dataset=dr.id_dataset" +
                "   JOIN variable_unidad_medida vum ON dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                "   JOIN unidad_medida um ON vum.id_unidad_medida=um.id_unidad_medida" +
                "   JOIN variable v ON v.id_variable=vum.id_variable" +
                "   JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable" +
                "   JOIN catalogo_organizacion co on co.id_variable=v.id_variable" +
                "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion" +
                "   WHERE v.vigencia=true AND co.id_organizacion= :idOrganizacion AND pi.id_proyecto= :idProy AND dt.codigo_dataset=:codigoDataset AND vum.vigencia=true AND pi.vigencia=true AND dr.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerVariablesParaCatalogoProProyeyectoOrganizacionCodigoDataset(@Param("idProy") Integer idProy,@Param("idOrganizacion") Integer idOrganizacion, @Param("codigoDataset") Integer codigoDataset);
    
    
    
    
    
}
