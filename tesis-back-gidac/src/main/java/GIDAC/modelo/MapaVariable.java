/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.util.List;

public class MapaVariable{
    
    
    private String nombreVariable;
    
    List<MapaProfundidadValor> mapaProfundidadValor;

    public String getNombreVariable() {
        return nombreVariable;
    }

    
    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public void setMapaProfundidadValor(List<MapaProfundidadValor> mapaProfundidadValor) {
        this.mapaProfundidadValor = mapaProfundidadValor;
    }

    public List<MapaProfundidadValor> getMapaProfundidadValor() {
        return mapaProfundidadValor;
    }
    
    
    
}