package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IUsuarioService;
import com.example.proyecto.sitio.interfaces.IUsuario;
import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuario data;

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>)  data.findAll();
    }

    @Override
    public int guardar(Usuario a) {
        int respuesta = 0;
        Usuario usuario = data.save(a);
        if (!usuario.equals(null)){
            respuesta = 1;
        }
        return 0;
    }

    @Override
    public Usuario iniciarSesion(String correo, String clave) {
        List<Usuario> usuarios = (List<Usuario>) data.findAll();
        Optional<Usuario> usuarioEncontrado = usuarios.stream().filter(usuario-> usuario.getCorreo().equals(correo) && usuario.getClave().equals(clave)).findFirst();

        if(usuarioEncontrado.isEmpty()){
            System.out.println("Los datos ingresados no coinciden");
            return null;
        }
        return usuarioEncontrado.get();
    }

    @Override
    public boolean validarLogin() {




        return false;
    }


}
