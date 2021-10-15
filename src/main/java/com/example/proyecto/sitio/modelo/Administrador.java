package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    private String rut;
    private String clave;
    private String correo;
    private String nombres;
    private String apellidos;

    public  Administrador(){

    }

    public Administrador(String rut, String clave, String correo, String nombres, String apellidos) {
        this.rut = rut;
        this.clave = clave;
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
}
