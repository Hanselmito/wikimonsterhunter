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
    private CheckBox elementoFuego;
    @FXML
    private CheckBox elementoAgua;
    @FXML
    private CheckBox elementoRayo;
    @FXML
    private CheckBox elementoHielo;
    @FXML
    private CheckBox elementoDraco;
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
    }

    private void ViewSelectedID() {
        Debilidades selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos != null) {
            elementoFuego.setSelected(selectedMonstruos.getElementoFuego() != DebilElementoFuego.SinDebilidad);
            elementoAgua.setSelected(selectedMonstruos.getElementoAgua() != DebilElementoAgua.SinDebilidad);
            elementoRayo.setSelected(selectedMonstruos.getElementoRayo() != DebilElementoRayo.SinDebilidad);
            elementoHielo.setSelected(selectedMonstruos.getElementoHielo() != DebilElementoHielo.SinDebilidad);
            elementoDraco.setSelected(selectedMonstruos.getElementoDraco() != DebilElementoDraco.SinDebilidad);
            efectividadFuego.setText(String.valueOf(selectedMonstruos.getEfectividadFuego()));
            efectividadAgua.setText(String.valueOf(selectedMonstruos.getEfectividadAgua()));
            efectividadRayo.setText(String.valueOf(selectedMonstruos.getEfectividadRayo()));
            efectividadHielo.setText(String.valueOf(selectedMonstruos.getEfectividadHielo()));
            efectividadDraco.setText(String.valueOf(selectedMonstruos.getEfectividadDraco()));
            idMonstruo.setText(String.valueOf(selectedMonstruos.getId_monstruo().getId()));
        }
    }

    private void LoadDebilidadesByIdData(){
        List<Debilidades> monstruosList = dDAO.findByAll();
        this.DebilidadesList = FXCollections.observableList(monstruosList);
        table.setItems(DebilidadesList);
    }

    @FXML
    public void handleInsertButtonAction() {
        // Retrieve data from form fields
        boolean fuego = elementoFuego.isSelected();
        boolean agua = elementoAgua.isSelected();
        boolean rayo = elementoRayo.isSelected();
        boolean hielo = elementoHielo.isSelected();
        boolean draco = elementoDraco.isSelected();

        // Check if the input fields are not empty
        if (efectividadFuego.getText().isEmpty() || efectividadAgua.getText().isEmpty() ||
                efectividadRayo.getText().isEmpty() || efectividadHielo.getText().isEmpty() ||
                efectividadDraco.getText().isEmpty() || idMonstruo.getText().isEmpty()) {
            showAlert("Todos los campos de efectividad y ID de monstruo deben estar llenos.");
            return;
        }

        int efectFuego = Integer.parseInt(efectividadFuego.getText());
        int efectAgua = Integer.parseInt(efectividadAgua.getText());
        int efectRayo = Integer.parseInt(efectividadRayo.getText());
        int efectHielo = Integer.parseInt(efectividadHielo.getText());
        int efectDraco = Integer.parseInt(efectividadDraco.getText());
        int idMonstruoValue = Integer.parseInt(idMonstruo.getText());

        Monstruos monstruo = MonstruosDAO.build().findById(idMonstruoValue);
        if (monstruo == null) {
            showAlert("No existe el monstruo con el ID proporcionado.");
            return;
        }

        // Create a new Debilidades object
        Debilidades debilidad = new Debilidades();
        debilidad.setElementoFuego(fuego);
        debilidad.setElementoAgua(agua);
        debilidad.setElementoRayo(rayo);
        debilidad.setElementoHielo(hielo);
        debilidad.setElementoDraco(draco);
        debilidad.setEfectividadFuego(efectFuego);
        debilidad.setEfectividadAgua(efectAgua);
        debilidad.setEfectividadRayo(efectRayo);
        debilidad.setEfectividadHielo(efectHielo);
        debilidad.setEfectividadDraco(efectDraco);
        debilidad.setId_monstruo(monstruo);

        try {
            dDAO.save(debilidad);
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

        // Retrieve data from form fields
        boolean fuego = elementoFuego.isSelected();
        boolean agua = elementoAgua.isSelected();
        boolean rayo = elementoRayo.isSelected();
        boolean hielo = elementoHielo.isSelected();
        boolean draco = elementoDraco.isSelected();
        int efectFuego = Integer.parseInt(efectividadFuego.getText());
        int efectAgua = Integer.parseInt(efectividadAgua.getText());
        int efectRayo = Integer.parseInt(efectividadRayo.getText());
        int efectHielo = Integer.parseInt(efectividadHielo.getText());
        int efectDraco = Integer.parseInt(efectividadDraco.getText());
        int idMonstruoValue = Integer.parseInt(idMonstruo.getText());

        Monstruos monstruo = MonstruosDAO.build().findById(idMonstruoValue);
        if (monstruo == null) {
            showAlert("No existe el monstruo con el ID proporcionado.");
            return;
        }

        // Update the selected Debilidades object
        selectedDebilidad.setElementoFuego(fuego);
        selectedDebilidad.setElementoAgua(agua);
        selectedDebilidad.setElementoRayo(rayo);
        selectedDebilidad.setElementoHielo(hielo);
        selectedDebilidad.setElementoDraco(draco);
        selectedDebilidad.setEfectividadFuego(efectFuego);
        selectedDebilidad.setEfectividadAgua(efectAgua);
        selectedDebilidad.setEfectividadRayo(efectRayo);
        selectedDebilidad.setEfectividadHielo(efectHielo);
        selectedDebilidad.setEfectividadDraco(efectDraco);
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
