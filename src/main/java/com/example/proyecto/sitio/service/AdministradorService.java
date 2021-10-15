package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IAdministradorService;
import com.example.proyecto.sitio.interfaces.IAdministrador;
import com.example.proyecto.sitio.modelo.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService implements IAdministradorService {

    @Autowired
    private IAdministrador data;

    @Override
    public List<Administrador> listar() {
        return null;
    }

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
