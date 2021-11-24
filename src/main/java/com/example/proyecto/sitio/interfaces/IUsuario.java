package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends CrudRepository<Usuario, String> {

    Usuario findByCorreo(String correo);

}
