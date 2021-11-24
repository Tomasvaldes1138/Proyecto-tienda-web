package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Administrador;
import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase define la interface IAdministradorService
 * @version 23/11/2021
 */

@Service
public interface IAdministradorService {
    public int save(Administrador a);
}
