package GIDAC.controladores;


import GIDAC.modelo.ListaCanton;
import GIDAC.modelo.ListaPais;
import GIDAC.modelo.ListaProvincias;
import GIDAC.modelo.Localizacion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.LocalizacionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/ubicacion/")
@CrossOrigin("*")
public class LocalizacionController {

    
    @Autowired
    private LocalizacionService service;

    @PostMapping("/guardar-ubicacion")
    public Object guardar(@RequestBody Localizacion oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-ubicacion/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-ubicacion-vigente")
    public List<Localizacion> listarVigente()
    {
        return service.buscarVigencia(true);
    }
    
    @GetMapping("/listar-ubicacion-eliminado")
    public List<Localizacion> listarEliminado()
    {
        return service.buscarVigencia(false);
    }
    
    @DeleteMapping("/eliminar-ubicacion/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Localizacion ol=(Localizacion) service.buscarPorId(id);
        ol.setVigencia(false);
        service.guardar(ol);
    }
    
    @GetMapping("/obtener-ubicacion/por-pais/{id}")
    public Object obtenerPorPais(@PathVariable String id)
    {
        return service.buscarPorPais(id);
    }
    
     @GetMapping("/obtener-ubicacion/por-provincia/{id}")
    public Object obtenerPorProvincia(@PathVariable String id)
    {
        return service.buscarPorProvincia(id);
    }
    
     @GetMapping("/obtener-ubicacion/por-canton/{id}")
    public Object obtenerPorCanton(@PathVariable String id)
    {
        return service.buscarPorCanton(id);
    }
    
     @GetMapping("/obtener-ubicacion/por-parroquia/{id}")
    public Object obtenerPorParroquia(@PathVariable String id)
    {
        return service.buscarPorParroquia(id);
    }
    
    //-------------------------------------------------------------
    //Guardar
    @PostMapping("/guardar-pais")
    public Object guardarPais(@RequestBody Localizacion oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PostMapping("/guardar-provincia")
    public Object guardarProvincia(@RequestBody Localizacion oC)
    {
        List<Localizacion> localizacion=service.buscarPaisesAdmin(oC.getCodigoPais(), true);    
        Localizacion localizacionDatos=localizacion.get(0);
        Localizacion localizacionDatosGuardar=new Localizacion();
        localizacionDatosGuardar.setCodigoPais(localizacionDatos.getCodigoPais());
        localizacionDatosGuardar.setNombrePais(localizacionDatos.getNombrePais());
        for (Localizacion dato : localizacion) {
            if (dato.getCodigoProvincia() == null || dato.getCodigoProvincia().isEmpty()) {
                localizacionDatosGuardar.setIdLocalizacion(dato.getIdLocalizacion());
                break;
            }
        }
        localizacionDatosGuardar.setCodigoProvincia(oC.getCodigoProvincia());
        localizacionDatosGuardar.setNombreProvincia(oC.getNombreProvincia());
        cValidaciones oV=new cValidaciones();
        localizacionDatosGuardar.setFechaCreacion(oV.fechaActual());
        return service.guardar(localizacionDatosGuardar);    
    }
    
    @PostMapping("/guardar-canton")
    public Object guardarCanton(@RequestBody Localizacion oC)
    {
        List<Localizacion> localizacion=service.buscarProvinciasAdmin(oC.getCodigoPais(), oC.getCodigoProvincia(), true);  
        Localizacion localizacionDatos=localizacion.get(0);
        Localizacion localizacionDatosGuardar=new Localizacion();
        localizacionDatosGuardar.setCodigoPais(localizacionDatos.getCodigoPais());
        localizacionDatosGuardar.setNombrePais(localizacionDatos.getNombrePais());
        localizacionDatosGuardar.setCodigoProvincia(localizacionDatos.getCodigoProvincia());
        localizacionDatosGuardar.setNombreProvincia(localizacionDatos.getNombreProvincia());
        for (Localizacion dato : localizacion) {
            if (dato.getCodigoCanton() == null || dato.getCodigoCanton().isEmpty()) {
                localizacionDatosGuardar.setIdLocalizacion(dato.getIdLocalizacion());
                break;
            }
        }
        localizacionDatosGuardar.setCodigoCanton(oC.getCodigoCanton());
        localizacionDatosGuardar.setNombreCanton(oC.getNombreCanton());
        cValidaciones oV=new cValidaciones();
        localizacionDatosGuardar.setFechaCreacion(oV.fechaActual());
        return service.guardar(localizacionDatosGuardar);     
    }
    
    @PostMapping("/guardar-parroquia")
    public Object guardarParroquia(@RequestBody Localizacion oC)
    {
        List<Localizacion> localizacion=service.buscarCantonesAdmin(oC.getCodigoPais(), oC.getCodigoProvincia(), oC.getCodigoCanton(), true);  
        Localizacion localizacionDatos=localizacion.get(0);
        Localizacion localizacionDatosGuardar=new Localizacion();
        localizacionDatosGuardar.setCodigoPais(localizacionDatos.getCodigoPais());
        localizacionDatosGuardar.setNombrePais(localizacionDatos.getNombrePais());
        localizacionDatosGuardar.setCodigoProvincia(localizacionDatos.getCodigoProvincia());
        localizacionDatosGuardar.setNombreProvincia(localizacionDatos.getNombreProvincia());
        localizacionDatosGuardar.setCodigoCanton(localizacionDatos.getCodigoCanton());
        localizacionDatosGuardar.setNombreCanton(localizacionDatos.getNombreCanton());
        for (Localizacion dato : localizacion) {
            if (dato.getCodigoParroquia() == null || dato.getCodigoParroquia().isEmpty()) {
                localizacionDatosGuardar.setIdLocalizacion(dato.getIdLocalizacion());
                break;
            }
        }
        localizacionDatosGuardar.setCodigoParroquia(oC.getCodigoParroquia());
        localizacionDatosGuardar.setNombreParroquia(oC.getNombreParroquia());
        cValidaciones oV=new cValidaciones();
        localizacionDatosGuardar.setFechaCreacion(oV.fechaActual());
        return service.guardar(localizacionDatosGuardar);    
    }
    
    //-------------------------------------------------------------
    //Actualizar
    @PostMapping("/actualizar-pais")
    public void actualizarPais(@RequestParam("datosBusqueda") String datosBusqueda, 
                                 @RequestParam("datosActualizar") String datosActualizar ) throws JsonProcessingException
    {
        Localizacion oBusqueda = new ObjectMapper().readValue(datosBusqueda, Localizacion.class);
        Localizacion oActualizar = new ObjectMapper().readValue(datosActualizar, Localizacion.class);
        
        List<Localizacion> localizacion=service.buscarPaisesAdmin(oBusqueda.getCodigoPais(), true);    
        cValidaciones oV=new cValidaciones();
        for (Localizacion dato : localizacion) {
            if (dato.getCodigoPais() != null && !dato.getCodigoPais().isEmpty()) {
                dato.setCodigoPais(oActualizar.getCodigoPais());
                dato.setNombrePais(oActualizar.getNombrePais());
                dato.setFechaActualizacion(oV.fechaActual());
                service.guardar(dato);    
            }
        } 
    }
    
    @PostMapping("/actualizar-provincia")
    public void actualizarProvincia (@RequestParam("datosBusqueda") String datosBusqueda, 
                                       @RequestParam("datosActualizar") String datosActualizar ) throws JsonProcessingException
    {
        Localizacion oBusqueda = new ObjectMapper().readValue(datosBusqueda, Localizacion.class);
        Localizacion oActualizar = new ObjectMapper().readValue(datosActualizar, Localizacion.class);
        
        List<Localizacion> localizacion=service.buscarProvinciasAdmin(oBusqueda.getCodigoPais(), oBusqueda.getCodigoProvincia(), true);  
        cValidaciones oV=new cValidaciones();
        for (Localizacion dato : localizacion) {
            
            if (dato.getCodigoProvincia() != null && !dato.getCodigoProvincia().isEmpty()) {
                dato.setCodigoProvincia(oActualizar.getCodigoProvincia());
                dato.setNombreProvincia(oActualizar.getNombreProvincia());
                dato.setFechaActualizacion(oV.fechaActual());
                service.guardar(dato);  
            }
        }  
    }
    
    @PostMapping("/actualizar-canton")
    public void actualizarCanton(@RequestParam("datosBusqueda") String datosBusqueda, 
                                   @RequestParam("datosActualizar") String datosActualizar ) throws JsonProcessingException
    {
        
        Localizacion oBusqueda = new ObjectMapper().readValue(datosBusqueda, Localizacion.class);
        Localizacion oActualizar = new ObjectMapper().readValue(datosActualizar, Localizacion.class);
        
        List<Localizacion> localizacion=service.buscarCantonesAdmin(oBusqueda.getCodigoPais(), oBusqueda.getCodigoProvincia(), oBusqueda.getCodigoCanton(), true);  
        cValidaciones oV=new cValidaciones();
        for (Localizacion dato : localizacion) {
            if (dato.getCodigoCanton() != null && !dato.getCodigoCanton().isEmpty()) {
                dato.setCodigoCanton(oActualizar.getCodigoCanton());
                dato.setNombreCanton(oActualizar.getNombreCanton());
                dato.setFechaActualizacion(oV.fechaActual());
                service.guardar(dato);   
            }
        }
    }
    
    @PostMapping("/actualizar-parroquia")
    public void actualizarParroquia(@RequestParam("datosBusqueda") String datosBusqueda, 
                                   @RequestParam("datosActualizar") String datosActualizar ) throws JsonProcessingException
    {
        
        Localizacion oBusqueda = new ObjectMapper().readValue(datosBusqueda, Localizacion.class);
        Localizacion oActualizar = new ObjectMapper().readValue(datosActualizar, Localizacion.class);
        
        List<Localizacion> localizacion=service.buscarParroquiasAdmin(oBusqueda.getCodigoPais(), oBusqueda.getCodigoProvincia(), oBusqueda.getCodigoCanton(), oBusqueda.getCodigoParroquia(), true);  
        cValidaciones oV=new cValidaciones();
        for (Localizacion dato : localizacion) {
            if (dato.getCodigoParroquia() != null && !dato.getCodigoParroquia().isEmpty()) {
                dato.setCodigoParroquia(oActualizar.getCodigoParroquia());
                dato.setNombreParroquia(oActualizar.getNombreParroquia());
                dato.setFechaActualizacion(oV.fechaActual());
                service.guardar(dato);    
            }
        }
    }

    //------------------------------------------------------------
    //Eliminar
    
    @DeleteMapping("/eliminar-paises/{idPais}")
    public void eliminarPais(@PathVariable String idPais)
    {
        List<Localizacion> localizacion=service.buscarPaisesAdmin(idPais, true);
        cValidaciones oV=new cValidaciones();
        for (Localizacion dato : localizacion) {
            dato.setVigencia(false);
            dato.setFechaActualizacion(oV.fechaActual());
            service.guardar(dato); 
        }
    }
    
    @DeleteMapping("/eliminar-provincias/{idPais}/{idProvincia}")
    public void eliminarProvincias(@PathVariable String idPais, @PathVariable String idProvincia)
    {
        List<Localizacion> localizacion=service.buscarProvinciasAdmin(idPais, idProvincia, true);  ;  
        cValidaciones oV=new cValidaciones();
        for (Localizacion dato : localizacion) {
            dato.setVigencia(false);
            dato.setFechaActualizacion(oV.fechaActual());
            service.guardar(dato); 
        }
    }
    
    @DeleteMapping("/eliminar-cantones/{idPais}/{idProvincia}/{idCanton}")
    public void eliminarCantones(@PathVariable String idPais, @PathVariable String idProvincia,@PathVariable String idCanton)
    {
        List<Localizacion> localizacion=service.buscarCantonesAdmin(idPais, idProvincia, idCanton, true);  ;  
        cValidaciones oV=new cValidaciones();
        for (Localizacion dato : localizacion) {
            dato.setVigencia(false);    
            dato.setFechaActualizacion(oV.fechaActual());
            service.guardar(dato); 
        }
    }
    
    @DeleteMapping("/eliminar-parroquias/{idPais}/{idProvincia}/{idCanton}/{idParroquia}")
    public void eliminarParroquia(@PathVariable String idPais, @PathVariable String idProvincia,@PathVariable String idCanton,@PathVariable String idParroquia)
    {
        List<Localizacion> localizacion=service.buscarParroquiasAdmin(idPais, idProvincia, idCanton, idParroquia, true);  
        cValidaciones oV=new cValidaciones();
        for (Localizacion dato : localizacion) {
            dato.setVigencia(false);
            dato.setFechaActualizacion(oV.fechaActual());
            service.guardar(dato); 
        }
    }
    
    //-----------------------------------------------------------
    @GetMapping("/obtener-ubicacion-paises")
    public List<ListaPais> obtenerPaises()
    {
        List<Object[]> datos= service.buscarPaises();
        List<ListaPais> lista=new ArrayList();
        for (Object[] dato : datos) {
            String datoAux=((String) dato[0]);
            if (datoAux != null && !datoAux.isEmpty()) {
                ListaPais ol=new ListaPais();
                ol.setCodigoPais((String) dato[0]);
                ol.setNombrePais((String) dato[1]);
                lista.add(ol);
            }
        }
        return lista;
    }
    
    @GetMapping("/obtener-ubicacion-provincias/{idPais}")
    public List<ListaProvincias> obtenerProvincias(@PathVariable("idPais") String idPais)
    {
        List<Object[]> datos= service.buscarPrvincias(idPais);
        List<ListaProvincias> lista=new ArrayList();
        for (Object[] dato : datos) {
            String datoAux=((String) dato[0]);
            if (datoAux != null && !datoAux.isEmpty()) {
                ListaProvincias ol=new ListaProvincias();
                ol.setCodigoProvincia((String) dato[0]);
                ol.setNombreProvincia((String) dato[1]);
                lista.add(ol);
            }
        }
        return lista;
    }
    
    @GetMapping("/obtener-ubicacion-cantones/{idPais}/{idProv}")
    public List<ListaCanton> obtenerCantones(@PathVariable("idPais") String idPais, @PathVariable("idProv") String idProv)
    {
        
        List<Object[]> datos= service.buscarCantones(idPais, idProv);
        List<ListaCanton> lista=new ArrayList();
        
        for (Object[] dato : datos) {
            String datoAux=((String) dato[0]);
            if (datoAux != null && !datoAux.isEmpty()) {
                ListaCanton ol=new ListaCanton();
                ol.setCodigoCanton((String) dato[0]);
                ol.setNombreCanton((String) dato[1]);
                lista.add(ol);
            }
        }
        return lista;
    }
    
    @GetMapping("/obtener-ubicacion-parroquias/{idPais}/{idProv}/{idCanton}")
    public List<Localizacion> obtenerParroquias(@PathVariable("idPais") String idPais, @PathVariable("idProv") String idProv, @PathVariable("idCanton") String idCanton )
    {
        List<Localizacion> datos= service.buscarParroquias(idPais, idProv, idCanton);
        List<Localizacion> lista=new ArrayList();
        
        for (Localizacion dato : datos) {
            if (dato.getCodigoParroquia() != null && !dato.getCodigoParroquia().isEmpty()) {
                lista.add(dato);
            }
        }
        return lista;
    }
    
}
