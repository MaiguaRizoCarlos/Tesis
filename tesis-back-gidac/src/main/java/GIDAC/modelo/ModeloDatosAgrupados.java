/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

public class ModeloDatosAgrupados{
    
    
    private Integer idDato;
    private float coordenadaX;
    private float coordenadaY;
    private float profundidadMinima;
    private float profundidadMaxima;
    private String abreviatura;
    private String nombreVariable;
    private String valor;

    public Integer getIdDato() {
        return idDato;
    }

    public void setIdDato(Integer idDato) {
        this.idDato = idDato;
    }

    public float getCoordenadaX() {
        return coordenadaX;
    }

    public float getCoordenadaY() {
        return coordenadaY;
    }

    public float getProfundidadMinima() {
        return profundidadMinima;
    }

    public float getProfundidadMaxima() {
        return profundidadMaxima;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public String getValor() {
        return valor;
    }

    public void setCoordenadaX(float coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(float coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void setProfundidadMinima(float profundidadMinima) {
        this.profundidadMinima = profundidadMinima;
    }

    public void setProfundidadMaxima(float profundidadMaxima) {
        this.profundidadMaxima = profundidadMaxima;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
}