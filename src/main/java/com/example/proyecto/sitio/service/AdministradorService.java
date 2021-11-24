package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IAdministradorService;
import com.example.proyecto.sitio.interfaces.IAdministrador;
import com.example.proyecto.sitio.modelo.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase implementa los metodos de IAdministradorService
 * @version 23/11/2021
 */

@Service
public class AdministradorService implements IAdministradorService {

    @Autowired
    private IAdministrador data;


    /**
     * Permite guardar el administrador a la base de datos
     *
     * @param a el administrador que se va a guardar
     * @return int con la respuesta a la bae de datos
     */
    @Override
    public int save(Administrador a) {
        int respuesta = 0;
        Administrador administrador = data.save(a);
        if(!administrador.equals(null)){
            respuesta = 1;
        }
        return 0;
    }

}
