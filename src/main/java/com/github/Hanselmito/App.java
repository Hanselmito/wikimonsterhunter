// src/main/java/com/github/Hanselmito/App.java
package com.github.Hanselmito;

import com.github.Hanselmito.View.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static AppController currentController;

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        App.stage = stage;
        View view = AppController.loadFXML(Scenes.ROOT);
        scene = new Scene(view.scene);
        currentController = (AppController) view.controller;
        currentController.onOpen(null);
        stage.setScene(scene);

        // Establecer el icono de la aplicación
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icons/Site-Icono.jpg")));

        stage.show();
    }
}