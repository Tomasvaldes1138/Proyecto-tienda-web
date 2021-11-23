package com.example.proyecto.sitio.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Esta clase define la clase de Carrito
 * @version 23/11/2021
 */

public class Carrito {

    // Canpos de la clase
    private List<Producto> productos = new ArrayList<>();

    private List<PCantidad> productos2 = new ArrayList<>();


    public Carrito() {

    }// cierra funcion

    /**
     * Esta funcion agrega productos al carrito
     *
     * @param producto Es el producto que se va a agregar al carrito
     */
    public void anadirProducto(Producto producto){

       PCantidad producto1 = new PCantidad(producto, 1);
       PCantidad productoE = obtenerProductoPorId( producto.getId_producto() );
       if ( productoE  != null ){
            int index = productos2.indexOf(productoE);
            int cantidad = productoE.getCantidad();
            productos2.get(index).setCantidad(++cantidad);
       }else{
           productos2.add(producto1);
       }

    }// cierra funcion

    /**
     * Esta funcion obtiene el producto a traves del id
     *
     * @param id Es la id del producto que se busta
     * @return Es el Producto que se obtiene por la id
     */
    public PCantidad obtenerProductoPorId(int id){
        Optional<PCantidad> producto = productos2.stream().filter(p -> p.getProducto().getId_producto() == id).findFirst();

        if( producto.isEmpty() ){
            return null;
        }else{
            return producto.get();
        }
    }// cierra funcion

    /**
     * Esta funcion permite aumentar la cantidad de un producto
     *
     * @param id_producto Es la id del producto que se quiere aumentar la cantidad
     */
    public void aumentar_cantidad(int id_producto){
        PCantidad producto = obtenerProductoPorId(id_producto);
        producto.setCantidad( producto.getCantidad() + 1 );
    }// cierra funcion

    /**
     * Esta funcion permite disminuir la cantidad de un producto
     *
     * @param id_producto Es la id del producto que se quiere aumentar la cantidad
     */
    public void disminuir_cantidad(int id_producto){
        PCantidad producto = obtenerProductoPorId(id_producto);
        if(producto.getCantidad()>1){
            producto.setCantidad( producto.getCantidad() - 1 );
        }else{
            System.err.println("LA CANTIDAD NO PUEDE SER MENOR A 1");
        }
    }// cierra funcion

    /**
     * Esta funcion retorna una lista de PCantidad
     *
     * @return La lista de PCantidad
     */
    public List<PCantidad> getProductos() {
        return productos2;
    }// cierra funcion

    /**
     * Esta funcion retorna una lista de Producto
     *
     * @return la lista de Producto
     */
    public List<Producto> getProductos2() {
        return productos;
    }// cierra funcion

    /**
     * Obtiene la cantidad de los productos de PCantidad
     *
     * @return retorna un int con la cantidad de los productos
     */
    public int getCantidad(){
        return productos2.stream().mapToInt(PCantidad::getCantidad).sum();
    }// cierra funcion


    /**
     * Esta funcion obtiene el total de la suma de la lista de productos
     *
     * @return retorna un int con el total de la suma de los precios de los productos
     */
    public int getTotal(){
        int total = 0;
        for(PCantidad p : productos2){
            total += p.getProducto().getPrecio() * p.getCantidad();
        }
        System.err.println("TOTAL: " + total);
        return total;
    }// cierra funcion

    /**
     * Esta funcion remueve un producto de la lista PCantidad
     *
     * @param producto Es el producto que se desea eliminar de la lista
     * @return Retorna la lista de productos sin el producto eliminado
     */
    public boolean removerProducto(PCantidad producto){
        return productos2.remove(producto);
    }// cierra funcion

    /**
     * Elimina todos los productos del carrito
     */
    public void limpiarCarrito(){
        productos2.clear();
    }// cierra funcion

}// cierre clase
