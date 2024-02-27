package GIDAC.controladores;

import GIDAC.modelo.TiempoEdicionDato;
import GIDAC.servicios.TiempoEdicionDatoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tiempo-edicion-dato")
@CrossOrigin("*")
public class TiempoEdicionDatoController {

    
    @Autowired
    private TiempoEdicionDatoService service;

    @PostMapping("/save")
    public Object save(@RequestBody TiempoEdicionDato oC)
    {
        cValidaciones ov=new cValidaciones();
        List<TiempoEdicionDato> listaDatos=service.findAll();
        for (TiempoEdicionDato dato : listaDatos) {
            dato.setFechaActualizacion(ov.fechaActual());
            dato.setVigencia(false);
            service.save(dato);
        }
        return service.save(oC);    
    }
    
    @PutMapping("/update")
    public Object update(@RequestBody TiempoEdicionDato oC)
    {
        return service.update(oC);    
    }
    
    @GetMapping("/findAll")
    public List<TiempoEdicionDato> findAll()
    {
        return service.findAll();
    }
    
    @GetMapping("/findById/{id}")
    public Object findById(@PathVariable Integer id)
    {
        return service.findById(id);
    }
    
    @GetMapping("/findByVigenciaTrueUno")
    public TiempoEdicionDato findByVigenciaTrueUno()
    {
        List<TiempoEdicionDato> oLista=service.findByVigencia(true);
        if(oLista.isEmpty()){
            return null;
        }else{
            return oLista.get(0);    
        }
    }
    
    @GetMapping("/findByVigenciaTrue")
    public List<TiempoEdicionDato> findByVigenciaTrue()
    {
        return service.findByVigencia(true);
    }
    
    @GetMapping("/findByVigenciaFalse")
    public List<TiempoEdicionDato> findByVigenciaFalse()
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
        List<TiempoEdicionDato> listaDatos=service.findAll();
        for (TiempoEdicionDato dato : listaDatos) {
            dato.setFechaActualizacion(ov.fechaActual());
            dato.setVigencia(false);
            service.save(dato);
        }
        TiempoEdicionDato oC=(TiempoEdicionDato) service.findById(id);
        oC.setFechaActualizacion(ov.fechaActual());
        oC.setVigencia(true);
        service.save(oC);
    }
    
}
