package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdministrador extends CrudRepository<Administrador, String> {

}
