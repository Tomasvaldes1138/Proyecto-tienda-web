package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IUsuarioProductoService;
import com.example.proyecto.sitio.interfaces.IUsuario;
import com.example.proyecto.sitio.interfaces.IUsuarioProducto;
import com.example.proyecto.sitio.modelo.OrdenCompra;
import com.example.proyecto.sitio.modelo.Producto;
import com.example.proyecto.sitio.modelo.UsuarioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioProductoService  implements IUsuarioProductoService {

    @Autowired
    private IUsuarioProducto data;

    /**
     * Metodo que retorna listado con los UsuarioProducto existentes en la base de datos
     * @return List<UsuarioProducto> UsuarioProducto encontrados
     */
    @Override
    public List<UsuarioProducto> listar() {
        return (List<UsuarioProducto>)  data.findAll();
    }

    /**
     * Metodo que guarda un UsuarioProducto en la base de datos
     * @param a UsuarioProducto a guardar
     * @return respuesta de la base de datos
     */
    @Override
    public int guardar(UsuarioProducto a) {
        int respuesta = 0;
        UsuarioProducto usuario_producto = data.save(a);
        if(!usuario_producto.equals(null)){
            respuesta = 1;
        }
        return respuesta;
    }

    /**
     * Metodo que retorna un listado UsuarioProducto segun el id_orden
     * @param id_orden id de la orden a buscar
     * @return Listado UsuarioProducto
     */
    @Override
    public List<UsuarioProducto> get_orden_producto(int id_orden) {

        List<UsuarioProducto> listado = (List<UsuarioProducto>) data.findAll();
        listado = listado.stream().filter( fila -> fila.getOrdenCompra().getId_orden() == id_orden).collect(Collectors.toList());
        return listado;
    }

    /**
     * Metodo que retorna el total de alguna compra realizada por los usuarios
      * @param id_orden id de la orden asociada a la compra
     * @return int con el total de la compra
     */
    @Override
    public int getTotal(int id_orden){
        List<UsuarioProducto> listado = get_orden_producto(id_orden);
        int total = 0;
        for (UsuarioProducto up : listado){
            total += up.getCantidad() * up.getProducto().getPrecio();
        }
        return total;
    }


}
