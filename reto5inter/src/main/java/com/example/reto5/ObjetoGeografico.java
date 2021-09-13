package com.example.reto5;

public class ObjetoGeografico {

    private String nombre;
    private String municipio;
    private String tagua;
    private String tcuerpo;
    private Double IRCA;
    private int poblacion;

    public ObjetoGeografico(String nombre, String municipio, String tagua, String tcuerpo, Double IRCA, int poblacion) {
        this.nombre = nombre;
        this.municipio = municipio;
        this.tagua = tagua;
        this.tcuerpo = tcuerpo;
        this.IRCA = IRCA;
        this.poblacion = poblacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setTagua(String tagua) {
        this.tagua = tagua;
    }

    public void setTcuerpo(String tcuerpo) {
        this.tcuerpo = tcuerpo;
    }

    public void setIRCA(Double IRCA) {
        this.IRCA = IRCA;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getIRCA() {
        return IRCA;
    }

    public int getPoblacion() {
        return poblacion;
    }
}
