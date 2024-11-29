package com.github.Hanselmito.View;

import com.github.Hanselmito.DAO.MonstruosDAO;
import com.github.Hanselmito.Entity.Enums.Clase;
import com.github.Hanselmito.Entity.Monstruos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Bestiario extends Controller implements Initializable {

    @FXML
    private TabPane tabPane;

    private MonstruosDAO monstruosDAO = new MonstruosDAO();

    // Map to store images for each Tab
    private Map<String, String> tabImages = new HashMap<>();
    // Map to store descriptions for each Clase
    private Map<String, String> tabDescriptions = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the map with image paths for each Tab
        tabImages.put("Dragon_Anciano", "/Icons/Bestiario-Icons/MHWI-Render_Alatreon.jpg");
        tabImages.put("Wyvern_de_Colmillos", "/Icons/Bestiario-Icons/MHRise-Render_Zinogre.jpg");
        tabImages.put("Wyvern_Volador", "/Icons/Bestiario-Icons/MHW-Render_Rathalos_Celeste.jpg");
        tabImages.put("Wyvern_Brutal", "/Icons/Bestiario-Icons/MHW-Render_Anjanath_001.jpg");
        tabImages.put("Bestia_de_Colmillos", "/Icons/Bestiario-Icons/MHWI-Render_Rajang.jpg");
        tabImages.put("Leviatan", "/Icons/Bestiario-Icons/MHX-Render_Lagiacrus.jpg");
        tabImages.put("Wyvern_Pajaro", "/Icons/Bestiario-Icons/MHRise-Render_Kulu-Ya-Ku.jpg");

        // Initialize the map with descriptions for each Clase
        tabDescriptions.put("Dragon_Anciano", "Monstruos caracterizados por tener normalmente cuatro patas y dos alas, su poder y longevidad los convierten en criaturas de leyenda.\n El poder de muchos es tal que pueden afectar al clima y se consideran desastres naturales vivientes, por lo que las poblaciones humanas se mantienen en constante alerta.\n Se pueden encontrar en muchos hábitats, desde montañas y desiertos a ruinas y fortificaciones.");
        tabDescriptions.put("Wyvern_de_Colmillos", "Monstruos caracterizados por su aspecto reptiliano y postura cuadrúpeda.\n Todos ellos son terrestres y pueden desplazarse por su entorno con gran agilidad.\n Algunos recurren a su velocidad para defenderse o utilizan sus poderosas extremidades para asestar golpes, mientras que otros viven en grupos liderados por un líder para cazar.\n Inicialmente solo se conocía al Zinogre, pero se han descubierto muchas más en el Nuevo Mundo.");
        tabDescriptions.put("Wyvern_Volador", "Monstruos dotados de un par de alas, son la clase dominante en el mundo de Monster Hunter.\n Aunque la mayoría se caracterizan por ser bípedos y tener patas delanteras convertidas en alas, hay algunos cuyas alas se han convertido parcial o completamente en patas para caminar de forma cuadrúpeda.\n Prosperan en todos los entornos y son eficaces depredadores.");
        tabDescriptions.put("Wyvern_Brutal", "Monstruos bípedos que recuerdan a dinosaurios, sus patas delanteras no suelen estar desarrolladas.\n Se encuentran en numerosos entornos, son muy territoriales y se basan casi únicamente de la fuerza bruta para atacar.\n Poseen dietas muy distintas, pudiendo alimentarse de madera o minerales, o llegando a ser eficaces cazadores.");
        tabDescriptions.put("Bestia_de_Colmillos", "Este grupo que abarca a los monstruos mamíferos, caracterizados por poseer sangre caliente y piel cubierta de pelaje en la mayoría de casos.\n Aunque en general no son tan fuertes como los wyverns, presentan una inteligencia mayor y pueden llegar a ser muy astutos.\n Abundan sobre todo en zonas de bosque y regiones frías.\n Anteriormente eran llamados Primatius o Pelagus");
        tabDescriptions.put("Leviatan", "Wyverns de cuerpo alargado adaptados a medios acuáticos, aunque algunas especies son completamente terrestres.\n Sus cuerpos estan bien adaptados a su entorno, lo que les permite hacer frente a muchas amenazas.\n Las especies acuáticas pueden salir a tierra, pero se vuelven bastante torpes y deben regresar al agua tras un tiempo.\n Algunos poseen formas juveniles o dimorfismo sexual marcado");
        tabDescriptions.put("Wyvern_Pajaro", "Grupo de monstruos que abarca a wyverns con aspecto de ave, raptores y aves verdaderas.\n Estos monstruos tienden a no ser muy grandes, y tienen un cuerpo ligero y esbelto, y la mayoría tienen pico.\n Algunos vuelan, mientras que otros son terrestres y forman grandes grupos liderados por un alfa.\n Son menos fuertes que otros wyverns, pero también son más astutos e inteligentes, y recurren a su velocidad y agilidad para combatir.");

        for (Tab tab : tabPane.getTabs()) {
            String tabText = tab.getText();
            Clase clase = Clase.valueOf(tabText.replace(" ", "_"));
            AnchorPane anchorPane = new AnchorPane();
            VBox vBox = new VBox(10);
            vBox.setAlignment(Pos.CENTER); // Center the content

            // Class name label
            Label classNameLabel = new Label(clase.getPartOfClase());
            classNameLabel.getStyleClass().add("label-title");
            HBox titleBox = new HBox(classNameLabel);
            titleBox.setAlignment(Pos.CENTER_RIGHT); // Align title to the right

            // Image
            ImageView imageView = new ImageView();
            String imagePath = tabImages.get(tabText);
            if (imagePath != null) {
                imageView.setImage(new Image(getClass().getResourceAsStream(imagePath)));
            } else {
                // Handle missing image
                imageView.setImage(new Image(getClass().getResourceAsStream("/Icons/descarga (1).jpg")));
            }
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageView.getStyleClass().add("image-left"); // Add CSS class for left alignment

            // Description label
            String description = tabDescriptions.get(tabText);
            Label descriptionLabel = new Label(description != null ? description : "Descripción no disponible");
            descriptionLabel.getStyleClass().add("label-description");

            // List of monster names and images
            ListView<VBox> monsterListView = new ListView<>();
            monsterListView.setOrientation(javafx.geometry.Orientation.HORIZONTAL);

            List<Monstruos> monstruosList = monstruosDAO.findByClase(clase.getPartOfClase());
            for (Monstruos monstruo : monstruosList) {
                VBox monsterBox = new VBox(5);

                // Monster name label
                Label monsterNameLabel = new Label(monstruo.getNombre());
                monsterNameLabel.getStyleClass().add("label-subtitle");

                monsterBox.getChildren().add(monsterNameLabel);
                monsterListView.getItems().add(monsterBox);
            }

            vBox.getChildren().addAll(titleBox, imageView, descriptionLabel, monsterListView);
            anchorPane.getChildren().add(vBox);
            tab.setContent(anchorPane);
        }
    }


    @Override
    public void onOpen(Object input) throws Exception {
    }

    @Override
    public void onClose(Object output) {

    }
}