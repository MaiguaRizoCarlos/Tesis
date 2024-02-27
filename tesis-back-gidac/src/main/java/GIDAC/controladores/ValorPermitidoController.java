package GIDAC.controladores;


import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.ValorPermitidoUnidadMedida;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.servicios.UnidadMedidaVariableService;
import GIDAC.servicios.ValorPermitidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valor-permitido")
@CrossOrigin("*")
public class ValorPermitidoController {

    
    @Autowired
    private ValorPermitidoService service;
    
    @Autowired
    private UnidadMedidaVariableService unidadMedidaVariableService;
    

    @PostMapping("/guardar-valor-permitido")
    public Object guardar(@RequestBody ValorPermitido oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/guardar-datos-valor-permitodo")
    public Object guardarDatosVariable(@RequestParam("variable") String datosJson, @RequestParam("valoresPermitidos") String datosJsonVariables) throws JsonProcessingException{
        
        Variable variableAux = new ObjectMapper().readValue(datosJson, Variable.class);
        ValorPermitidoUnidadMedida valorPermitidoUnidadMedida = new ObjectMapper().readValue(datosJsonVariables, ValorPermitidoUnidadMedida.class);
        
        cValidaciones oVx=new cValidaciones();
        
        if(variableAux.getTipoVariable().getIdTipoVariable()==1){
                VariableUnidadMedida VUM=new VariableUnidadMedida();
                List<VariableUnidadMedida> oCAux= unidadMedidaVariableService.buscarPorUnidadMedidaAndVariable(valorPermitidoUnidadMedida.getIdUnidadMedida(), variableAux.getIdVariable());
                if(oCAux.isEmpty()){
                    UnidadMedida unidadMedidaAux=new UnidadMedida();
                    unidadMedidaAux.setIdUnidadMedida(valorPermitidoUnidadMedida.getIdUnidadMedida());
                    VUM.setVariable(variableAux);
                    VUM.setUnidadMedida(unidadMedidaAux);
                    VUM.setFechaCreacion(oVx.fechaActual());
                }else{
                    VUM=oCAux.get(0);
                    VUM.setFechaActualizacion(oVx.fechaActual());
                }
                VUM.setVigencia(true);
                VUM=(VariableUnidadMedida) unidadMedidaVariableService.guardar(VUM);
                ValorPermitido ValorPermitidoGuardar=new ValorPermitido();
                if(service.obtenerPorMaxMinPerVariableUnidadMedida(valorPermitidoUnidadMedida.getValorMaximo(), valorPermitidoUnidadMedida.getValorMinimo(), valorPermitidoUnidadMedida.getValorPermitido(), VUM.getVariable().getIdVariable(), VUM.getUnidadMedida().getIdUnidadMedida())==null){
                    ValorPermitidoGuardar.setFechaCreacion(oVx.fechaActual());
                }else{
                    ValorPermitidoGuardar=(ValorPermitido) service.obtenerPorMaxMinPerVariableUnidadMedida(valorPermitidoUnidadMedida.getValorMaximo(), valorPermitidoUnidadMedida.getValorMinimo(), valorPermitidoUnidadMedida.getValorPermitido(), VUM.getVariable().getIdVariable(), VUM.getUnidadMedida().getIdUnidadMedida());
                    ValorPermitidoGuardar.setFechaActualizacion(oVx.fechaActual());
                }
                ValorPermitidoGuardar.setVigencia(true);
                ValorPermitidoGuardar.setVariableUnidadMedida(VUM);
                ValorPermitidoGuardar.setValorMaximo(valorPermitidoUnidadMedida.getValorMaximo());
                ValorPermitidoGuardar.setValorMinimo(valorPermitidoUnidadMedida.getValorMinimo());
                ValorPermitidoGuardar.setValorPermitido(valorPermitidoUnidadMedida.getValorPermitido());
                service.guardar(ValorPermitidoGuardar);
        }else{
                VariableUnidadMedida VUM=new VariableUnidadMedida();
                List<VariableUnidadMedida> oCAux= unidadMedidaVariableService.buscarPorUnidadMedidaAndVariable(0, variableAux.getIdVariable());
                if(oCAux.isEmpty()){
                    UnidadMedida unidadMedidaAux=new UnidadMedida();
                    unidadMedidaAux.setIdUnidadMedida(0);
                    VUM.setVariable(variableAux);
                    VUM.setUnidadMedida(unidadMedidaAux);
                    VUM.setFechaCreacion(oVx.fechaActual());
                }else{
                    VUM=oCAux.get(0);
                    VUM.setFechaActualizacion(oVx.fechaActual());
                }
                VUM.setVigencia(true);
                VUM=(VariableUnidadMedida) unidadMedidaVariableService.guardar(VUM);
                
                ValorPermitido ValorPermitidoGuardar=new ValorPermitido();
                
                if(service.obtenerPorPerVariableUnidadMedida(valorPermitidoUnidadMedida.getValorPermitido(), VUM.getVariable().getIdVariable(), VUM.getUnidadMedida().getIdUnidadMedida())==null){
                    ValorPermitidoGuardar.setFechaCreacion(oVx.fechaActual());
                }else{
                    ValorPermitidoGuardar=(ValorPermitido) service.obtenerPorPerVariableUnidadMedida( valorPermitidoUnidadMedida.getValorPermitido(), VUM.getVariable().getIdVariable(), VUM.getUnidadMedida().getIdUnidadMedida());
                    ValorPermitidoGuardar.setFechaActualizacion(oVx.fechaActual());
                }
                ValorPermitidoGuardar.setVigencia(true);
                ValorPermitidoGuardar.setVariableUnidadMedida(VUM);
                ValorPermitidoGuardar.setValorMaximo(valorPermitidoUnidadMedida.getValorMaximo());
                ValorPermitidoGuardar.setValorMinimo(valorPermitidoUnidadMedida.getValorMinimo());
                ValorPermitidoGuardar.setValorPermitido(valorPermitidoUnidadMedida.getValorPermitido());
                service.guardar(ValorPermitidoGuardar);
        }
        return variableAux;
    }
    
    @GetMapping("/obtener-valor-permitido/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-valor-permitido")
    public List<ValorPermitido> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-valor-permitido/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        cValidaciones oC=new cValidaciones();
        ValorPermitido vp=(ValorPermitido) service.buscarPorId(id);
        vp.setVigencia(false);
        vp.setFechaActualizacion(oC.fechaActual());
        service.guardar(vp);    
        List<ValorPermitido> vpAux= service.listarPorVigenciaVariableUnidadMedida(true, vp.getVariableUnidadMedida().getVariable().getIdVariable(), vp.getVariableUnidadMedida().getUnidadMedida().getIdUnidadMedida());
        if (vpAux.isEmpty()) {
            //VariableUnidadMedida VUM=(VariableUnidadMedida) unidadMedidaVariableService.buscarPorUnidadMedidaAndVariable(vp.getVariableUnidadMedida().getUnidadMedida().getIdUnidadMedida(), vp.getVariableUnidadMedida().getVariable().getIdVariable());
            VariableUnidadMedida VUM=vp.getVariableUnidadMedida();
            VUM.setFechaActualizacion(oC.fechaActual());
            VUM.setVigencia(false);
            unidadMedidaVariableService.guardar(VUM);
        }
    }
    
    @GetMapping("/listar-valor-permitido/por-variable/{id}")
    public List<ValorPermitido> listarPorVariable(@PathVariable Integer id)
    {
        return service.obtenerPorVariableUnidadMedida(id);
    }
    
    @GetMapping("/por-variable/{id}")
    public List<ValorPermitido> listarPorVariableIdVariable(@PathVariable Integer id)
    {
        return service.listarPorVariable(id);
    }
    
    
}
