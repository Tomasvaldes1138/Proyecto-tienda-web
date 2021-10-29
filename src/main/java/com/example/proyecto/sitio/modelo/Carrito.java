package com.example.proyecto.sitio.modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private List<Producto> productos = new ArrayList<>();

    public Carrito() {

    }

    public void anadirProducto(Producto producto){
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
