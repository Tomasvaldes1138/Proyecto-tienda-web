package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {

    public List<Usuario> Listar();
    public int save(Usuario a);
}