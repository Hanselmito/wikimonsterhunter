package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.*;
import com.github.Hanselmito.Entity.*;
import com.github.Hanselmito.Entity.Enums.*;
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

public class OtorgaController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField id_Equipo;
    @FXML
    private TextField id_Abilidades;
    @FXML
    private TableView<Otorga> table;
    @FXML
    private TableColumn<Otorga, Integer> ID_Equipo;
    @FXML
    private TableColumn<Otorga, Integer> ID_Abilidades;
    @FXML
    private Button insert;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button back;

    private OtorgaDAO otDAO = new OtorgaDAO();

    private ObservableList<Otorga> OtorgaList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadDebilidadesByIdData();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ViewSelectedID();
            }
        });
        ID_Equipo.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_Equipo().getId()).asObject());
        ID_Abilidades.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_Abilidades().getId()).asObject());
    }

    private void ViewSelectedID() {
        Otorga selectedOtorga = table.getSelectionModel().getSelectedItem();
        if (selectedOtorga != null) {
            id_Equipo.setText(String.valueOf(selectedOtorga.getId_Equipo().getId()));
            id_Abilidades.setText(String.valueOf(selectedOtorga.getId_Abilidades().getId()));
        }
    }

    private void LoadDebilidadesByIdData(){
        List<Otorga> otorgaList = otDAO.findAll();
        this.OtorgaList = FXCollections.observableList(otorgaList);
        table.setItems(OtorgaList);
    }

    @FXML
    public void handleInsertButtonAction() {
        String idEquipoValue = id_Equipo.getText();
        String idAbilidadesValue = id_Abilidades.getText();

        Equipo equipo = EquipoDAO.build().findById(Integer.parseInt(idEquipoValue));
        if (equipo == null) {
            showAlert("No existe el equipo con el ID proporcionado.");
            return;
        }

        Abilidades abilidades = AbilidadesDAO.build().findById(Integer.parseInt(idAbilidadesValue));
        if (abilidades == null) {
            showAlert("No existe el abilidades con el ID proporcionado.");
            return;
        }

        Otorga otorga = new Otorga();
        otorga.setId_Equipo(equipo);
        otorga.setId_Abilidades(abilidades);

        try {
            otDAO.save(otorga);
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
        Otorga selectedOtorga = table.getSelectionModel().getSelectedItem();
        if (selectedOtorga == null) {
            showAlert("Seleccione una para actualizar.");
            return;
        }


        String idEquipoValue = id_Equipo.getText();
        String idAbilidadesValue = id_Abilidades.getText();

        Equipo equipo = EquipoDAO.build().findById(Integer.parseInt(idEquipoValue));
        if (equipo == null) {
            showAlert("No existe el equipo con el ID proporcionado.");
            return;
        }

        Abilidades abilidades = AbilidadesDAO.build().findById(Integer.parseInt(idAbilidadesValue));
        if (abilidades == null) {
            showAlert("No existe el abilidades con el ID proporcionado.");
            return;
        }

        selectedOtorga.setId_Equipo(equipo);
        selectedOtorga.setId_Abilidades(abilidades);

        try {
            otDAO.update(selectedOtorga);
            LoadDebilidadesByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction(){
        Otorga selectedOtorga = table.getSelectionModel().getSelectedItem();
        if (selectedOtorga == null) {
            showAlert("Seleccione uno para eliminar.");
            return;
        }

        try {
            otDAO.delete(selectedOtorga);
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
