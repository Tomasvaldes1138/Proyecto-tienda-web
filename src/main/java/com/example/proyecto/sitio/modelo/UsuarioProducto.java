package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

/**
 * Esta clase define la clase de UsuarioProducto
 * @version 23/11/2021
 */

@Entity
@Table(name="usuario_producto")
public class UsuarioProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_orden" )
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "id_producto" )
    private Producto producto;

    private int cantidad;

    /**
     * Constructor vacio
     */
    public UsuarioProducto() {

    }

    /**
     * Obtiene la id del producto del usuario
     *
     * @return int con el id del producto del usuario
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la orden de compra del usuario
     *
     * @return Orden de compra del producto del usuario
     */
    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    /**
     * Obtiene el producto del usuario
     *
     * @return Producto del usuario
     */
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad del producto del usuario
     *
     * @return int con la cantidad del producto del usuario
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Settea la cantidad del producto del usuario
     *
     * @param cantidad es la cantidad que se va a settear
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Imprime todos los atributos de un UsuarioProducto
     *
     * @return Retorna un String con la informacion
     */
    @Override
    public String toString() {
        return "UsuarioProducto{" +
                "id=" + id +
                ", ordenCompra=" + ordenCompra +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
