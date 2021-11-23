package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IUsuarioService;
import com.example.proyecto.sitio.interfaces.IUsuario;
import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//public class UsuarioService implements IUsuarioService, UserDetailsService {
    public class UsuarioService implements IUsuarioService{

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
    public Usuario iniciarSesion(String username, String password) {
        List<Usuario> usuarios = (List<Usuario>) data.findAll();
        Optional<Usuario> usuarioEncontrado = usuarios.stream().filter(usuario-> usuario.getUsername().equals(username) && usuario.getPassword().equals(password)).findFirst();

        if(usuarioEncontrado.isEmpty()){
            System.out.println("Los datos ingresados no coinciden");
            return null;
        }
        return usuarioEncontrado.get();
    }

    public boolean validarLogin() {

        return false;
    }


/*    @Override //Aqui debemos indicar a security de donde sacar los datos
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando con el username" + username);
        Usuario usuario = data.findByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails userDet = new User(usuario.getNombres(), usuario.getPassword(), roles);
        return userDet;
    }*/
}
