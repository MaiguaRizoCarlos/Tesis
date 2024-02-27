package GIDAC.controladores;



import GIDAC.modelo.DashDatos;
import GIDAC.modelo.contadorDashAdmin;
import GIDAC.servicios.DatoRecolectadoService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
        

@RestController
@RequestMapping("/dash")
@CrossOrigin("*")
public class DashBoardController {

    @Autowired
    private DatoRecolectadoService service;
    
    //investigador
    @GetMapping("/obtener-por-altura")
    public List<DashDatos> obtenerAlturasMasUsuadas(){
        List<Object[]> listaObject= service.obtenerAlturasMasUsuadas();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        Double dato1=0.0;
        String dato3="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(Double) objeto[0];
            dato3=(String) objeto[1];
            BigInteger bigIntegerValue =(BigInteger) objeto[2];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1+" "+ dato3);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    @GetMapping("/obtener-por-unidad-medida")
    public List<DashDatos> obtenerUnidadesDeMedidaMasUsuadas(){
        List<Object[]> listaObject= service.obtenerUnidadesDeMedidaMasUsuadas();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        String dato2="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            dato2=(String) objeto[1];
            BigInteger bigIntegerValue =(BigInteger) objeto[2];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1+ " - "+ dato2);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;
    }
    
    
    @GetMapping("/obtener-por-profunidad")
    public List<DashDatos> obteneProfundidadesMasUsuadas(){
        List<Object[]> listaObject= service.obteneProfundidadesMasUsuadas();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        double dato1=0;
        double dato2=0;
        String dato3="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(double) objeto[0];
            dato2=(double) objeto[1];
            dato3=(String) objeto[2];
            BigInteger bigIntegerValue =(BigInteger) objeto[3];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1+ " - "+ dato2+ " "+ dato3);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia; 
    }
    
    @GetMapping("/obtener-por-varible-valor-promedio")
    public List<DashDatos> obteneValorPromedioVariablesNumericas(){
        List<Object[]> listaObject= service.obteneValorPromedioVariablesNumericas();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        String dato2="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            dato2=(String) objeto[1];
            dato4=(double) objeto[2];
            if(dato2.equals("NA")){
                dato2="";
            }
            BigDecimal bd = new BigDecimal(dato4);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            dato4 = bd.doubleValue();
            variable.setIndicador(dato1);
            variable.setUnidadMedida(dato2);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia; 
    }
    
    
    @GetMapping("/obtener-por-cantidad-variable")
    public List<DashDatos> obteneCantidadDatosPorVariable(){
        List<Object[]> listaObject= service.obteneCantidadDatosPorVariable();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            BigInteger bigIntegerValue =(BigInteger) objeto[1];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia; 
    }
    
    //administradior
    
    
    public static void copiarDatosSinRepetir(List<DashDatos> listaDestino, List<DashDatos> listaOrigen) {
        for (DashDatos datoEliminar : listaOrigen) {
            boolean indicadorRepetido = false;
            for (DashDatos dato : listaDestino) {
                if (compararCadenasCaracteres(dato.getIndicador(),datoEliminar.getIndicador())) {
                    indicadorRepetido = true;
                    break;
                }
            }
            if (!indicadorRepetido) {
                listaDestino.add(datoEliminar);
            }
        }
    }
    
    public static boolean compararCadenasCaracteres(String cadena1, String cadena2) {
        String formattedCadena1 = cadena1.replaceAll("\\s", "").toLowerCase();
        String formattedCadena2 = cadena2.replaceAll("\\s", "").toLowerCase();
        return formattedCadena1.equals(formattedCadena2);
    }
    
    
    @GetMapping("/obtener-por-area-investigadion")
    public List<DashDatos> obtenerCantidadProyectosPorAreaInvestigacion(){
        List<Object[]> listaObject= service.obtenerCantidadProyectosPorAreaInvestigacion();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        
        String dato1="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            BigInteger bigIntegerValue =(BigInteger) objeto[1];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        List<Object[]> listaObjectEliminar= service.obtenerCantidadProyectosPorAreaInvestigacionNulos();
        List<DashDatos> listaEquivalenciaEliminar = new ArrayList<>();
        for (Object[] objeto : listaObjectEliminar) {
            DashDatos variable = new DashDatos();
            variable.setIndicador((String) objeto[0]);
            variable.setValor(0.0);
            listaEquivalenciaEliminar.add(variable);
        }
        
        copiarDatosSinRepetir(listaEquivalencia, listaEquivalenciaEliminar);
        return listaEquivalencia; 
        
    }
    
    @GetMapping("/obtener-por-sector-impacto")
    public List<DashDatos> obtenerCantidadProyectosPorSectorImpacto(){
        List<Object[]> listaObject= service.obtenerCantidadProyectosPorSectorImpacto();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            BigInteger bigIntegerValue =(BigInteger) objeto[1];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        
        List<Object[]> listaObjectEliminar= service.obtenerCantidadProyectosPorSectorImpactoNulos();
        List<DashDatos> listaEquivalenciaEliminar = new ArrayList<>();
        for (Object[] objeto : listaObjectEliminar) {
            DashDatos variable = new DashDatos();
            variable.setIndicador((String) objeto[0]);
            variable.setValor(0.0);
            listaEquivalenciaEliminar.add(variable);
        }
        
        copiarDatosSinRepetir(listaEquivalencia, listaEquivalenciaEliminar);
        
        return listaEquivalencia; 
    }
           
    
    @GetMapping("/obtener-por-linea-investigacion")
    public List<DashDatos> obtenerCantidadProyectosPorLineaInvestigacion(){
        List<Object[]> listaObject= service.obtenerCantidadProyectosPorLineaInvestigacion();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            BigInteger bigIntegerValue =(BigInteger) objeto[1];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        
        List<Object[]> listaObjectEliminar= service.obtenerCantidadProyectosPorLineaInvestigacionNulos();
        List<DashDatos> listaEquivalenciaEliminar = new ArrayList<>();
        for (Object[] objeto : listaObjectEliminar) {
            DashDatos variable = new DashDatos();
            variable.setIndicador((String) objeto[0]);
            variable.setValor(0.0);
            listaEquivalenciaEliminar.add(variable);
        }
        
        copiarDatosSinRepetir(listaEquivalencia, listaEquivalenciaEliminar);
        return listaEquivalencia;
    }
    
    
    @GetMapping("/obtener-por-tipo-proyecto")
    public List<DashDatos> obtenerCantidadProyectosPorTipoProyecto(){
        List<Object[]> listaObject= service.obtenerCantidadProyectosPorTipoProyecto();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            BigInteger bigIntegerValue =(BigInteger) objeto[1];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        
        List<Object[]> listaObjectEliminar= service.obtenerCantidadProyectosPorTipoProyectoNulos();
        List<DashDatos> listaEquivalenciaEliminar = new ArrayList<>();
        for (Object[] objeto : listaObjectEliminar) {
            DashDatos variable = new DashDatos();
            variable.setIndicador((String) objeto[0]);
            variable.setValor(0.0);
            listaEquivalenciaEliminar.add(variable);
        }
        
        copiarDatosSinRepetir(listaEquivalencia, listaEquivalenciaEliminar);
        
        return listaEquivalencia;
    }
    
    
    @GetMapping("/obtener-por-tipo-investigacion")
    public List<DashDatos> obtenerCantidadProyectosPorTipoInvestigacion(){
        List<Object[]> listaObject= service.obtenerCantidadProyectosPorTipoInvestigacion();
        
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            BigInteger bigIntegerValue =(BigInteger) objeto[1];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        
        List<Object[]> listaObjectEliminar= service.obtenerCantidadProyectosPorTipoInvestigacionNulos();
        List<DashDatos> listaEquivalenciaEliminar = new ArrayList<>();
        for (Object[] objeto : listaObjectEliminar) {
            DashDatos variable = new DashDatos();
            variable.setIndicador((String) objeto[0]);
            variable.setValor(0.0);
            listaEquivalenciaEliminar.add(variable);
        }
        
        copiarDatosSinRepetir(listaEquivalencia, listaEquivalenciaEliminar);
        
        return listaEquivalencia;
    }
    
    
    @GetMapping("/obtener-por-estado-proyecto")
    public List<DashDatos> obtenerCantidadProyectosPorEstadoProyecto(){
        List<Object[]> listaObject= service.obtenerCantidadProyectosPorEstadoProyecto();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            BigInteger bigIntegerValue =(BigInteger) objeto[1];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        
        List<Object[]> listaObjectEliminar= service.obtenerCantidadProyectosPorEstadoProyectoNulos();
        List<DashDatos> listaEquivalenciaEliminar = new ArrayList<>();
        for (Object[] objeto : listaObjectEliminar) {
            DashDatos variable = new DashDatos();
            variable.setIndicador((String) objeto[0]);
            variable.setValor(0.0);
            listaEquivalenciaEliminar.add(variable);
        }
        
        copiarDatosSinRepetir(listaEquivalencia, listaEquivalenciaEliminar);
        
        return listaEquivalencia;
    }
    
    
    @GetMapping("/obtener-por-director")
    public List<DashDatos> obtenerCantidadProyectosPorDirector(){
        List<Object[]> listaObject= service.obtenerCantidadProyectosPorDirector();
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        String dato1="";
        String dato2="";
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DashDatos variable = new DashDatos();
            dato1=(String) objeto[0];
            dato2=(String) objeto[1];
            BigInteger bigIntegerValue =(BigInteger) objeto[2];
            dato4=bigIntegerValue.doubleValue();
            variable.setIndicador(dato1+ " "+ dato2);
            variable.setValor(dato4);
            listaEquivalencia.add(variable);
        }
        
        List<Object[]> listaObjectEliminar= service.obtenerCantidadProyectosPorDirectorNulos();
        List<DashDatos> listaEquivalenciaEliminar = new ArrayList<>();
        for (Object[] objeto : listaObjectEliminar) {
            DashDatos variable = new DashDatos();
            variable.setIndicador((String) objeto[0]+" "+(String) objeto[1]);
            variable.setValor(0.0);
            listaEquivalenciaEliminar.add(variable);
        }
        
        copiarDatosSinRepetir(listaEquivalencia, listaEquivalenciaEliminar);
        
        return listaEquivalencia;
    }
    
   
    //director
    @GetMapping("/obtener-estado-proyectos/{id}")
    public List<contadorDashAdmin> obtenerEstadoProyecto(@PathVariable Integer id){
        BigInteger bigIntegerValue;
        contadorDashAdmin oc=new contadorDashAdmin();
        List<contadorDashAdmin> listaEquivalencia = new ArrayList<>();
        bigIntegerValue= service.obtenerCantidadProyectosPorEstadoPublico(id);
        oc.setContInves(bigIntegerValue.intValue());
        bigIntegerValue= service.obtenerCantidadProyectosPorEstadoPrivado(id);
        oc.setContVisit(bigIntegerValue.intValue());
        bigIntegerValue= service.obtenerCantidadProyectosVigentes(id);
        oc.setContAdmin(bigIntegerValue.intValue());
        bigIntegerValue= service.obtenerCantidadProyectosEliminados(id);
        oc.setContDirec(bigIntegerValue.intValue());
        listaEquivalencia.add(oc);
        return listaEquivalencia;
    }
    
    //director
    @GetMapping("/obtener-indicadores-proyecto")
    public List<contadorDashAdmin> obtenerIndicadoresProyecto(){
        BigInteger bigIntegerValue;
        contadorDashAdmin oc=new contadorDashAdmin();
        List<contadorDashAdmin> listaEquivalencia = new ArrayList<>();
        bigIntegerValue= service.obtenerCantidadProyectosPorEstadoPublicoAdminDatos();
        oc.setContInves(bigIntegerValue.intValue());
        bigIntegerValue= service.obtenerCantidadProyectosPorEstadoPrivadoAdminDatos();
        oc.setContVisit(bigIntegerValue.intValue());
        bigIntegerValue= service.obtenerCantidadProyectosVigentesAdminDatos();
        oc.setContAdmin(bigIntegerValue.intValue());
        bigIntegerValue= service.obtenerCantidadProyectosEliminadosAdminDatos();
        oc.setContDirec(bigIntegerValue.intValue());
        listaEquivalencia.add(oc);
        return listaEquivalencia;
    }
    
    
    @GetMapping("/obtener-solicitudes-descarga/{id}")
    public List<DashDatos> obtenerSolicitudesDescarga(@PathVariable Integer id){
        
         List<DashDatos> listaEquivalencia = new ArrayList<>();
        
        BigInteger bigIntegerValue;
        bigIntegerValue= service.obtenerCantidadSolicitudesDescargaSolicitado(id);
        DashDatos variable1 = new DashDatos();
        variable1.setIndicador("Pendientes");
        variable1.setValor(bigIntegerValue.doubleValue());
        listaEquivalencia.add(variable1);
        
        bigIntegerValue= service.obtenerCantidadSolicitudesDescargaAprobadas(id);
        DashDatos variable2 = new DashDatos();
        variable2.setIndicador("Aprobados");
        variable2.setValor(bigIntegerValue.doubleValue());
        listaEquivalencia.add(variable2);
        
        bigIntegerValue= service.obtenerCantidadSolicitudesDescargaRechazadas(id);
        DashDatos variable3 = new DashDatos();
        variable3.setIndicador("Rechazados");
        variable3.setValor(bigIntegerValue.doubleValue());
        listaEquivalencia.add(variable3);
        
        bigIntegerValue= service.obtenerCantidadSolicitudesDescarga(id);
        DashDatos variable4 = new DashDatos();
        variable4.setIndicador("Total de solicitudes");
        variable4.setValor(bigIntegerValue.doubleValue());
        listaEquivalencia.add(variable4);
        
        return listaEquivalencia;
    }
    
    
    @GetMapping("/obtener-solicitudes-actualizar/{id}")
    public List<DashDatos> obtenerSolicitudesActualizar(@PathVariable Integer id){
        
        List<DashDatos> listaEquivalencia = new ArrayList<>();
        
        BigInteger bigIntegerValue;
        bigIntegerValue= service.obtenerCantidadSolicitudesActualizarSolicitado(id);
        DashDatos variable1 = new DashDatos();
        variable1.setIndicador("Pendientes");
        variable1.setValor(bigIntegerValue.doubleValue());
        listaEquivalencia.add(variable1);
        
        bigIntegerValue= service.obtenerCantidadSolicitudesActualizarAceptado(id);
        DashDatos variable2 = new DashDatos();
        variable2.setIndicador("Aprobados");
        variable2.setValor(bigIntegerValue.doubleValue());
        listaEquivalencia.add(variable2);
        
        bigIntegerValue= service.obtenerCantidadSolicitudesRechazado(id);
        DashDatos variable3 = new DashDatos();
        variable3.setIndicador("Rechazados");
        variable3.setValor(bigIntegerValue.doubleValue());
        listaEquivalencia.add(variable3);
        
        bigIntegerValue= service.obtenerCantidadSolicitudesActualizar(id);
        DashDatos variable4 = new DashDatos();
        variable4.setIndicador("Total de solicitudes");
        variable4.setValor(bigIntegerValue.doubleValue());
        listaEquivalencia.add(variable4);
        
        return listaEquivalencia;
    }
    
    @GetMapping("/obtener-grafica-solicitudes-por-anio/{id}")
    public List<Object[]> obtenerGraficaSolicitudesPorAnio(@PathVariable Integer id){
        return service.obtenerGraficaSolicitudesPorAnio(id);
    }
    
    @GetMapping("/obtener-grafica-solicitudes-por-mes/{id}")
    public List<Object[]> obtenerGraficaSolicitudesPorMes(@PathVariable Integer id){
        return service.obtenerGraficaSolicitudesPorMes(id);
    }
    
    @GetMapping("/obtener-grafica-solicitudes-por-dia/{id}")
    public List<Object[]> obtenerGraficaSolicitudesPorDia(@PathVariable Integer id){
        return service.obtenerGraficaSolicitudesPorDia(id);
    }
    
    @GetMapping("/obtener-grafica-solicitudes-actualizar-por-anio/{id}")
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorAnio(@PathVariable Integer id){
        return service.obtenerGraficaSolicitudesActualizarPorAnio(id);
    }
    
    @GetMapping("/obtener-grafica-solicitudes-actualizar-por-mes/{id}")
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorMes(@PathVariable Integer id){
        return service.obtenerGraficaSolicitudesActualizarPorMes(id);
    }
    
    @GetMapping("/obtener-grafica-solicitudes-actualizar-por-dia/{id}")
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorDia(@PathVariable Integer id){
        return service.obtenerGraficaSolicitudesActualizarPorDia(id);
    }
    
    @GetMapping("/obtener-grafica-acceso-por-anio")
    public List<Object[]> obtenerGraficaAccesoPorAnio(){
        return service.obtenerGraficaAccesoPorAnio();
    }
    
    @GetMapping("/obtener-grafica-acceso-por-mes")
    public List<Object[]> obtenerGraficaAccesoPorMes(){
        return service.obtenerGraficaAccesoPorMes();
    }
    
    @GetMapping("/obtener-grafica-acceso-por-dia")
    public List<Object[]> obtenerGraficaAccesoPorDia(){
        return service.obtenerGraficaAccesoPorDia();
    }
    
    
    @GetMapping("/obtener-grafica-usuario-por-rol")
    public List<Object[]> obtenerGraficaUsuariosPorRol(){
        return service.obtenerGraficaUsuariosPorRol();
    }
  
    
    @GetMapping("/obtener-solictudes-actualizar-investigador/{id}")
    public List<contadorDashAdmin> obtenerSolitudesActualizarInvetigador(@PathVariable Integer id){
        BigInteger bigIntegerValue;
        contadorDashAdmin oc=new contadorDashAdmin();
        List<contadorDashAdmin> listaEquivalencia = new ArrayList<>();
        bigIntegerValue= service.obtenerCantidadSolicitudesActualizarInvestigador(id);
        oc.setContInves(bigIntegerValue.intValue());
        bigIntegerValue= service.obtenerCantidadSolicitudesActualizarAcesptadoInvestigador(id);
        oc.setContVisit(bigIntegerValue.intValue());
        bigIntegerValue= service.obtenerCantidadSolicitudesActualizarRechazadoInvestigador(id);
        oc.setContAdmin(bigIntegerValue.intValue());
        listaEquivalencia.add(oc);
        return listaEquivalencia;
    }
    
}
