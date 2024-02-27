package GIDAC.controladores;


import GIDAC.modelo.Conglomerado;
import GIDAC.servicios.ConglomeradoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conglomerado")
@CrossOrigin("*")
public class ConglomeradoController {

    
    @Autowired
    private ConglomeradoService service;

    @PostMapping("/guardar-conglomerado")
    public Object guardar(@RequestBody Conglomerado oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar-conglomerado")
    public Object actualizar(@RequestBody Conglomerado oC)
    {
        Conglomerado oD=(Conglomerado) service.buscarPorId(oC.getIdConglomerado());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-conglomerado/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-conglomerado")
    public List<Conglomerado> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-conglomerado/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Conglomerado oC=(Conglomerado) service.buscarPorId(id);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        oC.setVigencia(false);
        service.guardar(oC);    
    }
    
    
    
    @GetMapping("/obtener-conglomerado/por-proyecto/{id}")
    public List<Conglomerado> buscarPorProyectoInvestigacion(@PathVariable Integer id){
        
        List<Conglomerado> listaCompleta=service.buscarPorProyectoInvestigacion(id);
        List<Object[]> datos=service.obtenerConglomeradosUsados(id);
        for (Conglomerado lista : listaCompleta) {
            boolean aux=true;
            for (Object[] dato : datos) {
                int num=(Integer)dato[0];
                if(lista.getIdConglomerado()==num){
                    lista.setEditable(false);
                    service.guardar(lista);    
                    aux=false;  
                    break;
                }
            }
            if(aux==true){
                if(lista.isEditable()==false){
                    lista.setEditable(true);
                    service.guardar(lista);   
                }
            }
        }
        
        return service.buscarPorProyectoInvestigacion(id);
    }
    
}
