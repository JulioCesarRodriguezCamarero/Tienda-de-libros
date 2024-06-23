package org.grisu.swing.fx.swing.servicio;

import org.grisu.swing.fx.swing.entities.Producto;
import org.grisu.swing.fx.swing.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServicioImpl implements IProductoServicio {

    @Autowired
    private IProductoRepository repository;

    @Override
    public List<Producto> obtenerProductos() {
        return repository.findAll();
    }

    @Override
    public void agregarProducto(Producto producto) {
        repository.save(producto);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        repository.delete(producto);
    }

    @Override
    public Producto obtenerProductoPorId(String id) {
        return repository.findById(id).orElseGet(null);
    }

    @Override
    public List<Producto> obtenerProductoPorSeccion(String seccion) {
        return repository.findBySeccion(seccion);
    }


}
