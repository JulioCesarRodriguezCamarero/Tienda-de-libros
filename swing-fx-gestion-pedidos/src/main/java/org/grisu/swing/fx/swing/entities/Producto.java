package org.grisu.swing.fx.swing.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "productos")
public class Producto implements Serializable {
    @Id
    @Column(name = "CODIGO_ARTICULO", nullable = false)
    private String id;
    @Column(name = "SECCION")
    private String seccion;
    @Column(name = "NOMBRE_ARTICULO")
    private String nombreArticulo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "PRECIO")
    private String precio;
    @Column(name = "FECHA")
    private String fecha;
    @Column(name = "IMPORTADO")
    private String importado;
    @Column(name = "PAÍS_DE_ORIGEN")
    private String paisOrigen;

    @Serial
    private static final long serialVersionUID = -2427727318881240771L;
}
