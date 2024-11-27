package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.MaterialesDAO;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Materiales;
import com.github.Hanselmito.Entity.Monstruos;
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

public class MaterialesController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField DropRate;
    @FXML
    private TextField Mediante;
    @FXML
    private TextField Cantidad;
    @FXML
    private TextField IDMonstruo;
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
    private TableView<Materiales> table;
    @FXML
    private TableColumn<Materiales, Integer> ID;
    @FXML
    private TableColumn<Materiales, Integer> IDMonstruos;
    @FXML
    private ImageView imageView;

    private MaterialesDAO maDAO = new MaterialesDAO();

    private ObservableList<Materiales> MaterialesList;

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
        IDMonstruos.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_monstruo().getId()).asObject());
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
        List<Materiales> materialesList = maDAO.findAll();
        this.MaterialesList = FXCollections.observableList(materialesList);
        table.setItems(MaterialesList);
    }

    @FXML
    public void handleInsertButtonAction() throws IOException {
        String nombre = Nombre.getText();
        String dropRate = DropRate.getText();
        String mediante = Mediante.getText();
        String cantidad = Cantidad.getText();
        String idMonstruo = IDMonstruo.getText();

        Monstruos monstruos = MonstruosDAO.build().findById(Integer.parseInt(idMonstruo));
        if (monstruos == null) {
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
        Materiales materiales = new Materiales();
        materiales.setImagen(imageData);
        materiales.setNombre(nombre);
        materiales.setDropRate(dropRate);
        materiales.setMediante(mediante);
        materiales.setCantidad(Integer.parseInt(cantidad));
        materiales.setId_monstruo(monstruos);

        try {
            maDAO.save(materiales);
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
        Materiales selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos == null) {
            showAlert("Seleccione un monstruo para actualizar.");
            return;
        }

        // Retrieve data from form fields
        String nombre = Nombre.getText();
        String dropRate = DropRate.getText();
        String mediante = Mediante.getText();
        String cantidad = Cantidad.getText();
        String idMonstruo = IDMonstruo.getText();

        Monstruos monstruos = MonstruosDAO.build().findById(Integer.parseInt(idMonstruo));
        if (monstruos == null) {
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
        Materiales materiales = new Materiales();
        materiales.setImagen(imageData);
        materiales.setNombre(nombre);
        materiales.setDropRate(dropRate);
        materiales.setMediante(mediante);
        materiales.setCantidad(Integer.parseInt(cantidad));
        materiales.setId_monstruo(monstruos);

        try {
            maDAO.update(selectedMonstruos);
            LoadMaterialesByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction() throws IOException {
        Materiales selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos == null) {
            showAlert("Seleccione un monstruo para eliminar.");
            return;
        }

        try {
            maDAO.delete(selectedMonstruos);
            LoadMaterialesByIdData();
            showAlert("Entidad eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al eliminar la entidad.");
        }
    }

    private void ViewSelectedID(){
        Materiales selectedMateriales = table.getSelectionModel().getSelectedItem();
        if (selectedMateriales != null){
            imageView.setImage(new Image(new ByteArrayInputStream(selectedMateriales.getImagen())));
            Nombre.setText(selectedMateriales.getNombre());
            DropRate.setText(selectedMateriales.getDropRate());
            Mediante.setText(selectedMateriales.getMediante());
            Cantidad.setText(String.valueOf(selectedMateriales.getCantidad()));
            IDMonstruo.setText(String.valueOf(selectedMateriales.getId_monstruo().getId()));
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
