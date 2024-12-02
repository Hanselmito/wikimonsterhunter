package com.github.Hanselmito.Entity.Enums;

public enum Estados {
    plaga_de_draco("plaga_de_draco"),
    plaga_de_fuego("plaga_de_fuego"),
    plaga_de_rayo("plaga_de_rayo"),
    plaga_de_hielo("plaga_de_hielo"),
    plaga_de_agua("plaga_de_agua"),
    sin_estado("sin_estado");

    private String PartOfEstados;
    Estados(String partOfEstados) {
        PartOfEstados = partOfEstados;
    }
    public String getPartOfEstados() {
        return PartOfEstados;
    }
}
