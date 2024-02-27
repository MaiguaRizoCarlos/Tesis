package GIDAC.controladores;

import GIDAC.modelo.InformacionEcoAndes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.InformacionAppWebService;

@RestController
@RequestMapping("/app-web")
@CrossOrigin("*")
public class InformacionEcoAndesController {

    
    @Autowired
    private InformacionAppWebService informacionAppWebService;

    //Informacion de la App Web
    @GetMapping("/mostrar-informacion-app-web")
    public List<InformacionEcoAndes> mostrarInformacionAppweb()
    {
        return informacionAppWebService.findAll();
    }
    
    @GetMapping("/mostrar-informacion-app-web-vigente")
    public Object mostrarInformacionAppwebVigente()
    {
        return informacionAppWebService.findByVigencia();
    }
    
    @GetMapping("/obtener-informacion-app-web/{id}")
    public Object getInformacionAppweb(@PathVariable Integer id)
    {
        return informacionAppWebService.findById(id);
    }
    @PostMapping("/guardar-informacion-app-web")
    public Object guardarInformacionAppweb(@RequestBody InformacionEcoAndes oC)
    {
        
        cValidaciones validaciones = new cValidaciones();
        if(informacionAppWebService.findByVigencia()!=null){
            
            InformacionEcoAndes informacionEcoAndes=(InformacionEcoAndes) informacionAppWebService.findByVigencia();
            informacionEcoAndes.setVigencia(false);
            informacionAppWebService.save(informacionEcoAndes);
        }
        oC.setVigencia(true);
        oC.setFechaCreacion(validaciones.fechaActual());
        return informacionAppWebService.save(oC);    
    }
}
