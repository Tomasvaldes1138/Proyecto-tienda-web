package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.ICiudadService;
import com.example.proyecto.sitio.interfaces.ICiudad;
import com.example.proyecto.sitio.modelo.Ciudad;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Esta clase implementa los metodos de ICiudadService
 * @version 23/11/2021
 */
@Service
public class CiudadService implements ICiudadService {

    @Autowired
    private ICiudad data;


    /**
     *Obtiene las ciudades de la base de datos
     * @return ciudades encontradas
     */
    @Override
    public List<Ciudad> listar() {
        return (List<Ciudad>) data.findAll();
    }
}
