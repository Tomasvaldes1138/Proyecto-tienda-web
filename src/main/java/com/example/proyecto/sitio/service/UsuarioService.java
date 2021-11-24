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
public class UsuarioService implements IUsuarioService, UserDetailsService {

    @Autowired
    private IUsuario data;


    /**
     * Lista los Usuarios de la base de datos
     *
     * @return una Lista con los Usuarios encontrados
     */
    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>)  data.findAll();
    }

    /**
     * Guarda al Usuario en la base de datos
     *
     * @param a El usuario que se quiere guardar
     * @return int con la respuesta a la base de datos
     */
    @Override
    public int guardar(Usuario a) {
        int respuesta = 0;
        Usuario usuario = data.save(a);
        if (!usuario.equals(null)){
            respuesta = 1;
        }
        return 0;
    }

    /**
     * Metodo que carga los datos de un usuario segun su username
     *
     * @param username username del usuario que se va a buscar
     * @return UserDetails con el usuario detectado
     * @throws UsernameNotFoundException
     */
    @Override //Aqui debemos indicar a security de donde sacar los datos
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando con el correo" + username);
        Usuario usuario = data.findByCorreo(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails userDet = new User(usuario.getNombres(), usuario.getClave(), roles);
        return userDet;
    }
}
