package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Materiales;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MaterialesMonstruo extends Controller implements Initializable {

    @FXML
    private Label sobreLabel; // Etiqueta para mostrar información sobre el monstruo
    @FXML
    private Label Generarl; // Etiqueta para la sección general
    @FXML
    private Label Equipo; // Etiqueta para la sección de equipo
    @FXML
    private Label Armas; // Etiqueta para la sección de armas

    @FXML
    private TableView<Materiales> materialesTableView; // Tabla para mostrar los materiales

    @FXML
    private TableColumn<Materiales, ImageView> iconoColumn; // Columna para los iconos de los materiales

    @FXML
    private TableColumn<Materiales, String> nombreColumn; // Columna para los nombres de los materiales

    @FXML
    private TableColumn<Materiales, String> probColumn; // Columna para las probabilidades de obtención

    @FXML
    private TableColumn<Materiales, String> medianteColumn; // Columna para el método de obtención

    @FXML
    private TableColumn<Materiales, Integer> cantidadColumn; // Columna para la cantidad de materiales

    private MonstruosDAO monstruosDAO = new MonstruosDAO(); // DAO para acceder a los datos de los monstruos
    private String monsterName; // Nombre del monstruo
    private ImageView imageView; // Vista de imagen para los iconos

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar la columna de iconos para mostrar las imágenes de los materiales
        iconoColumn.setCellValueFactory(cellData -> {
            byte[] visualData = cellData.getValue().getImagen();
            if (visualData != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(visualData);
                Image image = new Image(bis);

                imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                return new SimpleObjectProperty<>(imageView);
            } else {
                return null;
            }
        });
        // Configurar las columnas de la tabla con los valores correspondientes
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        probColumn.setCellValueFactory(new PropertyValueFactory<>("dropRate"));
        medianteColumn.setCellValueFactory(new PropertyValueFactory<>("mediante"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }

    @Override
    public void onOpen(Object input) throws Exception {
        // Establecer el nombre del monstruo y cargar los datos de los materiales
        if (input instanceof String) {
            monsterName = (String) input;
            loadMaterialesData(monsterName);
        }
    }

    // Cargar los datos de los materiales del monstruo
    private void loadMaterialesData(String monsterName) {
        List<Materiales> materialesList = monstruosDAO.findMaterialesByMonstruoName(monsterName);
        materialesTableView.getItems().setAll(materialesList);
        sobreLabel.setText("Sobre " + monsterName + ":");
    }

    @FXML
    private void goBack() throws Exception {
        // Cambiar a la escena de mostrar monstruos
        App.currentController.changeScene(Scenes.MonstrarMonstruos, monsterName);
    }

    @FXML
    private void goEquipo() throws Exception {
        // Cambiar a la escena de equipo del monstruo
        App.currentController.changeScene(Scenes.EquipoMonstruo, monsterName);
    }

    @FXML
    private void goArmas() throws Exception {
        // Cambiar a la escena de armas del monstruo
        App.currentController.changeScene(Scenes.ArmasMonstruo, monsterName);
    }

    @Override
    public void onClose(Object output) {
        // Método vacío para manejar el cierre de la vista
    }
}