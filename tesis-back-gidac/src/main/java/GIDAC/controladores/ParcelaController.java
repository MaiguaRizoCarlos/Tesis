package GIDAC.controladores;


import GIDAC.modelo.Parcela;
import GIDAC.servicios.ParcelaService;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcela")
@CrossOrigin("*")
public class ParcelaController {

    
    @Autowired
    private ParcelaService service;

    @PostMapping("/guardar-parcela")
    public Object guardar(@RequestBody Parcela oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar-parcela")
    public Object actualizar(@RequestBody Parcela oC)
    {
        Parcela oD=(Parcela) service.buscarPorId(oC.getIdParcela());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-parcela/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-parcela")
    public List<Parcela> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-parcela/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Parcela oC=(Parcela) service.buscarPorId(id);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        oC.setVigencia(false);
        service.guardar(oC);    
    }
    
    @GetMapping("/obtener-parcela/por-conglomerado/{id}")
    public List obtenerPorConglomerado(@PathVariable Integer id)
    {
        List<Parcela> listaCompleta=service.buscarPorConglomerado(id);
        List<Object[]> datos=service.obtenerParcelasUsadas(id);
        for (Parcela lista : listaCompleta) {
            boolean aux=true;
            for (Object[] dato : datos) {
                int num=(Integer)dato[0];
                if(lista.getIdParcela()==num){
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
        return service.buscarPorConglomerado(id);
    }
    
    
}
