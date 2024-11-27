package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.FisiologiaDAO;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Fisiologia;
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

public class FisiologiaController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField PuntosDebiles;
    @FXML
    private TextField Corte;
    @FXML
    private TextField Impacto;
    @FXML
    private TextField Disparo;
    @FXML
    private TextField ParteRompibles;
    @FXML
    private TextField IDMonstruos;
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
    private TableView<Fisiologia> table;
    @FXML
    private TableColumn<Fisiologia, Integer> ID;
    @FXML
    private ImageView imageView;

    private FisiologiaDAO fioDAO = new FisiologiaDAO();

    private ObservableList<Fisiologia> FiologiaList;

    private File imageFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadFisiologiaByIdData();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ViewSelectedID();
            }
        });
        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_monstruo().getId()).asObject());
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

    private void LoadFisiologiaByIdData(){
        List<Fisiologia> monstruosList = fioDAO.findByAll();
        this.FiologiaList = FXCollections.observableList(monstruosList);
        table.setItems(FiologiaList);
    }

    @FXML
    public void handleInsertButtonAction() throws IOException {
        String puntoDebiles = PuntosDebiles.getText();
        String corte = Corte.getText();
        String impacto = Impacto.getText();
        String disparo = Disparo.getText();
        String parteRompibles = ParteRompibles.getText();
        String idMonstruos = IDMonstruos.getText();

        Monstruos monstruos = MonstruosDAO.build().findById(Integer.parseInt(idMonstruos));
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
        Fisiologia fio = new Fisiologia();
        fio.setImagen(imageData);
        fio.setPuntos_debiles(puntoDebiles);
        fio.setCorte(Integer.parseInt(corte));
        fio.setImpacto(Integer.parseInt(impacto));
        fio.setDisparo(Integer.parseInt(disparo));
        fio.setPartes_rompibles(parteRompibles);
        fio.setId_monstruo(monstruos);

        try {
            fioDAO.save(fio);
            LoadFisiologiaByIdData();
            showAlert("Entidad Insertada compruébalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleUpdateButtonAction() throws IOException {
        // Retrieve the selected Monstruos object from the table
        Fisiologia selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos == null) {
            showAlert("Seleccione un monstruo para actualizar.");
            return;
        }

        // Retrieve data from form fields
        String puntoDebiles = PuntosDebiles.getText();
        String corte = Corte.getText();
        String impacto = Impacto.getText();
        String disparo = Disparo.getText();
        String parteRompibles = ParteRompibles.getText();
        String idMonstruos = IDMonstruos.getText();

        Monstruos monstruos = MonstruosDAO.build().findById(Integer.parseInt(idMonstruos));
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
        selectedMonstruos.setImagen(imageData);
        selectedMonstruos.setPuntos_debiles(puntoDebiles);
        selectedMonstruos.setCorte(Integer.parseInt(corte));
        selectedMonstruos.setImpacto(Integer.parseInt(impacto));
        selectedMonstruos.setDisparo(Integer.parseInt(disparo));
        selectedMonstruos.setPartes_rompibles(parteRompibles);
        selectedMonstruos.setId_monstruo(monstruos);

        try {
            fioDAO.update(selectedMonstruos);
            LoadFisiologiaByIdData();
            showAlert("Entidad actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al actualizar la entidad.");
        }
    }

    @FXML
    public void handleDeleteButtonAction() throws IOException {
        Fisiologia selectedMonstruos = table.getSelectionModel().getSelectedItem();
        if (selectedMonstruos == null) {
            showAlert("Seleccione un monstruo para eliminar.");
            return;
        }

        try {
            fioDAO.delete(selectedMonstruos);
            LoadFisiologiaByIdData();
            showAlert("Entidad eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error al eliminar la entidad.");
        }
    }

    private void ViewSelectedID(){
        Fisiologia selectedFisiologia = table.getSelectionModel().getSelectedItem();
        if (selectedFisiologia != null){
            imageView.setImage(new Image(new ByteArrayInputStream(selectedFisiologia.getImagen())));
            PuntosDebiles.setText(selectedFisiologia.getPuntos_debiles());
            Corte.setText(String.valueOf(selectedFisiologia.getCorte()));
            Impacto.setText(String.valueOf(selectedFisiologia.getImpacto()));
            Disparo.setText(String.valueOf(selectedFisiologia.getDisparo()));
            ParteRompibles.setText(selectedFisiologia.getPartes_rompibles());
            IDMonstruos.setText(String.valueOf(selectedFisiologia.getId_monstruo()));
        }
    }

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

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
