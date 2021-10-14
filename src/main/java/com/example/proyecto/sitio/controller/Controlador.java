package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.modelo.Categoria;
import com.example.proyecto.sitio.modelo.Entrega;
import com.example.proyecto.sitio.modelo.OrdenCompra;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @GetMapping("/home")
    public String home_productos(Model model){

        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        System.out.println(productos.get(0).getCategoria().getNombre_categoria());

        return "home";
    }

    @GetMapping("categoria/procesadores")
    public String categoria_procesadores(Model model){

//         List<Producto> productos2 = service.listar();

  //      List<Producto> productos = Arrays.asList(producto1, producto2, producto3, producto4, producto5);
    //    model.addAttribute("productos", productos);
        return "categoria/procesadores";
    }
    @GetMapping("categoria/fuentes_poder")
    public String categoria_fuentes_poder(Model model){
//        List<Producto> productos = Arrays.asList(producto15, producto16);


  //      model.addAttribute("productos", productos);
        return "categoria/fuentes_poder";
    }
    @GetMapping("categoria/gabinetes")
    public String categoria_gabinetes(Model model){

//        List<Producto> productos = Arrays.asList(producto11, producto12);
  //      model.addAttribute("productos", productos);
        return "categoria/gabinetes";
    }
    @GetMapping("categoria/memorias_ram")
    public String categoria_memorias_ram(Model model){

//        List<Producto> productos = Arrays.asList(producto13, producto14);
  //      model.addAttribute("productos", productos);
        return "categoria/memorias_ram";
    }
    @GetMapping("categoria/placas_madre")
    public String categoria_placas_madre(Model model){
//        List<Producto> productos = Arrays.asList(producto17, producto18);


  //      model.addAttribute("productos", productos);
        return "categoria/placas_madre";
    }
    @GetMapping("categoria/tarjetas_graficas")
    public String categoria_tarjetas_graficas(Model model){

//        List<Producto> productos = Arrays.asList(producto6, producto7, producto8, producto9, producto10);
  //      model.addAttribute("productos", productos);
        return "categoria/tarjetas_graficas";
    }

    @GetMapping("/carrito")
    public String carrito(Model model){

//        List<Producto> productos = Arrays.asList(producto1,producto17, producto6);
  //      model.addAttribute("productos", productos);

        return "carrito";
    }


    @GetMapping("/comprobante")
    public String comprobante(){
        return "comprobante";
    }


    @GetMapping("/info_productos")
    public String info_productos(Model model){


//        List<Producto> productos = Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6, producto7, producto8, producto9, producto10, producto11, producto12, producto13, producto14, producto15, producto16, producto17, producto18);
  //      model.addAttribute("productos", productos);

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
    public String nuevo_administrador(){
        return "nuevo_administrador";
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

       // List<Producto> productos = Arrays.asList(producto1,producto17, producto6);
        //model.addAttribute("productos", productos);
        return "tipo_entrega";
    }


    @GetMapping("/actualizar_producto")
    public String actualizar_producto(Model model){

        //model.addAttribute("producto", producto17);
        return "actualizar_producto";
    }



    @RequestMapping( value ="/nuevo_producto")
    public String nuevo_producto() {
        return "nuevo_producto";
    }




    @PostMapping( value = "/insertar_producto")
    public String insertarProducto(@ModelAttribute Producto producto) {
        return "nuevo_producto";
    }



}
