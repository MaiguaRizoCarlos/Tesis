package GIDAC.controladores;


import GIDAC.modelo.contadorDashAdmin;
import GIDAC.servicios.AccesoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.VisitantesService;
import GIDAC.servicios.UsuarioService;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class DashAdminController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AccesoService accesoService;
 
    @GetMapping("/contador-dash")
    public List<contadorDashAdmin> obtenerTotal(){
        List<Object[]> usuario = this.usuarioService.obtenerUsuariosPorRol();

        Object[] adminArray = usuario.get(0);
        Object[] direcArray = usuario.get(1);
        Object[] invesArray = usuario.get(2);
        
        Integer admin = ((java.math.BigInteger) adminArray[1]).intValue();
        Integer direc = ((java.math.BigInteger) direcArray[1]).intValue();
        Integer inves = ((java.math.BigInteger) invesArray[1]).intValue();
        
        contadorDashAdmin oC=new contadorDashAdmin();
        List<contadorDashAdmin> oCC=new ArrayList();
        oC.setContAdmin(admin);
        oC.setContDirec(direc);
        oC.setContInves(inves);
        oC.setContVisit(accesoService.findAll().size());
        oCC.add(oC);
        return oCC;
    }

}
