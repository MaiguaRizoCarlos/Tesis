package GIDAC.repositorios;

import GIDAC.modelo.CatalogoOrganizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatalogoOrganizacionRepository extends JpaRepository<CatalogoOrganizacion,Integer> {
    List<CatalogoOrganizacion> findByVigencia(Boolean vigencia);
    CatalogoOrganizacion findByCodigoVariableOrganizacion(String codigovariable);
    CatalogoOrganizacion findByVariableSistemaAndVariableIdVariable(Boolean vigencia, Integer idvariable);
    List<CatalogoOrganizacion> findByVigenciaAndOrganizacionIdOrganizacionAndOrganizacionVigencia(Boolean vigencia, Integer idOrganizacion, Boolean vigenciaOrganizacion);
    List<CatalogoOrganizacion> findByVigenciaAndOrganizacionIdOrganizacion(Boolean vigencia, Integer idOrganizacion);
    List<CatalogoOrganizacion> findByVigenciaAndVariableIdVariable(Boolean vigencia, Integer idVariable);
    List<CatalogoOrganizacion> findByVigenciaAndVariableIdVariableAndOrganizacionVigencia(Boolean vigencia, Integer idVariable, Boolean vigenciaOrganizacion);
    
    @Query(value="  SELECT v.codigo_variable, v.nombre_variable, tv.nombre_tipo_variable,  co.codigo_variable_organizacion, co.nombre_variable_organizacion, o.nombre_organizacion, um.abreviatura, um.unidad_medida" +
                "   FROM variable v " +
                "	JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable " +
                "	JOIN variable_unidad_medida vum ON vum.id_variable=v.id_variable " +
                "	JOIN unidad_medida um ON um.id_unidad_medida = vum.id_unidad_medida " +
                "	JOIN catalogo_organizacion co ON co.id_variable = v.id_variable " +
                "	JOIN organizacion o ON o.id_organizacion = co.id_organizacion " +
                "   WHERE v.vigencia=true AND co.vigencia=true AND o.vigencia=true AND vum.vigencia=true AND um.vigencia=true " +
                "   ORDER BY v.codigo_variable, v.nombre_variable, tv.nombre_tipo_variable, o.nombre_organizacion ",
            nativeQuery=true)
    List<Object[]> obtenerDatosDescargar();
    
}
