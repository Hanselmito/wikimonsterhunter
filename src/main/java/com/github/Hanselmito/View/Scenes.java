package com.github.Hanselmito.View;

public enum Scenes {
    ROOT("View/layout.fxml"),
    /* Pantalla de controladores */
    MonstruosController("View/MonstruosController.fxml"),
    FisiologiaController("View/FisiologiaController.fxml"),
    DebilidadesController("View/DebilidadesController.fxml"),
    EstadoController("View/EstadoController.fxml"),
    MaterialesController("View/MaterialesController.fxml"),
    ArmasController("View/ArmasController.fxml"),
    EquipoController("View/EquipoController.fxml"),
    AbilidadesController("View/HabilidadesController.fxml"),
    OtorgaController("View/OtorgaController.fxml"),
    /* pantallas de mostrar */
    Bestiario("View/Bestiario.fxml"),
    MostrarMonstruos("View/MostrarMonstruos.fxml"),
    MaterialMonstruo("View/MaterialesMonstruo.fxml"),
    ArmasMonstruo("View/ArmasMonstruo.fxml"),
    EquipoMonstruo("View/EquipoMonstruo.fxml"),
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
