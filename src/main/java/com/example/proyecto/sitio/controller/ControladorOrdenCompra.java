package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IOrdenCompraService;
import com.example.proyecto.sitio.interfaceService.IUsuarioProductoService;
import com.example.proyecto.sitio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.example.proyecto.sitio.controller.ControladorProducto.carrito;
import static com.example.proyecto.sitio.controller.ControladorUsuario.usuarioLogeado;

@Controller
@RequestMapping
public class ControladorOrdenCompra {

    //@Qualifier("IOrdenCompraService")
    @Autowired
    private IOrdenCompraService serviceOrdenCompra;

    @Autowired
    private IUsuarioProductoService serviceUsuarioProducto;




    @PostMapping("/generar_orden_compra")
    public String generar_orden_compra(@ModelAttribute OrdenCompra ordenCompra) {
        LocalDateTime fechaActual = LocalDateTime.now(ZoneId.of("GMT-3"));
        ordenCompra.setUsuario(usuarioLogeado);
        ordenCompra.setFecha(fechaActual);
        serviceOrdenCompra.save(ordenCompra);
        //Aqui deberiamos guardar los productos que teniamos en el carrito.
        guardarOrdenMasProducto(ordenCompra);
        return "redirect:/orden_exitosa";
    }

    public void guardarOrdenMasProducto(OrdenCompra ordenCompra){

        carrito.getProductos().forEach(p_cantidad-> {
            UsuarioProducto usuario_producto = new UsuarioProducto();
            usuario_producto.setOrdenCompra(ordenCompra);
            usuario_producto.setProducto( p_cantidad.getProducto() );
            usuario_producto.setCantidad( p_cantidad.getCantidad() );
            serviceUsuarioProducto.guardar(usuario_producto);
        } );
        System.out.println("Ya deberian estar guardados");
    }

        //****************************************************
        //******************* ORDEN EXITOSA ******************
        //****************************************************

        @GetMapping("/orden_exitosa") //PELIGRO!!
        public String orden_exitosa (Model model){
 //           List<Producto> productos_carrito = get_productos_carrito();

            model.addAttribute("orden_compra", new OrdenCompra());
            model.addAttribute("precio", carrito.getTotal() );
            String contenido = usuarioLogeado == null ? "Login" : usuarioLogeado.getNombres();
            model.addAttribute("nombre_cliente", contenido);
            return "orden_exitosa";
        }


        //****************************************************
        //******************* COMPROBANTE ********************
        //****************************************************

        @GetMapping("/comprobante")
        public String comprobante (@RequestParam(name="id_orden", required = false) int id_orden, Model model) {
            System.out.println("Viene un ID de : "+ id_orden);
           // String comprobante = "/comprobantes/";
            String comprobante = serviceOrdenCompra.buscarPorId(id_orden).getComprobantePago();
            System.out.println(comprobante);

            model.addAttribute("comprobante", comprobante);
            return "comprobante";
        }

        @PostMapping("/subir_comprobante")
        public String subir (@ModelAttribute OrdenCompra ordenCompra,
                @RequestParam("file") MultipartFile comprobante){
            if (!comprobante.isEmpty()) {
                Path directorioComprobantes = Paths.get("src//main//resources//static/comprobantes");
                String rutaAbsoluta = directorioComprobantes.toFile().getAbsolutePath();
                try {
                    byte[] byteComprobante = comprobante.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + comprobante.getOriginalFilename());
                    Files.write(rutaCompleta, byteComprobante);

                    ordenCompra.setComprobantePago(comprobante.getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                OrdenCompra actualizada = serviceOrdenCompra.buscarPorId(7);  //este ID deberia venir de un formulario
                actualizada.setComprobantePago( ordenCompra.getComprobantePago() );

                serviceUsuarioProducto.get_orden_producto(14);

                serviceOrdenCompra.save(actualizada);
            }
            return "redirect:/home";
        }


        @GetMapping("/orden_compra")
        public String orden_compra (@RequestParam(name="id_orden", required = false) int id_orden, Model model) {

            OrdenCompra ordenCompra = serviceOrdenCompra.buscarPorId(id_orden);
            List<UsuarioProducto> usuarioProducto = serviceUsuarioProducto.get_orden_producto(id_orden);


            model.addAttribute("orden", ordenCompra);
            model.addAttribute("precio_total", serviceUsuarioProducto.getTotal(id_orden) );
            model.addAttribute("usuarioProducto", usuarioProducto);

            return "orden_compra";
        }



}
