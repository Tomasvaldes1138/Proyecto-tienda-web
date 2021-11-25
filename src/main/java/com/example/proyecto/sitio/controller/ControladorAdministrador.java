package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.*;
import com.example.proyecto.sitio.modelo.Producto;
import com.example.proyecto.sitio.modelo.Roles;
import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping

/**
 * Esta clase contiene todos los metodos necesarios para el funcionamiento de un usuario con rol de administrador
 * @version 23/11/2021
 */

public class ControladorAdministrador {


    @Autowired
    private IUsuarioService serviceUsuario;

    @Autowired
    private IRolesService serviceRoles;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IProductoService service;

    @Autowired
    private IOrdenCompraService serviceOrdenCompra;


    //****************************************************
    //************* PANEL ADMINISTRADOR ******************
    //****************************************************

    /**
     * Esta funcion redirecciona a la vista de login admin
     *
     * @return Redirecciona a login admin
     */
    @GetMapping("/login_admin")
    public String login_admin(){
        return "login_admin";
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
        model.addAttribute("usuario", new Usuario());
        return "nuevo_administrador";
    }// cierra funcion

    /**
     * Esta funcion insertar al nuevo usuario con rol de administrador a la base de datos
     *
     * @param usuario Es el usuario que se agregara a la base de datos
     * @return Redirecciona a lavista insertar administrador
     */
    @PostMapping(value="insertar_admin")
    public String insertar_admin(@ModelAttribute Usuario usuario){
        usuario.setClave(encoder.encode(usuario.getClave() ));
        usuario.setEnabled((short) 1);
        serviceUsuario.guardar(usuario);
        Roles rol = new Roles();
        rol.setUsuario(usuario);
        rol.setRol("ROLE_ADMIN");
        serviceRoles.save(rol);
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

        model.addAttribute("producto", service.buscarPorId(id_producto) );
        model.addAttribute("producto_actualizado", new Producto());
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
