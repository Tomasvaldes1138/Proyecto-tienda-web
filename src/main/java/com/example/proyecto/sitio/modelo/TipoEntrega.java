package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

/**
 * Esta clase define la clase de TipoEntrega
 * @version 23/11/2021
 */

@Entity
@Table(name = "tipo_entrega")
public class TipoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_entrega;
    private String nombre_entrega;


    /**
     * Constructor vacio
     */
    public TipoEntrega(){
    }

    public TipoEntrega(int id_entrega, String nombre_entrega) {
        this.id_entrega = id_entrega;
        this.nombre_entrega = nombre_entrega;
    }

    /**
     * Obtiene id del tipo de entrega
     *
     * @return int con la id
     */
    public int getId_entrega() {
        return id_entrega;
    }

    /**
     * Settea id del tipo de entrega
     *
     * @param id_entrega int con la id
     */
    public void setId_entrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    /**
     * Obtiene nombre del tipo de entrega
     *
     * @return String con el nombre
     */
    public String getNombre_entrega() {
        return nombre_entrega;
    }

    /**
     * Settea el nombre del tipo de entrega
     *
     * @param nombre_entrega String con el nombre
     */
    public void setNombre_entrega(String nombre_entrega) {
        this.nombre_entrega = nombre_entrega;
    }
}
