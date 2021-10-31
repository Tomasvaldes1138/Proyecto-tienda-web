package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

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

    public Ciudad(int id_ciudad, String nombre, Region region) {
        this.id_ciudad = id_ciudad;
        this.nombre = nombre;
        this.region = region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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
}
