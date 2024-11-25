package com.github.Hanselmito.view;

public enum Scenes {
    ROOT("view/layout.fxml"),
    MonstruosController("view/MonstruosController.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }
}
