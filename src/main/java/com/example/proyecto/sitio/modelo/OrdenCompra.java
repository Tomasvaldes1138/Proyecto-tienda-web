package com.example.proyecto.sitio.modelo;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@Table(name="orden_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private String fecha;
    //private String cliente;
    //private Entrega tipoEntrega;
    //private String ordenCompra;
    //private int total;
    private String comprobante;

    public OrdenCompra() {

    }

    //public OrdenCompra(int id,String fecha,String cliente,Entrega tipoEntrega,String ordenCompra,String comprobantePago,int total) {
    //    this.id = id;
    //     this.fecha = fecha;
    //     this.cliente = cliente;
    //     this.tipoEntrega = tipoEntrega;
    //     this.ordenCompra = ordenCompra;
    //     this.comprobantePago = comprobantePago;
    //     this.total = total;
    //}


    public OrdenCompra(int id, String comprobante) {
        this.id = id;
        this.comprobante = comprobante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobantePago(String comprobante) {
        this.comprobante = comprobante;
    }



}
