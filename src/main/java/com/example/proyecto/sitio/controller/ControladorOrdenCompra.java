package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IOrdenCompraService;
import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.interfaceService.IUsuarioProductoService;
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
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.example.proyecto.sitio.controller.ControladorProducto.carrito;
import static com.example.proyecto.sitio.controller.ControladorUsuario.usuarioLogeado;

@Controller
@RequestMapping
public class ControladorOrdenCompra {

    @Autowired
    private IOrdenCompraService serviceOrdenCompra;

    @Autowired
    private IUsuarioProductoService serviceUsuarioProducto;

    @Autowired
    private IProductoService service;




    @PostMapping("/generar_orden_compra")
    public String generar_orden_compra(@ModelAttribute OrdenCompra ordenCompra) {
        LocalDateTime fechaActual = LocalDateTime.now(ZoneId.of("GMT-3"));
        ordenCompra.setUsuario(usuarioLogeado);
        ordenCompra.setTotal( serviceUsuarioProducto.getTotal(ordenCompra.getId_orden()) );
        ordenCompra.setFecha(fechaActual);
        serviceOrdenCompra.save(ordenCompra);
        //Aqui falta disminuir el stock del producto
        guardarOrdenMasProducto(ordenCompra);
        return "redirect:/orden_exitosa?id_orden="+ordenCompra.getId_orden() ;
    }

    public void guardarOrdenMasProducto(OrdenCompra ordenCompra){

        carrito.getProductos().forEach(p_cantidad-> {
            UsuarioProducto usuario_producto = new UsuarioProducto();
            usuario_producto.setOrdenCompra(ordenCompra);
            usuario_producto.setProducto( p_cantidad.getProducto() );
            usuario_producto.setCantidad( p_cantidad.getCantidad() );
            service.disminuir_stock(p_cantidad.getCantidad(), p_cantidad.getProducto().getId_producto() ); //disminuyendo stock
            serviceUsuarioProducto.guardar(usuario_producto);
        } );
        System.out.println("Ya deberian estar guardados");
    }

        //****************************************************
        //******************* ORDEN EXITOSA ******************
        //****************************************************

        @GetMapping("/orden_exitosa") //PELIGRO!!
        public String orden_exitosa (@RequestParam(name="id_orden") int id_orden, Model model){
            OrdenCompra orden_compra = new OrdenCompra();
            orden_compra.setId_orden(id_orden);

            model.addAttribute("orden_compra", orden_compra);
            model.addAttribute("precio", serviceUsuarioProducto.getTotal(id_orden) );
            String contenido = usuarioLogeado == null ? "Login" : usuarioLogeado.getNombres();
            model.addAttribute("nombre_cliente", contenido);
            return "orden_exitosa";
        }


        //****************************************************
        //******************* COMPROBANTE ********************
        //****************************************************

        @GetMapping("/comprobante")
        public String comprobante (@RequestParam(name="id_orden", required = false) int id_orden, Model model) {
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

                OrdenCompra actualizada = serviceOrdenCompra.buscarPorId(  ordenCompra.getId_orden()  );  //este ID deberia venir de un formulario
                actualizada.setComprobantePago( ordenCompra.getComprobantePago() );

                serviceOrdenCompra.save(actualizada);
            }
            return "redirect:/home";
        }


        @GetMapping("/orden_compra")
        public String orden_compra (@RequestParam(name="id_orden") int id_orden, Model model) {

            OrdenCompra ordenCompra = serviceOrdenCompra.buscarPorId(id_orden);
            List<UsuarioProducto> usuarioProducto = serviceUsuarioProducto.get_orden_producto(id_orden);

            model.addAttribute("orden", ordenCompra);
            model.addAttribute("precio_total", serviceUsuarioProducto.getTotal(id_orden) );
            model.addAttribute("usuarioProducto", usuarioProducto);

            return "orden_compra";
        }

        //****************************************************
        //***********COMPROBANTES Y ORDENES ******************
        //****************************************************

        @GetMapping(value = "mis_comprobantes")
        public String mis_comprobantes(Model model){
            if(usuarioLogeado==null){
                return "redirect:/login";
            }
            List<OrdenCompra> ordenes = serviceOrdenCompra.buscarPorCorreo(usuarioLogeado.getCorreo());
            model.addAttribute("ordenes", ordenes);
            return "mis_comprobantes";
        }

        @PostMapping("/orden_comprobante")
        public String orden_comprobante(@RequestParam(name="id_orden") int id_orden) {
            System.err.println("COMPROBANTE PARA ORDEN: " + id_orden);
            return "redirect:/orden_exitosa?id_orden="+id_orden;
        }

        @GetMapping("/mis_ordenes")
        public String mis_ordenes(Model model){
            if(usuarioLogeado==null){
                return "redirect:/login";
            }
            List<OrdenCompra> ordenes = serviceOrdenCompra.buscarPorCorreo(usuarioLogeado.getCorreo());
            model.addAttribute("ordenes", ordenes);
            return "mis_ordenes";
        }

    @PostMapping("/ver_orden_usuario")
    public String ver_orden_usuario(@RequestParam(name="id_orden") int id_orden) {
        return "redirect:/orden_compra?id_orden="+id_orden;
    }



}
