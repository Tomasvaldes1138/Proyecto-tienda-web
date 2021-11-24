package com.example.proyecto.sitio.modelo;

/**
 * Esta clase define la clase de PCantidad
 * @version 23/11/2021
 */


public class PCantidad {


    private Producto producto;
    private int cantidad;


    public PCantidad() {

    }

    /**
     * Constructor de Pcantidad
     *
     * @param producto Producto de PCantidad
     * @param cantidad un int de cantidad
     */
    public PCantidad(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene del producto del PCantidad
     *
     * @return retorna un Producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * settea el producto al PCantidad
     *
     * @param producto es el Producto que se va a settear
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Se obtiene la cantidad del PCantidad
     *
     * @return retorna un int
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Settea la cantidad del PCantidad
     *
     * @param cantidad es la cantidad que se va a settear
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
