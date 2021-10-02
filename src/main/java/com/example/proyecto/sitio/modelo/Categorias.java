package com.example.proyecto.sitio.modelo;

public enum Categorias {


    PROCESADORES("Procesadores"),
    TARJETAS_GRAFICAS("Tarjetas Graficas"),
    PLACAS_MADRE("Placa Madre"),
    MEMORIAS_RAM("Memorias Ram"),
    FUENTES_PODER("Fuentes de Poder"),
    GABINETES("Gabinetes");

    private String categoria;


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override public String toString() { return categoria; }



    Categorias(String categoria) {
    }
}
