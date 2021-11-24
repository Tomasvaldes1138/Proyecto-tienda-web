package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Ciudad;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase define la interface ICiudadService
 * @version 23/11/2021
 */

@Service
public interface ICiudadService {

    public List<Ciudad> listar();
}
