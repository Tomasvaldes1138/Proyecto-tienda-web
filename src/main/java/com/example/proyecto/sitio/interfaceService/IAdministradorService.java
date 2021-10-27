package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Administrador;
import com.example.proyecto.sitio.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAdministradorService {
    public List<Administrador> listar();
    public int save(Administrador a);
    public Administrador iniciarSesion(String correo, String clave);
}
