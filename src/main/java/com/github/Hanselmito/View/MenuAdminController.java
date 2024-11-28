package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminController extends Controller implements Initializable {
    @FXML
    private Button Back;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image7;
    @FXML
    private ImageView image8;
    @FXML
    private Label MSC;
    @FXML
    private Label FSC;
    @FXML
    private Label DSC;
    @FXML
    private Label ESC;
    @FXML
    private Label MASC;
    @FXML
    private Label ARSC;
    @FXML
    private Label EQSC;
    @FXML
    private Label ABSC;
    @FXML
    private Button Otorga;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.MonstruosController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MSC.setText(MonstruosController.class.getSimpleName());
        image2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.FisiologiaController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        FSC.setText(FisiologiaController.class.getSimpleName());
        image3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.DebilidadesController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        DSC.setText(DebilidadesController.class.getSimpleName());
        image4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.EstadoController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ESC.setText(EstadoController.class.getSimpleName());
        image5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.MaterialesController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MASC.setText(MaterialesController.class.getSimpleName());
        image6.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.ArmasController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ARSC.setText(ArmasController.class.getSimpleName());
        image7.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.EquipoController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        EQSC.setText(EquipoController.class.getSimpleName());
        image8.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.AbilidadesController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ABSC.setText(AbilidadesController.class.getSimpleName());
    }

    @Override
    public void onOpen(Object input) throws Exception {
        // Implement any logic needed when the screen is opened
    }

    @Override
    public void onClose(Object output) {
        // Implement any logic needed when the screen is closed
    }

    @FXML
    private void goOtorga() throws Exception {
        App.currentController.changeScene(Scenes.OtorgaController,null);
    }

    @FXML
    private void goBack() throws Exception {
        App.currentController.changeScene(Scenes.Menu,null);
    }
}