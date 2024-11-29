package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Fisiologia;
import com.github.Hanselmito.Entity.Debilidades;
import com.github.Hanselmito.Entity.Estado;
import com.github.Hanselmito.Entity.Monstruos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MonstrarMonstruos extends Controller implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Button BackButton;

    private MonstruosDAO monstruosDAO = new MonstruosDAO();
    private String monsterName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize tabs only if monsterName is set
        if (monsterName != null) {
            initializeTabs();
        }
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
        initializeTabs();
    }

    private void initializeTabs() {
        for (Tab tab : tabPane.getTabs()) {
            String tabText = tab.getText();
            AnchorPane anchorPane = new AnchorPane();
            VBox vBox = new VBox(10);
            vBox.setAlignment(Pos.CENTER);

            if (tabText.equals("Datos")) {
                List<Monstruos> monstruosList = monstruosDAO.findByName(monsterName);
                for (Monstruos monstruo : monstruosList) {
                    Label nameLabel = new Label("Nombre: " + monstruo.getNombre());
                    Label titulosLabel = new Label("Titulos: " + monstruo.getTitulos());
                    Label claseLabel = new Label("Clase: " + monstruo.getClase());
                    Label elementosLabel = new Label("Elementos: " + monstruo.getElementos());
                    Label estadosLabel = new Label("Estados: " + monstruo.getEstados());
                    Label debilidadLabel = new Label("Debilidad: " + monstruo.getDebilidad());
                    Label habitatsLabel = new Label("Habitats: " + monstruo.getHabitats());
                    Label tamanoLabel = new Label("Tamano: " + monstruo.getTamano());
                    Label parientesLabel = new Label("Parientes: " + monstruo.getParientes());

                    ImageView imageView = new ImageView();
                    imageView.setImage(new Image(new ByteArrayInputStream(monstruo.getImagen())));
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);

                    // Create Labels for navigation
                    Label label1 = new Label("Materiales");
                    label1.setOnMouseClicked(event -> {
                        try {
                            AppController appController = (AppController) App.currentController;
                            appController.changeScene(Scenes.MaterialMonstruo, monstruo.getNombre());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                    Label label2 = new Label("Equipo");
                    label2.setOnMouseClicked(event -> {
                        try {
                            App.currentController.changeScene(Scenes.Menu, null);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                    Label label3 = new Label("Armas");
                    label3.setOnMouseClicked(event -> {
                        try {
                            App.currentController.changeScene(Scenes.Menu, null);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                    HBox navigationBox = new HBox(10, label1, label2, label3);
                    navigationBox.setAlignment(Pos.CENTER);

                    VBox vBoxDatos = new VBox(10, nameLabel, imageView, titulosLabel, claseLabel, elementosLabel, estadosLabel, debilidadLabel, habitatsLabel, tamanoLabel, parientesLabel, navigationBox);
                    vBoxDatos.getStyleClass().add("datos-vbox");
                    vBox.getChildren().add(vBoxDatos);
                }
            } else if (tabText.equals("Fisiologia")) {
                List<Fisiologia> fisiologiaList = monstruosDAO.findFisiologiaByMonstruoName(monsterName);
                for (Fisiologia fisiologia : fisiologiaList) {
                    ImageView imageView = new ImageView();
                    imageView.setImage(new Image(new ByteArrayInputStream(fisiologia.getImagen())));
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);

                    Label puntosDebilesLabel = new Label("Puntos Debiles: " + fisiologia.getPuntos_debiles());
                    Label corteLabel = new Label("Corte: " + fisiologia.getCorte());
                    Label impactoLabel = new Label("Impacto: " + fisiologia.getImpacto());
                    Label disparoLabel = new Label("Disparo: " + fisiologia.getDisparo());
                    Label partesRompiblesLabel = new Label("Partes Rompibles: " + fisiologia.getPartes_rompibles());

                    VBox vBoxLeft = new VBox(10, imageView, puntosDebilesLabel);
                    VBox vBoxRight = new VBox(10, corteLabel, impactoLabel, disparoLabel, partesRompiblesLabel);
                    HBox hBox = new HBox(20, vBoxLeft, vBoxRight);

                    VBox vBoxFisiologia = new VBox(10, hBox);
                    vBoxFisiologia.getStyleClass().add("fisiologia-vbox");
                    vBox.getChildren().add(vBoxFisiologia);
                }
            } else if (tabText.equals("Debilidades y Estados")) {
                Label debilidadesTitleLabel = new Label("Debilidades");
                vBox.getChildren().add(debilidadesTitleLabel);

                Label elementosTitleLabel = new Label("Elementos");
                ImageView imageView = new ImageView();
                Label estadosTitleLabel = new Label("Estados");

                HBox titleHBox = new HBox(20, elementosTitleLabel, imageView, estadosTitleLabel);
                vBox.getChildren().add(titleHBox);

                VBox elementosVBox = new VBox(10);
                elementosVBox.getStyleClass().add("elementos-vbox");
                VBox elementosValuesVBox = new VBox(10);

                VBox estadosVBox = new VBox(10);
                estadosVBox.getStyleClass().add("estados-vbox");
                VBox estadosValuesVBox = new VBox(10);

                List<Object[]> debilidadesYEstadosList = monstruosDAO.findDebilidadesYEstadosByMonstruoName(monsterName);
                for (Object[] debilidadesYEstados : debilidadesYEstadosList) {
                    Debilidades debilidad = (Debilidades) debilidadesYEstados[0];
                    Estado estado = (Estado) debilidadesYEstados[1];

                    // Add elements and their values
                    addElementWithValue(elementosVBox, elementosValuesVBox, "Fuego", debilidad.getEfectividadFuego());
                    addElementWithValue(elementosVBox, elementosValuesVBox, "Agua", debilidad.getEfectividadAgua());
                    addElementWithValue(elementosVBox, elementosValuesVBox, "Rayo", debilidad.getEfectividadRayo());
                    addElementWithValue(elementosVBox, elementosValuesVBox, "Hielo", debilidad.getEfectividadHielo());
                    addElementWithValue(elementosVBox, elementosValuesVBox, "Draco", debilidad.getEfectividadDraco());

                    // Add states and their values
                    addStateWithValue(estadosVBox, estadosValuesVBox, "Veneno", estado.getEfectividadVeneno());
                    addStateWithValue(estadosVBox, estadosValuesVBox, "Sueño", estado.getEfectividadSueno());
                    addStateWithValue(estadosVBox, estadosValuesVBox, "Parálisis", estado.getEfectividadParalisis());
                    addStateWithValue(estadosVBox, estadosValuesVBox, "Nitro", estado.getEfectividadNitro());
                    addStateWithValue(estadosVBox, estadosValuesVBox, "Aturdimiento", estado.getEfectividadAturdimiento());
                }

                HBox hBox = new HBox(20, elementosVBox, elementosValuesVBox, estadosVBox, estadosValuesVBox);
                vBox.getChildren().add(hBox);
            }

            anchorPane.getChildren().add(vBox);
            tab.setContent(anchorPane);
        }
    }

    private void addElementWithValue(VBox elementsVBox, VBox valuesVBox, String element, int value) {
        Label elementLabel = new Label(element);
        elementsVBox.getChildren().add(elementLabel);

        Label valueLabel = new Label(String.valueOf(value));
        valuesVBox.getChildren().add(valueLabel);
    }

    private void addStateWithValue(VBox statesVBox, VBox valuesVBox, String state, int value) {
        Label stateLabel = new Label(state);
        statesVBox.getChildren().add(stateLabel);

        Label valueLabel = new Label(String.valueOf(value));
        valuesVBox.getChildren().add(valueLabel);
    }

    @Override
    public void onOpen(Object input) throws Exception {
        if (input instanceof String) {
            setMonsterName((String) input);
        }
    }

    @FXML
    private void goBack() throws Exception {
        App.currentController.changeScene(Scenes.Bestiario,null);
    }

    @Override
    public void onClose(Object output) {
    }
}