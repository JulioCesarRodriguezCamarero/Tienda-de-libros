package org.grisu.swing.fx.swing;

import javafx.application.Application;
import org.grisu.swing.fx.swing.presentacion.SistemaProductosFx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwingFxGestionPedidosApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SwingFxGestionPedidosApplication.class, args);
        Application.launch(SistemaProductosFx.class,args);
    }

}
