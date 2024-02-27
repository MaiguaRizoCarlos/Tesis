/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

public class Perfilado{
    
    
    private Integer idVariable;
    private Integer numeroColumna;
    private String nombreVariable;
    private String unidadMedida;
    private String nombreVariableOrganizacion;
    private String nombreOrganizacion;
    private String nombreTipoVariable;
    private Integer cantidadNulos;
    private Integer cantidadFueraRanngo;
    private Integer cantidadRepetidos;
    private Integer cantidadOutlier;
    private Integer cantidadErroresSintacticos;
    private Integer cantidadDato;
    private Integer cantidadDatosCorrectos;
    

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public void setNumeroColumna(Integer numeroColumna) {
        this.numeroColumna = numeroColumna;
    }

    public Integer getIdVariable() {
        return idVariable;
    }

    public Integer getNumeroColumna() {
        return numeroColumna;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public void setNombreTipoVariable(String nombreTipoVariable) {
        this.nombreTipoVariable = nombreTipoVariable;
    }

    public void setCantidadNulos(Integer cantidadNulos) {
        this.cantidadNulos = cantidadNulos;
    }

    public void setCantidadFueraRanngo(Integer cantidadFueraRanngo) {
        this.cantidadFueraRanngo = cantidadFueraRanngo;
    }

    public void setCantidadRepetidos(Integer cantidadRepetidos) {
        this.cantidadRepetidos = cantidadRepetidos;
    }

    public String getNombreTipoVariable() {
        return nombreTipoVariable;
    }

    public Integer getCantidadNulos() {
        return cantidadNulos;
    }

    public Integer getCantidadFueraRanngo() {
        return cantidadFueraRanngo;
    }

    public Integer getCantidadRepetidos() {
        return cantidadRepetidos;
    }

    public Integer getCantidadDato() {
        return cantidadDato;
    }

    public Integer getCantidadDatosCorrectos() {
        return cantidadDatosCorrectos;
    }

    public void setCantidadDato(Integer cantidadDato) {
        this.cantidadDato = cantidadDato;
    }

    public void setCantidadDatosCorrectos(Integer cantidadDatosCorrectos) {
        this.cantidadDatosCorrectos = cantidadDatosCorrectos;
    }

    public void setNombreVariableOrganizacion(String nombreVariableOrganizacion) {
        this.nombreVariableOrganizacion = nombreVariableOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getNombreVariableOrganizacion() {
        return nombreVariableOrganizacion;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public Integer getCantidadOutlier() {
        return cantidadOutlier;
    }

    public void setCantidadOutlier(Integer cantidadOutlier) {
        this.cantidadOutlier = cantidadOutlier;
    }

    public Integer getCantidadErroresSintacticos() {
        return cantidadErroresSintacticos;
    }

    public void setCantidadErroresSintacticos(Integer cantidadErroresSintacticos) {
        this.cantidadErroresSintacticos = cantidadErroresSintacticos;
    }

    

    
    
    
}