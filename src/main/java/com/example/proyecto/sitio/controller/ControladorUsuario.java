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

    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

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
    }

    //****************************************************
    //******************* LOGIN USUARIO ******************
    //****************************************************

    @GetMapping("/login")
    public String login(Model model){
        //model.addAttribute("usuario", new Usuario());
        return "login";
    }




}
