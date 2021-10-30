package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.UsuarioProducto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioProducto extends CrudRepository<UsuarioProducto, Integer> {

}
