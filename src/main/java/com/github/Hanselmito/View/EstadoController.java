package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.EstadoDAO;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Enums.*;
import com.github.Hanselmito.Entity.Estado;
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

public class EstadoController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ChoiceBox<EstadoVeneno> estadoVeneno;
    @FXML
    private ChoiceBox<EstadoSueno> estadoSueno;
    @FXML
    private ChoiceBox<EstadoParalisis> estadoParalisis;
    @FXML
    private ChoiceBox<EstadoNitro> estadoNitro;
    @FXML
    private ChoiceBox<EstadoAturdimiento> estadoAturdimiento;
    @FXML
    private TextField efectividadVeneno;
    @FXML
    private TextField efectividadSueno;
    @FXML
    private TextField efectividadParalisis;
    @FXML
    private TextField efectividadNitro;
    @FXML
    private TextField efectividadAturdimiento;
    @FXML
    private TextField idMonstruo;
    @FXML
    private TableView<Estado> table;
    @FXML
    private TableColumn<Estado, Integer> ID;
    @FXML
    private Button insert;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button back;

    private EstadoDAO esDAO = new EstadoDAO();

    private ObservableList<Estado> estadoList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadEstadoByIdData();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ViewSelectedID();
            }
        });
        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_monstruo().getId()).asObject());
        estadoVeneno.getItems().addAll(EstadoVeneno.values());
        estadoSueno.getItems().addAll(EstadoSueno.values());
        estadoParalisis.getItems().addAll(EstadoParalisis.values());
        estadoNitro.getItems().addAll(EstadoNitro.values());
        estadoAturdimiento.getItems().addAll(EstadoAturdimiento.values());
    }

    private void ViewSelectedID() {
        Estado selectedEstado = table.getSelectionModel().getSelectedItem();
        if (selectedEstado != null) {
            estadoVeneno.setValue(selectedEstado.getEstadoVeneno());
            estadoSueno.setValue(selectedEstado.getEstadoSueno());
            estadoParalisis.setValue(selectedEstado.getEstadoParalisis());
            estadoNitro.setValue(selectedEstado.getEstadoNitro());
            estadoAturdimiento.setValue(selectedEstado.getEstadoAturdimiento());
            efectividadVeneno.setText(String.valueOf(selectedEstado.getEfectividadVeneno()));
            efectividadSueno.setText(String.valueOf(selectedEstado.getEfectividadSueno()));
            efectividadParalisis.setText(String.valueOf(selectedEstado.getEfectividadParalisis()));
            efectividadNitro.setText(String.valueOf(selectedEstado.getEfectividadNitro()));
            efectividadAturdimiento.setText(String.valueOf(selectedEstado.getEfectividadAturdimiento()));
            idMonstruo.setText(String.valueOf(selectedEstado.getId_monstruo()));
        }
    }

    private void LoadEstadoByIdData(){
        List<Estado> debilidadesList = esDAO.findByAll();
        this.estadoList = FXCollections.observableList(debilidadesList);
        table.setItems(estadoList);
    }

    @FXML
    public void handleInsertButtonAction() {
        EstadoVeneno elementVeneno = estadoVeneno.getValue();
        EstadoSueno elementSueno = estadoSueno.getValue();
        EstadoParalisis elementParalisis = estadoParalisis.getValue();
        EstadoNitro elementNitro = estadoNitro.getValue();
        EstadoAturdimiento elementAturdimiento = estadoAturdimiento.getValue();

        String efectVeneno = efectividadVeneno.getText();
        String efectSueno = efectividadSueno.getText();
        String efectParalisis = efectividadParalisis.getText();
        String efectNitro = efectividadNitro.getText();
        String efectAturdimiento = efectividadAturdimiento.getText();
        String idMonstruoValue = idMonstruo.getText();

        // Check if the input fields are not empty
        if (efectividadVeneno.getText().isEmpty() || efectividadSueno.getText().isEmpty() ||
                efectividadParalisis.getText().isEmpty() || efectividadNitro.getText().isEmpty() ||
                efectividadAturdimiento.getText().isEmpty() || idMonstruo.getText().isEmpty()) {
            showAlert("Todos los campos de efectividad y ID de monstruo deben estar llenos.");
            return;
        }

        Monstruos monstruo = MonstruosDAO.build().findById(Integer.parseInt(idMonstruoValue));
        if (monstruo == null) {
            showAlert("No existe el monstruo con el ID proporcionado.");
            return;
        }

        // Check if an Estado entity with the same ID of Monstruo already exists
        /**Estado existingEstado = esDAO.findById(monstruo.getId());
         if (existingEstado != null) {
         showAlert("Ya existe una entidad con el mismo ID de Monstruo.");
         return;
         }**/


            // Create a new Estado object
            Estado estado = new Estado();
            estado.setEstadoVeneno(elementVeneno);
            estado.setEstadoSueno(elementSueno);
            estado.setEstadoParalisis(elementParalisis);
            estado.setEstadoNitro(elementNitro);
            estado.setEstadoAturdimiento(elementAturdimiento);
            estado.setEfectividadVeneno(Integer.parseInt(efectVeneno));
            estado.setEfectividadSueno(Integer.parseInt(efectSueno));
            estado.setEfectividadParalisis(Integer.parseInt(efectParalisis));
            estado.setEfectividadNitro(Integer.parseInt(efectNitro));
            estado.setEfectividadAturdimiento(Integer.parseInt(efectAturdimiento));
            estado.setId_monstruo(monstruo);

            try {
                esDAO.save(estado);
                LoadEstadoByIdData();
                showAlert("Entidad insertada correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error al insertar la entidad.");
            }

    }

    @FXML
    public void handleUpdateButtonAction() throws IOException {
        // Retrieve the selected Debilidades object from the table
        Estado selectedEstado = table.getSelectionModel().getSelectedItem();
        if (selectedEstado == null) {
            showAlert("Seleccione un estado para actualizar.");
            return;
        }


        EstadoVeneno elementVeneno = estadoVeneno.getValue();
        EstadoSueno elementSueno = estadoSueno.getValue();
        EstadoParalisis elementParalisis = estadoParalisis.getValue();
        EstadoNitro elementNitro = estadoNitro.getValue();
        EstadoAturdimiento elementAturdimiento = estadoAturdimiento.getValue();
        String efectVeneno = efectividadVeneno.getText();
        String efectSueno = efectividadSueno.getText();
        String efectParalisis = efectividadParalisis.getText();
        String efectNitro = efectividadNitro.getText();
        String efectAturdimiento = efectividadAturdimiento.getText();
        String idMonstruoValue = idMonstruo.getText();

        Monstruos monstruo = MonstruosDAO.build().findById(Integer.parseInt(idMonstruoValue));
        if (monstruo == null) {
            showAlert("No existe el monstruo con el ID proporcionado.");
            return;
        }

        selectedEstado.setEstadoVeneno(elementVeneno);
        selectedEstado.setEstadoSueno(elementSueno);
        selectedEstado.setEstadoParalisis(elementParalisis);
        selectedEstado.setEstadoNitro(elementNitro);
        selectedEstado.setEstadoAturdimiento(elementAturdimiento);
        selectedEstado.setEfectividadVeneno(Integer.parseInt(efectVeneno));
        selectedEstado.setEfectividadSueno(Integer.parseInt(efectSueno));
        selectedEstado.setEfectividadParalisis(Integer.parseInt(efectParalisis));
        selectedEstado.setEfectividadNitro(Integer.parseInt(efectNitro));
        selectedEstado.setEfectividadAturdimiento(Integer.parseInt(efectAturdimiento));
        selectedEstado.setId_monstruo(monstruo);

        try {
            esDAO.update(selectedEstado);
            LoadEstadoByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction(){
        Estado selectedEstado = table.getSelectionModel().getSelectedItem();
        if (selectedEstado == null) {
            showAlert("Seleccione un monstruo para eliminar.");
            return;
        }

        try {
            esDAO.delete(selectedEstado);
            LoadEstadoByIdData();
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
