// Adjust the image size in EquipoMonstruo.java
package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Abilidades;
import com.github.Hanselmito.Entity.Equipo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// Clase EquipoMonstruo que extiende de Controller e implementa Initializable
public class EquipoMonstruo extends Controller implements Initializable {

    @FXML
    private TabPane tabPane; // Pestañas de la interfaz
    @FXML
    private Button BackButton; // Botón para regresar

    private MonstruosDAO monstruosDAO = new MonstruosDAO(); // DAO para acceder a los datos de los monstruos
    private String monsterName; // Nombre del monstruo

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar las pestañas solo si el nombre del monstruo está establecido
        if (monsterName != null) {
            initializeTabs();
        }
    }

    // Establecer el nombre del monstruo y inicializar las pestañas
    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
        initializeTabs();
    }

    // Inicializar las pestañas con la información del equipo del monstruo
    private void initializeTabs() {
        // Obtener la lista de equipos asociados al monstruo
        List<Equipo> equipoList = monstruosDAO.findEquipoByMonstruoName(monsterName);
        for (Equipo equipo : equipoList) {
            // Crear una nueva pestaña para cada equipo
            Tab tab = new Tab(equipo.getNombre());
            AnchorPane anchorPane = new AnchorPane();
            VBox vBox = new VBox(10);
            vBox.setAlignment(Pos.CENTER);
            vBox.getStyleClass().add("vbox");

            // Crear y configurar la vista de imagen para el equipo
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(new ByteArrayInputStream(equipo.getImagen())));
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            imageView.getStyleClass().add("image-view");

            // Crear y configurar la etiqueta del nombre del equipo
            Label nameLabel = new Label("Nombre: " + equipo.getNombre());
            nameLabel.getStyleClass().add("label");

            // Agregar la imagen y el nombre del equipo al VBox
            vBox.getChildren().addAll(imageView, nameLabel);

            // Obtener y mostrar todas las habilidades asociadas con el equipo
            List<Abilidades> habilidadesList = equipo.getList_Abilidades();
            for (Abilidades habilidad : habilidadesList) {
                Label habilidadLabel = new Label("Habilidad: " + habilidad.getNombre());
                habilidadLabel.getStyleClass().add("label");
                vBox.getChildren().add(habilidadLabel);
            }

            // Agregar el VBox al AnchorPane y establecerlo como contenido de la pestaña
            anchorPane.getChildren().add(vBox);
            tab.setContent(anchorPane);
            tabPane.getTabs().add(tab);
        }
    }

    @Override
    public void onOpen(Object input) throws Exception {
        // Establecer el nombre del monstruo si el input es una cadena
        if (input instanceof String) {
            setMonsterName((String) input);
        }
    }

    @FXML
    private void goBack() throws Exception {
        // Cambiar a la escena de mostrar monstruos
        App.currentController.changeScene(Scenes.MostrarMonstruos, monsterName);
    }

    @Override
    public void onClose(Object output) {
        // Método vacío para manejar el cierre de la vista
    }
}