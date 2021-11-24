package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IRolesService;
import com.example.proyecto.sitio.interfaceService.IUsuarioService;
import com.example.proyecto.sitio.modelo.Roles;
import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping

/**
 * Esta clase contiene todos los metodos necesarios para el funcionamiento del usuario
 * @version 23/11/2021
 */

public class ControladorUsuario {

    @Autowired
    private IUsuarioService serviceUsuario;

    @Autowired
    private IRolesService serviceRoles;


    @Autowired
    private BCryptPasswordEncoder encoder;


    //****************************************************
    //******************* REGISTRO USUARIO ***************
    //****************************************************

    /**
     * Esta funcion redirecciona al registro de usuario
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista registro
     */
    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }// cierra funcion


    /**
     * Esta funcion inserta usuarios a la base de datos
     *
     * @param usuario Es el usuario que se va a insertar
     * @return Redirecciona a la vista login
     */
    @PostMapping(value = "insertar_usuario")
    public String insertar_usuario(@ModelAttribute Usuario usuario){
        usuario.setClave(encoder.encode(usuario.getClave() ));
        usuario.setEnabled((short) 1);
        serviceUsuario.guardar(usuario);
        Roles rol = new Roles();
        rol.setUsuario(usuario);
        rol.setRol("ROLE_USER");
        serviceRoles.save(rol);
        return "redirect:/login";
    }// cierra funcion

  



    //****************************************************
    //******************* LOGIN USUARIO ******************
    //****************************************************

    /**
     * Esta funcion redirecciona a login
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista login
     */
    @GetMapping("/login")
    public String login(Model model){
        //model.addAttribute("usuario", new Usuario());
        return "login";
    }



}// cierre clase
