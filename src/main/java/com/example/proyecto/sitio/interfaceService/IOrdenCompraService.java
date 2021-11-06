package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.OrdenCompra;
import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrdenCompraService {
    public List<OrdenCompra> listar();
    public int save(OrdenCompra a);
    public void set_comprobante_pago(String comprobantePago);
    public OrdenCompra buscarPorId(int id);
    public List<OrdenCompra> buscarPorCorreo(String correo);


}
