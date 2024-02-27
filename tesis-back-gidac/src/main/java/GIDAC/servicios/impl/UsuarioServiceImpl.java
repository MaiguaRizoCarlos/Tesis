package GIDAC.servicios.impl;

import GIDAC.utils.UsuarioFoundException;
import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.Rol;
import GIDAC.modelo.Usuario;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.repositorios.GrupoInvestigacionRepository;
import GIDAC.repositorios.ProyectoInvestigacionRepository;
import GIDAC.repositorios.RolRepository;
import GIDAC.repositorios.UsuarioRepository;
import GIDAC.servicios.EmailEnvioService;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;
import GIDAC.servicios.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private ProyectoInvestigacionRepository proyectoInvestigacionRepository;
    
    @Autowired
    private GrupoInvestigacionRepository grupoInvestigacionRepository;
    
    @Autowired
    private AreaInvestigacionRepository areaInvestigacionRepository;
    
    
    @Autowired
    private EmailEnvioService emailEnvioService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public Usuario obtenerEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


    @Override
    public void eliminarUsuario(Integer usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
    
    @Override
    public Usuario actualizarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    
    

    @Override
    public Usuario guardarUsuario(Usuario usuario) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByEmail(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        }
        else{
            emailEnvioService.enviarEmailRegistroUsuario(usuario);
            usuario.setContrasenia(this.bCryptPasswordEncoder.encode(usuario.getContrasenia()));
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }
    
    @Override
    public Usuario obtenerUsuarioId(Integer Id) {
        return usuarioRepository.findById(Id).get();
    }
    
    @Override
    public Set<Usuario> obtenerUsuarios() {
        //return usuarioRepository.findByRolId(2L);
        return new LinkedHashSet<>(usuarioRepository.findAll());
    }

    @Override
    public List<Usuario> obtenerNormal(){
        
        
        //return (List<Usuario>) usuarioRepository.findByRol_IdRol(2);
        return (List<Usuario>) usuarioRepository.findAll();
    }
    
    @Override
    public List<Usuario> usuarioPorRol(Integer id){
        Rol rol=new Rol();
        rol.setIdRol(id);
        return (List<Usuario>) usuarioRepository.findByVigenciaAndRol(true, rol);
        //return (List<Usuario>) usuarioRepository.findAll();
    }
    
    @Override
    public List<Object[]> obtenerUsuariosPorRol(){
        return usuarioRepository.obtenerUsuariosPorRol();
    }
    
    @Override
    public List<Usuario> usuarioPorRolEliminado(Integer id){
        Rol rol=new Rol();
        rol.setIdRol(id);
        return (List<Usuario>) usuarioRepository.findByVigenciaAndRol(false, rol);
        //return (List<Usuario>) usuarioRepository.findAll();
    }
    
    
    @Override
    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    @Override
    public List<Usuario> buscarInvestigadoresNoAsignados(Integer id){
     ProyectoInvestigacion oInves = proyectoInvestigacionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Curso no encontrado"));
        
        List<GrupoInvestigacion> inscripciones=(List<GrupoInvestigacion>) grupoInvestigacionRepository.findByVigenciaAndProyectoInvestigacionAndUsuarioRolNombreRol(true,oInves, "INVESTIGADOR");
        
        List<Usuario> oInvestigador = new ArrayList<>();
        Rol rol= new Rol();
        rol=rolRepository.getById(3);
        List<Usuario> inves= (List<Usuario>) usuarioRepository.findByVigenciaAndRol(true,rol);
        
        for (GrupoInvestigacion inscripcion : inscripciones) {
            oInvestigador.add(inscripcion.getUsuario());
        }
        inves.removeAll(oInvestigador);
        return inves;
   }
   
    @Override
    public Object obtenerCantidad() {
        return usuarioRepository.obtenerCantidadUsuarioPorRol();
    }

} 