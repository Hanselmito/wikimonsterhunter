package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.ArmasDAO;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Armas;
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

// Clase ArmasMonstruo que extiende de Controller e implementa Initializable
public class ArmasMonstruo extends Controller implements Initializable {

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

    // Inicializar las pestañas con la información de las armas del monstruo
    // ArmasMonstruo.java
    private void initializeTabs() {
        List<Armas> armasList = monstruosDAO.findArmasByMonstruoName(monsterName);
        for (Armas arma : armasList) {
            Tab tab = new Tab(arma.getNombre());
            AnchorPane anchorPane = new AnchorPane();
            VBox vBox = new VBox(10);
            vBox.setAlignment(Pos.CENTER);
            vBox.getStyleClass().add("vbox");

            ImageView imageView = new ImageView();
            imageView.setImage(new Image(new ByteArrayInputStream(arma.getImagen())));
            imageView.getStyleClass().add("image-view");

            Label nameLabel = new Label("Nombre: " + arma.getNombre());
            nameLabel.getStyleClass().add("label");

            List<Armas> armasDetails = new ArmasDAO().findAllWithoutNameAndImage(arma.getNombre());
            if (!armasDetails.isEmpty()) {
                Armas details = armasDetails.get(0);
                Label ataqueLabel = new Label("Ataque: " + details.getAtaque());
                Label atributoLabel = new Label("Atributo: " + details.getAtributo());
                Label afiladoLabel = new Label("Afilado: " + details.getAfilado());
                Label afinidadLabel = new Label("Afinidad: " + details.getAfinidad());
                Label defensaLabel = new Label("Defensa: " + details.getDefensa());
                Label ranurasLabel = new Label("Ranuras: " + details.getRanuras());
                Label materialesLabel = new Label("Materiales: " + details.getMateriales());

                ataqueLabel.getStyleClass().add("label");
                atributoLabel.getStyleClass().add("label");
                afiladoLabel.getStyleClass().add("label");
                afinidadLabel.getStyleClass().add("label");
                defensaLabel.getStyleClass().add("label");
                ranurasLabel.getStyleClass().add("label");
                materialesLabel.getStyleClass().add("label");

                vBox.getChildren().addAll(imageView, nameLabel, ataqueLabel, atributoLabel, afiladoLabel, afinidadLabel, defensaLabel, ranurasLabel, materialesLabel);
            } else {
                vBox.getChildren().addAll(imageView, nameLabel);
            }

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