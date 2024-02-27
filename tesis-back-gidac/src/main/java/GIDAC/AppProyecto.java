package GIDAC;



import GIDAC.controladores.cValidaciones;
import GIDAC.modelo.EmailEnvio;
import GIDAC.modelo.EstadoProyectoInvestigacion;
import GIDAC.modelo.EstadoSolicitudActualizar;
import GIDAC.modelo.EstadoSolicitudDescarga;
import GIDAC.utils.UsuarioFoundException;
import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.Rol;
import GIDAC.modelo.TipoVariable;
import GIDAC.modelo.Usuario;
import GIDAC.servicios.EmailEnvioService;
import GIDAC.servicios.EstadoProyectoInvestigacionService;
import GIDAC.servicios.EstadoSolicitudActualizarService;
import GIDAC.servicios.EstadoSolicitudDescargaService;
import GIDAC.servicios.GrupoInvestigacionService;
import GIDAC.servicios.RolService;
import GIDAC.servicios.TipoVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import GIDAC.servicios.UsuarioService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;




@SpringBootApplication
public class AppProyecto implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;
        
        @Autowired
	private TipoVariableService tipoVariableService;
        
        @Autowired
	private EstadoSolicitudDescargaService estadoSolicitudDescargaService;
        
        @Autowired
	private EstadoSolicitudActualizarService estadoSolicitudActualizarService;
        
        @Autowired
	private EstadoProyectoInvestigacionService estadoProyectoInvestigacionService;
        
        @Autowired
	private RolService rolService;
        
        @Autowired
	private EmailEnvioService emailEnvioService;
        
        @Autowired
	private GrupoInvestigacionService grupoInvestigacionService;
        
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppProyecto.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
            cValidaciones oVal=new cValidaciones();
            
            
            
            
            //----------------------------------------------------------------------------
            //ROLES
            //----------------------------------------------------------------------------
            //ADMINISTRADADOR
            try{  
                if(rolService.findById(1)==null){
                    Rol rol = new Rol();
                    rol.setIdRol(1);
                    rol.setNombreRol("ADMINISTRADOR");
                    rolService.save(rol);    
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el administrador: "+exception);
            }
            
            //ADMINISTRADADOR DE DATOS
            try{  
                if(rolService.findById(2)==null){
                    Rol rol = new Rol();
                    rol.setIdRol(2);
                    rol.setNombreRol("DIRECTOR");
                    rolService.save(rol);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el director: "+exception);
            }
            
            //ADMINISTRADADOR DE DATOS
            try{  
                if(rolService.findById(3)==null){
                    Rol rol = new Rol();
                    rol.setIdRol(3);
                    rol.setNombreRol("INVESTIGADOR");
                    rolService.save(rol);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el investigador: "+exception);
            }
            
            //ADMINISTRADADOR DE DATOS
            try{  
                if(rolService.findById(4)==null){
                    Rol rol = new Rol();
                    rol.setIdRol(4);
                    rol.setNombreRol("ADMINISTRADOR DE DATOS");
                    rolService.save(rol);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el administrador de datos: "+exception);
            }
            
            
            //----------------------------------------------------------------------------
            //TIPO VARIABLE
            //----------------------------------------------------------------------------
            //TIPO VARIABLE NUMERICA
            try{  
                if(tipoVariableService.buscarPorId(1)==null){
                    TipoVariable tipoVariable = new TipoVariable();
                    tipoVariable.setIdTipoVariable(1);
                    tipoVariable.setNombreTipoVariable("Numérico");
                    tipoVariableService.guardar(tipoVariable);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el el tipo de variable númerico: "+exception);
            }
            
            //TIPO VARIABLE CATEGORICO
            try{  
                if(tipoVariableService.buscarPorId(2)==null){
                    TipoVariable tipoVariable = new TipoVariable();
                    tipoVariable.setIdTipoVariable(2);
                    tipoVariable.setNombreTipoVariable("Categórico");
                    tipoVariableService.guardar(tipoVariable);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el el tipo de variable categórico: "+exception);
            }
            
            //----------------------------------------------------------------------------
            //ESTADO PROYECTO
            //----------------------------------------------------------------------------
            //Estado proyecto investigacion privado
            try{  
                if(estadoProyectoInvestigacionService.findById(1)==null){
                    EstadoProyectoInvestigacion estadoProyectoInvestigacion = new EstadoProyectoInvestigacion();
                    estadoProyectoInvestigacion.setIdEstadoProyecto(1);
                    estadoProyectoInvestigacion.setNombreEstadoProyecto("Privado");
                    estadoProyectoInvestigacionService.save(estadoProyectoInvestigacion);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el estado del proyecto de investigación privado: "+exception);
            }
            
            //Estado proyecto investigacion publico
            try{  
                if(estadoProyectoInvestigacionService.findById(2)==null){
                    EstadoProyectoInvestigacion estadoProyectoInvestigacion = new EstadoProyectoInvestigacion();
                    estadoProyectoInvestigacion.setIdEstadoProyecto(2);
                    estadoProyectoInvestigacion.setNombreEstadoProyecto("Público");
                    estadoProyectoInvestigacionService.save(estadoProyectoInvestigacion);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el estado del proyecto de investigación publico: "+exception);
            }
            
            
            //----------------------------------------------------------------------------
            //ESTADO SOLICITUD DESCARGA
            //----------------------------------------------------------------------------
            
            //Estado solicitud descarga solicitada
            try{  
                if(estadoSolicitudDescargaService.buscarPorId(1)==null){
                    EstadoSolicitudDescarga estadoSolicitudDescarga = new EstadoSolicitudDescarga();
                    estadoSolicitudDescarga.setIdEstadoDescarga(1);
                    estadoSolicitudDescarga.setNombreEstadoDescarga("Solicitado");
                    estadoSolicitudDescargaService.guardar(estadoSolicitudDescarga);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el estado de solicitud de descarga solicitado: "+exception);
            }
            
            //Estado solicitud descarga aprobada
            try{  
                if(estadoSolicitudDescargaService.buscarPorId(2)==null){
                    EstadoSolicitudDescarga estadoSolicitudDescarga = new EstadoSolicitudDescarga();
                    estadoSolicitudDescarga.setIdEstadoDescarga(2);
                    estadoSolicitudDescarga.setNombreEstadoDescarga("Aprobado");
                    estadoSolicitudDescargaService.guardar(estadoSolicitudDescarga);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el estado de solicitud de descarga Aprobado: "+exception);
            }
            
            //Estado solicitud descarga rechazada
            try{  
                if(estadoSolicitudDescargaService.buscarPorId(3)==null){
                    EstadoSolicitudDescarga estadoSolicitudDescarga = new EstadoSolicitudDescarga();
                    estadoSolicitudDescarga.setIdEstadoDescarga(3);
                    estadoSolicitudDescarga.setNombreEstadoDescarga("Rechazado");
                    estadoSolicitudDescargaService.guardar(estadoSolicitudDescarga);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el estado de solicitud de descarga Rechazado: "+exception);
            }
            
            
            //----------------------------------------------------------------------------
            //ESTADO SOLICTUD ACTUALIZAR
            //----------------------------------------------------------------------------
            //Estado actualizar  solicitada
            try{  
                if(estadoSolicitudActualizarService.buscarPorId(1)==null){
                    EstadoSolicitudActualizar estadoSolicitudActualizar = new EstadoSolicitudActualizar();
                    estadoSolicitudActualizar.setIdEstadoSolicitud(1);
                    estadoSolicitudActualizar.setNombreEstadoSolicitud("Solicitado");
                    estadoSolicitudActualizarService.guardar(estadoSolicitudActualizar);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el estado de solicitud de actualizar solicitado: "+exception);
            }
            
            //Estado actualizar  aprobada
            try{  
                if(estadoSolicitudActualizarService.buscarPorId(2)==null){
                    EstadoSolicitudActualizar estadoSolicitudActualizar = new EstadoSolicitudActualizar();
                    estadoSolicitudActualizar.setIdEstadoSolicitud(2);
                    estadoSolicitudActualizar.setNombreEstadoSolicitud("Aprobado");
                    estadoSolicitudActualizarService.guardar(estadoSolicitudActualizar);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el estado de solicitud de actualizar Aprobado: "+exception);
            }
            
            //Estado actualizar  rechazada
            try{  
                if(estadoSolicitudActualizarService.buscarPorId(3)==null){
                    EstadoSolicitudActualizar estadoSolicitudActualizar = new EstadoSolicitudActualizar();
                    estadoSolicitudActualizar.setIdEstadoSolicitud(3);
                    estadoSolicitudActualizar.setNombreEstadoSolicitud("Rechazado");
                    estadoSolicitudActualizarService.guardar(estadoSolicitudActualizar);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el estado de solicitud de actualizar Rechazado: "+exception);
            }
            
            
            //----------------------------------------------------------------------------
            //ADMINISTRADIOR
            //----------------------------------------------------------------------------
            //Registrar administrador
            Usuario usuarioGuardado=new Usuario();
            try{  
                if(usuarioService.obtenerUsuarioId(1)==null){
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(1);
                    usuario.setNombreUsuario("Juan Carlos");
                    usuario.setApellidoUsuario("Maigua Rizo");
                    usuario.setCedula("0550458681");
                    usuario.setContrasenia("12345");
                    usuario.setEmail("cjdmaigua@gmail.com");
                    usuario.setFechaCreacion(oVal.fechaActual());
                    usuario.setFechaActualizacion(oVal.fechaActual());
                    Rol rol = new Rol();
                    rol.setIdRol(1);
                    rol.setNombreRol("ADMINISTRADOR");
                    usuario.setRol(rol);
                    usuarioGuardado = usuarioService.guardarUsuario(usuario);
                }
            }catch(Exception exception){
                throw new Exception("Error al administrador: "+exception);
            }
            
            //----------------------------------------------------------------------------
            //EMAIL ENVIO
            //----------------------------------------------------------------------------
            //EMAIL ENVIO
            try{  
                List<EmailEnvio> oLista=emailEnvioService.findByVigencia(true);
                if(oLista.isEmpty()){
                    EmailEnvio emailEnvio=new EmailEnvio();
                    usuarioGuardado.setIdUsuario(1);
                    emailEnvio.setEmail("espoch.gidac@outlook.com");
                    emailEnvio.setContrasenia("*EspochGrupoGIDAC*");
                    emailEnvio.setHost("smtp.office365.com");
                    emailEnvio.setPort("587");
                    emailEnvio.setVigencia(true);
                    emailEnvio.setFechaActualizacion(oVal.fechaActual());
                    emailEnvio.setFechaCreacion(oVal.fechaActual());
                    emailEnvio.setUsuario(usuarioGuardado);
                    emailEnvioService.save(emailEnvio);
                }
            }catch(Exception exception){
                throw new Exception("Error al registrar el administrador: "+exception);
            }

	}

	
}
