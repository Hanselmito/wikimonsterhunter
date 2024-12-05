package com.github.Hanselmito.View;

import com.github.Hanselmito.App;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class AppController extends Controller implements Initializable {
    @FXML
    private VBox vbox;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Canvas particleCanvas;
    private Controller centerController;

    private List<Particle> particles = new ArrayList<>();
    private Random random = new Random();

    public static View loadFXML(Scenes scenes) throws Exception {
        String url = scenes.getURL();
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene = p;
        view.controller = c;
        return view;
    }

    public void changeScene(Scenes scene, Object data) throws Exception {
        View view = loadFXML(scene);
        vbox.getChildren().setAll(view.scene);
        applyFadeTransition(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data);
    }

    public void openModal(Scenes scene, String title, Controller parent, Object data) throws Exception {
        View view = loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        stage.setScene(_scene);
        view.controller.onOpen(parent);
        stage.showAndWait();
    }

    private static void applyFadeTransition(Parent root) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }


    /*
    * methods para simular una carga de 100% y cambiar de escena
    */
    private void startLoading() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i, 100);
                    Thread.sleep(50);
                }
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            try {
                changeScene(Scenes.Menu, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), vbox);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }

    /*
    * methods que se ejecuta al cargar la vista
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startLoading();
        startParticleAnimation();
    }

    /*
    * methods para animar particulas
    */
    private void startParticleAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateParticles();
                drawParticles();
            }
        };
        timer.start();
    }

    /*
    * methods para actualizar las particulas.
    */
    private void updateParticles() {
        if (particles.size() < 100) {
            particles.add(new Particle(random.nextDouble() * particleCanvas.getWidth(), random.nextDouble() * particleCanvas.getHeight()));
        }

        for (Particle particle : particles) {
            particle.update();
        }
    }

    /*
     * methods para dibujar las particulas.
     */
    private void drawParticles() {
        GraphicsContext gc = particleCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, particleCanvas.getWidth(), particleCanvas.getHeight());

        for (Particle particle : particles) {
            gc.setFill(Color.WHITE);
            gc.fillOval(particle.getX(), particle.getY(), 2, 2);
        }
    }

    /*
    * methods para crear particulas.
    */
    private class Particle {
        private double x, y;
        private double velocityX, velocityY;

        public Particle(double x, double y) {
            this.x = x;
            this.y = y;
            this.velocityX = (random.nextDouble() - 0.5) * 2;
            this.velocityY = (random.nextDouble() - 0.5) * 2;
        }

        public void update() {
            x += velocityX;
            y += velocityY;

            if (x < 0 || x > particleCanvas.getWidth()) {
                velocityX = -velocityX;
            }
            if (y < 0 || y > particleCanvas.getHeight()) {
                velocityY = -velocityY;
            }
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
}