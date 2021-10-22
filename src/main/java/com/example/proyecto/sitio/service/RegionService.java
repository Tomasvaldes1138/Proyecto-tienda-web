package com.example.proyecto.sitio.service;

import com.example.proyecto.sitio.interfaceService.IRegionService;
import com.example.proyecto.sitio.interfaces.IRegion;
import com.example.proyecto.sitio.modelo.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService implements IRegionService{

    @Autowired
    private IRegion data;


    @Override
    public List<Region> listar() {
        return (List<Region>) data.findAll();
    }
}
