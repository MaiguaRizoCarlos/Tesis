package GIDAC.controladores;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.Familia;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableFamilia;
import GIDAC.modelo.VariableFamiliaDifusion;
import GIDAC.servicios.UnidadMedidaVariableService;
import GIDAC.servicios.VariableFamiliaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variable-familia")
@CrossOrigin("*")
public class VariablFamiliaController {

    
    @Autowired
    private VariableFamiliaService service;
    

    @PostMapping("/guardar")
    public Object guardar(@RequestBody VariableFamilia oC) throws JsonProcessingException{
//        oC.setIdVariable(oC.getVariable().getIdVariable());
//        oC.setIdUnidadMedida(oC.getUnidadMedida().getIdUnidadMedida());
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar-familia")
    public Object actualizarDatosFamilia(@RequestParam("variable") String datosJson, @RequestParam("listaFamilia") String datosJsonFamilia) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        Variable variableAux = new ObjectMapper().readValue(datosJson, Variable.class);
        
        List<Familia> familias = objectMapper.readValue(datosJsonFamilia, new TypeReference<List<Familia>>() {});
        
        List<VariableFamilia> variableFamilia= service.buscarPorVariable(variableAux.getIdVariable(), true);
        
        cValidaciones ov=new cValidaciones();
        
        for(VariableFamilia dato1:variableFamilia) {
            boolean op=false;
            for(Familia dato2:familias) {
                if(dato1.getFamilia().getIdFamilia()==dato2.getIdFamilia()){
                    op=true;
                    break;
                }
            }
            if(op==false){
                dato1=(VariableFamilia) service.buscarPorVariableFamilia(dato1.getVariable().getIdVariable(), dato1.getFamilia().getIdFamilia(), true);
                dato1.setVigencia(false);
                dato1.setFechaActualizacion(ov.fechaActual());
                service.guardar(dato1);    
            }
        }
        
        for(Familia familia:familias) {
            
            VariableFamilia oVariableFamilia=new VariableFamilia();
            Familia oFamilia=new Familia();
            oFamilia.setIdFamilia(familia.getIdFamilia());
            if(service.buscarPorVariableFamilia(variableAux.getIdVariable(), oFamilia.getIdFamilia())==null){
                oVariableFamilia.setFechaCreacion(ov.fechaActual());
            }else{
                VariableFamilia oFAux=(VariableFamilia) service.buscarPorVariableFamilia(variableAux.getIdVariable(), oFamilia.getIdFamilia());
                oVariableFamilia.setFechaCreacion(oFAux.getFechaCreacion());
                oVariableFamilia.setFechaActualizacion(ov.fechaActual());
            }
            oVariableFamilia.setVigencia(true);
            oVariableFamilia.setFamilia(oFamilia);
            oVariableFamilia.setVariable(variableAux);
            oVariableFamilia.setIdVariable(variableAux.getIdVariable());
            oVariableFamilia.setIdFamilia(oFamilia.getIdFamilia());
            service.guardar(oVariableFamilia);
        }
        
        return variableAux;
    }
    
    @PutMapping("/actualizar")
    public Object actualizar(@RequestBody VariableFamilia oC) throws JsonProcessingException{
//        oC.setIdVariable(oC.getVariable().getIdVariable());
//        oC.setIdUnidadMedida(oC.getUnidadMedida().getIdUnidadMedida());
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{idVariable}/{idFamilia}")
    public Object obtenerVigente(@PathVariable String idVariable,@PathVariable Integer idFamilia)
    {    
        return service.buscarPorId(idVariable, idFamilia, true);
    }
    
    @GetMapping("/listar-equivalencia-variable-vigentes")
    public List<VariableFamilia> listarVigentes()
    {    
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-equivalencia-variable-eliminados")
    public List<VariableFamilia> listarEliminados()
    {    
        return service.buscarTodos(false);
    }
    
    
    @DeleteMapping("/eliminar/{idVariable}/{idFamilia}")
    public void eliminar(@PathVariable String idVariable,@PathVariable Integer idFamilia)
    {   
        VariableFamilia oC=(VariableFamilia) service.buscarPorId(idVariable, idFamilia, true);
        oC.setVigencia(false);
        cValidaciones ov=new cValidaciones();
        oC.setFechaActualizacion(ov.fechaActual());
        service.guardar(oC);
    }
    
    @GetMapping("/listar-familia-por-variable/{idVariable}")
    public List<VariableFamilia> listarFamiliasSeleccionadas(@PathVariable Integer idVariable)
    {   
        return service.buscarPorVariable(idVariable, true);
    }
    
    
     @GetMapping("/listar-familias-difusion")
    public List<VariableFamiliaDifusion> listarFamiliasDifucion()
    {
        List<Object[]> listaObject= service.listarFamiliasDifusion();
        List<VariableFamiliaDifusion> listaFamilia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariableFamiliaDifusion variable = new VariableFamiliaDifusion();

            variable.setIdFamilia((Integer) objeto[0]);
            variable.setDescripcion((String) objeto[1]);
            listaFamilia.add(variable);
        }
        return listaFamilia;  
    }
    
    
    
    
}
