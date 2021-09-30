package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IProductoService;
import com.example.proyecto.sitio.interfaces.IProducto;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
