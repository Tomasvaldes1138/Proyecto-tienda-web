package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoles extends CrudRepository<Roles, Integer> {

}