package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre_producto;
    private String descripcion;
    private int cantidad;
    private String categoria;

    public Producto(){

    }




}
