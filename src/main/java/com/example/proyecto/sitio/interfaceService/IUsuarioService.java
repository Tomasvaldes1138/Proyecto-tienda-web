package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {

    public List<Usuario> Listar();
    public int guardar(Usuario a);
    public Usuario iniciarSesion(String username, String password);
    public boolean validarLogin();
}
