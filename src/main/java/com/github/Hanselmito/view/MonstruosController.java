package com.github.Hanselmito.view;

import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Enums.Clase;
import com.github.Hanselmito.Entity.Enums.Debilidad;
import com.github.Hanselmito.Entity.Enums.Elementos;
import com.github.Hanselmito.Entity.Enums.Estados;
import com.github.Hanselmito.Entity.Monstruos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; // Importación correcta
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
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
    private ChoiceBox clase;
    @FXML
    private ChoiceBox elementos;
    @FXML
    private ChoiceBox estados;
    @FXML
    private ChoiceBox debilidad;
    @FXML
    private Button file;
    @FXML
    private Button insert;
    @FXML
    private ImageView imageView; // Uso correcto de ImageView

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
                InputStream is = new FileInputStream(imageFile);
                Image image = new Image(is);
                imageView.setImage(image); // Mostrar la imagen en el ImageView
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleInsertButtonAction() throws IOException {
        // Retrieve data from form fields
        String nombreText = nombre.getText();
        String titulosText = titulos.getText();
        String habitatsText = habitats.getText();
        String tamanoText = tamano.getText();
        String parientesText = parientes.getText();
        Clase claseValue = (Clase) clase.getValue();
        Elementos elementosValue = (Elementos) elementos.getValue();
        Estados estadosValue = (Estados) estados.getValue();
        Debilidad debilidadValue = (Debilidad) debilidad.getValue();

        // Convert image to byte array
        byte[] imageBytes = null;
        if (imageFile != null) {
            try (InputStream is = new FileInputStream(imageFile)) {
                imageBytes = is.readAllBytes();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

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
        mo.setImagen(imageBytes);

        try {
            moDAO.save(mo);
            showAlert("Entidad Insertada compruébalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}