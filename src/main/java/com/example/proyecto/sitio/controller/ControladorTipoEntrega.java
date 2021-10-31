package com.example.proyecto.sitio.controller;


import com.example.proyecto.sitio.interfaceService.ICiudadService;
import com.example.proyecto.sitio.interfaceService.IRegionService;
import com.example.proyecto.sitio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.example.proyecto.sitio.controller.ControladorProducto.carrito;
import static com.example.proyecto.sitio.controller.ControladorUsuario.usuarioLogeado;

@Controller
@RequestMapping
public class ControladorTipoEntrega {

    @Autowired
    private IRegionService service_region;

    @Autowired
    private ICiudadService service_ciudad;


    //****************************************************
    //******************* TIPO ENTREGA *******************
    //****************************************************

    @GetMapping("/tipo_entrega")
    public String tipo_entrega(Model model){
        List<Producto> productos = carrito.getProductos();
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


}