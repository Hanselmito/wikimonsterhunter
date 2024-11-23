module com.github.Hanselmito {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;

    opens com.github.Hanselmito to javafx.fxml;
    exports com.github.Hanselmito;
    exports com.github.Hanselmito.View;
    opens com.github.Hanselmito.View to javafx.fxml;
    opens com.github.Hanselmito.Conection to java.xml.bind;
}
