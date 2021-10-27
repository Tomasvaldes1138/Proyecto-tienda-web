package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

@Entity
@Table(name = "tipo_entrega")
public class TipoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_entrega;
    private String nombre_entrega;

    public TipoEntrega(){

    }

    public TipoEntrega(int id_entrega, String nombre_entrega) {
        this.id_entrega = id_entrega;
        this.nombre_entrega = nombre_entrega;
    }

    public int getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    public String getNombre_entrega() {
        return nombre_entrega;
    }

    public void setNombre_entrega(String nombre_entrega) {
        this.nombre_entrega = nombre_entrega;
    }
}
