package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
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

public class ArmasMonstruo extends Controller implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Button BackButton;

    private MonstruosDAO monstruosDAO = new MonstruosDAO();
    private String monsterName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (monsterName != null) {
            initializeTabs();
        }
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
        initializeTabs();
    }

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

            vBox.getChildren().addAll(imageView, nameLabel);
            anchorPane.getChildren().add(vBox);
            tab.setContent(anchorPane);
            tabPane.getTabs().add(tab);
        }
    }

    @Override
    public void onOpen(Object input) throws Exception {
        if (input instanceof String) {
            setMonsterName((String) input);
        }
    }

    @FXML
    private void goBack() throws Exception {
        App.currentController.changeScene(Scenes.MonstrarMonstruos, monsterName);
    }

    @Override
    public void onClose(Object output) {
    }
}