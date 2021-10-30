package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

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

    public UsuarioProducto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "UsuarioProducto{" +
                "id=" + id +
                ", ordenCompra=" + ordenCompra +
                ", producto=" + producto +
                '}';
    }
}
