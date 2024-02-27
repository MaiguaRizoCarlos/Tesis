package GIDAC.repositorios;

import GIDAC.modelo.VariableFamilia;
import GIDAC.modelo.VariableFamiliaId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VariableFamiliaRepository extends JpaRepository<VariableFamilia,VariableFamiliaId> {
    List<VariableFamilia> findByVigencia(Boolean vigencia);
    
    List<VariableFamilia> findByIdVariableAndIdFamiliaAndVigencia(String idVariable, Integer idFamilia, Boolean vigencia);
    
    List<VariableFamilia> findByVariableIdVariableAndVigencia(Integer idVariable, Boolean vigencia);
    VariableFamilia findByVariableIdVariableAndFamiliaIdFamiliaAndVigencia(Integer idVariable, Integer idfamilia, Boolean vigencia);
    VariableFamilia findByVariableIdVariableAndFamiliaIdFamilia(Integer idVariable, Integer idfamilia);
    
    @Query(value="SELECT DISTINCT f.id_familia, f.descripcion" +
                " FROM variable v JOIN variable_familia vf on v.id_variable=vf.id_variable" +
                " JOIN familia f on f.id_familia=vf.id_familia" +
                " WHERE vf.vigencia=true AND f.vigencia=true AND v.id_variable=:idVariable",
            nativeQuery=true)
    List<Object[]> obtenerFamiliasAsignadas(@Param("idVariable") Integer idvariable);
    
    @Query(value="SELECT DISTINCT f.id_familia, f.descripcion" +
                " FROM variable v JOIN variable_familia vf on v.id_variable=vf.id_variable" +
                " JOIN familia f on f.id_familia=vf.id_familia" +
                " WHERE vf.vigencia=true AND f.vigencia=true;",
            nativeQuery=true)
    List<Object[]> obtenerFamiliasDisponibles();
}
