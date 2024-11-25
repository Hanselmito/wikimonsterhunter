package com.github.Hanselmito.View;

public enum Scenes {
    ROOT("View/layout.fxml"),
    MonstruosController("View/MonstruosController.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }
}
