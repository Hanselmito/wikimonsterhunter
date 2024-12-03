// MostrarMonstruos.java
package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Fisiologia;
import com.github.Hanselmito.Entity.Debilidades;
import com.github.Hanselmito.Entity.Estado;
import com.github.Hanselmito.Entity.Monstruos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MostrarMonstruos extends Controller implements Initializable {

    @FXML
    private TabPane tabPane; // Pestañas de la interfaz
    @FXML
    private Button BackButton; // Botón para regresar

    private MonstruosDAO monstruosDAO = new MonstruosDAO(); // DAO para acceder a los datos de los monstruos
    private String monsterName; // Nombre del monstruo a mostrar

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

    // Inicializar las pestañas con la información del monstruo
    private void initializeTabs() {
        for (Tab tab : tabPane.getTabs()) {
            String tabText = tab.getText();
            AnchorPane anchorPane = new AnchorPane();
            VBox vBox = new VBox(10);
            vBox.setAlignment(Pos.CENTER);

            if (tabText.equals("Datos")) {
                // Pestaña de datos generales del monstruo
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

                    // Crear etiquetas para la navegación
                    Label label1 = new Label("Materiales");
                    label1.setOnMouseClicked(event -> {
                        try {
                            AppController appController = App.currentController;
                            appController.changeScene(Scenes.MaterialMonstruo, monstruo.getNombre());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                    Label label2 = new Label("Equipo");
                    label2.setOnMouseClicked(event -> {
                        try {
                            AppController appController = App.currentController;
                            appController.changeScene(Scenes.EquipoMonstruo, monstruo.getNombre());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                    Label label3 = new Label("Armas");
                    label3.setOnMouseClicked(event -> {
                        try {
                            AppController appController = App.currentController;
                            appController.changeScene(Scenes.ArmasMonstruo, monstruo.getNombre());
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
            }else if (tabText.equals("Fisiologia")) {
                List<Fisiologia> fisiologiaList = monstruosDAO.findFisiologiaByMonstruoName(monsterName);
                for (Fisiologia fisiologia : fisiologiaList) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/github/Hanselmito/View/tablaFisio.fxml"));
                        AnchorPane fisiologiaPane = loader.load();

                        Label tituloLabel = (Label) fisiologiaPane.lookup("#titulo");
                        tituloLabel.setText("Fisiologia");

                        ImageView imageView = (ImageView) fisiologiaPane.lookup("#imageView");
                        imageView.setImage(new Image(new ByteArrayInputStream(fisiologia.getImagen())));

                        Label puntosLabel = (Label) fisiologiaPane.lookup("#puntos");
                        puntosLabel.setText("Puntos Debiles");

                        Label datosLabel = (Label) fisiologiaPane.lookup("#datos");
                        datosLabel.setText(fisiologia.getPuntos_debiles());
                        datosLabel.setWrapText(true);

                        Label parteRompibleLabel = (Label) fisiologiaPane.lookup("#parteRompible");
                        parteRompibleLabel.setText("Partes Rompibles");

                        Label corLabel = (Label) fisiologiaPane.lookup("#cor");
                        corLabel.setText("Corte");

                        Label impactLabel = (Label) fisiologiaPane.lookup("#impact");
                        impactLabel.setText("Impacto");

                        Label disparoLabel = (Label) fisiologiaPane.lookup("#disparo");
                        disparoLabel.setText("Disparo");

                        Label infoCorteLabel = (Label) fisiologiaPane.lookup("#infoCorte");
                        infoCorteLabel.setText("★".repeat(fisiologia.getCorte()));

                        Label infoImpactoLabel = (Label) fisiologiaPane.lookup("#infoImpacto");
                        infoImpactoLabel.setText("★".repeat(fisiologia.getImpacto()));

                        Label infoDisparoLabel = (Label) fisiologiaPane.lookup("#infoDisparo");
                        infoDisparoLabel.setText("★".repeat(fisiologia.getDisparo()));

                        Label textoParteLabel = (Label) fisiologiaPane.lookup("#textoParte");
                        textoParteLabel.setText(fisiologia.getPartes_rompibles());

                        vBox.getChildren().add(fisiologiaPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else if (tabText.equals("Debilidades y Estados")) {
                List<Object[]> debilidadesYEstadosList = monstruosDAO.findDebilidadesYEstadosByMonstruoName(monsterName);
                for (Object[] debilidadesYEstados : debilidadesYEstadosList) {
                    Debilidades debilidad = (Debilidades) debilidadesYEstados[0];
                    Estado estado = (Estado) debilidadesYEstados[1];

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/github/Hanselmito/View/tablaDyE.fxml"));
                        AnchorPane debilidadesEstadosPane = loader.load();

                        Label tituloLabel = (Label) debilidadesEstadosPane.lookup("#Titulo");
                        tituloLabel.setText("Debilidades y Estados");

                        Label fuegoLabel = (Label) debilidadesEstadosPane.lookup("#Fuego");
                        fuegoLabel.setText("Fuego");

                        Label infoFuegoLabel = (Label) debilidadesEstadosPane.lookup("#infoF");
                        infoFuegoLabel.setText("★".repeat(debilidad.getEfectividadFuego()));

                        Label aguaLabel = (Label) debilidadesEstadosPane.lookup("#Agua");
                        aguaLabel.setText("Agua");

                        Label infoAguaLabel = (Label) debilidadesEstadosPane.lookup("#infoA");
                        infoAguaLabel.setText("★".repeat(debilidad.getEfectividadAgua()));

                        Label rayoLabel = (Label) debilidadesEstadosPane.lookup("#Rayo");
                        rayoLabel.setText("Rayo");

                        Label infoRayoLabel = (Label) debilidadesEstadosPane.lookup("#infoR");
                        infoRayoLabel.setText("★".repeat(debilidad.getEfectividadRayo()));

                        Label hieloLabel = (Label) debilidadesEstadosPane.lookup("#Hielo");
                        hieloLabel.setText("Hielo");

                        Label infoHieloLabel = (Label) debilidadesEstadosPane.lookup("#infoH");
                        infoHieloLabel.setText("★".repeat(debilidad.getEfectividadHielo()));

                        Label dracoLabel = (Label) debilidadesEstadosPane.lookup("#Draco");
                        dracoLabel.setText("Draco");

                        Label infoDracoLabel = (Label) debilidadesEstadosPane.lookup("#infoD");
                        infoDracoLabel.setText("★".repeat(debilidad.getEfectividadDraco()));

                        Label venenoLabel = (Label) debilidadesEstadosPane.lookup("#Veneno");
                        venenoLabel.setText("Veneno");

                        Label infoVenenoLabel = (Label) debilidadesEstadosPane.lookup("#infoV");
                        infoVenenoLabel.setText("★".repeat(estado.getEfectividadVeneno()));

                        Label suenoLabel = (Label) debilidadesEstadosPane.lookup("#Sueño");
                        suenoLabel.setText("Sueño");

                        Label infoSuenoLabel = (Label) debilidadesEstadosPane.lookup("#infoS");
                        infoSuenoLabel.setText("★".repeat(estado.getEfectividadSueno()));

                        Label paralisisLabel = (Label) debilidadesEstadosPane.lookup("#Paralisis");
                        paralisisLabel.setText("Parálisis");

                        Label infoParalisisLabel = (Label) debilidadesEstadosPane.lookup("#infoP");
                        infoParalisisLabel.setText("★".repeat(estado.getEfectividadParalisis()));

                        Label nitroLabel = (Label) debilidadesEstadosPane.lookup("#Nitro");
                        nitroLabel.setText("Nitro");

                        Label infoNitroLabel = (Label) debilidadesEstadosPane.lookup("#infoN");
                        infoNitroLabel.setText("★".repeat(estado.getEfectividadNitro()));

                        Label aturdimientoLabel = (Label) debilidadesEstadosPane.lookup("#Aturdimiento");
                        aturdimientoLabel.setText("Aturdimiento");

                        Label infoAturdimientoLabel = (Label) debilidadesEstadosPane.lookup("#infoAT");
                        infoAturdimientoLabel.setText("★".repeat(estado.getEfectividadAturdimiento()));

                        vBox.getChildren().add(debilidadesEstadosPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            anchorPane.getChildren().add(vBox);
            tab.setContent(anchorPane);
        }
    }

    // Agregar un elemento y su valor a los VBox correspondientes
    private void addElementWithValue(VBox elementsVBox, VBox valuesVBox, String element, int value) {
        Label elementLabel = new Label(element);
        elementsVBox.getChildren().add(elementLabel);

        Label valueLabel = new Label(String.valueOf(value));
        valuesVBox.getChildren().add(valueLabel);
    }

    // Agregar un estado y su valor a los VBox correspondientes
    private void addStateWithValue(VBox statesVBox, VBox valuesVBox, String state, int value) {
        Label stateLabel = new Label(state);
        statesVBox.getChildren().add(stateLabel);

        Label valueLabel = new Label(String.valueOf(value));
        valuesVBox.getChildren().add(valueLabel);
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
        // Cambiar a la escena del Bestiario
        App.currentController.changeScene(Scenes.Bestiario, null);
    }

    @Override
    public void onClose(Object output) {
        // Método vacío para manejar el cierre de la vista
    }
}