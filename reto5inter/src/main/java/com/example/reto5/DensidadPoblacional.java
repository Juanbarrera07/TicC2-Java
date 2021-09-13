package com.example.reto5;

public class DensidadPoblacional extends ObjetoGeografico{

    public DensidadPoblacional(String nombre, String municipio, String tagua, String tcuerpo, Double IRCA, int poblacion) {
        super(nombre, municipio, tagua, tcuerpo, IRCA, poblacion);
    }

    public int getPoblacion() {
        return super.getPoblacion();
    }

    public int afeccion(int poblacion){
        int afeccion;
        if (poblacion < 10000){
            afeccion = 0;
        }else if (poblacion < 50000){
            afeccion = 1;
        } else {
            afeccion = 2;
        }
        return afeccion;
    }
}
