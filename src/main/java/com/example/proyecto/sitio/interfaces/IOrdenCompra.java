package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.OrdenCompra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenCompra extends CrudRepository<OrdenCompra, Integer> {




}
