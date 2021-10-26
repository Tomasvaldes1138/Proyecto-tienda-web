package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.*;
import com.example.proyecto.sitio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    @Autowired
    private IOrdenCompraService serviceOrdenCompra;

    @Autowired
    private IUsuarioService serviceUsuario;

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

    @PostMapping("buscar_productos")
    public String buscar_productos(@RequestParam(required = false) String busqueda, Model model){
        busqueda = busqueda.toLowerCase();
        List<Producto> productos = service.filtrar(busqueda);
        model.addAttribute("productos", productos);


        System.out.println("------Busqueda: " + busqueda + " ---------");
        return "categoria/filtrados";
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
    public String login(Model model){
        model.addAttribute("usuario", new Usuario());
        return "login";
    }
    @PostMapping(value = "validar_login")
    public String validar_login(@ModelAttribute Usuario usuario){
        boolean valido = serviceUsuario.iniciarSesion(usuario.getCorreo(), usuario.getClave() );
        if(valido){
            return "redirect:/home";
        }
        return "redirect:/login";
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

    @GetMapping("/pedidos_realizados")
    public String pedidos_realizados(Model model){


       // List<OrdenCompra> ordenes = Arrays.asList(orden1, orden2, orden3, orden4);
    //    model.addAttribute("ordenes", ordenes);

        return "pedidos_realizados";
    }


    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping(value = "insertar_usuario")
    public String insertar_usuario(@ModelAttribute Usuario usuario){
        serviceUsuario.guardar(usuario);
        System.out.println(usuario.toString());
        return "login";
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


    @GetMapping("/orden_exitosa")
    public String orden_exitosa(Model model){
        model.addAttribute("orden_compra", new OrdenCompra());
        return "orden_exitosa";
    }

    @PostMapping("/subir_comprobante")
    public String subir( @ModelAttribute OrdenCompra ordenCompra, BindingResult result, Model model,
                        @RequestParam("file") MultipartFile comprobante, RedirectAttributes atributo){
        if(!comprobante.isEmpty()){
            Path directorioComprobantes = Paths.get("src//main//resources//static/comprobantes");
            String rutaAbsoluta = directorioComprobantes.toFile().getAbsolutePath();
            try {
                byte[] byteComprobante = comprobante.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+comprobante.getOriginalFilename());
                Files.write(rutaCompleta,byteComprobante);

                ordenCompra.setComprobantePago(comprobante.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }

        serviceOrdenCompra.save(ordenCompra);

        }
        return "redirect:/home";
    }









}
