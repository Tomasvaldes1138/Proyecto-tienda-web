package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.ITipoEntregaService;
import com.example.proyecto.sitio.interfaces.ITipoEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoEntregaService implements ITipoEntregaService {
    @Autowired
    private ITipoEntrega data;
}
