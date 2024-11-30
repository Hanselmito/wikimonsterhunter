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
    private Label sobreLabel;
    @FXML
    private Label Generarl;
    @FXML
    private Label Equipo;
    @FXML
    private Label Armas;

    @FXML
    private TableView<Materiales> materialesTableView;

    @FXML
    private TableColumn<Materiales, ImageView> iconoColumn;

    @FXML
    private TableColumn<Materiales, String> nombreColumn;

    @FXML
    private TableColumn<Materiales, String> probColumn;

    @FXML
    private TableColumn<Materiales, String> medianteColumn;

    @FXML
    private TableColumn<Materiales, Integer> cantidadColumn;

    private MonstruosDAO monstruosDAO = new MonstruosDAO();
    private String monsterName;
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        probColumn.setCellValueFactory(new PropertyValueFactory<>("dropRate"));
        medianteColumn.setCellValueFactory(new PropertyValueFactory<>("mediante"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }

    @Override
    public void onOpen(Object input) throws Exception {
        if (input instanceof String) {
            monsterName = (String) input;
            loadMaterialesData(monsterName);
        }
    }

    private void loadMaterialesData(String monsterName) {
        List<Materiales> materialesList = monstruosDAO.findMaterialesByMonstruoName(monsterName);
        materialesTableView.getItems().setAll(materialesList);
        sobreLabel.setText("Sobre " + monsterName + ":");
    }

    @FXML
    private void goBack() throws Exception {
        App.currentController.changeScene(Scenes.MonstrarMonstruos, monsterName);
    }
    @FXML
    private void goEquipo() throws Exception {
        App.currentController.changeScene(Scenes.EquipoMonstruo, monsterName);
    }
    @FXML
    private void goArmas() throws Exception {
        App.currentController.changeScene(Scenes.ArmasMonstruo, monsterName);
    }

    @Override
    public void onClose(Object output) {
    }
}