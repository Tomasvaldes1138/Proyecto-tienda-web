package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.modelo.Categorias;
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


        // HARDCODE DE PRODUCTOS
        // procesadores
        Producto producto1 = new Producto(1, "Intel i3 8100", Categorias.PROCESADORES, 10, 90990, "https://www.spdigital.cl/img/products/new_web/1513344557098-37987109_8596780584.jpg", 10);
        Producto producto2 = new Producto(2,"Intel i5 8400",Categorias.PROCESADORES,5,120000,"https://m.media-amazon.com/images/I/71Y9F8tJ8FL._AC_SL1500_.jpg",10);
        Producto producto3 = new Producto(3,"AMD Ryzen 3600",Categorias.PROCESADORES,5,12000,"https://m.media-amazon.com/images/I/71WPGXQLcLL._AC_SL1384_.jpg",10);
        Producto producto4 = new Producto(4,"AMD Ryzen 7 3700x",Categorias.PROCESADORES,7,200000,"https://m.media-amazon.com/images/I/717Di3DGIbL._AC_SL1092_.jpg",10);
        Producto producto5 = new Producto(5,"Intel i9 10900k",Categorias.PROCESADORES,2,300000,"https://www.profesionalreview.com/wp-content/uploads/2020/05/intel-core-i9-10900k-review10.png%22",10);



        Producto producto6 = new Producto(6, "MSI rtx 3080 ventus 3x", Categorias.TARJETAS_GRAFICAS, 5, 699990, "https://asset.msi.com/resize/image/global/product/product_7_20200917182157_5f6338c5cd72a.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png", 10);
        Producto producto7 = new Producto(7,"MSI gtx 1060 gaming X",Categorias.TARJETAS_GRAFICAS,10,13000,"https://c1.neweggimages.com/ProductImage/14-137-018-S99.jpg",10);
        Producto producto8 = new Producto(8,"AMD msi RX 6800 XT",Categorias.TARJETAS_GRAFICAS,7,500000,"https://c1.neweggimages.com/ProductImageCompressAll1280/14-137-607-V05.jpg",10);
        Producto producto9 = new Producto(9,"Gigabyte RTX 3090 Eagle",Categorias.TARJETAS_GRAFICAS,20,400000,"https://images10.newegg.com/BizIntell/item/14/932/14-932-327/GIGABYTE%20Video%20Card-a0.png",10);
        Producto producto10 = new Producto(10,"AMD XFX 5500 XT THICC II",Categorias.TARJETAS_GRAFICAS,7,190000,"https://images.jumpseller.com/store/bulldog-pc/7241669/Portada.jpg?1610197116",10);


        Producto producto11 = new Producto(11,"NZXT H510 -Black/Red",Categorias.GABINETES,20,90000,"https://m.media-amazon.com/images/I/51kYiXNtftL._AC_SL1000_.jpg",10);
        Producto producto12 = new Producto(12,"Corsair 4000X RGB",Categorias.GABINETES,30,70000,"https://m.media-amazon.com/images/I/81MvIP9T0mL._AC_SL1500_.jpg",10);


        Producto producto13 = new Producto(13,"G.Skill Tridentz RGB 16Gb 2x8 3600mhz",Categorias.MEMORIAS_RAM,100,40000,"https://m.media-amazon.com/images/I/61l4EStxhnL._AC_SL1274_.jpg",10);
        Producto producto14 = new Producto(14,"Corsair Vengeance LPX 16gb 2x8 3200mhz",Categorias.MEMORIAS_RAM,40,34000,"https://m.media-amazon.com/images/I/51kHiPeTSmL._AC_SL1000_.jpg",10);


        Producto producto15 = new Producto(15,"EVGA 430W 80 PLUS",Categorias.FUENTES_PODER,90,20000,"https://m.media-amazon.com/images/I/71Q+m2q9smL._AC_SL1200_.jpg",10);
        Producto producto16 = new Producto(16,"EVGA 450 80 PLUS BRONCE",Categorias.FUENTES_PODER,2,30000,"https://m.media-amazon.com/images/I/71zHGCtPNEL._AC_SL1200_.jpg",10);


        Producto producto17 = new Producto(17,"ASUS ROG Strix B450-F gaming AM4", Categorias.PLACAS_MADRE,10,150000,"https://m.media-amazon.com/images/I/91MWQtTH4bL._AC_SL1500_.jpg",10);
        Producto producto18 = new Producto(18,"ASUS ROG Strix Z590-E Gaming LGA 1200",Categorias.PLACAS_MADRE,3,30000,"https://m.media-amazon.com/images/I/91saKkUUPAL._AC_SL1500_.jpg",10);

        List<Producto> productos = Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6, producto7, producto8, producto9, producto10, producto11, producto12, producto13, producto14, producto15, producto16, producto17, producto18);
        model.addAttribute("productos", productos);

        return "home";
    }

    @GetMapping("categoria/procesadores")
    public String categoria_procesadores(Model model){

        Producto producto1 = new Producto(1, "Intel i3 8100", Categorias.PROCESADORES, 10, 90990, "https://www.spdigital.cl/img/products/new_web/1513344557098-37987109_8596780584.jpg", 10);
        Producto producto2 = new Producto(2,"Intel i5 8400",Categorias.PROCESADORES,5,120000,"https://m.media-amazon.com/images/I/71Y9F8tJ8FL._AC_SL1500_.jpg",10);
        Producto producto3 = new Producto(3,"AMD Ryzen 3600",Categorias.PROCESADORES,5,12000,"https://m.media-amazon.com/images/I/71WPGXQLcLL._AC_SL1384_.jpg",10);
        Producto producto4 = new Producto(4,"AMD Ryzen 7 3700x",Categorias.PROCESADORES,7,200000,"https://m.media-amazon.com/images/I/717Di3DGIbL._AC_SL1092_.jpg",10);
        Producto producto5 = new Producto(5,"Intel i9 10900k",Categorias.PROCESADORES,2,300000,"https://www.profesionalreview.com/wp-content/uploads/2020/05/intel-core-i9-10900k-review10.png%22",10);

        List<Producto> productos = Arrays.asList(producto1, producto2, producto3, producto4, producto5);
        model.addAttribute("productos", productos);
        return "categoria/procesadores";
    }
    @GetMapping("categoria/fuentes_poder")
    public String categoria_fuentes_poder(Model model){
        Producto producto15 = new Producto(15,"EVGA 430W 80 PLUS",Categorias.FUENTES_PODER,90,20000,"https://m.media-amazon.com/images/I/71Q+m2q9smL._AC_SL1200_.jpg",10);
        Producto producto16 = new Producto(16,"EVGA 450 80 PLUS BRONCE",Categorias.FUENTES_PODER,2,30000,"https://m.media-amazon.com/images/I/71zHGCtPNEL._AC_SL1200_.jpg",10);
        List<Producto> productos = Arrays.asList(producto15, producto16);


        model.addAttribute("productos", productos);
        return "categoria/fuentes_poder";
    }
    @GetMapping("categoria/gabinetes")
    public String categoria_gabinetes(Model model){

        Producto producto11 = new Producto(11,"NZXT H510 -Black/Red",Categorias.GABINETES,20,90000,"https://m.media-amazon.com/images/I/51kYiXNtftL._AC_SL1000_.jpg",10);
        Producto producto12 = new Producto(12,"Corsair 4000X RGB",Categorias.GABINETES,30,70000,"https://m.media-amazon.com/images/I/81MvIP9T0mL._AC_SL1500_.jpg",10);
        List<Producto> productos = Arrays.asList(producto11, producto12);
        model.addAttribute("productos", productos);
        return "categoria/gabinetes";
    }
    @GetMapping("categoria/memorias_ram")
    public String categoria_memorias_ram(Model model){

        Producto producto13 = new Producto(13,"G.Skill Tridentz RGB 16Gb 2x8 3600mhz",Categorias.MEMORIAS_RAM,100,40000,"https://m.media-amazon.com/images/I/61l4EStxhnL._AC_SL1274_.jpg",10);
        Producto producto14 = new Producto(14,"Corsair Vengeance LPX 16gb 2x8 3200mhz",Categorias.MEMORIAS_RAM,40,34000,"https://m.media-amazon.com/images/I/51kHiPeTSmL._AC_SL1000_.jpg",10);
        List<Producto> productos = Arrays.asList(producto13, producto14);
        model.addAttribute("productos", productos);
        return "categoria/memorias_ram";
    }
    @GetMapping("categoria/placas_madre")
    public String categoria_placas_madre(Model model){
        Producto producto17 = new Producto(17,"ASUS ROG Strix B450-F gaming AM4", Categorias.PLACAS_MADRE,10,150000,"https://m.media-amazon.com/images/I/91MWQtTH4bL._AC_SL1500_.jpg",10);
        Producto producto18 = new Producto(18,"ASUS ROG Strix Z590-E Gaming LGA 1200",Categorias.PLACAS_MADRE,3,30000,"https://m.media-amazon.com/images/I/91saKkUUPAL._AC_SL1500_.jpg",10);
        List<Producto> productos = Arrays.asList(producto17, producto18);


        model.addAttribute("productos", productos);
        return "categoria/placas_madre";
    }
    @GetMapping("categoria/tarjetas_graficas")
    public String categoria_tarjetas_graficas(Model model){

        Producto producto6 = new Producto(6, "MSI rtx 3080 ventus 3x", Categorias.TARJETAS_GRAFICAS, 5, 699990, "https://asset.msi.com/resize/image/global/product/product_7_20200917182157_5f6338c5cd72a.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png", 10);
        Producto producto7 = new Producto(7,"MSI gtx 1060 gaming X",Categorias.TARJETAS_GRAFICAS,10,13000,"https://c1.neweggimages.com/ProductImage/14-137-018-S99.jpg",10);
        Producto producto8 = new Producto(8,"AMD msi RX 6800 XT",Categorias.TARJETAS_GRAFICAS,7,500000,"https://c1.neweggimages.com/ProductImageCompressAll1280/14-137-607-V05.jpg",10);
        Producto producto9 = new Producto(9,"Gigabyte RTX 3090 Eagle",Categorias.TARJETAS_GRAFICAS,20,400000,"https://images10.newegg.com/BizIntell/item/14/932/14-932-327/GIGABYTE%20Video%20Card-a0.png",10);
        Producto producto10 = new Producto(10,"AMD XFX 5500 XT THICC II",Categorias.TARJETAS_GRAFICAS,7,190000,"https://images.jumpseller.com/store/bulldog-pc/7241669/Portada.jpg?1610197116",10);



        List<Producto> productos = Arrays.asList(producto6, producto7, producto8, producto9, producto10);
        model.addAttribute("productos", productos);
        return "categoria/tarjetas_graficas";
    }

    @GetMapping("/carrito")
    public String carrito(Model model){
        Producto producto1 = new Producto(1, "Intel i3 8100", Categorias.PROCESADORES, 10, 90990, "https://www.spdigital.cl/img/products/new_web/1513344557098-37987109_8596780584.jpg", 10);
        Producto producto17 = new Producto(17,"ASUS ROG Strix B450-F gaming AM4", Categorias.PLACAS_MADRE,10,150000,"https://m.media-amazon.com/images/I/91MWQtTH4bL._AC_SL1500_.jpg",10);
        Producto producto6 = new Producto(6, "MSI rtx 3080 ventus 3x", Categorias.TARJETAS_GRAFICAS, 5, 699990, "https://asset.msi.com/resize/image/global/product/product_7_20200917182157_5f6338c5cd72a.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png", 10);

        List<Producto> productos = Arrays.asList(producto1,producto17, producto6);
        model.addAttribute("productos", productos);


        return "carrito";
    }



    @GetMapping("/comprobante")
    public String comprobante(){
        return "comprobante";
    }



    @GetMapping("/info_productos")
    public String info_productos(Model model){


        Producto producto1 = new Producto(1, "Intel i3 8100", Categorias.PROCESADORES, 10, 90990, "https://www.spdigital.cl/img/products/new_web/1513344557098-37987109_8596780584.jpg", 10);
        Producto producto2 = new Producto(2,"Intel i5 8400",Categorias.PROCESADORES,5,120000,"https://m.media-amazon.com/images/I/71Y9F8tJ8FL._AC_SL1500_.jpg",10);
        Producto producto3 = new Producto(3,"AMD Ryzen 3600",Categorias.PROCESADORES,5,12000,"https://m.media-amazon.com/images/I/71WPGXQLcLL._AC_SL1384_.jpg",10);
        Producto producto4 = new Producto(4,"AMD Ryzen 7 3700x",Categorias.PROCESADORES,7,200000,"https://m.media-amazon.com/images/I/717Di3DGIbL._AC_SL1092_.jpg",10);
        Producto producto5 = new Producto(5,"Intel i9 10900k",Categorias.PROCESADORES,2,300000,"https://www.profesionalreview.com/wp-content/uploads/2020/05/intel-core-i9-10900k-review10.png%22",10);



        Producto producto6 = new Producto(6, "MSI rtx 3080 ventus 3x", Categorias.TARJETAS_GRAFICAS, 5, 699990, "https://asset.msi.com/resize/image/global/product/product_7_20200917182157_5f6338c5cd72a.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png", 10);
        Producto producto7 = new Producto(7,"MSI gtx 1060 gaming X",Categorias.TARJETAS_GRAFICAS,10,13000,"https://c1.neweggimages.com/ProductImage/14-137-018-S99.jpg",10);
        Producto producto8 = new Producto(8,"AMD msi RX 6800 XT",Categorias.TARJETAS_GRAFICAS,7,500000,"https://c1.neweggimages.com/ProductImageCompressAll1280/14-137-607-V05.jpg",10);
        Producto producto9 = new Producto(9,"Gigabyte RTX 3090 Eagle",Categorias.TARJETAS_GRAFICAS,20,400000,"https://images10.newegg.com/BizIntell/item/14/932/14-932-327/GIGABYTE%20Video%20Card-a0.png",10);
        Producto producto10 = new Producto(10,"AMD XFX 5500 XT THICC II",Categorias.TARJETAS_GRAFICAS,7,190000,"https://images.jumpseller.com/store/bulldog-pc/7241669/Portada.jpg?1610197116",10);


        Producto producto11 = new Producto(11,"NZXT H510 -Black/Red",Categorias.GABINETES,20,90000,"https://m.media-amazon.com/images/I/51kYiXNtftL._AC_SL1000_.jpg",10);
        Producto producto12 = new Producto(12,"Corsair 4000X RGB",Categorias.GABINETES,30,70000,"https://m.media-amazon.com/images/I/81MvIP9T0mL._AC_SL1500_.jpg",10);


        Producto producto13 = new Producto(13,"G.Skill Tridentz RGB 16Gb 2x8 3600mhz",Categorias.MEMORIAS_RAM,100,40000,"https://m.media-amazon.com/images/I/61l4EStxhnL._AC_SL1274_.jpg",10);
        Producto producto14 = new Producto(14,"Corsair Vengeance LPX 16gb 2x8 3200mhz",Categorias.MEMORIAS_RAM,40,34000,"https://m.media-amazon.com/images/I/51kHiPeTSmL._AC_SL1000_.jpg",10);


        Producto producto15 = new Producto(15,"EVGA 430W 80 PLUS",Categorias.FUENTES_PODER,90,20000,"https://m.media-amazon.com/images/I/71Q+m2q9smL._AC_SL1200_.jpg",10);
        Producto producto16 = new Producto(16,"EVGA 450 80 PLUS BRONCE",Categorias.FUENTES_PODER,2,30000,"https://m.media-amazon.com/images/I/71zHGCtPNEL._AC_SL1200_.jpg",10);


        Producto producto17 = new Producto(17,"ASUS ROG Strix B450-F gaming AM4", Categorias.PLACAS_MADRE,10,150000,"https://m.media-amazon.com/images/I/91MWQtTH4bL._AC_SL1500_.jpg",10);
        Producto producto18 = new Producto(18,"ASUS ROG Strix Z590-E Gaming LGA 1200",Categorias.PLACAS_MADRE,3,30000,"https://m.media-amazon.com/images/I/91saKkUUPAL._AC_SL1500_.jpg",10);

        List<Producto> productos = Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6, producto7, producto8, producto9, producto10, producto11, producto12, producto13, producto14, producto15, producto16, producto17, producto18);
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
        Producto producto1 = new Producto(1, "Intel i3 8100", Categorias.PROCESADORES, 10, 90990, "https://www.spdigital.cl/img/products/new_web/1513344557098-37987109_8596780584.jpg", 10);
        Producto producto17 = new Producto(17,"ASUS ROG Strix B450-F gaming AM4", Categorias.PLACAS_MADRE,10,150000,"https://m.media-amazon.com/images/I/91MWQtTH4bL._AC_SL1500_.jpg",10);
        Producto producto6 = new Producto(6, "MSI rtx 3080 ventus 3x", Categorias.TARJETAS_GRAFICAS, 5, 699990, "https://asset.msi.com/resize/image/global/product/product_7_20200917182157_5f6338c5cd72a.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png", 10);

        List<Producto> productos = Arrays.asList(producto1,producto17, producto6);
        model.addAttribute("productos", productos);
        return "tipo_entrega";
    }


    @GetMapping("/actualizar_producto")
    public String actualizar_producto(Model model){
        Producto producto17 = new Producto(17,"ASUS ROG Strix B450-F gaming AM4", Categorias.PLACAS_MADRE,10,150000,"https://m.media-amazon.com/images/I/91MWQtTH4bL._AC_SL1500_.jpg",10);

        model.addAttribute("producto", producto17);
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
