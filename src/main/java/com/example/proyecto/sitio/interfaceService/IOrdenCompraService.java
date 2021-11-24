package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.OrdenCompra;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase define la interface IOrdenCompraService
 * @version 23/11/2021
 */

@Service
public interface IOrdenCompraService {
    public List<OrdenCompra> listar();
    public int save(OrdenCompra a);
    public OrdenCompra buscarPorId(int id);
    public List<OrdenCompra> buscarPorCorreo(String correo);


}
