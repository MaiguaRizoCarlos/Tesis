import { Injectable } from '@angular/core';
import baserUrl from './helper';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VariableService {

 

  constructor(private http:HttpClient) { }

  public guardar(modelo:any){
    return this.http.post(`${baserUrl}/variable/guardar-variable`,modelo);
  }

  public actualizarDatosGeneralesVariable(modelo:any){
    return this.http.put(`${baserUrl}/variable/actualizar-datos-generales-variable`,modelo);
  }

  public guardarDatosVariable(modelo:any){
    return this.http.post(`${baserUrl}/variable/guardar-datos-variable`,modelo);
  }
  
  public actualizar(modelo:any){
    return this.http.put(`${baserUrl}/variable/guardar-variable`,modelo);
  }

  public obtener(id:any){
    return this.http.get(`${baserUrl}/variable/obtener-variable/${id}`);
  }

  public listar(){
    return this.http.get(`${baserUrl}/variable/listar-variable`);
  }

  public listarVariablesDifusion(id:any, idOrganizacion:any){
    return this.http.get(`${baserUrl}/variable/listar-variable-difucion/${id}/${idOrganizacion}`);
  }

  public listarCompletas(){
    return this.http.get(`${baserUrl}/variable/listar-variable-completas`);
  }

  public listarVigetes(){
    return this.http.get(`${baserUrl}/variable/variables-vigentes`);
  }

  public listarNoVigetes(){
    return this.http.get(`${baserUrl}/variable/variables-no-vigentes`);
  }

  public listarCompletasInvestigador(){
    return this.http.get(`${baserUrl}/variable/listar-variable-completas-investigador`);
  }

  public listarIncompletas(){
    return this.http.get(`${baserUrl}/variable/listar-variable-incompletas`);
  }

  public eliminar(id:any){
    return this.http.delete(`${baserUrl}/variable/eliminar-variable/${id}`); 
  }

  public activar(id:any){
    return this.http.delete(`${baserUrl}/variable/activar-variable/${id}`); 
  }

  public listarFamiliasVariables(){
    return this.http.get(`${baserUrl}/variable-familia/listar-familias-difusion`);
  }

  public listarFamiliasVariablesInvestigador(idFamilia:any, idProyecto:any){
    return this.http.get(`${baserUrl}/variable/listar-variable-investigador/${idFamilia}/${idProyecto}`);
  }


  public obtenerVariablesDescargarProyecto(id:any, idOrganizacion:any, codigoDataset:any){
    return this.http.get(`${baserUrl}/variable/listar-variable-descargar-proyecto/${id}/${idOrganizacion}/${codigoDataset}`);
  }

  public descargarExcel(): Observable<Blob> {
    return this.http.get(`${baserUrl}/variable/excel`, { responseType: 'blob' });
  }

  public descargarPDF(): Observable<Blob> {
    return this.http.get(`${baserUrl}/variable/pdf`, { responseType: 'blob' });
  }

  
}