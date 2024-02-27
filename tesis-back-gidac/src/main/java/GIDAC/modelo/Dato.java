/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.util.List;

public class Dato{
    
    
    private String profundidades;
    private String medida;
    private String valor;

    public Dato(String profundidades, String valor) {
        this.profundidades = profundidades;
        this.valor = valor;
    }
    
    

    public String getProfundidades() {
        return profundidades;
    }

    public String getValor() {
        return valor;
    }

    public String getMedida() {
        return medida;
    }
    
    
    
}