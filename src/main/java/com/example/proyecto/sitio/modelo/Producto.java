package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

/**
 * Esta clase define la clase de Producto
 * @version 23/11/2021
 */

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
    private String nombre;
    private int stock;
    private int precio;
    private String url_imagen;
    private int descuento;
    private int precio_anterior;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    /**
     * Construcor vacio
     */
    public Producto() {

    }

    /**
     * Constructor de Producto
     *
     * @param id_producto     Id del producto
     * @param nombre          nombre del producto
     * @param stock           stock del producto
     * @param precio          precio del producto
     * @param url_imagen      url de la imagen del producto
     * @param descuento       descuento del producto
     * @param precio_anterior precio anterior del producto
     */
    public Producto(int id_producto, String nombre, int stock, int precio, String url_imagen, int descuento, int precio_anterior) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.url_imagen = url_imagen;
        this.descuento = descuento;
        this.precio_anterior = precio_anterior;
    }

    /**
     * Constructor de Producto
     *
     * @param id_producto     Id del producto
     * @param nombre          nombre del producto
     * @param stock           stock del producto
     * @param precio          precio del producto
     * @param url_imagen      url de la imagen del producto
     * @param descuento       descuento del producto
     * @param precio_anterior precio anterior del producto
     * @param categoria       categoria del producto
     */
    public Producto(int id_producto, String nombre, int stock, int precio, String url_imagen, int descuento, int precio_anterior, Categoria categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.url_imagen = url_imagen;
        this.descuento = descuento;
        this.precio_anterior = precio_anterior;
        this.categoria = categoria;
    }

    /**
     * Obtiene id del producto
     *
     * @return int con la id del producto
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * Settea la id del producto
     *
     * @param id un int la id que se quiere settear
     */
    public void setId_producto(int id) {
        this.id_producto = id;
    }

    /**
     * Obtiene nombre del producto
     *
     * @return String con el nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Settea el nombre del producto
     *
     * @param nombre un String que se le quiere settear
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la categoria del producto
     *
     * @return una Categoria del producto
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Settea la categoria del producto
     *
     * @param categoria La Categoria que se le quiere settear
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene el stock del producto
     *
     * @return int con el stock del producto
     */
    public int getStock() {
        return stock;
    }

    /**
     * Settea el stock del producto
     *
     * @param stock un int que se le quiere settear
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el precio del producto
     *
     * @return int con el precio del producto
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Settea el precio del producto
     *
     * @param precio un int con el precio que se le quiere settear
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la url de la imagen del producto
     *
     * @return un String con la url de la imagen del producto
     */
    public String getUrl_imagen() {
        return url_imagen;
    }

    /**
     * Settear la url de la imagen del producto
     *
     * @param url_imagen un String con la url que se le quiere settear
     */
    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    /**
     * Obtiene el descuento del producto
     *
     * @return un int con el descuento del producto
     */
    public int getDescuento() {
        return descuento;
    }

    /**
     * Settea el descuento del producto
     *
     * @param descuento un int con el descuento que se le quiere settear
     */
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getPrecio_anterior() {
        return precio_anterior;
    }

    public void setPrecio_anterior(int precio_anterior) {
        this.precio_anterior = precio_anterior;
    }

    /**
     * Funcion para imprimir un producto
     *
     * @return un String con los atributos de un producto
     */
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id_producto +
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

    /**
     * Funcion para aplicar descuento y obtener el precio anterior
     */
    public void aplicarDescuento(){
        this.precio_anterior = this.precio + (this.precio/100 * this.descuento );
    }

}
