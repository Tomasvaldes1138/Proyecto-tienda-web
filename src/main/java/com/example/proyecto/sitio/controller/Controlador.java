package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IAdministradorService;
import com.example.proyecto.sitio.interfaceService.ICiudadService;
import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.interfaceService.IRegionService;
import com.example.proyecto.sitio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class Controlador {


    @Autowired
    private IProductoService service;
    

    @Autowired
    private IRegionService service_region;

    @Autowired
    private ICiudadService service_ciudad;

    @Autowired
    private IAdministradorService serviceAdmin;

    static ArrayList<Integer> productos_id = new ArrayList<>();


    /**
     *Metodo que obtiene los productos de la base de datos y los entrega a la vista index
     * @param model interfaz que contiene el metodo addAttribute
     * @return String que redirecciona a la vista index.html
     */

    @GetMapping("/home")
    public String home_productos(Model model){
        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        return "home";
    }

    @GetMapping("categoria/procesadores")
    public String categoria_procesadores(Model model){
        List<Producto> procesadores = filtroCategoria(service.listar(), "Procesadores" );
        model.addAttribute("productos", procesadores);
        return "categoria/procesadores";
    }

    public List<Producto> filtroCategoria(List<Producto> productos, String categoria){
        return productos.stream().filter(producto -> producto.getCategoria().getNombre_categoria().equals(categoria)).collect(Collectors.toList());
    }

    @GetMapping("categoria/fuentes_poder")
    public String categoria_fuentes_poder(Model model){
        List<Producto> fuentes_poder = filtroCategoria(service.listar(), "Fuentes de Poder" );
        model.addAttribute("productos", fuentes_poder);
        return "categoria/fuentes_poder";
    }
    @GetMapping("categoria/gabinetes")
    public String categoria_gabinetes(Model model){
        List<Producto> gabinetes = filtroCategoria(service.listar(), "Gabinetes" );
        model.addAttribute("productos", gabinetes);
        return "categoria/gabinetes";
    }
    @GetMapping("categoria/memorias_ram")
    public String categoria_memorias_ram(Model model){
        List<Producto> memorias_ram = filtroCategoria(service.listar(), "Memorias Ram" );
        model.addAttribute("productos", memorias_ram);
        return "categoria/memorias_ram";
    }
    @GetMapping("categoria/placas_madre")
    public String categoria_placas_madre(Model model){
        List<Producto> placas_madre = filtroCategoria(service.listar(), "Placas Madre" );
        model.addAttribute("productos", placas_madre);
        return "categoria/placas_madre";
    }
    @GetMapping("categoria/tarjetas_graficas")
    public String categoria_tarjetas_graficas(Model model){
        List<Producto> tarjetas_graficas = filtroCategoria(service.listar(), "Tarjetas Graficas" );
        model.addAttribute("productos", tarjetas_graficas);
        return "categoria/tarjetas_graficas";
    }

    @GetMapping("/carrito")
    public String carrito(Model model){
        List<Producto> productos_carrito= get_productos_carrito();

        model.addAttribute("productos", productos_carrito);
        model.addAttribute("precio", productos_carrito.stream().mapToInt(Producto::getPrecio).sum() );
        model.addAttribute("cantidad", productos_carrito.size());

        return "carrito";
    }

    public List<Producto> get_productos_carrito(){
        List<Producto> productos_carrito= new ArrayList<>();

        for (int id : productos_id) {
            Producto producto2 = service.listar().stream().filter(producto -> producto.getId() == id).findAny().get();
            productos_carrito.add(producto2);
        }

        return productos_carrito;
    }


    @PostMapping(value="agregar_producto")
    public String agregar_producto(@ModelAttribute("producto") Producto producto){
        productos_id.add( producto.getId() );
        System.out.println("holaaa: " +  productos_id );
        return "redirect:/home";
    }


    @GetMapping("/comprobante")
    public String comprobante(){
        return "comprobante";
    }


    @GetMapping("/info_productos")
    public String info_productos(Model model){

        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        return "info_productos";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/login_admin")
    public String login_admin(){
        return "login_admin";
    }

    @GetMapping("/nuevo_administrador")
    public String nuevo_administrador(Model model){
        model.addAttribute("administrador", new Administrador());
        return "nuevo_administrador";
    }
    @PostMapping(value="insertar_admin")
    public String insertar_admin(@ModelAttribute Administrador administrador){
        //Necesito llamar a service de Administrador pero ya esta declarada por producto.
        serviceAdmin.save(administrador);
        System.out.println(administrador.toString());
        return "index";
    }


    @GetMapping("/orden_compra")
    public String orden_compra(){
        return "orden_compra";
    }
    @GetMapping("/orden_exitosa")
    public String orden_exitosa(){
        return "orden_exitosa";
    }


    @GetMapping("/pedidos_realizados")
    public String pedidos_realizados(Model model){
        OrdenCompra orden1 = new OrdenCompra(1,"21-09-2021","Juan Perez", Entrega.DESPACHO, "", "", 119990);
        OrdenCompra orden2 = new OrdenCompra(2,"26-09-2021","Pedro Perez", Entrega.RETIRO, "", "", 19990);
        OrdenCompra orden3 = new OrdenCompra(3,"28-09-2021","Juan Diaz", Entrega.DESPACHO, "", "", 89990);
        OrdenCompra orden4 = new OrdenCompra(4,"29-09-2021","Pedro Perez", Entrega.RETIRO, "", "", 59990);

        List<OrdenCompra> ordenes = Arrays.asList(orden1, orden2, orden3, orden4);
        model.addAttribute("ordenes", ordenes);

        return "pedidos_realizados";
    }



    @GetMapping("/registro")
    public String registro(){
        return "registro";
    }


    @GetMapping("/tipo_entrega")
    public String tipo_entrega(Model model){
        List<Producto> productos = get_productos_carrito();
        List<Region> regiones = service_region.listar();
        List<Ciudad> ciudades = service_ciudad.listar();

        model.addAttribute("regiones", regiones);
        model.addAttribute("ciudades", ciudades);

        model.addAttribute("productos", productos);
        model.addAttribute("precio", productos.stream().mapToInt(Producto::getPrecio).sum() );
        return "tipo_entrega";
    }


    @GetMapping("/actualizar_producto")
    public String actualizar_producto(Model model){

        model.addAttribute("producto", service.listar().get(8));
        return "actualizar_producto";
    }



    @RequestMapping( value ="/nuevo_producto")
    public String nuevo_producto(Model model) {
        model.addAttribute("producto", new Producto());
        return "nuevo_producto";
    }

    @PostMapping(value="insertar_producto")
    public String insertar_producto(@ModelAttribute Producto producto){
        System.out.println("Producto: " + producto.toString());
        //Necesito llamar a service de Administrador pero ya esta declarada por producto.
        service.save(producto);
        return "index";
    }


}
