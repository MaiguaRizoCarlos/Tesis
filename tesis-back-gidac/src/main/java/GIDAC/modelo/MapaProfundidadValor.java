/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

public class MapaProfundidadValor{
    
    
    private float profundidadMinima;
    private float profundidadMaxima;
    private String abreviatura;
    private Double valor;


    public float getProfundidadMinima() {
        return profundidadMinima;
    }

    public float getProfundidadMaxima() {
        return profundidadMaxima;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public Double getValor() {
        return valor;
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
    
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
    
}