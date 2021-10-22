package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.Ciudad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICiudad extends CrudRepository<Ciudad, Integer> {

}
