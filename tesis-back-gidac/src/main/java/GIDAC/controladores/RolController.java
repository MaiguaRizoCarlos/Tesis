package GIDAC.controladores;

import GIDAC.modelo.InformacionEcoAndes;
import GIDAC.modelo.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.RolService;

@RestController
@RequestMapping("/rol")
@CrossOrigin("*")
public class RolController {

    
    @Autowired
    private RolService rolService;

    
    @GetMapping("/mostrar-roles")
    public List<Rol> mostrarRoles()
    {
        return rolService.findAll();
    }
    @GetMapping("/obtener-rol/{idRol}")
    public Object getRol(@PathVariable("idRol") Integer idRol)
    {
        return rolService.findById(idRol);
    }
    @PostMapping("/guardar-rol")
    public Object guardarRol(@RequestBody Rol oC)
    {
        return rolService.save(oC);    
    }
}
