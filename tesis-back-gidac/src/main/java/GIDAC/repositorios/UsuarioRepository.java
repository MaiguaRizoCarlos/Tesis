package GIDAC.repositorios;

import GIDAC.modelo.Rol;
import GIDAC.modelo.Usuario;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

    public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    public Usuario findByEmail(String email);
    
    public List<Usuario> findByVigenciaAndRol(Boolean vigencia, Rol rol);
    //public Usuario findByUsername(String user);
    @Query(value="SELECT r.nombre_rol as rol, COUNT(u.id_usuario) AS cantidadUsuario FROM Rol r JOIN Usuario u ON r.id_rol = u.id_rol WHERE u.vigencia = true GROUP BY r.nombre_rol",nativeQuery=true)
    List<Object[]> obtenerCantidadUsuarioPorRol();
    
    @Query(value="SELECT u.id_usuario, u.nombre_usuario, u.apellido_usuario, u.email, u.contrasenia, r.nombre_rol, u.vigencia " +
                " FROM usuario u join rol r on u.id_rol = r.id_rol" +
                " WHERE u.email=:email"
            ,nativeQuery=true)
    List<Object[]> obtenerUsuarioPorEmail(@Param("email") String email);
    
    @Query(value="select r.id_rol, count (u.id_usuario) " +
                " from rol r join usuario u on r.id_rol = u.id_rol" +
                " where u.vigencia=true"+
                " group by (r.id_rol)" +
                " order by r.id_rol asc"
            ,nativeQuery=true)
    List<Object[]> obtenerUsuariosPorRol();
    

    
}
