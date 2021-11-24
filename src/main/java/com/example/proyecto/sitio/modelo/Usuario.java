package com.example.proyecto.sitio.modelo;

import javax.persistence.*;
/**
 * Esta clase define la clase de Usuario
 * @version 23/11/2021
 */

@Entity
@Table(name = "usuario")

public class Usuario  {

    @Id
    private String correo;
    private String nombres;
    private String apellidos;
    private String rut;
    private String clave;


    /**
     * Constructor vacio
     */
    public Usuario() {

    }

    public Usuario(String correo, String nombres, String apellidos, String rut, String clave) {
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rut = rut;
        this.clave = clave;
    }

    /**
     * Obtiene el nombre del usuario
     *
     * @return String con el nombre del usuario
     */
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el apellido del usuario
     *
     * @return String con el apellido del usuario
     */
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el rut del usuario
     *
     * @return String con el rut del usuario
     */
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene el correo del usuario
     *
     * @return String con el correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Settea el correo del usuario
     *
     * @param correo el correo que se va a settear
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el clave del usuario
     *
     * @return String con el clave del usuario
     */
    public String getClave() {
        return clave;
    }

    /**
     * Settea la clave del usuario
     *
     * @param clave La clave que se va a settear
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
}
