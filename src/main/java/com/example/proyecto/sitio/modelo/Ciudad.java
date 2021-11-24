package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

/**
 * Esta clase define la clase de Ciudad
 * @version 23/11/2021
 */

@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ciudad;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_region" )
    private Region region;

    public Ciudad(){

    }

    /**
     * Constructor para ciudad
     *
     * @param id_ciudad el id que tendra la ciudad
     * @param nombre el nombre que tendra la ciudad
     * @param region la region en la que estara la ciudad
     */
    public Ciudad(int id_ciudad, String nombre, Region region) {
        this.id_ciudad = id_ciudad;
        this.nombre = nombre;
        this.region = region;
    }

    /**
     * Obtiene el nombre de la ciudad
     *
     * @return Retorna un String con el nombre de la ciudad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Settea el nombre a una ciudad
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtienes la region de la ciudad
     *
     * @return
     */
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id) {
        this.id_ciudad = id;
    }
}// cierra clase
