package GIDAC.servicios.impl;

import GIDAC.modelo.Usuario;

import GIDAC.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByEmail(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }
    
    
    
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("-------------------------------------------------");
//        List<Object[]> usuario = this.usuarioRepository.obtenerUsuarioPorEmail(username);
//        
//        if (usuario==null) {
//            throw new UsernameNotFoundException("Usuario no encontrado");
//        }
//        
//        Integer idUsuario=0;
//        String nombreUsuario="";
//        String apellidoUsuario="";
//        String email="";
//        String contrasenia="";
//        String nombreRol="";
//        boolean vigencia=true;
//            
//        for (Object[] dato : usuario) {
//            idUsuario = (Integer) dato[0];
//            nombreUsuario = (String) dato[1];
//            apellidoUsuario = (String) dato[2];
//            email = (String) dato[3];
//            contrasenia = (String) dato[4];
//            nombreRol = (String) dato[5];
//            vigencia = (boolean) dato[6];
//            break;
//        }
//        
//        UsuarioDetalle usuarioDetalle = new UsuarioDetalle(idUsuario, nombreUsuario, apellidoUsuario, email, contrasenia, nombreRol, vigencia);
//        
//        return usuarioDetalle;
//    }

}

