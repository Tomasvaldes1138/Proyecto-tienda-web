package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IUsuarioService;
import com.example.proyecto.sitio.interfaces.IUsuario;
import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuario data;

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>)  data.findAll();
    }

    @Override
    public int save(Usuario a) {
        int respuesta = 0;
        Usuario usuario = data.save(a);
        if (!usuario.equals(null)){
            respuesta = 1;
        }
        return 0;
    }
}
