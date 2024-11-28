package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.AbilidadesDAO;
import com.github.Hanselmito.Entity.Abilidades;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AbilidadesController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField nivel;
    @FXML
    private TextField descripcion;
    @FXML
    private TextField Equip;
    @FXML
    private Button file;
    @FXML
    private Button insert;
    @FXML
    private Button update;
    @FXML
    private Button Back;
    @FXML
    private Button Delete;
    @FXML
    private TableView<Abilidades> table;
    @FXML
    private TableColumn<Abilidades, Integer> ID;
    @FXML
    private ImageView imageView;

    private AbilidadesDAO abDAO = new AbilidadesDAO();

    private ObservableList<Abilidades> AbilidadesList;

    private File imageFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadAbilidadesByIdData();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ViewSelectedID();
            }
        });
        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
    }

    private void LoadAbilidadesByIdData(){
        List<Abilidades> abilidadesList = abDAO.findAll();
        this.AbilidadesList = FXCollections.observableList(abilidadesList);
        table.setItems(AbilidadesList);
    }

    @FXML
    private void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) imageView.getScene().getWindow();
        imageFile = fileChooser.showOpenDialog(stage);
        if (imageFile != null) {
            try {
                System.out.println("Archivo de imagen seleccionado: " + imageFile.getAbsolutePath());
                InputStream is = new FileInputStream(imageFile);
                Image image = new Image(is);
                if (image.isError()) {
                    System.err.println("Error al cargar la imagen: " + image.getException().getMessage());
                } else {
                    imageView.setImage(image);
                    System.out.println("Imagen cargada correctamente en el ImageView.");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se seleccionó ningún archivo de imagen.");
        }
    }

    @FXML
    public void handleInsertButtonAction() throws IOException {
        String nombre = Nombre.getText();
        String nivelValue = nivel.getText();
        String descripcionValue = descripcion.getText();
        String EquipValue = Equip.getText();

        byte[] imageData = new byte[(int) imageFile.length()];
        FileInputStream fis = new FileInputStream(imageFile);
        fis.read(imageData);
        fis.close();

        // Create a new Monstruos object
        Abilidades abil = new Abilidades();
        abil.setImagen(imageData);
        abil.setNombre(nombre);
        abil.setNivel(Integer.parseInt(nivelValue));
        abil.setDescripcion(descripcionValue);
        abil.setEquip(EquipValue);

        try {
            abDAO.save(abil);
            LoadAbilidadesByIdData();
            showAlert("Entidad Insertada compruébalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleUpdateButtonAction() throws IOException {
        // Retrieve the selected Monstruos object from the table
        Abilidades selectedAbilidades = table.getSelectionModel().getSelectedItem();
        if (selectedAbilidades == null) {
            showAlert("Seleccione un abilidad para actualizar.");
            return;
        }

        // Retrieve data from form fields
        String nombre = Nombre.getText();
        String nivelValue = nivel.getText();
        String descripcionValue = descripcion.getText();
        String setValue = Equip.getText();

        byte[] imageData = new byte[(int) imageFile.length()];
        FileInputStream fis = new FileInputStream(imageFile);
        fis.read(imageData);
        fis.close();

        // Create a new Monstruos object
        selectedAbilidades.setImagen(imageData);
        selectedAbilidades.setNombre(nombre);
        selectedAbilidades.setNivel(Integer.parseInt(nivelValue));
        selectedAbilidades.setDescripcion(descripcionValue);
        selectedAbilidades.setEquip(setValue);

        try {
            abDAO.update(selectedAbilidades);
            LoadAbilidadesByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction() throws IOException {
        Abilidades selectedAbilidades = table.getSelectionModel().getSelectedItem();
        if (selectedAbilidades == null) {
            showAlert("Seleccione un abilidad para eliminar.");
            return;
        }

        try {
            abDAO.delete(selectedAbilidades);
            LoadAbilidadesByIdData();
            showAlert("Entidad eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al eliminar la entidad.");
        }
    }

    private void ViewSelectedID(){
        Abilidades selectedabilidades = table.getSelectionModel().getSelectedItem();
        if (selectedabilidades != null){
            imageView.setImage(new Image(new ByteArrayInputStream(selectedabilidades.getImagen())));
            Nombre.setText(selectedabilidades.getNombre());
            nivel.setText(String.valueOf(selectedabilidades.getNivel()));
            descripcion.setText(selectedabilidades.getDescripcion());
            Equip.setText(selectedabilidades.getEquip());
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
