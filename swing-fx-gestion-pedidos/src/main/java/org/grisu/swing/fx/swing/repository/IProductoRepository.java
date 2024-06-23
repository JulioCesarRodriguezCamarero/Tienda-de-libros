package org.grisu.swing.fx.swing.repository;

import org.grisu.swing.fx.swing.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductoRepository extends JpaRepository<Producto, String > {

    List<Producto> findBySeccion(String seccion);
}
