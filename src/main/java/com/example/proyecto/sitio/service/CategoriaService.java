package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.ICategoriaService;
import com.example.proyecto.sitio.interfaces.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Esta clase implementa los metodos de ICategoriaService
 * @version 23/11/2021
 */
public class CategoriaService implements ICategoriaService {

    @Autowired
    private ICategoria data;

}
