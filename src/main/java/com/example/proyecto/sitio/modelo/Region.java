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
     * obtiene el nombre de la region
     *
     * @return retorna un String con el nombre de la region
     */
    public String getNombre_region() {
        return nombre_region;
    }

    public void setNombre_region(String nombre_region) {
        this.nombre_region = nombre_region;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }
}
