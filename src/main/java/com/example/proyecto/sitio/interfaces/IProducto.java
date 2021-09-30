package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducto extends CrudRepository<Producto, Integer> {
}
