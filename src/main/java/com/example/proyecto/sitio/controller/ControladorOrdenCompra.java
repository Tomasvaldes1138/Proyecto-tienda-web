package com.example.proyecto.sitio.controller;

import com.example.proyecto.sitio.interfaceService.IOrdenCompraService;
import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.interfaceService.IUsuarioProductoService;
import com.example.proyecto.sitio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

@Controller
@RequestMapping

/**
 * Esta clase contiene todos los metodos necesarios para el funcionamiento de la orden de compra
 * @version 23/11/2021
 */

public class ControladorOrdenCompra {

    @Autowired
    private IOrdenCompraService serviceOrdenCompra;

    @Autowired
    private IUsuarioProductoService serviceUsuarioProducto;

    @Autowired
    private IProductoService service;


    /**
     * Esta funcion genera la orden de compra con los datos y productos del cliente, fecha y total
     *
     * @param ordenCompra Es la orden de compra a la cual es le agregaran todos los datos
     * @param auth Permite obtener el nombre del usuario que ha iniciado sesion
     * @return Redirecciona a la vista orden exitosa para subir el comprobante
     */

    @PostMapping("/generar_orden_compra")
    public String generar_orden_compra(@ModelAttribute OrdenCompra ordenCompra, Authentication auth) {
        LocalDateTime fechaActual = LocalDateTime.now(ZoneId.of("GMT-3"));

        Usuario usuario = new Usuario();
        usuario.setCorreo(auth.getName());

        ordenCompra.setUsuario(usuario);
        ordenCompra.setTotal( carrito.getTotal() );
        ordenCompra.setFecha(fechaActual);
        serviceOrdenCompra.save(ordenCompra);
        guardarOrdenMasProducto(ordenCompra);
        carrito.limpiarCarrito();
        return "redirect:/orden_exitosa?id_orden="+ordenCompra.getId_orden() ;
    }// cierra funcion

    /**
     * Esta funcion guarda los productos con sus cantidades en la orden de compra
     *
     * @param ordenCompra Es la orden de compra a la cual se le agregaran los productos
     */

    public void guardarOrdenMasProducto(OrdenCompra ordenCompra){

        carrito.getProductos().forEach(p_cantidad-> {
            UsuarioProducto usuario_producto = new UsuarioProducto();
            usuario_producto.setOrdenCompra(ordenCompra);
            usuario_producto.setProducto( p_cantidad.getProducto() );
            usuario_producto.setCantidad( p_cantidad.getCantidad() );
            service.disminuir_stock(p_cantidad.getCantidad(), p_cantidad.getProducto().getId_producto() ); //disminuyendo stock
            serviceUsuarioProducto.guardar(usuario_producto);
        } );
    }// cierra funcion

        //****************************************************
        //******************* ORDEN EXITOSA ******************
        //****************************************************

    /**
     * Es la funcion redirecciona a la vista que muestra la orden exitosa del comprobante generado
     *
     * @param id_orden Es la orden de compra a la que se le obtendran los datos
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista orden exitosa
     */
        @GetMapping("/orden_exitosa") //PELIGRO!!
        public String orden_exitosa (@RequestParam(name="id_orden") int id_orden, Model model){
            OrdenCompra orden_compra = new OrdenCompra();
            orden_compra.setId_orden(id_orden);

            model.addAttribute("orden_compra", orden_compra);
            model.addAttribute("precio", serviceUsuarioProducto.getTotal(id_orden) );

            return "orden_exitosa";
        }// cierra funcion


        //****************************************************
        //******************* COMPROBANTE ********************
        //****************************************************
    /**
     * Esta funcion muestra el comprobante de pago de una orden
     *
     * @param id_orden Es la orden a la cual se le obtiene el comprobante
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista comprobante
     */
        @GetMapping("/comprobante")
        public String comprobante (@RequestParam(name="id_orden", required = false) int id_orden, Model model) {
            String comprobante = serviceOrdenCompra.buscarPorId(id_orden).getComprobantePago();
            System.out.println(comprobante);
            model.addAttribute("comprobante", comprobante);
            return "comprobante";
        }// cierra funcion

    /**
     * Esta funcion permite agregar el comprobante a una orden de compra
     *
     * @param ordenCompra Es la orden de compra a la cual es le quiere agregar el comprobante
     * @param comprobante Es el archivo comprobante que va a ser subido
     * @return Redireccion a la vista home
     */
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

                OrdenCompra actualizada = serviceOrdenCompra.buscarPorId(  ordenCompra.getId_orden()  );
                actualizada.setComprobantePago( ordenCompra.getComprobantePago() );

                serviceOrdenCompra.save(actualizada);
            }
            return "redirect:/home";
        }// cierra funcion

    /**
     * Esta funcion muestra todos los datos de una orden de compra en especifico
     *
     * @param id_orden Es la orden de compra a la que se le obtendran los datos
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @return Redirecciona a la vista orden compra
     */
        @GetMapping("/orden_compra")
        public String orden_compra (@RequestParam(name="id_orden") int id_orden, Model model) {

            OrdenCompra ordenCompra = serviceOrdenCompra.buscarPorId(id_orden);
            List<UsuarioProducto> usuarioProducto = serviceUsuarioProducto.get_orden_producto(id_orden);

            model.addAttribute("orden", ordenCompra);
            model.addAttribute("precio_total", serviceUsuarioProducto.getTotal(id_orden) );
            model.addAttribute("usuarioProducto", usuarioProducto);

            return "orden_compra";
        }// cierra funcion

        //****************************************************
        //***********COMPROBANTES Y ORDENES ******************
        //****************************************************


    /**
     * Esta funcion redirecciona a la vista orden exitosa para subir el comprobante
     * a ordenes que esten pendientes
     *
     * @param id_orden Es la orden de compra a la que se le obtendran los datos
     * @return Redirecciona a orden exitosa
     */

      @PostMapping("/orden_comprobante")
      public String orden_comprobante(@RequestParam(name="id_orden") int id_orden) {
          System.err.println("COMPROBANTE PARA ORDEN: " + id_orden);
          return "redirect:/orden_exitosa?id_orden="+id_orden;
      }

       
    /**
     * Esta funcion redirecciona a una orden de compra en especifico
     * @param id_orden Es la orden de compra a la que se le obtendran los datos
     * @return Redirecciona a la orden de compra que se quiere ver
     */
    @PostMapping("/ver_orden_usuario")
    public String ver_orden_usuario(@RequestParam(name="id_orden") int id_orden) {
        return "redirect:/orden_compra?id_orden="+id_orden;
    }// cierra funcion

    /**
     * Esta funcion permite a los usuarios acceder a las ordenes de compra con
     * comprobantes pendientes
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @param auth Permite obtener el nombre del usuario que ha iniciado sesion
     * @return Redirecciona a mis comprobantes
     */
      @GetMapping(value = "mis_comprobantes")
      public String mis_comprobantes(Model model, Authentication auth){
          List<OrdenCompra> ordenes = serviceOrdenCompra.filtrarComprobantesPendientes(auth.getName());
          model.addAttribute("ordenes", ordenes);
          return "mis_comprobantes";
      }// cierra funcion

    /**
     * Esta funcion permite a los usuarios acceder a las ordenes de compra realizada
     * por el usuario
     *
     * @param model Es un contenedor de Spring Boot que tiene informacion del programa
     * @param auth Permite obtener el nombre del usuario que ha iniciado sesion
     * @return Redirecciona a mis ordenes
     */
      @GetMapping("/mis_ordenes")
      public String mis_ordenes(Model model, Authentication auth){
          List<OrdenCompra> ordenes = serviceOrdenCompra.buscarPorCorreo(auth.getName());
          model.addAttribute("ordenes", ordenes);
          return "mis_ordenes";
      }// cierra funcion

}// cierre clase
