package com.example.proyecto.sitio.interfaceService;

import com.example.proyecto.sitio.modelo.Region;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase define la interface IRegionService
 * @version 23/11/2021
 */

@Service
public interface IRegionService {
    public List<Region> listar();

}
