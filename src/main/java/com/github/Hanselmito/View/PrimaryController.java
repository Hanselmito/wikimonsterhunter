package com.github.Hanselmito.View;

import java.io.IOException;

import com.github.Hanselmito.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
