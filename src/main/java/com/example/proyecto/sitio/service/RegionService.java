package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IRegionService;
import com.example.proyecto.sitio.interfaces.IRegion;
import com.example.proyecto.sitio.modelo.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Esta clase implementa los metodos de IRegionService
 * @version 23/11/2021
 */
@Service
public class RegionService implements IRegionService{

    @Autowired
    private IRegion data;

    /**
     * Metodo que devuelve las regiones existentes en la base de datos
     * @return  listado con las regiones encontradas
     */
    @Override
    public List<Region> listar() {
        return (List<Region>) data.findAll();
    }
}
