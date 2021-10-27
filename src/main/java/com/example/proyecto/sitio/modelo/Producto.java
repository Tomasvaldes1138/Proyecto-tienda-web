package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int stock;
    private int precio;
    private String url_imagen;
    private int descuento;
    private int precio_anterior;

    @ManyToOne
    @JoinColumn(name = "id_categoria" )
    private Categoria categoria;

    public Producto(){

    }

    public Producto(int id, String nombre, int stock, int precio, String url_imagen, int descuento, int precio_anterior) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.url_imagen = url_imagen;
        this.descuento = descuento;
        this.precio_anterior = precio_anterior ;
    }

    public Producto(int id, String nombre, int stock, int precio, String url_imagen, int descuento, int precio_anterior, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.url_imagen = url_imagen;
        this.descuento = descuento;
        this.precio_anterior = precio_anterior;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
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

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getPrecio_anterior() {
        return precio_anterior;
    }

    public void setPrecio_anterior(int precio_anterior) {
        this.precio_anterior = precio_anterior;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria=" + categoria +
                ", stock=" + stock +
                ", precio=" + precio +
                ", urlImagen='" + url_imagen + '\'' +
                ", descuento=" + descuento +
                ", precioAnterior='" + precio_anterior + '\'' +
                '}';
    }

    public String convertirString() {
        String convertir=this.categoria.toString().replace("_"," ");
        char[] arr = convertir.toCharArray();

        for(int i = 1; i < convertir.length();i++) {
            arr[i] = Character.toLowerCase(arr[i]);
        }

        return new String(arr);
    }

}
