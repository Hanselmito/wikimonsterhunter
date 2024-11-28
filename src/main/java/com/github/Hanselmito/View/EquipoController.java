package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.EquipoDAO;
import com.github.Hanselmito.DAO.MaterialesDAO;
import com.github.Hanselmito.Entity.Armas;
import com.github.Hanselmito.Entity.Enums.Atributo;
import com.github.Hanselmito.Entity.Enums.Tipo;
import com.github.Hanselmito.Entity.Equipo;
import com.github.Hanselmito.Entity.Materiales;
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

public class EquipoController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField Nombre;
    @FXML
    private ChoiceBox tipo;
    @FXML
    private TextField habilidades;
    @FXML
    private TextArea Materiales;
    @FXML
    private TextField IDMaterial;
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
    private TableView<Equipo> table;
    @FXML
    private TableColumn<Equipo, Integer> ID;
    @FXML
    private TableColumn<Equipo, Integer> IDMateriales;
    @FXML
    private ImageView imageView;

    private EquipoDAO eqDAO = new EquipoDAO();

    private ObservableList<Equipo> EquipoList;

    private File imageFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadEquiposByIdData();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ViewSelectedID();
            }
        });
        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        IDMateriales.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_materiales().getId()).asObject());
        tipo.getItems().addAll(Tipo.values());
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

    private void LoadEquiposByIdData(){
        List<Equipo> equipoList = eqDAO.findAll();
        this.EquipoList = FXCollections.observableList(equipoList);
        table.setItems(EquipoList);
    }

    @FXML
    public void handleInsertButtonAction() throws IOException {
        String nombre = Nombre.getText();
        Tipo tipoValue = (Tipo) tipo.getValue();
        String habilidad = habilidades.getText();
        String materiales = Materiales.getText();
        String idMaterial = IDMaterial.getText();

        Materiales material = MaterialesDAO.build().findById(Integer.parseInt(idMaterial));
        if (material == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no existe el monstruo");
            alert.show();
            return;
        }

        // Convert image to byte array
        byte[] imageData = new byte[(int) imageFile.length()];
        FileInputStream fis = new FileInputStream(imageFile);
        fis.read(imageData);
        fis.close();

        // Create a new Monstruos object
        Equipo equpo = new Equipo();
        equpo.setNombre(nombre);
        equpo.setImagen(imageData);
        equpo.setTipo(tipoValue);
        equpo.setHabilidades(habilidad);
        equpo.setImagen(imageData);
        equpo.setMateriales(materiales);
        equpo.setId_materiales(material);

        try {
            eqDAO.save(equpo);
            LoadEquiposByIdData();
            showAlert("Entidad Insertada compruébalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleUpdateButtonAction() throws IOException {
        // Retrieve the selected Monstruos object from the table
        Equipo selectedMaterial = table.getSelectionModel().getSelectedItem();
        if (selectedMaterial == null) {
            showAlert("Seleccione un material para actualizar.");
            return;
        }

        // Retrieve data from form fields
        String nombre = Nombre.getText();
        Tipo tipoValue = (Tipo) tipo.getValue();
        String habilidad = habilidades.getText();
        String materiales = Materiales.getText();
        String idMaterial = IDMaterial.getText();

        Materiales material = MaterialesDAO.build().findById(Integer.parseInt(idMaterial));
        if (material == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no existe el monstruo");
            alert.show();
            return;
        }

        // Convert image to byte array
        byte[] imageData = new byte[(int) imageFile.length()];
        FileInputStream fis = new FileInputStream(imageFile);
        fis.read(imageData);
        fis.close();

        // Create a new Monstruos object
        selectedMaterial.setNombre(nombre);
        selectedMaterial.setTipo(tipoValue);
        selectedMaterial.setHabilidades(habilidad);
        selectedMaterial.setImagen(imageData);
        selectedMaterial.setMateriales(materiales);
        selectedMaterial.setId_materiales(material);

        try {
            eqDAO.update(selectedMaterial);
            LoadEquiposByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction() throws IOException {
        Equipo selectedMaterial = table.getSelectionModel().getSelectedItem();
        if (selectedMaterial == null) {
            showAlert("Seleccione un material para eliminar.");
            return;
        }

        try {
            eqDAO.delete(selectedMaterial);
            LoadEquiposByIdData();
            showAlert("Entidad eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al eliminar la entidad.");
        }
    }

    private void ViewSelectedID(){
        Equipo selectedMateriales = table.getSelectionModel().getSelectedItem();
        if (selectedMateriales != null){
            Nombre.setText(selectedMateriales.getNombre());
            tipo.setValue(selectedMateriales.getTipo());
            habilidades.setText(selectedMateriales.getHabilidades());
            imageView.setImage(new Image(new ByteArrayInputStream(selectedMateriales.getImagen())));
            Materiales.setText(selectedMateriales.getMateriales());
            IDMaterial.setText(String.valueOf(selectedMateriales.getId_materiales().getId()));
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
