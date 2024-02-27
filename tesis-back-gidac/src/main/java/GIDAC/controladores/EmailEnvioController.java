package GIDAC.controladores;

import GIDAC.modelo.Acceso;
import GIDAC.modelo.EmailEnvio;
import GIDAC.servicios.AccesoService;
import GIDAC.servicios.EmailEnvioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email-envio")
@CrossOrigin("*")
public class EmailEnvioController {

    
    @Autowired
    private EmailEnvioService service;

    @PostMapping("/save")
    public Object save(@RequestBody EmailEnvio oC)
    {
        cValidaciones ov=new cValidaciones();
        List<EmailEnvio> listaEmails=service.findAll();
        for (EmailEnvio dato : listaEmails) {
            dato.setFechaActualizacion(ov.fechaActual());
            dato.setVigencia(false);
            service.save(dato);
        }
        return service.save(oC);    
    }
    
    @PutMapping("/update")
    public Object update(@RequestBody EmailEnvio oC)
    {
        return service.update(oC);    
    }
    
    @GetMapping("/findAll")
    public List<EmailEnvio> findAll()
    {
        return service.findAll();
    }
    
    @GetMapping("/findById/{id}")
    public Object findById(@PathVariable Integer id)
    {
        return service.findById(id);
    }
    
    @GetMapping("/cambiarEstado/{id}")
    public Object cambiarEstado(@PathVariable Integer id)
    {
        cValidaciones ov=new cValidaciones();
        List<EmailEnvio> listaEmails=service.findAll();
        for (EmailEnvio dato : listaEmails) {
            dato.setFechaActualizacion(ov.fechaActual());
            dato.setVigencia(false);
            service.save(dato);
        }
        EmailEnvio oC=(EmailEnvio) service.findById(id);
        oC.setFechaActualizacion(ov.fechaActual());
        oC.setVigencia(true);
        return service.save(oC);
    }
    
    @GetMapping("/findByVigenciaTrueUno")
    public EmailEnvio findByVigenciaTrueUno()
    {
        List<EmailEnvio> oLista=service.findByVigencia(true);
        if(oLista.isEmpty()){
            return null;
        }else{
            return oLista.get(0);    
        }
    }
    
    @GetMapping("/findByVigenciaTrue")
    public List<EmailEnvio> findByVigenciaTrue()
    {
        return service.findByVigencia(true);
    }
    
    @GetMapping("/findByVigenciaFalse")
    public List<EmailEnvio> findByVigenciaFalse()
    {
        return service.findByVigencia(false);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id)
    {
        service.delete(id);
    }
    
    @DeleteMapping("/restore/{id}")
    public void restore(@PathVariable Integer id)
    {
        cValidaciones ov=new cValidaciones();
        List<EmailEnvio> listaEmails=service.findAll();
        for (EmailEnvio dato : listaEmails) {
            dato.setFechaActualizacion(ov.fechaActual());
            dato.setVigencia(false);
            service.save(dato);
        }
        EmailEnvio oC=(EmailEnvio) service.findById(id);
        oC.setFechaActualizacion(ov.fechaActual());
        oC.setVigencia(true);
        service.save(oC);
    }
    
}
