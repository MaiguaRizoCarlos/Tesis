/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.util.List;

public class MapaCoordenadas{
    
    
    private float coordenadaX;
    private float coordenadaY;

    List<MapaVariable> mapaVariable;
    public float getCoordenadaX() {
        return coordenadaX;
    }

    public float getCoordenadaY() {
        return coordenadaY;
    }
    
    public void setCoordenadaX(float coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(float coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void setMapaVariable(List<MapaVariable> mapaVariable) {
        this.mapaVariable = mapaVariable;
    }

    public List<MapaVariable> getMapaVariable() {
        return mapaVariable;
    }
    
    
    
}