package com.example.proyecto.sitio.modelo;

/**
 * Esta clase define la clase de Region
 * @version 23/11/2021
 */

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_region;
    private String nombre_region;

    /**
     * Obtiene el nombre de la region
     *
     * @return Retorna un String con el nombre de la region
     */
    public String getNombre_region() {
        return nombre_region;
    }

    /**
     * Settea el nombre de la region
     *
     * @param nombre_region String con la region
     */
    public void setNombre_region(String nombre_region) {
        this.nombre_region = nombre_region;
    }

    /**
     * Obtiene la id de la region
     *
     * @return int con la id
     */
    public int getId_region() {
        return id_region;
    }

    /**
     * Settea la id de la region
     *
     * @param id_region int con el id
     */
    public void setId_region(int id_region) {
        this.id_region = id_region;
    }
}
