package org.grisu.swing.fx.swing.servicio;

import org.grisu.swing.fx.swing.entities.Producto;

import java.util.List;

public interface IProductoServicio {

    List<Producto> obtenerProductos();
    void agregarProducto(Producto producto);
    void eliminarProducto(Producto producto);
    Producto obtenerProductoPorId(String id);
    List<Producto> obtenerProductoPorSeccion(String seccion);
}
