package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.*;
import com.example.proyecto.sitio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    static Usuario usuarioLogeado = null;

    static Administrador administradorLogeado = null;




    /**
     *Metodo que obtiene los productos de la base de datos y los entrega a la vista index
     * @param model interfaz que contiene el metodo addAttribute
     * @return String que redirecciona a la vista index.html
     */

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
        Usuario valido = serviceUsuario.iniciarSesion(usuario.getCorreo(), usuario.getClave() );
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

    @PostMapping(value = "cerrar_sesionAdministrador")
    public String cerrar_sesionAdministrador(){
        usuarioLogeado= null;
        return "redirect:/home";
    }


    //*******************************************************
    //******************* HOME ******************************
    //*******************************************************


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
        List<Producto> productos_carrito= get_productos_carrito();

        model.addAttribute("productos", productos_carrito);
        model.addAttribute("precio", productos_carrito.stream().mapToInt(Producto::getPrecio).sum() );
        model.addAttribute("cantidad", productos_carrito.size());

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );

        return "carrito";
    }

    @PostMapping(value="agregar_producto")
    public String agregar_producto(@ModelAttribute("producto") Producto producto){
        productos_id.add( producto.getId() );
        return "redirect:/home";
    }

    public List<Producto> get_productos_carrito(){
        List<Producto> productos_carrito= new ArrayList<>();

        for (int id : productos_id) {
            Producto producto2 = service.listar().stream().filter(producto -> producto.getId() == id).findAny().get();
            productos_carrito.add(producto2);
        }

        return productos_carrito;
    }

    @PostMapping(value = "continuar_despacho")
    public String validar_carrito(){
        if(productos_id != null){
            return "redirect:/tipo_entrega";
        }
        return "redirect:/carrito";
    }

    //****************************************************
    //******************* TIPO ENTREGA *******************
    //****************************************************

    @GetMapping("/tipo_entrega")
    public String tipo_entrega(Model model){
        List<Producto> productos = get_productos_carrito();
        List<Region> regiones = service_region.listar();
        List<Ciudad> ciudades = service_ciudad.listar();

        model.addAttribute("regiones", regiones);
        model.addAttribute("ciudades", ciudades);
        model.addAttribute("orden_compra", new OrdenCompra());

        model.addAttribute("productos", productos);
        model.addAttribute("precio", productos.stream().mapToInt(Producto::getPrecio).sum() );

        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "tipo_entrega";
    }

    @PostMapping("/generar_orden_compra")
    public String generar_orden_compra(@ModelAttribute OrdenCompra ordenCompra){
        LocalDateTime fechaActual = LocalDateTime.now(ZoneId.of("GMT-3"));
        ordenCompra.setUsuario(usuarioLogeado);
        ordenCompra.setFecha(fechaActual);
        serviceOrdenCompra.save(ordenCompra);
        return "redirect:/orden_exitosa";
    }

    //****************************************************
    //******************* ORDEN EXITOSA ******************
    //****************************************************

    @GetMapping("/orden_exitosa")
    public String orden_exitosa(Model model){
        List<Producto> productos_carrito= get_productos_carrito();
        model.addAttribute("orden_compra", new OrdenCompra());
        model.addAttribute("precio", productos_carrito.stream().mapToInt(Producto::getPrecio).sum() );
        String contenido = usuarioLogeado==null ? "Login" : usuarioLogeado.getNombres() ;
        model.addAttribute("nombre_cliente", contenido );
        return "orden_exitosa";
    }


    //****************************************************
    //******************* COMPROBANTE ********************
    //****************************************************

    @GetMapping("/comprobante")
    public String comprobante(){
        return "comprobante";
    }

    @PostMapping("/subir_comprobante")
    public String subir( @ModelAttribute OrdenCompra ordenCompra,
                         @RequestParam("file") MultipartFile comprobante){
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

    @PostMapping(value="insertar_producto")
    public String insertar_producto(@ModelAttribute Producto producto){
        service.save(producto);
        return "redirect:/info_productos";
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








    @GetMapping("/orden_compra")
    public String orden_compra(){
        return "orden_compra";
    }

    @GetMapping("/pedidos_realizados")
    public String pedidos_realizados(Model model){
        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        return "pedidos_realizados";
    }

    @GetMapping("/actualizar_producto")
    public String actualizar_producto(Model model){

        model.addAttribute("producto", service.listar().get(8));

        String contenido = administradorLogeado==null ? "Login" : administradorLogeado.getNombres() ;
        model.addAttribute("nombre_administrador", contenido );
        return "actualizar_producto";
    }


}
