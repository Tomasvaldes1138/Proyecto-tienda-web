package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IAdministradorService;
import com.example.proyecto.sitio.interfaces.IAdministrador;
import com.example.proyecto.sitio.modelo.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Administrador iniciarSesion(String correo, String clave) {
        List<Administrador> administradores = (List<Administrador>) data.findAll();
        Optional<Administrador> administradorEncontrado = administradores.stream().filter(usuario-> usuario.getCorreo().equals(correo) && usuario.getClave().equals(clave)).findFirst();

        if(administradorEncontrado.isEmpty()){
            System.out.println("Los datos ingresados no coinciden");
            return null;
        }
        return administradorEncontrado.get();
    }
}
