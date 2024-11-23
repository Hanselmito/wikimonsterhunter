module com.github.Hanselmito {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.Hanselmito to javafx.fxml;
    exports com.github.Hanselmito;
}
