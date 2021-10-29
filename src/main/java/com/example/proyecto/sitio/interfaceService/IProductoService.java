package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductoService {

    public List<Producto> listar();
    public int save(Producto a);
    public List<Producto> filtrar(String busqueda);
    public Producto buscarPorId(int id);


}
