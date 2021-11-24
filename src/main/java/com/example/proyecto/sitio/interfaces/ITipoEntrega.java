package com.example.proyecto.sitio.interfaces;

import com.example.proyecto.sitio.modelo.TipoEntrega;
import org.springframework.data.repository.CrudRepository;

/**
 * Esta clase define la interface ITipoEntrega
 * de la cual es obtiene el crud para insertar en la base de datos
 * @version 23/11/2021
 */

public interface ITipoEntrega extends CrudRepository<TipoEntrega, Integer> {

}
