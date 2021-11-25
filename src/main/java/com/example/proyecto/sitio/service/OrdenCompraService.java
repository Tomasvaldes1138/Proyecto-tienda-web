package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IOrdenCompraService;
import com.example.proyecto.sitio.interfaces.IOrdenCompra;
import com.example.proyecto.sitio.modelo.OrdenCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Esta clase implementa los metodos de IOrdenCompraService
 * @version 23/11/2021
 */

@Service
public class OrdenCompraService implements IOrdenCompraService {
    String hola = "safasf";

    @Autowired
    private IOrdenCompra data;

    @Override
    public List<OrdenCompra> listar() {
        return (List<OrdenCompra>) data.findAll();
    }

    /**
     * Guarda las ordenes de compra en la base de datos
     *
     * @param a La orden que se quiere guardar
     * @return int con la respuesta a la base de datos
     */
    @Override
    public int save(OrdenCompra a) {
        int respuesta = 0;
        OrdenCompra orden_compra = data.save(a);
        if(!orden_compra.equals(null)){
            respuesta = 1;
        }
        return 0;
    }

    /**
     * Metodo que busca ordenes de compra por su id
     * @param id id de la orden de compra  a buscar
     * @return OrdenCompra orden de compra encontrada
     */
    @Override
    public OrdenCompra buscarPorId(int id) {
        Optional<OrdenCompra> orden_compra = data.findById(id);
        if(orden_compra.isEmpty()){
            return null;
        }
        return orden_compra.get();
    }

    /**
     * Metodo que busca las ordenes de compras asociadas a un correo
     * @param correo correo del usuario relacionado con la orden
     * @return  listado de las ordenes asociadas a un usuario
     */
    @Override
    public List<OrdenCompra> buscarPorCorreo(String correo) {
        return ( (List<OrdenCompra>) data.findAll() ).stream().
                                    filter(oc -> oc.getUsuario().getCorreo().equals(correo))
                                    .collect(Collectors.toList());
    }

    @Override
    public List<OrdenCompra> filtrarComprobantesPendientes(String correo) {
        List<OrdenCompra> ordenes = this.buscarPorCorreo(correo);
        return ordenes.stream().filter(o -> o.getComprobantePago() == null).collect(Collectors.toList());
    }

}
