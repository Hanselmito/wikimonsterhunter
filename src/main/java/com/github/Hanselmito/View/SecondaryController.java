package com.github.Hanselmito.View;

import java.io.IOException;

import com.github.Hanselmito.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}