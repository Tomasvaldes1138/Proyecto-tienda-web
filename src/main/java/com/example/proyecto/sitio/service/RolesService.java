package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IRolesService;
import com.example.proyecto.sitio.interfaces.IRoles;
import com.example.proyecto.sitio.modelo.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RolesService implements IRolesService {
    @Autowired
    private IRoles data;

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
