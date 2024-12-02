package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Enums.Clase;
import com.github.Hanselmito.Entity.Enums.Debilidad;
import com.github.Hanselmito.Entity.Enums.Elementos;
import com.github.Hanselmito.Entity.Enums.Estados;
import com.github.Hanselmito.Entity.Monstruos;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; // Importación correcta
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MonstruosController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField nombre;
    @FXML
    private TextField titulos;
    @FXML
    private TextField habitats;
    @FXML
    private TextField tamano;
    @FXML
    private TextField parientes;
    @FXML
    private ChoiceBox<Clase> clase;
    @FXML
    private ChoiceBox<Elementos> elementos;
    @FXML
    private ChoiceBox<Estados> estados;
    @FXML
    private ChoiceBox<Debilidad> debilidad;
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
    private TableView<Monstruos> table;
    @FXML
    private TableColumn<Monstruos, Integer> ID;
    @FXML
    private ImageView imageView;

    private ObservableList<Monstruos> MonstruosList;

    private File imageFile;

    private MonstruosDAO moDAO = new MonstruosDAO();

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadMonstruosByIdData();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ViewSelectedID();
            }
        });
        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        clase.getItems().addAll(Clase.values());
        elementos.getItems().addAll(Elementos.values());
        estados.getItems().addAll(Estados.values());
        debilidad.getItems().addAll(Debilidad.values());
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
                    imageView.setImage(image); // Mostrar la imagen en el ImageView
                    System.out.println("Imagen cargada correctamente en el ImageView.");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se seleccionó ningún archivo de imagen.");
        }
    }

    private void LoadMonstruosByIdData(){
        List<Monstruos> monstruosList = moDAO.findAll();
        this.MonstruosList = FXCollections.observableList(monstruosList);
        table.setItems(MonstruosList);
    }

    @FXML
    public void handleInsertButtonAction() throws IOException {
        // Retrieve data from form fields
        String nombreText = nombre.getText();
        String titulosText = titulos.getText();
        Clase claseValue = clase.getValue();
        Elementos elementosValue = elementos.getValue();
        Estados estadosValue = estados.getValue();
        Debilidad debilidadValue = debilidad.getValue();
        String habitatsText = habitats.getText();
        String tamanoText = tamano.getText();
        String parientesText = parientes.getText();

        // Check if the name already exists
        List<String> existingNames = moDAO.findAllNames();
        if (existingNames.contains(nombreText)) {
            showAlert("El nombre del monstruo ya existe. Por favor, elija otro nombre.");
            return;
        }

        // Convert image to byte array
        byte[] imageData = new byte[(int) imageFile.length()];
        FileInputStream fis = new FileInputStream(imageFile);
        fis.read(imageData);
        fis.close();

        // Create a new Monstruos object
        Monstruos mo = new Monstruos();
        mo.setNombre(nombreText);
        mo.setTitulos(titulosText);
        mo.setClase(claseValue);
        mo.setElementos(elementosValue);
        mo.setEstados(estadosValue);
        mo.setDebilidad(debilidadValue);
        mo.setHabitats(habitatsText);
        mo.setTamano(tamanoText);
        mo.setParientes(parientesText);
        mo.setImagen(imageData);

        try {
            moDAO.save(mo);
            LoadMonstruosByIdData();
            showAlert("Entidad Insertada compruébalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleUpdateButtonAction() throws IOException {
        // Retrieve the selected Monstruos object from the table
        Monstruos selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos == null) {
            showAlert("Seleccione un monstruo para actualizar.");
            return;
        }

        // Retrieve data from form fields
        String nombreText = nombre.getText();
        String titulosText = titulos.getText();
        String habitatsText = habitats.getText();
        String tamanoText = tamano.getText();
        String parientesText = parientes.getText();
        Clase claseValue = clase.getValue();
        Elementos elementosValue = elementos.getValue();
        Estados estadosValue = estados.getValue();
        Debilidad debilidadValue = debilidad.getValue();

        // Convert image to byte array
        byte[] imageData = new byte[(int) imageFile.length()];
        FileInputStream fis = new FileInputStream(imageFile);
        fis.read(imageData);
        fis.close();

        // Update the selected Monstruos object
        selectedMonstruos.setNombre(nombreText);
        selectedMonstruos.setTitulos(titulosText);
        selectedMonstruos.setClase(claseValue);
        selectedMonstruos.setElementos(elementosValue);
        selectedMonstruos.setEstados(estadosValue);
        selectedMonstruos.setDebilidad(debilidadValue);
        selectedMonstruos.setHabitats(habitatsText);
        selectedMonstruos.setTamano(tamanoText);
        selectedMonstruos.setParientes(parientesText);
        selectedMonstruos.setImagen(imageData);

        try {
            moDAO.update(selectedMonstruos);
            LoadMonstruosByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction() throws IOException {
        Monstruos selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos == null) {
            showAlert("Seleccione un monstruo para eliminar.");
            return;
        }

        try {
            moDAO.delete(selectedMonstruos);
            LoadMonstruosByIdData();
            showAlert("Entidad eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al eliminar la entidad.");
        }
    }

    private void ViewSelectedID(){
        Monstruos selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos != null){
            nombre.setText(selectedMonstruos.getNombre());
            titulos.setText(selectedMonstruos.getTitulos());
            habitats.setText(selectedMonstruos.getHabitats());
            tamano.setText(selectedMonstruos.getTamano());
            parientes.setText(selectedMonstruos.getParientes());
            clase.setValue(selectedMonstruos.getClase());
            elementos.setValue(selectedMonstruos.getElementos());
            estados.setValue(selectedMonstruos.getEstados());
            debilidad.setValue(selectedMonstruos.getDebilidad());
            imageView.setImage(new Image(new ByteArrayInputStream(selectedMonstruos.getImagen())));
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
}