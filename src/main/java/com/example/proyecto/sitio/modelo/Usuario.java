package com.example.proyecto.sitio.modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")

public class Usuario implements Serializable {

    @Id
    private String correo;
    private String nombres;
    private String apellidos;
    private String rut;
    private String clave;


    public Usuario() {

    }

    public Usuario(String correo, String nombres, String apellidos, String rut, String clave) {
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rut = rut;
        this.clave = clave;
    }

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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
