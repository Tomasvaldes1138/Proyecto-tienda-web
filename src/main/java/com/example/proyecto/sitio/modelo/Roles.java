package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "usuario_correo" )
    private Usuario usuario;
    private String rol;

    public Roles(int id, Usuario usuario, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.rol = rol;
    }

    public Roles() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
