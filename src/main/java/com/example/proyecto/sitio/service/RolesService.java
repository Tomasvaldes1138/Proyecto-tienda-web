package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IRolesService;
import com.example.proyecto.sitio.interfaces.IRoles;
import com.example.proyecto.sitio.modelo.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase implementa los metodos de IRolesService
 * @version 23/11/2021
 */
@Service
public class RolesService implements IRolesService {
    @Autowired
    private IRoles data;

    /**
     * Metodo que se encarga de guardar un rol en la base de datos
     * @param r rol a guardar
     * @return respuesta de la base de datos
     */
    @Override
    public int save(Roles r) {
        int respuesta = 0;
        Roles roles = data.save(r);
        if(!roles.equals(null)){
            respuesta = 1;
        }
        return 0;
    }
}
