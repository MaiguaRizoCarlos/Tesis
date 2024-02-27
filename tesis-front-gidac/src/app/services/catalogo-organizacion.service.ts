import { Injectable } from '@angular/core';
import baserUrl from './helper';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CatalogoOrganizacionService {

  constructor(private http:HttpClient) { }

  public guardar(modelo:any){
    return this.http.post(`${baserUrl}/catalogo-organizacion/guardar-catalogo-organizacion`,modelo);
  }

  public actualizar(modelo:any){
    return this.http.put(`${baserUrl}/catalogo-organizacion/actualizar-catalogo-organizacion`,modelo);
  }

  public obtener(id:any){
    return this.http.get(`${baserUrl}/catalogo-organizacion/obtener-catalogo-organizacion/${id}`);
  }

  public obtenerPorCodigoOrganizacion(id:any){
    return this.http.get(`${baserUrl}/catalogo-organizacion/obtener-catalogo-organizacion-codigo/${id}`);
  }
  

  public obtenerCatalogoPorOrganizacion(id:any){
    return this.http.get(`${baserUrl}/catalogo-organizacion/listar-por-organizacion/${id}`);
  }

  public obtenerCatalogoPorOrganizacionEliminado(id:any){
    return this.http.get(`${baserUrl}/catalogo-organizacion/listar-por-organizacion-eliminado/${id}`);
  }

  public obtenerPorVariableSistemaVigente(id:any){
    return this.http.get(`${baserUrl}/catalogo-organizacion/listar-por-variable-sistema-vigente/${id}`);
  }

  public obtenerPorVariableSistemaEliminado(id:any){
    return this.http.get(`${baserUrl}/catalogo-organizacion/listar-por-variable-sistema-eliminado/${id}`);
  }
  

  public listar(){
    return this.http.get(`${baserUrl}/catalogo-organizacion/listar-catalogo-organizacion`);
  }

  public eliminar(id:any){
    return this.http.delete(`${baserUrl}/catalogo-organizacion/eliminar-catalogo-organizacion/${id}`); 
  }

  public restablecer(id:any){
    return this.http.delete(`${baserUrl}/catalogo-organizacion/reestablecer/${id}`); 
  }

  public comprobarArchivo(modelo:any){
    return this.http.post(`${baserUrl}/catalogo-organizacion/comprobar-archivo`,modelo);
  }

  public importarArchivo(modelo:any){
    return this.http.post(`${baserUrl}/catalogo-organizacion/importar-archivo`,modelo);
  }

  
}
  