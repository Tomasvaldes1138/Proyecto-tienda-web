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

    @Override
    public List<UsuarioProducto> listar() {
        return (List<UsuarioProducto>)  data.findAll();
    }

    @Override
    public int guardar(UsuarioProducto a) {
        int respuesta = 0;
        UsuarioProducto usuario_producto = data.save(a);
        if(!usuario_producto.equals(null)){
            respuesta = 1;
        }
        return respuesta;
    }

    @Override
    public List<UsuarioProducto> get_orden_producto(int id_orden) {

        List<UsuarioProducto> listado = (List<UsuarioProducto>) data.findAll();
        listado = listado.stream().filter( fila -> fila.getOrdenCompra().getId_orden() == id_orden).collect(Collectors.toList());
        return listado;
    }

    @Override
    public int getTotal(int id_orden){
        List<UsuarioProducto> listado = get_orden_producto(id_orden);
        int total = 0;
        for (UsuarioProducto up : listado){
            total += up.getCantidad() * up.getProducto().getPrecio();
        }
        System.out.println("TOTAL!: " + total);
        return total;
    }


}
