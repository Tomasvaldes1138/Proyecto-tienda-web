package com.example.proyecto.sitio.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase define la clase de OrdenCompra
 * @version 23/11/2021
 */

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

    private String comprobantePago;

    private String calle;

    private String telefono;

    private int total;

    /**
     * Constructor vacio
     */
    public OrdenCompra() {
    }


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

    /**
     * Obtiene la id de la orden
     *
     * @return int con el id de la orden
     */
    public int getId_orden() {
        return id_orden;
    }

    /**
     * Settea la id de la orden
     *
     * @param id int que se le quiere settear
     */
    public void setId_orden(int id) {
        this.id_orden = id;
    }


    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    /**
     * Obtiene el comprobante de pago
     *
     * @return un String con el comprobante de pago
     */
    public String getComprobantePago() {
        return comprobantePago;
    }

    /**
     * Settea un comprobante de pago a una orden de compra
     *
     * @param comprobante un String que se le va a settear
     */
    public void setComprobantePago(String comprobante) {
        this.comprobantePago = comprobante;
    }

    /**
     * Obtiene el Usuario de la orden de compra
     *
     * @return un Usuario con el usuario de la orden
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Settea el Usuario de la orden de compra
     *
     * @param usuario el Usuario que se le quiere settear
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la fecha de la orden de compra
     *
     * @return un LocalDateTime con la fecha de la orden
     */
    public String getFecha() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Settear la fecha de la orden de compra
     *
     * @param fecha el LocalDateTime que se le quiere settear
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la ciudad de la orden de compra
     *
     * @return retorna una Ciudad de la orden de compra
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * Settea la ciudad a la orden de compra
     *
     * @param ciudad la Ciudad que se le va a settear
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene la calle de la orden de compra
     *
     * @return un String con la call de la orden de compra
     */
    public String getCalle() {
        return calle;
    }


    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el telefono de la orden de compra
     *
     * @return un String con el telefono
     */
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el total de la orden de compra
     *
     * @return un int con el total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Settea el total de la orden de compra
     *
     * @param total un int con el total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Imprime la orden de compra
     *
     * @return un String con los atributos de la orden de compra
     */
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
                ", total=" + total +
                '}';
    }
}// cierra clase
