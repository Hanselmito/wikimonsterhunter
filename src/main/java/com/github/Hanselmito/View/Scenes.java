package com.github.Hanselmito.View;

public enum Scenes {
    ROOT("View/layout.fxml"),
    MonstruosController("View/MonstruosSaveController.fxml"),
    MenuAdmin("View/MenuAdmin.fxml"),
    Menu("View/Menu.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }
}
