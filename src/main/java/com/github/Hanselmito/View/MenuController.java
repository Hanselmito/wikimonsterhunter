package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable {
    @FXML
    private Label TituloDelMenu;
    @FXML
    private ImageView ImageMenu;
    @FXML
    private ImageView Edit;
    @FXML
    private Pane MenuPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private SplitMenuButton MonsterMenu;
    @FXML
    private MenuItem bestiario;
    @FXML
    private Label Texto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TituloDelMenu.setText("Wiki Monster Hunter");
        LocalDate currentDate = LocalDate.now();
        String dayOfWeek = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String day = String.valueOf(currentDate.getDayOfMonth());
        String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String year = String.valueOf(currentDate.getYear());

        String labelText = "Este wiki está editado por fans y para fans,\n ¡y las contribuciones son siempre bienvenidas!\n Aquí puedes encontrar toda la información acerca de\n la saga favorita Monster Hunter, y si no existe.\n ¡siempre puedes crearla!\n\nEstamos a " + dayOfWeek + ", " + day + " de " + month + " de " + year + " .\n\n¡Disfruta de tu estancia!";
        Texto.setText(labelText);

        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.isControlDown()) {
                double zoomFactor = 1.05;
                double deltaY = event.getDeltaY();

                if (deltaY < 0) {
                    zoomFactor = 2.0 - zoomFactor;
                }

                scrollPane.setScaleX(scrollPane.getScaleX() * zoomFactor);
                scrollPane.setScaleY(scrollPane.getScaleY() * zoomFactor);
                event.consume();
            }
        });

        TituloDelMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.Menu, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ImageMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.Menu, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Edit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.MenuAdmin, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MonsterMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                App.currentController.changeScene(Scenes.Bestiario, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        bestiario.setOnAction(event -> {
            try {
                App.currentController.changeScene(Scenes.Bestiario, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }
}