package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.OrdenCompra;
import com.example.proyecto.sitio.modelo.UsuarioProducto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioProductoService {

    public List<UsuarioProducto> listar();
    public int guardar(UsuarioProducto a);
    public List<UsuarioProducto> get_orden_producto(int id_orden);


}
