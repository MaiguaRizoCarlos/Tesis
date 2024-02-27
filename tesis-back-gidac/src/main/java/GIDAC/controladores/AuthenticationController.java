package GIDAC.controladores;

import GIDAC.configuraciones.JwtUtils;
import GIDAC.utils.UsuarioNotFoundException;
import GIDAC.modelo.CorreoElectronico;
import GIDAC.modelo.JwtRequest;
import GIDAC.modelo.JwtResponse;
import GIDAC.modelo.Usuario;
import GIDAC.modelo.Visitantes;
import GIDAC.servicios.EmailEnvioService;
import GIDAC.servicios.RolService;
import GIDAC.servicios.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import GIDAC.servicios.VisitantesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;
import GIDAC.servicios.UsuarioService;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;
    
    @Autowired
    private VisitantesService crudVisitante;
    
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private EmailEnvioService emailEnvioService;
    
    @Autowired
    private JwtUtils jwtUtils;

     @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getEmail(),jwtRequest.getContrasenia());
        }catch (UsuarioNotFoundException exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String email,String contrasenia) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,contrasenia));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    
    
    
    
    @GetMapping("/listar-visitante")
    public List<Visitantes> listar()
    {
        return crudVisitante.findAll();
    }
    //guardar
    @PostMapping("/guardar-visitante")
    public Object guardar(@RequestBody Visitantes oC)
    {
        if(oC.getIp().equals("")){
            return null;
        }else{
            return crudVisitante.save(oC);    
        }
    }
    @PostMapping("/editar-perfil")    
    public ResponseEntity<Usuario> editarUsuarioActual(@RequestParam("user") String datosJson, @RequestParam("imagen") MultipartFile imagen) throws JsonProcessingException, Exception{
        cValidaciones validaciones =new cValidaciones();
        Usuario usuario = new ObjectMapper().readValue(datosJson, Usuario.class);
        usuario.setFechaActualizacion(validaciones.fechaActual());
        Usuario usuarioAux=usuarioService.obtenerUsuarioId(usuario.getIdUsuario());
        usuario.setRol(usuarioAux.getRol());
        if(usuarioAux.getEmail().equals(usuario.getEmail())){
            emailEnvioService.enviarEmailActualizacionPerfilUsuario(usuario);
        }else{
            emailEnvioService.enviarEmailActualizacionPerfilUsuarioEmailDiferente(usuario,usuarioAux.getEmail());
        }
        usuario.setContrasenia(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
//        if(!imagen.isEmpty()){
//            try{
//                usuario.setImagenPerfil(imagen.getBytes());
//                
//            }catch(Exception e){
//                System.out.println("Error al ingresar la imagen");
//            }
//        }
        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuario)); 
    } 
    
//     public ResponseEntity<Usuario> editarUsuarioActual(@RequestPart("usuario") Usuario usuario, @RequestPart("imagen") MultipartFile imagen) throws IOException{
//        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
//        System.out.println("Imagen guardada__________________________________");
//        if(!imagen.isEmpty()){
//            try{
//                usuario.setDatos(imagen.getBytes());
//                
//            }catch(Exception e){
//                System.out.println("Error al ingresar la imagen");
//            }
//        }
//        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuario)); 
//    } 
//    
    
    
    
    
//         public void editarUsuarioActual(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) throws IOException{
//            Usuario usuarioLocal = usuarioService.obtenerUsuarioId(id);
//            try{
//                usuarioLocal.setDatos(file.getBytes());
//                usuarioService.actualizarUsuario(usuarioLocal);
//                System.out.println("________________________________________guardadro");
//            }catch(Exception e){
//                System.out.println("error----------------------------------------------------------");
//            }
//    } 

    
    
    @PostMapping("/enviar-email-recuperar-contrasenia")
    public Boolean enviarCorreoElectronico1(@RequestBody CorreoElectronico correoElectronico) throws Exception {
        Usuario usuarioLocal = usuarioService.obtenerPorEmail(correoElectronico.getDestinatario());
        if(usuarioLocal != null){
            try {
                String clave=generarClave();
                System.out.println("clave usuario:         "+clave);
                usuarioLocal.setContrasenia(clave);
                emailEnvioService.enviarEmailResetearContrasenia(usuarioLocal);
                usuarioLocal.setContrasenia(this.bCryptPasswordEncoder.encode(clave));
                usuarioService.actualizarUsuario(usuarioLocal);
                return true;
            } catch (MailException e) {
                System.out.println("error "+e);
                return false;
            }
        }
        else{
            System.out.println("El usuario no existe");
            return false;
        }
    }
    
    private static final String CARACTERES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public String generarClave() {
        StringBuilder sb = new StringBuilder(10);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return sb.toString();
    }

    
    
}