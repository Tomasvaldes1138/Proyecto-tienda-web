package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IUsuarioService;
import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ControladorUsuario {

    @Autowired
    private IUsuarioService serviceUsuario;

    protected static Usuario usuarioLogeado = null;



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
        serviceUsuario.guardar(usuario);
        return "login";
    }

    //****************************************************
    //******************* LOGIN USUARIO ******************
    //****************************************************

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping(value = "validar_login")
    public String validar_login(@ModelAttribute Usuario usuario){
        Usuario valido = serviceUsuario.iniciarSesion(usuario.getUsername(), usuario.getPassword() );
        if(valido != null){
            usuarioLogeado = valido;
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    @PostMapping(value = "cerrar_sesionUsuario")
    public String cerrar_sesionUsuario(){
        usuarioLogeado= null;
        return "redirect:/home";
    }




}
