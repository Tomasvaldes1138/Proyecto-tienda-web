package com.example.proyecto.sitio.modelo;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@Table(name="ordenCompra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fecha;
    private String cliente;
    private Entrega tipoEntrega;
    private String ordenCompra;
    private String comprobantePago;
    private int total;

    public OrdenCompra() {

    }

    public OrdenCompra(int id,String fecha,String cliente,Entrega tipoEntrega,String ordenCompra,String comprobantePago,int total) {
         this.id = id;
         this.fecha = fecha;
         this.cliente = cliente;
         this.tipoEntrega = tipoEntrega;
         this.ordenCompra = ordenCompra;
         this.comprobantePago = comprobantePago;
         this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Entrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(Entrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getComprobantePago() {
        return comprobantePago;
    }

    public void setComprobantePago(String comprobantePago) {
        this.comprobantePago = comprobantePago;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



}
