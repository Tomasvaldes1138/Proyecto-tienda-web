package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

/**
 * Esta clase define la clase de Administrador
 * @version 23/11/2021
 */

@Entity
@Table(name = "administrador")

public class Administrador {

    @Id
    private String correo;
    private String nombres;
    private String apellidos;
    private String rut;
    private String clave;


    public  Administrador(){

    }

    /**
     * Constructor de Administrador
     *
     * @param correo norreo del administrador
     * @param nombres nombres del administrador
     * @param apellidos apellidos del administrador
     * @param rut rut del administrador
     * @param clave clave del administrador
     */
    public Administrador(String correo, String nombres, String apellidos, String rut, String clave) {
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rut = rut;
        this.clave = clave;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene la clave del administrador
     *
     * @return String con la clave del administrador
     */
    public String getClave() {
        return clave;
    }

    /**
     * Settea la clave del administrador
     *
     * @param clave La nueva clave del administrador
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Obtiene el correo del administrador
     *
     * @return String con el correo del administrador
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Settea el correo del administrador
     *
     * @param correo El nuevo correo del administrador
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene los nombres del administrador
     *
     * @return String con los nombres del administrador
     */
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Funcion que imprime el administrador
     *
     * @return String con los atributos del administrador
     */
    @Override
    public String toString() {
        return "Administrador{" +
                "rut='" + rut + '\'' +
                ", clave='" + clave + '\'' +
                ", correo='" + correo + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}// Cierra clase
