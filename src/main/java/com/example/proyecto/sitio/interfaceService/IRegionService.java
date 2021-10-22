package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Region;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRegionService {
    public List<Region> listar();

}
