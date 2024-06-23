package org.grisu.swing.fx.swing.presentacion;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.grisu.swing.fx.swing.SwingFxGestionPedidosApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class SistemaProductosFx extends Application {

    //    public static void main(String[] args) {
//        launch(args);
//    }
    private ConfigurableApplicationContext contexto;

    @Override
    public void init() throws Exception {
       this.contexto = new SpringApplicationBuilder(SwingFxGestionPedidosApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
//cargar archivo index para mostrar los elementos
        FXMLLoader loader = new FXMLLoader(SwingFxGestionPedidosApplication.class.getResource("/templates/Index.fxml"));

        // cargar objetos de Spring
        loader.setControllerFactory(contexto::getBean);

        // configurar objetos de FX, arroja una excepción y la configuramos al tipo más genérico
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    // Método para detener la aplicación
    @Override
    public void stop() throws Exception {
      contexto.close();
        Platform.exit();
    }
}
