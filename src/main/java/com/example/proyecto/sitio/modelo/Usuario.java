package com.example.proyecto.sitio.modelo;

import javax.persistence.*;
@Entity
@Table(name = "usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nombres;
    private String apellidos;
    private String rut;
    private String email;
    private String clave;


    public Usuario() {

    }

    public Usuario(String nombres, String apellidos, String rut, String email, String clave) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rut = rut;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
