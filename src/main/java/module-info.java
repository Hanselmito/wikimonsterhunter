module com.github.Hanselmito {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    requires java.desktop;

    opens com.github.Hanselmito to javafx.fxml;
    exports com.github.Hanselmito;
    exports com.github.Hanselmito.view;
    opens com.github.Hanselmito.view to javafx.fxml;
    opens com.github.Hanselmito.Conection to java.xml.bind;
}
