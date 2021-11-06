package com.example.proyecto.sitio.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="orden_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_orden;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "correo" )
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_entrega" )
    private TipoEntrega tipoEntrega;

    @ManyToOne
    @JoinColumn(name = "id_ciudad" )
    private Ciudad ciudad;
    //private String listaProductos;
    //private int total;
    private String comprobantePago;

    private String calle;

    private String telefono;

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


    public OrdenCompra(int id_orden, LocalDateTime fecha, Usuario usuario, TipoEntrega tipoEntrega, String comprobantePago) {
        this.id_orden = id_orden;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipoEntrega = tipoEntrega;
        this.comprobantePago = comprobantePago;
    }

    public OrdenCompra(int id_orden, String comprobante) {
        this.id_orden = id_orden;
        this.comprobantePago = comprobante;
    }

    public OrdenCompra(int id_orden, TipoEntrega tipoEntrega) {
        this.id_orden = id_orden;
        this.tipoEntrega = tipoEntrega;
    }

    public OrdenCompra(int id_orden, TipoEntrega tipoEntrega, String comprobantePago) {
        this.id_orden = id_orden;
        this.tipoEntrega = tipoEntrega;
        this.comprobantePago = comprobantePago;
    }

    public OrdenCompra(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id) {
        this.id_orden = id;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "OrdenCompra{" +
                "id_orden=" + id_orden +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", tipoEntrega=" + tipoEntrega +
                ", ciudad=" + ciudad +
                ", comprobantePago='" + comprobantePago + '\'' +
                ", calle='" + calle + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
