package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.modelo.Categorias;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("/listar")
    public String listar(Model model) {
        //Producto producto1 = new Producto(1, "Manzana", "Manzanita", 2, "Frutas");

        //List<Producto> productos = service.listar();
        //model.addAttribute("productos", productos);
        //model.addAttribute("producto", C  );

        return "index";
    }

    @GetMapping("/home")
    public String home_productos(Model model){
        Producto producto1 = new Producto(1, "Intel i3 8100!!!", Categorias.PROCESADORES, 10, 90990, "https://www.spdigital.cl/img/products/new_web/1513344557098-37987109_8596780584.jpg", 10);
        Producto producto2 = new Producto(2, "MSI rtx 3080 ventus 3x", Categorias.TARJETAS_GRAFICAS, 5, 699990, "https://asset.msi.com/resize/image/global/product/product_7_20200917182157_5f6338c5cd72a.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png", 10);

        List<Producto> productos = Arrays.asList(producto1, producto2);

        model.addAttribute("productos", productos);

        return "home";
    }


}
