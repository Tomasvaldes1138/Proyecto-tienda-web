package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.ICategoriaService;
import com.example.proyecto.sitio.interfaces.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoriaService implements ICategoriaService {

    @Autowired
    private ICategoria data;

}
