package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.OrdenCompra;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrdenCompraService {
    public List<OrdenCompra> listar();
    public int save(OrdenCompra a);
}
