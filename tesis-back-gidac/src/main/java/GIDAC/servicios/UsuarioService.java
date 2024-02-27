/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;

import GIDAC.modelo.Usuario;
import java.util.List;
import java.util.Set;

/**
 *
 * @author My Notebook
 */
public interface UsuarioService {
    public Usuario guardarUsuario(Usuario usuario) throws Exception;

    public Usuario actualizarUsuario(Usuario usuario);
    
    public Usuario obtenerEmail(String email);

    public void eliminarUsuario(Integer usuarioId);
    
    public Usuario obtenerUsuarioId(Integer usuarioId);
    
    public Set<Usuario> obtenerUsuarios();
    
    public List<Usuario> obtenerNormal();
    
    public List<Usuario> usuarioPorRol(Integer id);
    
    public List<Usuario> usuarioPorRolEliminado(Integer id);
    
    public Usuario obtenerPorEmail(String email);
    
    public Object obtenerCantidad();
    
    public List<Usuario> buscarInvestigadoresNoAsignados(Integer id);
    
    public List<Object[]> obtenerUsuariosPorRol();
    
    
}
