package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.modelo.Carrito;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.proyecto.sitio.controller.ControladorUsuario.usuarioLogeado;

@Controller
@RequestMapping
public class ControladorProducto {

    @Autowired
    private IProductoService service;


    protected static ArrayList<Integer> productos_id = new ArrayList<>();

    protected static Carrito carrito = new Carrito();

    @GetMapping("/home")
    public String home_productos(Model model){

        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "home";
    }

    @PostMapping("buscar_productos")
    public String buscar_productos(@RequestParam(required = false) String busqueda, Model model){
        busqueda = busqueda.toLowerCase();
        List<Producto> productos = service.filtrar(busqueda);
        model.addAttribute("productos", productos);
        return "categoria/filtrados";
    }

    //*******************************************************
    //******************* CATEGORIAS ************************
    //*******************************************************

    @GetMapping("categoria/procesadores")
    public String categoria_procesadores(Model model){
        List<Producto> procesadores = filtroCategoria(service.listar(), "Procesadores" );
        model.addAttribute("productos", procesadores);

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "categoria/procesadores";
    }

    @GetMapping("categoria/fuentes_poder")
    public String categoria_fuentes_poder(Model model){
        List<Producto> fuentes_poder = filtroCategoria(service.listar(), "Fuentes de Poder" );
        model.addAttribute("productos", fuentes_poder);

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "categoria/fuentes_poder";
    }

    @GetMapping("categoria/gabinetes")
    public String categoria_gabinetes(Model model){
        List<Producto> gabinetes = filtroCategoria(service.listar(), "Gabinetes" );
        model.addAttribute("productos", gabinetes);

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "categoria/gabinetes";
    }

    @GetMapping("categoria/memorias_ram")
    public String categoria_memorias_ram(Model model){
        List<Producto> memorias_ram = filtroCategoria(service.listar(), "Memorias Ram" );
        model.addAttribute("productos", memorias_ram);

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "categoria/memorias_ram";
    }

    @GetMapping("categoria/placas_madre")
    public String categoria_placas_madre(Model model){
        List<Producto> placas_madre = filtroCategoria(service.listar(), "Placas Madre" );
        model.addAttribute("productos", placas_madre);

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "categoria/placas_madre";
    }

    @GetMapping("categoria/tarjetas_graficas")
    public String categoria_tarjetas_graficas(Model model){
        List<Producto> tarjetas_graficas = filtroCategoria(service.listar(), "Tarjetas Graficas" );
        model.addAttribute("productos", tarjetas_graficas);

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "categoria/tarjetas_graficas";
    }

    public List<Producto> filtroCategoria(List<Producto> productos, String categoria){
        return productos.stream().filter(producto -> producto.getCategoria().getNombre_categoria().equals(categoria)).collect(Collectors.toList());
    }

    //****************************************************
    //********************* CARRITO **********************
    //****************************************************

    @GetMapping("/carrito")
    public String carrito(Model model){
        List<Producto> productos_carrito= carrito.getProductos();

        model.addAttribute("productos", productos_carrito);
        model.addAttribute("precio", productos_carrito.stream().mapToInt(Producto::getPrecio).sum() );
        model.addAttribute("cantidad", productos_carrito.size());

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );

        return "carrito";
    }

    @PostMapping(value="agregar_producto")
    public String agregar_producto(@ModelAttribute("producto") Producto producto){

        //Aqui deberia venir el objeto producto
        System.out.println("Producto: " + producto.toString() );
        System.out.println("LLamando a metodo buscarPorId");


        carrito.anadirProducto( service.buscarPorId(producto.getId_producto()) );

        return "redirect:/home";
    }

    @PostMapping(value="insertar_producto")
    public String insertar_producto(@ModelAttribute Producto producto){
        service.save(producto);
        return "redirect:/info_productos";
    }

     /*
    public List<Producto> get_productos_carrito(){
        List<Producto> productos_carrito= new ArrayList<>();

        for (int id : productos_id) {
            Producto producto2 = service.listar().stream().filter(producto -> producto.getId() == id).findAny().get();
            productos_carrito.add(producto2);
        }

        return productos_carrito;

    }
    */


    @PostMapping(value = "continuar_despacho")
    public String validar_carrito(){
        if(productos_id != null){
            return "redirect:/tipo_entrega";
        }
        return "redirect:/carrito";
    }
}
