package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.modelo.Carrito;
import com.example.proyecto.sitio.modelo.PCantidad;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping

/**
 * Esta clase contiene todos los metodos necesarios para el funcionamiento de los productos
 * @version 23/11/2021
 */

public class ControladorProducto {

    @Autowired
    private IProductoService service;


    protected static ArrayList<Integer> productos_id = new ArrayList<>();

    protected static Carrito carrito = new Carrito();


    /**
     * Esta funcion muestra todos los productos de la base de datos
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a home
     */
    @GetMapping("/home")
    public String home_productos(Model model){

        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        return "home";
    }// cierra funcion

    /**
     * Esta funcion permite buscar productos especificos en la base de datos
     *
     * @param busqueda Es el producto a buscar
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista categoria filtrados
     */
    @PostMapping("buscar_productos")
    public String buscar_productos(@RequestParam(required = false) String busqueda, Model model){
        busqueda = busqueda.toLowerCase();
        List<Producto> productos = service.filtrar(busqueda);
        model.addAttribute("productos", productos);
        return "categoria/buscar_productos";
    }// cierra funcion

    //*******************************************************
    //******************* CATEGORIAS ************************
    //*******************************************************

    /**
     *  Esta funcion muestra los productos con la categoria de
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a categoria procesadores
     */
    @GetMapping("categoria/procesadores")
    public String categoria_procesadores(Model model, Authentication auth){
        List<Producto> procesadores = filtroCategoria(service.listar(), "Procesadores" );
        model.addAttribute("productos", procesadores);
        System.err.println("USUARIO: " + auth.getName());
        return "categoria/procesadores";
    }// cierra funcion

    /**
     * Esta funcion muestra los productos con la categoria de fuentes de pooder
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a categoria fuentes de poder
     */
    @GetMapping("categoria/fuentes_poder")
    public String categoria_fuentes_poder(Model model){
        List<Producto> fuentes_poder = filtroCategoria(service.listar(), "Fuentes de Poder" );
        model.addAttribute("productos", fuentes_poder);
        return "categoria/fuentes_poder";
    }// cierra funcion

    /**
     * Esta funcion muestra los productos con la categoria de gabinetes
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a categoria fuentes de poder
     */
    @GetMapping("categoria/gabinetes")
    public String categoria_gabinetes(Model model){
        List<Producto> gabinetes = filtroCategoria(service.listar(), "Gabinetes" );
        model.addAttribute("productos", gabinetes);
        return "categoria/gabinetes";
    }// cierra funcion

    /**
     * Esta funcion muestra los productos con la categoria de memoerias ram
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a categoria memorias ram
     */
    @GetMapping("categoria/memorias_ram")
    public String categoria_memorias_ram(Model model){
        List<Producto> memorias_ram = filtroCategoria(service.listar(), "Memorias Ram" );
        model.addAttribute("productos", memorias_ram);
        return "categoria/memorias_ram";
    }// cierra funcion

    /**
     * Esta funcion muestra los productos con la categoria de placas madre
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a categoria placas madre
     */
    @GetMapping("categoria/placas_madre")
    public String categoria_placas_madre(Model model){
        List<Producto> placas_madre = filtroCategoria(service.listar(), "Placas Madre" );
        model.addAttribute("productos", placas_madre);
        return "categoria/placas_madre";
    }// cierra funcion

    /**
     * Esta funcion muestra los productos con la categoria de tarjetas graficas
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a categoria placas madre
     */
    @GetMapping("categoria/tarjetas_graficas")
    public String categoria_tarjetas_graficas(Model model){
        List<Producto> tarjetas_graficas = filtroCategoria(service.listar(), "Tarjetas Graficas" );
        model.addAttribute("productos", tarjetas_graficas);
        return "categoria/tarjetas_graficas";
    }// cierra funcion

    /**
     * Esta funcion permite filtrar los productos en las categorias
     *
     * @param productos Son los productos que se filtran
     * @param categoria Es la categoria que se busca filtrar
     * @return Retorna una lista de productos filtrados
     */
    public List<Producto> filtroCategoria(List<Producto> productos, String categoria){
        return productos.stream().filter(producto -> producto.getCategoria().getNombre_categoria().equals(categoria)).collect(Collectors.toList());
    }// cierra funcion

    //****************************************************
    //********************* CARRITO **********************
    //****************************************************

    /**
     *
     * Esta funcion agregaga los productos a la vista carrito
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista carrito
     */
    @GetMapping("/carrito")
    public String carrito(Model model){
        List<PCantidad> productos_carrito= carrito.getProductos();

        model.addAttribute("productos", productos_carrito);
        model.addAttribute("precio_total", carrito.getTotal() );
        model.addAttribute("cantidad", carrito.getCantidad() );

        return "carrito";
    }// cierra funcion

    /**
     *  Esta funcion agrega los productos al carrito
     *
     * @param producto Es el producto que va a ser agregado al carrito
     * @param archivoHTML Es la vista desde la cual se esta accediendo a esta funcion
     * @return Se redirecciona a la vista desde que se llamo la funcion
     */
    @PostMapping(value="agregar_producto")
    public String agregar_producto(@ModelAttribute("producto") Producto producto,
                                   @RequestParam(name="archivoHTML") String archivoHTML){

        int id_producto = producto.getId_producto();
        Producto productoEncontrado = service.buscarPorId( id_producto );
        carrito.anadirProducto( productoEncontrado );

        if(archivoHTML.equals("home")){
            return "redirect:/home";
        }
        return "redirect:categoria/"+archivoHTML;
    }// cierra funcion

    /**
     * Esta funcion inserta nuevos productos a la base de datos desde la vista del admin
     *
     * @param producto Es el producto que va a ser insertado
     * @return Redirecciona a la informacion de los productos del administrador
     */
    @PostMapping(value="insertar_producto")
    public String insertar_producto(@ModelAttribute Producto producto){
        service.save(producto);
        return "redirect:/info_productos";
    }// cierra funcion

    /**
     * Esta funcion valida que hayan productos en el carrito para continuar con el proceso
     *
     * @return Redirecciona a la vista que se llame desde la funcion
     */
    @PostMapping(value = "continuar_despacho")
    public String validar_carrito(){
        if(productos_id != null){
            return "redirect:/tipo_entrega";
        }
        return "redirect:/carrito";
    }// cierra funcion

    /**
     * Esta funcion remueve un producto del carrito
     *
     * @param id_producto Es el producto que sera removido
     * @return Redirecciona a la vista carrito
     */
    @GetMapping("/remover_producto")
    public String removerProducto(@RequestParam(name="id_producto", required = false) int id_producto){
        carrito.removerProducto(  carrito.obtenerProductoPorId(id_producto) );
        return "redirect:/carrito";

    }// cierra funcion

    /**
     * Esta funcion aumenta la cantidad del producto
     *
     * @param id_producto Es el producto al que se le aumenta la cantidad
     * @return Redirecciona al carrito
     */
    @GetMapping("/aumentar_cantidad")
    public String aumentar_cantidad(@RequestParam(name="id_producto", required = false) int id_producto){
        carrito.aumentar_cantidad(id_producto);
        return "redirect:/carrito";
    }// cierra funcion

    /**
     * Esta funcion disminuye la cantidad del producto
     *
     * @param id_producto Es el producto al que se le disminuye la cantidad
     * @return Redirecciona al carrito
     */
    @GetMapping("/disminuir_cantidad")
    public String disminuir_cantidad(@RequestParam(name="id_producto", required = false) int id_producto){
        carrito.disminuir_cantidad(id_producto);
        return "redirect:/carrito";
    }// cierra funcion

}// cierre clase
