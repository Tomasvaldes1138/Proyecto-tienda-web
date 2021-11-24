package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase define la interface IUsuarioService
 * @version 23/11/2021
 */

@Service
public interface IUsuarioService {

    public List<Usuario> Listar();
    public int guardar(Usuario a);
    public Usuario iniciarSesion(String correo, String clave);
    public boolean validarLogin();
}
