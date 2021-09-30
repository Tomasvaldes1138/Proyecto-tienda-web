package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class Controlador {


    @Autowired
    private IProductoService service;

    /**
     *Metodo que obtiene los productos de la base de datos y los entrega a la vista index
     * @param model interfaz que contiene el metodo addAttribute
     * @return String que redirecciona a la vista index.html
     */
    @GetMapping("/listar")
    public String listar(Model model){
        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
