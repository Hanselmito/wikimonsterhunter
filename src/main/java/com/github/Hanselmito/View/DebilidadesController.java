package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.DebilidadesDAO;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Debilidades;
import com.github.Hanselmito.Entity.Enums.*;
import com.github.Hanselmito.Entity.Monstruos;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DebilidadesController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ChoiceBox<DebilElementoFuego> elementoFuego;
    @FXML
    private ChoiceBox<DebilElementoAgua> elementoAgua;
    @FXML
    private ChoiceBox<DebilElementoRayo> elementoRayo;
    @FXML
    private ChoiceBox<DebilElementoHielo> elementoHielo;
    @FXML
    private ChoiceBox<DebilElementoDraco> elementoDraco;
    @FXML
    private TextField efectividadFuego;
    @FXML
    private TextField efectividadAgua;
    @FXML
    private TextField efectividadRayo;
    @FXML
    private TextField efectividadHielo;
    @FXML
    private TextField efectividadDraco;
    @FXML
    private TextField idMonstruo;
    @FXML
    private TableView<Debilidades> table;
    @FXML
    private TableColumn<Debilidades, Integer> ID;
    @FXML
    private Button insert;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button back;

    private DebilidadesDAO dDAO = new DebilidadesDAO();

    private ObservableList<Debilidades> DebilidadesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadDebilidadesByIdData();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ViewSelectedID();
            }
        });
        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_monstruo().getId()).asObject());
        elementoFuego.getItems().addAll(DebilElementoFuego.values());
        elementoAgua.getItems().addAll(DebilElementoAgua.values());
        elementoRayo.getItems().addAll(DebilElementoRayo.values());
        elementoHielo.getItems().addAll(DebilElementoHielo.values());
        elementoDraco.getItems().addAll(DebilElementoDraco.values());
    }

    private void ViewSelectedID() {
        Debilidades selectedDebilidades = table.getSelectionModel().getSelectedItem();
        if (selectedDebilidades != null) {
            elementoFuego.setValue(selectedDebilidades.getElementoFuego());
            elementoAgua.setValue(selectedDebilidades.getElementoAgua());
            elementoRayo.setValue(selectedDebilidades.getElementoRayo());
            elementoHielo.setValue(selectedDebilidades.getElementoHielo());
            elementoDraco.setValue(selectedDebilidades.getElementoDraco());
            efectividadFuego.setText(String.valueOf(selectedDebilidades.getEfectividadFuego()));
            efectividadAgua.setText(String.valueOf(selectedDebilidades.getEfectividadAgua()));
            efectividadRayo.setText(String.valueOf(selectedDebilidades.getEfectividadRayo()));
            efectividadHielo.setText(String.valueOf(selectedDebilidades.getEfectividadHielo()));
            efectividadDraco.setText(String.valueOf(selectedDebilidades.getEfectividadDraco()));
            idMonstruo.setText(String.valueOf(selectedDebilidades.getId_monstruo()));
        }
    }

    private void LoadDebilidadesByIdData(){
        List<Debilidades> debilidadesList = dDAO.findByAll();
        this.DebilidadesList = FXCollections.observableList(debilidadesList);
        table.setItems(DebilidadesList);
    }

    @FXML
    public void handleInsertButtonAction() {
        DebilElementoFuego elementFuego = elementoFuego.getValue();
        DebilElementoAgua elementAgua = elementoAgua.getValue();
        DebilElementoRayo elementRayo = elementoRayo.getValue();
        DebilElementoHielo elementHielo = elementoHielo.getValue();
        DebilElementoDraco elementDraco = elementoDraco.getValue();

        String efectFuego = efectividadFuego.getText();
        String efectAgua = efectividadAgua.getText();
        String efectRayo = efectividadRayo.getText();
        String efectHielo = efectividadHielo.getText();
        String efectDraco = efectividadDraco.getText();
        String idMonstruoValue = idMonstruo.getText();

        // Check if the input fields are not empty
        if (efectividadFuego.getText().isEmpty() || efectividadAgua.getText().isEmpty() ||
                efectividadRayo.getText().isEmpty() || efectividadHielo.getText().isEmpty() ||
                efectividadDraco.getText().isEmpty() || idMonstruo.getText().isEmpty()) {
            showAlert("Todos los campos de efectividad y ID de monstruo deben estar llenos.");
            return;
        }

        Monstruos monstruo = MonstruosDAO.build().findById(Integer.parseInt(idMonstruoValue));
        if (monstruo == null) {
            showAlert("No existe el monstruo con el ID proporcionado.");
            return;
        }

        // Check if a Debilidades entity with the same ID of Monstruo already exists
        /**Debilidades existingDebilidad = dDAO.findById(monstruo.getId());
        if (existingDebilidad != null) {
            showAlert("Ya existe una entidad con el mismo ID de Monstruo.");
            return;
        }**/

        // Create a new Debilidades object
        Debilidades debilidades = new Debilidades();
        debilidades.setElementoFuego(elementFuego);
        debilidades.setElementoAgua(elementAgua);
        debilidades.setElementoRayo(elementRayo);
        debilidades.setElementoHielo(elementHielo);
        debilidades.setElementoDraco(elementDraco);
        debilidades.setEfectividadFuego(Integer.parseInt(efectFuego));
        debilidades.setEfectividadAgua(Integer.parseInt(efectAgua));
        debilidades.setEfectividadRayo(Integer.parseInt(efectRayo));
        debilidades.setEfectividadHielo(Integer.parseInt(efectHielo));
        debilidades.setEfectividadDraco(Integer.parseInt(efectDraco));
        debilidades.setId_monstruo(monstruo);

        try {
            dDAO.save(debilidades);
            LoadDebilidadesByIdData();
            showAlert("Entidad insertada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al insertar la entidad.");
        }
    }

    @FXML
    public void handleUpdateButtonAction() throws IOException {
        // Retrieve the selected Debilidades object from the table
        Debilidades selectedDebilidad = table.getSelectionModel().getSelectedItem();
        if (selectedDebilidad == null) {
            showAlert("Seleccione una debilidad para actualizar.");
            return;
        }


        DebilElementoFuego elementFuego = elementoFuego.getValue();
        DebilElementoAgua elementAgua = elementoAgua.getValue();
        DebilElementoRayo elementRayo = elementoRayo.getValue();
        DebilElementoHielo elementHielo = elementoHielo.getValue();
        DebilElementoDraco elementDraco = elementoDraco.getValue();

        String efectFuego = efectividadFuego.getText();
        String efectAgua = efectividadAgua.getText();
        String efectRayo = efectividadRayo.getText();
        String efectHielo = efectividadHielo.getText();
        String efectDraco = efectividadDraco.getText();
        String idMonstruoValue = idMonstruo.getText();

        Monstruos monstruo = MonstruosDAO.build().findById(Integer.parseInt(idMonstruoValue));
        if (monstruo == null) {
            showAlert("No existe el monstruo con el ID proporcionado.");
            return;
        }

        selectedDebilidad.setElementoFuego(elementFuego);
        selectedDebilidad.setElementoAgua(elementAgua);
        selectedDebilidad.setElementoRayo(elementRayo);
        selectedDebilidad.setElementoHielo(elementHielo);
        selectedDebilidad.setElementoDraco(elementDraco);
        selectedDebilidad.setEfectividadFuego(Integer.parseInt(efectFuego));
        selectedDebilidad.setEfectividadAgua(Integer.parseInt(efectAgua));
        selectedDebilidad.setEfectividadRayo(Integer.parseInt(efectRayo));
        selectedDebilidad.setEfectividadHielo(Integer.parseInt(efectHielo));
        selectedDebilidad.setEfectividadDraco(Integer.parseInt(efectDraco));
        selectedDebilidad.setId_monstruo(monstruo);

        try {
            dDAO.update(selectedDebilidad);
            LoadDebilidadesByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction(){
        Debilidades selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos == null) {
            showAlert("Seleccione un monstruo para eliminar.");
            return;
        }

        try {
            dDAO.delete(selectedMonstruos);
            LoadDebilidadesByIdData();
            showAlert("Entidad eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al eliminar la entidad.");
        }
    }

    @FXML
    private void goBack() throws Exception {
        App.currentController.changeScene(Scenes.MenuAdmin,null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }
}
