package com.example.proyecto.sitio.modelo;

import javax.persistence.*;

/**
 * Esta clase define la clase de los Roles
 * @version 23/11/2021
 */

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

    /**
     * Constructor vacio
     */
    public Roles() {

    }

    /**
     * Obtiene el id de los roles
     *
     * @return int con la id
     */
    public int getId() {
        return id;
    }

    /**
     * Settea la id de los roles
     *
     * @param id int con la id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el rol del Usuario
     *
     * @return Usuario de los roles
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Settea el rol del Usuario
     *
     * @param usuario Settea el usuario al rol
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el rol
     *
     * @return String con el rol
     */
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
