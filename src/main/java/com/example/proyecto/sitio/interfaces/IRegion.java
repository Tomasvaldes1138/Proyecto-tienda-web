package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegion extends CrudRepository<Region, Integer> {

}
