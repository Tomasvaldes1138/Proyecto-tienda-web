package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

@Entity
@Table(name="orden_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "correo" )
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_entrega" )
    private TipoEntrega tipoEntrega;
    //private String listaProductos;
    //private int total;
    private String comprobantePago;

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
        this.comprobantePago = comprobante;
    }

    public OrdenCompra(int id, TipoEntrega tipoEntrega) {
        this.id = id;
        this.tipoEntrega = tipoEntrega;
    }

    public OrdenCompra(int id, TipoEntrega tipoEntrega, String comprobantePago) {
        this.id = id;
        this.tipoEntrega = tipoEntrega;
        this.comprobantePago = comprobantePago;
    }

    public OrdenCompra(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getComprobantePago() {
        return comprobantePago;
    }

    public void setComprobantePago(String comprobante) {
        this.comprobantePago = comprobante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "OrdenCompra{" +
                "id=" + id +
                ", tipoEntrega=" + tipoEntrega +
                ", comprobantePago='" + comprobantePago + '\'' +
                '}';
    }
}
