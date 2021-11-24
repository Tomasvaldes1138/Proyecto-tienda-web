package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.interfaces.IProducto;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Esta clase implementa los metodos de IProductoService
 * @version 23/11/2021
 */
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProducto data;

    /**
     *  Metodo que retorna un List de Producto
     * @return retorna un objeto List con los Productos encontrados
     */
    @Override
    public List<Producto> listar() {
        return (List<Producto>) data.findAll();
    }

    /**
     * Metodo qye guarda un producto en la base de datos
     * @param a producto a guardar
     * @return int con respuesta de la base de datos
     */
    @Override
    public int save(Producto a) {
        int respuesta = 0;
        Producto producto = data.save(a);
        if(!producto.equals(null)){
            respuesta = 1;
        }
        return 0;
    }

    /**
     * Metodo que filtra los productos cuyo nombre contenga los caracteres de busqueda
     * @param busqueda String para filtrar los productos
     * @return listado con los productos encontrados
     */
    @Override
    public List<Producto> filtrar(String busqueda) {
        List<Producto> productos = (List<Producto>) data.findAll();
        productos = productos.stream().filter(producto -> producto.getNombre().toLowerCase().contains(busqueda)).collect(Collectors.toList());
        return productos;
    }

    /**
     * Metodo que busca un producto por su id
     * @param id id del producto a buscar
     * @return producto encontrado
     */

    @Override
    public Producto buscarPorId(int id) {
         Optional<Producto> producto = data.findById(id);
         if(producto.isEmpty()){
             return null;
         }
        return producto.get();
    }

    /**
     * metodo que se encarga de disminuit el stock de un producto especifico
     * @param cantidad cantidad a disminuir
     * @param id_producto id del producto
     */
    @Override
    public void disminuir_stock(int cantidad, int id_producto) {
        Producto producto = buscarPorId(id_producto);
        producto.setStock(producto.getStock() - cantidad);
    }


}
