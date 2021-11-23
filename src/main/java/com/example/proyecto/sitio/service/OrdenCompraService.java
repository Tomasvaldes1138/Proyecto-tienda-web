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

@Service
public class OrdenCompraService implements IOrdenCompraService {
    String hola = "safasf";

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

    @Override
    @Query(value = "update orden_compra set comprobante_pago = :comprobantePago where id = 5 ", nativeQuery = true)
    public void set_comprobante_pago(String comprobantePago) {
        data.findById(3).get().setComprobantePago(comprobantePago);
    }

    @Override
    public OrdenCompra buscarPorId(int id) {
        Optional<OrdenCompra> orden_compra = data.findById(id);
        if(orden_compra.isEmpty()){
            return null;
        }
        return orden_compra.get();
    }



    @Override
    public List<OrdenCompra> buscarPorUsername(String username) {
        return ( (List<OrdenCompra>) data.findAll() ).stream().
                                    filter(oc -> oc.getUsuario().getUsername().equals(username))
                                    .collect(Collectors.toList());
    }
}
