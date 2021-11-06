package com.example.proyecto.sitio.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carrito {

    private List<Producto> productos = new ArrayList<>();

    private List<PCantidad> productos2 = new ArrayList<>();

    //PCarrito
    //int id_producto
    //int cantidad


    public Carrito() {

    }

    public void anadirProducto(Producto producto){

       PCantidad producto1 = new PCantidad(producto, 1);
       PCantidad productoE = obtenerProductoPorId( producto.getId_producto() );
       if ( productoE  != null ){
           System.err.println("PRODUCTO DUPLICADO!!");
            int index = productos2.indexOf(productoE);
            int cantidad = productoE.getCantidad();
            productos2.get(index).setCantidad(++cantidad);
       }else{
           System.err.println("PRODUCTO NO DUPLICADO!");
           productos2.add(producto1);
       }

    }


    public PCantidad obtenerProductoPorId(int id){
        Optional<PCantidad> producto = productos2.stream().filter(p -> p.getProducto().getId_producto() == id).findFirst();

        if( producto.isEmpty() ){
            return null;
        }else{
            return producto.get();
        }
    }

    public void aumentar_cantidad(int id_producto){
        PCantidad producto = obtenerProductoPorId(id_producto);
        producto.setCantidad( producto.getCantidad() + 1 );
    }

    public void disminuir_cantidad(int id_producto){
        PCantidad producto = obtenerProductoPorId(id_producto);
        if(producto.getCantidad()>1){
            producto.setCantidad( producto.getCantidad() - 1 );
        }else{
            System.err.println("LA CANTIDAD NO PUEDE SER MENOR A 1");
        }
    }

    public List<PCantidad> getProductos() {
        return productos2;
    }

    public List<Producto> getProductos2() {
        return productos;
    }

    public int getCantidad(){
        return productos2.stream().mapToInt(PCantidad::getCantidad).sum();
    }
    public int getTotal(){
        int total = 0;
        for(PCantidad p : productos2){
            total += p.getProducto().getPrecio() * p.getCantidad();
        }
        System.err.println("TOTAL: " + total);
        return total;
    }

    public boolean removerProducto(PCantidad producto){
        return productos2.remove(producto);
    }




}
