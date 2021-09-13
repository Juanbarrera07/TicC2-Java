package com.example.reto5;

public class CuerpoDeAgua extends ObjetoGeografico {

    public CuerpoDeAgua(String nombre, String municipio, String tagua, String tcuerpo, Double IRCA, int poblacion) {
        super(nombre, municipio, tagua, tcuerpo, IRCA, poblacion);
    }

    public String getNombre() {
        return super.getNombre();
    }

    public Double getIRCA() {
        return super.getIRCA();
    }

    public String nivel(Double irca, String nombre){
        String nombres = "";
        if (irca <= 35 && irca > 14){
            nombres = nombre;
        }
        return nombres;
    }
}
