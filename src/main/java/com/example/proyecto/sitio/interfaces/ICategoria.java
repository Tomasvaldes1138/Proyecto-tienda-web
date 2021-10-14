package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoria extends CrudRepository<Categoria, Integer> {

}
