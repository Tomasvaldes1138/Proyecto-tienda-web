package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Administrador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAdministradorService {
    public List<Administrador> listar();
    public int save(Administrador a);
}
