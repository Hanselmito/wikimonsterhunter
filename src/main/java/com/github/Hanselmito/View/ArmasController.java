package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.ArmasDAO;
import com.github.Hanselmito.DAO.MaterialesDAO;
import com.github.Hanselmito.Entity.Armas;
import com.github.Hanselmito.Entity.Enums.Atributo;
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

public class ArmasController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Ataque;
    @FXML
    private ChoiceBox atributo;
    @FXML
    private TextField Afilado;
    @FXML
    private TextField Afinidad;
    @FXML
    private TextField Defensa;
    @FXML
    private TextField Ranuras;
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
    private TableView<Armas> table;
    @FXML
    private TableColumn<Armas, Integer> ID;
    @FXML
    private TableColumn<Armas, Integer> IDMateriales;
    @FXML
    private ImageView imageView;

    private ArmasDAO arDAO = new ArmasDAO();

    private ObservableList<Armas> ArmasList;

    private File imageFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadMaterialesByIdData();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ViewSelectedID();
            }
        });
        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        IDMateriales.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_materiales().getId()).asObject());
        atributo.getItems().addAll(Atributo.values());
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

    private void LoadMaterialesByIdData(){
        List<Armas> armasList = arDAO.findALL();
        this.ArmasList = FXCollections.observableList(armasList);
        table.setItems(ArmasList);
    }

    @FXML
    public void handleInsertButtonAction() throws IOException {
        String nombre = Nombre.getText();
        String ataque = Ataque.getText();
        Atributo atributoValue = (Atributo) atributo.getValue();
        String afilado = Afilado.getText();
        String afinidad = Afinidad.getText();
        String defensa = Defensa.getText();
        String ranuras = Ranuras.getText();
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
        Armas armas = new Armas();
        armas.setImagen(imageData);
        armas.setNombre(nombre);
        armas.setAtaque(Integer.parseInt(ataque));
        armas.setAtributo(atributoValue);
        armas.setAfilado(afilado);
        armas.setAfinidad(afinidad);
        armas.setDefensa(Integer.parseInt(defensa));
        armas.setRanuras(Integer.parseInt(ranuras));
        armas.setMateriales(materiales);
        armas.setId_materiales(material);

        try {
            arDAO.save(armas);
            LoadMaterialesByIdData();
            showAlert("Entidad Insertada compruébalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleUpdateButtonAction() throws IOException {
        // Retrieve the selected Monstruos object from the table
        Armas selectedMaterial = table.getSelectionModel().getSelectedItem();
        if (selectedMaterial == null) {
            showAlert("Seleccione un material para actualizar.");
            return;
        }

        // Retrieve data from form fields
        String nombre = Nombre.getText();
        String ataque = Ataque.getText();
        Atributo atributoValue = (Atributo) atributo.getValue();
        String afilado = Afilado.getText();
        String afinidad = Afinidad.getText();
        String defensa = Defensa.getText();
        String ranuras = Ranuras.getText();
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
        selectedMaterial.setImagen(imageData);
        selectedMaterial.setNombre(nombre);
        selectedMaterial.setAtaque(Integer.parseInt(ataque));
        selectedMaterial.setAtributo(atributoValue);
        selectedMaterial.setAfilado(afilado);
        selectedMaterial.setAfinidad(afinidad);
        selectedMaterial.setDefensa(Integer.parseInt(defensa));
        selectedMaterial.setRanuras(Integer.parseInt(ranuras));
        selectedMaterial.setMateriales(materiales);
        selectedMaterial.setId_materiales(material);

        try {
            arDAO.update(selectedMaterial);
            LoadMaterialesByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction() throws IOException {
        Armas selectedMaterial = table.getSelectionModel().getSelectedItem();
        if (selectedMaterial == null) {
            showAlert("Seleccione un material para eliminar.");
            return;
        }

        try {
            arDAO.delete(selectedMaterial);
            LoadMaterialesByIdData();
            showAlert("Entidad eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al eliminar la entidad.");
        }
    }

    private void ViewSelectedID(){
        Armas selectedMateriales = table.getSelectionModel().getSelectedItem();
        if (selectedMateriales != null){
            imageView.setImage(new Image(new ByteArrayInputStream(selectedMateriales.getImagen())));
            Nombre.setText(selectedMateriales.getNombre());
            Ataque.setText(String.valueOf(selectedMateriales.getAtaque()));
            atributo.setValue(selectedMateriales.getAtributo());
            Afilado.setText(selectedMateriales.getAfilado());
            Afinidad.setText(selectedMateriales.getAfinidad());
            Defensa.setText(String.valueOf(selectedMateriales.getDefensa()));
            Ranuras.setText(String.valueOf(selectedMateriales.getRanuras()));
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
