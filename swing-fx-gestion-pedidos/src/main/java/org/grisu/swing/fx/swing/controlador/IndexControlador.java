package org.grisu.swing.fx.swing.controlador;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.aspectj.weaver.patterns.IfPointcut;
import org.grisu.swing.fx.swing.entities.Producto;
import org.grisu.swing.fx.swing.servicio.IProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class IndexControlador implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private IProductoServicio servicio;
    @FXML
    private TableView<Producto> tareaTabla;
    @FXML
    private TableColumn<Producto, String> idTareaColumna;
    @FXML
    private TableColumn<Producto, String> seccionTareaColumna;
    @FXML
    private TableColumn<Producto, String> articuloColumna;
    @FXML
    private TableColumn<Producto, String> descripcionColumna;
    @FXML
    private TableColumn<Producto, String> precioColumna;
    @FXML
    private TableColumn<Producto, String> fechaColumna;
    @FXML
    private TableColumn<Producto, String> importadoColumna;
    @FXML
    private TableColumn<Producto, String> paisColumna;

    private final ObservableList<Producto> productoLista = FXCollections.observableArrayList();
    @FXML
    private TextField seccionTexto;
    @FXML
    private TextField articuloTexto;
    @FXML
    private TextField precioTexto;
    @FXML
    private TextField paisTexto;
    @FXML
    private TextField idInterno;
    @FXML
    private TextField descripcionTexto;
    @FXML
    private TextField fechaTexto;
    @FXML
    private TextField importadoTexto;
    @FXML
    private ComboBox seccionesItemCombo;
    @FXML
    private ComboBox paisesItemCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        seccionesItemCombo.getSelectionModel().selectedItemProperty();
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarProductos();

    }

    private void configurarColumnas() {
        idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        seccionTareaColumna.setCellValueFactory(new PropertyValueFactory<>("seccion"));
        articuloColumna.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        descripcionColumna.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<>("precio"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        importadoColumna.setCellValueFactory(new PropertyValueFactory<>("importado"));
        paisColumna.setCellValueFactory(new PropertyValueFactory<>("paisOrigen"));
    }

    private void listarProductos() {
        productoLista.clear();
        productoLista.addAll(servicio.obtenerProductos());
        tareaTabla.setItems(productoLista);
        seccionesItemCombo.setItems(new ObservableListWrapper<>(productoLista.stream()
                .map(Producto::getSeccion).distinct().toList()));
        paisesItemCombo.setItems(new ObservableListWrapper<>(productoLista.stream().map(Producto::getPaisOrigen).distinct().toList()));
    }



    public void agregarProducto() {
        if (seccionTexto.getText().isEmpty()) {
            mostrarMensaje("Error", "Debe proporcionar una sección");
            seccionTexto.requestFocus();
            return;
        } else {
            var producto = new Producto();
            recolectarDatosFormulario(producto);
            if (producto.getId().isEmpty()) {
                mostrarMensaje("Error", "Debe proporcionar  un id");
                idInterno.requestFocus();
                return;

            }


            producto.setId(producto.getId());
            servicio.agregarProducto(producto);
            mostrarMensaje("Información", "Producto creado con exito");
            limpiarFormulario();
            listarProductos();
        }
    }

    @FXML
    private void limpiarFormulario() {
        idInterno.clear();
        seccionTexto.clear();
        articuloTexto.clear();
        precioTexto.clear();
        paisTexto.clear();
        fechaTexto.clear();
        importadoTexto.clear();
        descripcionTexto.clear();


    }

    protected void recolectarDatosFormulario(Producto producto) {
        if (!idInterno.equals("null") || !idInterno.getText().equals("")) {
            producto.setId(idInterno.getText());
        }
        producto.setId(idInterno.getText());
        producto.setSeccion(seccionTexto.getText());
        producto.setNombreArticulo(articuloTexto.getText());
        producto.setPrecio(precioTexto.getText());
        producto.setPaisOrigen(paisTexto.getText());
        producto.setDescripcion(descripcionTexto.getText());
        producto.setFecha(fechaTexto.getText());
        producto.setImportado(importadoTexto.getText());

    }

    private void mostrarMensaje(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void cargarTareaFormulario() {
        var producto = tareaTabla.getSelectionModel().getSelectedItem();
        if (producto != null) {
            seccionTexto.setText(producto.getSeccion());
            articuloTexto.setText(producto.getNombreArticulo());
            precioTexto.setText(producto.getPrecio());
            paisTexto.setText(producto.getPaisOrigen());
            descripcionTexto.setText(producto.getDescripcion());
            fechaTexto.setText(producto.getFecha());
            importadoTexto.setText(producto.getImportado());
            idInterno.setText(producto.getId());


        }
    }

    public void modificarProducto() {
        if (idInterno.getText().isEmpty() && seccionTexto.getText().isEmpty() && articuloTexto.getText().isEmpty()) {
            mostrarMensaje("Error", "Debe seleccionar un producto");
            return;
        }
        if (idInterno.getText().isEmpty()) {
            mostrarMensaje("Error", "Debe proporcionar un id");
            return;
        }
        var producto = new Producto();
        recolectarDatosFormulario(producto);
        servicio.agregarProducto(producto);
        mostrarMensaje("Información", "Producto modificado con exito");
        limpiarFormulario();
        listarProductos();
    }

    public void eliminarProducto() {
        if (idInterno.getText().equals("") && seccionTexto.getText().equals("") && articuloTexto.getText().equals("")) {
            mostrarMensaje("Error", "Debe seleccionar un producto");
            return;
        }
        var producto = new Producto();
        recolectarDatosFormulario(producto);
        servicio.eliminarProducto(producto);
        mostrarMensaje("Información", "Producto eliminado con exito");
        limpiarFormulario();
        listarProductos();

    }

    public void cargaCombo() {
        productoLista.clear();
        Producto producto = new Producto();
        producto.setSeccion(seccionesItemCombo.getSelectionModel().getSelectedItem().toString());
//        System.out.println(producto.getSeccion());
        productoLista.addAll(servicio.obtenerProductoPorSeccion(producto.getSeccion()));
        tareaTabla.setItems(productoLista);

    }
}
