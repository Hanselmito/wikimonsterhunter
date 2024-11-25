package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminController extends Controller implements Initializable {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.MonstruosController, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MSC.setText(MonstruosSaveController.class.getSimpleName());
        //image2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> navigateToScreen(Scenes.Screen2));
        //image3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> navigateToScreen(Scenes.Screen3));
        //image4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> navigateToScreen(Scenes.Screen4));
        //image5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> navigateToScreen(Scenes.Screen5));
        //image6.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> navigateToScreen(Scenes.Screen6));
        //image7.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> navigateToScreen(Scenes.Screen7));
    }



    @Override
    public void onOpen(Object input) throws Exception {
        // Implement any logic needed when the screen is opened
    }

    @Override
    public void onClose(Object output) {
        // Implement any logic needed when the screen is closed
    }
}