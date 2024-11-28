package com.github.Hanselmito.View;

public enum Scenes {
    ROOT("View/layout.fxml"),
    MonstruosController("View/MonstruosController.fxml"),
    FisiologiaController("View/FisiologiaController.fxml"),
    DebilidadesController("View/DebilidadesController.fxml"),
    EstadoController("View/EstadoController.fxml"),
    MaterialesController("View/MaterialesController.fxml"),
    ArmasController("View/ArmasController.fxml"),
    EquipoController("View/EquipoController.fxml"),
    AbilidadesController("View/AbilidadesController.fxml"),
    OtorgaController("View/OtorgaController.fxml"),
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
