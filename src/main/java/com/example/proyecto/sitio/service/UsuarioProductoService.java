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
        System.out.println("Guardando ID orden con ID Producto");
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

        System.out.println("deberiamos tener una lista con los productos asociados a una compra con id: " + id_orden);

        listado.forEach(fila -> System.out.println(fila.toString()));

        return null;
    }


}
