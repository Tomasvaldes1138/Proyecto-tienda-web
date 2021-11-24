package com.example.proyecto.sitio.modelo;


import javax.persistence.*;
import java.util.ArrayList;

/**
 * Esta clase define la clase de Categoria
 * @version 23/11/2021
 */


@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_categoria;

    private String nombre_categoria;

    /**
     * Obtiene el nombre de la categoria
     *
     * @return String con el nombre
     */
    public String getNombre_categoria() {
        return nombre_categoria;
    }
}// cierre clase
