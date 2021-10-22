package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IOrdenCompraService;
import com.example.proyecto.sitio.interfaces.IOrdenCompra;
import com.example.proyecto.sitio.modelo.OrdenCompra;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCompraService implements IOrdenCompraService {

    @Autowired
    private IOrdenCompra data;

    @Override
    public List<OrdenCompra> listar() {
        return (List<OrdenCompra>) data.findAll();
    }

    @Override
    public int save(OrdenCompra a) {
        int respuesta = 0;
        OrdenCompra orden_compra = data.save(a);
        if(!orden_compra.equals(null)){
            respuesta = 1;
        }
        return 0;
    }
}
