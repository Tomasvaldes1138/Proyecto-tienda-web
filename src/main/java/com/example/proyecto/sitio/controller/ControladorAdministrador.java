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

/**
 * Esta clase contiene todos los metodos necesarios para el funcionamiento del administrador
 * @version 23/11/2021
 */

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

    /**
     * Esta funcion redirecciona a la vista de login admin
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a login admin
     */
    @GetMapping("/login_admin")
    public String login_admin(Model model){
        model.addAttribute("administrador",new Administrador());
        return "login_admin";
    }// cierra funcion

    /** Es un contenedor de Spring Boot que tiene informacion del programa
     * Esta funcion valida que el login sea de administrador
     * @param administrador Es el administrador que se validara para el login
     * @return Si se cumple la condicion redirecciona a la vista pedidos realizados
     *         Si no se cumple la condicion redirecciona a la vista login admin
     */
    @PostMapping(value = "validar_loginAdmin")
    public String validar_loginAdmin(@ModelAttribute Administrador administrador){
        Administrador valido = serviceAdmin.iniciarSesion(administrador.getCorreo(), administrador.getClave() );
        if(valido != null){
            administradorLogeado = valido;
            return "redirect:/pedidos_realizados";
        }
        return "redirect:/login_admin";
    }// cierra funcion

    /**
     * Esta funcion cierra la sesion del administrador
     *
     * @return Redirecciona a la vista home
     */
    @PostMapping(value = "cerrar_sesionAdministrador")
    public String cerrar_sesionAdministrador(){
        administradorLogeado= null;
        return "redirect:/home";
    }// cierra funcion

    /**
     * Esta funcion muestra la informacion de los productos que estan en la base de datos
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista info productos
     */
    @GetMapping("/info_productos")
    public String info_productos(Model model){

        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        return "info_productos";
    }// cierra funcion

    /**
     * Esta funcion permite agregar nuevos productos a la base de datos
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a nuevo producto
     */
    @RequestMapping( value ="/nuevo_producto")
    public String nuevo_producto(Model model) {
        model.addAttribute("producto", new Producto());
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        return "nuevo_producto";
    }// cierra funcion

    /**
     * Esta funcion permite agregar nuevos administradores a la base de datos
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista nuevo administrador
     */
    @GetMapping("/nuevo_administrador")
    public String nuevo_administrador(Model model){
        model.addAttribute("administrador", new Administrador());
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        return "nuevo_administrador";
    }// cierra funcion

    /**
     * Esta funcion insertar al nuevo administrador a la base de datos
     *
     * @param administrador Es el administrador que se agregara a la base de datos
     * @return Redirecciona a lavista insertar administrador
     */
    @PostMapping(value="insertar_admin")
    public String insertar_admin(@ModelAttribute Administrador administrador){

        serviceAdmin.save(administrador);
        return "redirect:/nuevo_administrador";
    }// cierra funcion

    /**
     * Esta funcion muestra los pedidos realizados por los usuarios
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista pedidos realizados
     */
    @GetMapping("/pedidos_realizados")
    public String pedidos_realizados(Model model){
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        model.addAttribute("ordenes", serviceOrdenCompra.listar());

        return "pedidos_realizados";
    }// cierra funcion

    /**
     * Esta funcion permite actualizar los productos que estan en la base de datos
     *
     * @param id_producto Es la id del producto que sera actualizado
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista actualizar producto
     */
    @GetMapping("/actualizar_producto")
    public String actualizar_producto(@RequestParam(name="id_producto") int id_producto, Model model){
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;

        model.addAttribute("producto", service.buscarPorId(id_producto) );
        model.addAttribute("producto_actualizado", new Producto());
        model.addAttribute("nombre_administrador", contenido );
        return "actualizar_producto";
    }// cierra funcion

    /**
     * Esta funcion guarda la actualizacion del producto
     *
     * @param producto El producto que sera actualizado
     * @return Redirecciona a la vista info productos
     */
    @PostMapping("/actualizar_producto_post")
    public String actualizar_producto_post(@ModelAttribute Producto producto){
        producto.aplicarDescuento();
        System.out.println("PRODUCTO!: "+ producto);
        service.save(producto);
        return "redirect:/info_productos";
    }// cierra funcion
}// cierra clase
