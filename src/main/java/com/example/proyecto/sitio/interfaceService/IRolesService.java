package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Roles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRolesService {
    public int save(Roles a);

}
