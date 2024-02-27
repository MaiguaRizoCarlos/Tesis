package GIDAC.controladores;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.Familia;
import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.ValorPermitidoUnidadMedida;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableDifusion;
import GIDAC.modelo.VariableFamilia;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.modelo.VariableView;
import GIDAC.modelo.VariablesEncontradas;
import GIDAC.servicios.CatalogoOrganizacionService;
import GIDAC.servicios.TipoVariableService;
import GIDAC.servicios.UnidadMedidaService;
import GIDAC.servicios.UnidadMedidaVariableService;
import GIDAC.servicios.ValorPermitidoService;
import GIDAC.servicios.VariableFamiliaService;
import GIDAC.servicios.VariableService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variable")
@CrossOrigin("*")
public class VariableController {

    
    @Autowired
    private VariableService service;

    @Autowired
    private CatalogoOrganizacionService catalogoOrganizacionService;
    
    @Autowired
    private TipoVariableService tipoVariableService;
    
    @Autowired
    private ValorPermitidoService valorPermitidoService;
    
    @Autowired
    private UnidadMedidaVariableService unidadMedidaVariableService;
    
    @Autowired
    private UnidadMedidaService unidadMedidaService;
    
    @Autowired
    private VariableFamiliaService variableFamiliaService;
    
    @PostMapping("/guardar-variable")
    public Object guardar(@RequestBody Variable oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/guardar-datos-variable")
    public Object guardarDatosVariable(@RequestParam("variable") String datosJson,@RequestParam("catalogoOrganizacion") String datosJsonCatalogo, @RequestParam("listaValoresPermitidos") String datosJsonVariables, @RequestParam("listaFamilia") String datosJsonFamilia) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        Variable variableAux = new ObjectMapper().readValue(datosJson, Variable.class);
        
        CatalogoOrganizacion variableAuxCatalogo = new ObjectMapper().readValue(datosJsonCatalogo, CatalogoOrganizacion.class);
        List<ValorPermitidoUnidadMedida> valoresPermitidos = objectMapper.readValue(datosJsonVariables, new TypeReference<List<ValorPermitidoUnidadMedida>>() {});
        List<Familia> familias = objectMapper.readValue(datosJsonFamilia, new TypeReference<List<Familia>>() {});
        cValidaciones oVx=new cValidaciones();
        variableAux.setFechaCreacion(oVx.fechaActual());
        variableAux=(Variable) service.guardar(variableAux);
        variableAuxCatalogo.setVariable(variableAux);
        variableAuxCatalogo.setFechaCreacion(oVx.fechaActual());
        variableAuxCatalogo.setVariableSistema(true);
        catalogoOrganizacionService.guardar(variableAuxCatalogo);
        
        for(Familia familia:familias) {
            VariableFamilia oVariableFamilia=new VariableFamilia();
            Familia oFamilia=new Familia();
            oFamilia.setIdFamilia(familia.getIdFamilia());
                    
            oVariableFamilia.setFamilia(oFamilia);
            oVariableFamilia.setVariable(variableAux);
            oVariableFamilia.setIdVariable(variableAux.getIdVariable());
            oVariableFamilia.setIdFamilia(oFamilia.getIdFamilia());
            oVariableFamilia.setFechaCreacion(oVx.fechaActual());
            variableFamiliaService.guardar(oVariableFamilia);
        }
        
        if(variableAux.getTipoVariable().getIdTipoVariable()==1){
            for(ValorPermitidoUnidadMedida valorPermitido:valoresPermitidos) {
                VariableUnidadMedida VUM=new VariableUnidadMedida();
                List<VariableUnidadMedida> oCAux= unidadMedidaVariableService.buscarPorUnidadMedidaAndVariable(valorPermitido.getIdUnidadMedida(), variableAux.getIdVariable());
                if(oCAux.isEmpty()){
                    UnidadMedida unidadMedidaAux=new UnidadMedida();
                    unidadMedidaAux.setIdUnidadMedida(valorPermitido.getIdUnidadMedida());
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
                
                if(valorPermitidoService.obtenerPorMaxMinPerVariableUnidadMedida(valorPermitido.getValorMaximo(), valorPermitido.getValorMinimo(), valorPermitido.getValorPermitido(), VUM.getVariable().getIdVariable(), VUM.getUnidadMedida().getIdUnidadMedida())==null){
                    ValorPermitidoGuardar.setFechaCreacion(oVx.fechaActual());
                }else{
                    ValorPermitidoGuardar=(ValorPermitido) valorPermitidoService.obtenerPorMaxMinPerVariableUnidadMedida(valorPermitido.getValorMaximo(), valorPermitido.getValorMinimo(), valorPermitido.getValorPermitido(), VUM.getVariable().getIdVariable(), VUM.getUnidadMedida().getIdUnidadMedida());
                    ValorPermitidoGuardar.setFechaActualizacion(oVx.fechaActual());
                }
                ValorPermitidoGuardar.setVigencia(true);
                ValorPermitidoGuardar.setVariableUnidadMedida(VUM);
                ValorPermitidoGuardar.setValorMaximo(valorPermitido.getValorMaximo());
                ValorPermitidoGuardar.setValorMinimo(valorPermitido.getValorMinimo());
                ValorPermitidoGuardar.setValorPermitido(valorPermitido.getValorPermitido());
                valorPermitidoService.guardar(ValorPermitidoGuardar);
            }
        }else{
            for(ValorPermitidoUnidadMedida valorPermitido:valoresPermitidos) {
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
                
                if(valorPermitidoService.obtenerPorPerVariableUnidadMedida(valorPermitido.getValorPermitido(), VUM.getVariable().getIdVariable(), VUM.getUnidadMedida().getIdUnidadMedida())==null){
                    ValorPermitidoGuardar.setFechaCreacion(oVx.fechaActual());
                }else{
                    ValorPermitidoGuardar=(ValorPermitido) valorPermitidoService.obtenerPorPerVariableUnidadMedida(valorPermitido.getValorPermitido(), VUM.getVariable().getIdVariable(), VUM.getUnidadMedida().getIdUnidadMedida());
                    ValorPermitidoGuardar.setFechaActualizacion(oVx.fechaActual());
                }
                ValorPermitidoGuardar.setVigencia(true);
                ValorPermitidoGuardar.setVariableUnidadMedida(VUM);
                ValorPermitidoGuardar.setValorMaximo(valorPermitido.getValorMaximo());
                ValorPermitidoGuardar.setValorMinimo(valorPermitido.getValorMinimo());
                ValorPermitidoGuardar.setValorPermitido(valorPermitido.getValorPermitido());
                valorPermitidoService.guardar(ValorPermitidoGuardar);
            }
        }
        return variableAux;
    }
    
    @PutMapping("/actualizar-datos-generales-variable")
    public void actualizarDatosGeneralesVariable(@RequestParam("variable") String datosJson,@RequestParam("catalogoOrganizacion") String datosJsonCatalogo) throws JsonProcessingException{
        Variable variableAux = new ObjectMapper().readValue(datosJson, Variable.class);
        CatalogoOrganizacion variableAuxCatalogo = new ObjectMapper().readValue(datosJsonCatalogo, CatalogoOrganizacion.class);
        
        Variable vD=(Variable) service.buscarPorId(variableAux.getIdVariable());
        
        CatalogoOrganizacion cD= (CatalogoOrganizacion) catalogoOrganizacionService.buscarPorId(variableAuxCatalogo.getIdVariableOrganizacion());
        
        cValidaciones oV=new cValidaciones();
        variableAux.setFechaCreacion(vD.getFechaCreacion());
        variableAux.setFechaActualizacion(oV.fechaActual());
        
        variableAuxCatalogo.setFechaCreacion(cD.getFechaCreacion());
        variableAuxCatalogo.setFechaActualizacion(oV.fechaActual());
        
        service.guardar(variableAux);
        catalogoOrganizacionService.guardar(variableAuxCatalogo);
    }
    
    @GetMapping("/listar-variable-descargar-proyecto/{id}/{idOrganizacion}/{codigoDataset}")
    public List<VariablesEncontradas> listarVariablesDescarga(@PathVariable Integer id, @PathVariable Integer idOrganizacion, @PathVariable Integer codigoDataset)
    {
        List<Object[]> listaObject;
        if(idOrganizacion==0){
            if(codigoDataset==0){
                listaObject= service.listarCatalogoParaPerfiladoPorProyecto(id);    
            }else{
                listaObject= service.listarCatalogoParaPerfiladoPorProyectoCodigoDataset(id, codigoDataset);    
            }
            
        }else{
            if(codigoDataset==0){
                listaObject= service.listarCatalogoParaPerfiladoPorProyectoOganizacion(id,idOrganizacion);    
            }else{
                listaObject= service.listarCatalogoParaPerfiladoPorProyectoOganizacionCodigoDataset(id,idOrganizacion, codigoDataset);    
            }
            
        }
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariableUnidadMedida((Integer) objeto[0]);
            variable.setIdVariable((Integer) objeto[1]);
            variable.setNombreVariable((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);
            variable.setNombreTipoVariable((String) objeto[4]);
            variable.setUnidadMedida((String) objeto[5]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    
    @GetMapping("/obtener-variable/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-variable")
    public List<Variable> listar()
    {
        return service.buscarTodos();
    }
    
    @GetMapping("/listar-variable-completas")
    public List<VariablesEncontradas> listarCompletas()
    {
        
        List<Object[]> listaObject= service.litsarVairbalesCompletas();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariable((Integer) objeto[0]);
            variable.setCodigoVariable((String) objeto[1]);
            variable.setNombreVariable((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);
            variable.setNombreTipoVariable((String) objeto[4]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    @GetMapping("/listar-variable-completas-investigador")
    public List<VariablesEncontradas> listarCompletasInvestigador()
    {
        List<Object[]> listaObject= service.litsarVairbalesCompletasInvestigador();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariable((Integer) objeto[0]);
            //variable.setIdVariable((Integer) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreOrganizacion((String) objeto[4]);
            variable.setNombreTipoVariable((String) objeto[5]);
            variable.setNombreVariableOrganizacion((String) objeto[3]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    
    @GetMapping("/listar-variable-incompletas-investigador")
    public List<VariablesEncontradas> listarIncompletas()
    {
        
        List<Object[]> listaObject= service.litsarVairbalesIncompletas();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariable((Integer) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreOrganizacion((String) objeto[2]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    @GetMapping("/listar-variable-difucion/{id}/{idOrganizacion}")
    public List<VariableDifusion> listarVariablesUsuarioComun(@PathVariable Integer id, @PathVariable Integer idOrganizacion)
    {
        List<Object[]> listaObject= new ArrayList<Object[]>();
        if(id==0){
            if(idOrganizacion==0){
                listaObject= service.litsarVairbalesConDatosSinFamilia();
            }else{
                listaObject= service.litsarVairbalesConDatosSinFamiliaOrganizacion(idOrganizacion);
            }
        }else{
            if(idOrganizacion==0){
                listaObject= service.litsarVairbalesConDatosConFiltroFamilia(id);
            }else{
                listaObject= listaObject= service.litsarVairbalesConDatosConFiltroFamiliaOrganizacion(id, idOrganizacion);
            }
        }
        List<VariableDifusion> listaVariables = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariableDifusion variable = new VariableDifusion();

            variable.setIdVariable((Integer) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreSIglas((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);

            listaVariables.add(variable);
        }
        return listaVariables;   
    }
    
    @GetMapping("/listar-variable-investigador/{idFamilia}/{idProyecto}")
    public List<VariableDifusion> listarVariablesInvestigador(@PathVariable Integer idFamilia,@PathVariable Integer idProyecto )
    {
        List<Object[]> listaObject= new ArrayList<Object[]>();
        if(idFamilia==0){
            listaObject= service.litsarVairbalesConDatosSinFamiliaInvestigador(idProyecto);
        }else{
            listaObject= service.litsarVairbalesConDatosConFiltroFamiliaInvestigador(idFamilia, idProyecto);
        }
        List<VariableDifusion> listaVariables = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariableDifusion variable = new VariableDifusion();

            variable.setIdVariable((Integer) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreSIglas((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);

            listaVariables.add(variable);
        }
        return listaVariables;   
    }
   
    @GetMapping("/variables-vigentes")
    public List variablesVIgentes()
    {
        return service.buscarPorVigencia(true);
    }
    
    @GetMapping("/variables-no-vigentes")
    public List variablesNoVIgentes()
    {
        return  service.buscarPorVigencia(false);
    }
    
    @DeleteMapping("/eliminar-variable/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @DeleteMapping("/activar-variable/{id}")
    public void activar(@PathVariable Integer id)
    {
        service.activar(id);
    }
    
    //Obtener pdf
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> dowloadPDF() {
        byte[] pdfBytes = service.dowloadPdf(); 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "catalogo.pdf"); 
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
    
    //Obtener excel
    @GetMapping("/excel")
    public ResponseEntity<byte[]> dowloadExcel() {
        byte[] pdfBytes = service.dowloadExcel(); 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "catalogo.xlsx"); 
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
    
}
