package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.interfaces.IProducto;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProducto data;

    /**
     *  Metodo que retorna un List de Producto
     * @return List<Producto> retorna un objeto List con los Productos encontrados
     */
    @Override
    public List<Producto> listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public int save(Producto a) {
        int respuesta = 0;
        Producto producto = data.save(a);
        if(!producto.equals(null)){
            respuesta = 1;
        }
        return 0;
    }

    @Override
    public List<Producto> filtrar(String busqueda) {
        List<Producto> productos = (List<Producto>) data.findAll();
        productos = productos.stream().filter(producto -> producto.getNombre().toLowerCase().contains(busqueda)).collect(Collectors.toList());
        return productos;
    }

    @Override
    public Producto buscarPorId(int id) {
         Optional<Producto> producto = data.findById(id);
         if(producto.isEmpty()){
             return null;
         }
        return producto.get();
    }



}
