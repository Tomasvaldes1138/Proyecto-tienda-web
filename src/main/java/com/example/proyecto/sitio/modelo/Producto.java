package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private Categorias categoria;
    private int stock;
    private int precio;
    private String urlImagen;
    private int descuento;
    private String precioAnterior;


    public Producto(){

    }

    public Producto(int id, String nombre, Categorias categoria, int stock, int precio, String urlImagen, int descuento) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.urlImagen = urlImagen;
        this.descuento = descuento;

        //precioANTERIOR
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(String precioAnterior) {
        this.precioAnterior = precioAnterior;
    }
}
