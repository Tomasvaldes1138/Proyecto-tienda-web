package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IAdministradorService;
import com.example.proyecto.sitio.interfaceService.IOrdenCompraService;
import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.modelo.Administrador;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ControladorAdministrador {


    @Autowired
    private IAdministradorService serviceAdmin;

    @Autowired
    private IProductoService service;

    @Autowired
    private IOrdenCompraService serviceOrdenCompra;

    static Administrador administradorLogeado = null;


    //****************************************************
    //************* PANEL ADMINISTRADOR ******************
    //****************************************************


    @GetMapping("/login_admin")
    public String login_admin(Model model){
        model.addAttribute("administrador",new Administrador());
        return "login_admin";
    }

    @PostMapping(value = "validar_loginAdmin")
    public String validar_loginAdmin(@ModelAttribute Administrador administrador){
        Administrador valido = serviceAdmin.iniciarSesion(administrador.getCorreo(), administrador.getClave() );
        if(valido != null){
            administradorLogeado = valido;
            return "redirect:/pedidos_realizados";
        }
        return "redirect:/login_admin";
    }

    @PostMapping(value = "cerrar_sesionAdministrador")
    public String cerrar_sesionAdministrador(){
        administradorLogeado= null;
        return "redirect:/home";
    }


    @GetMapping("/info_productos")
    public String info_productos(Model model){

        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        return "info_productos";
    }

    @RequestMapping( value ="/nuevo_producto")
    public String nuevo_producto(Model model) {
        model.addAttribute("producto", new Producto());
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        return "nuevo_producto";
    }



    @GetMapping("/nuevo_administrador")
    public String nuevo_administrador(Model model){
        model.addAttribute("administrador", new Administrador());
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        return "nuevo_administrador";
    }

    @PostMapping(value="insertar_admin")
    public String insertar_admin(@ModelAttribute Administrador administrador){
        serviceAdmin.save(administrador);

        return "redirect:/nuevo_administrador";
    }



    @GetMapping("/pedidos_realizados")
    public String pedidos_realizados(Model model){
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        model.addAttribute("ordenes", serviceOrdenCompra.listar());

        return "pedidos_realizados";
    }

    @GetMapping("/actualizar_producto")
    public String actualizar_producto(@RequestParam(name="id_producto") int id_producto, Model model){
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;

        model.addAttribute("producto", service.buscarPorId(id_producto) );
        model.addAttribute("producto_actualizado", new Producto());
        model.addAttribute("nombre_administrador", contenido );
        return "actualizar_producto";
    }

    @PostMapping("/actualizar_producto_post")
    public String actualizar_producto_post(@ModelAttribute Producto producto){
        service.save(producto);
        return "redirect:/info_productos";
    }






}
