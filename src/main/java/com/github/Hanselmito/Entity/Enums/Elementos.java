package com.github.Hanselmito.Entity.Enums;

public enum Elementos {
    Draco("Draco"),
    Fuego("Fuego"),
    Rayo("Rayo"),
    Hielo("Hielo"),
    Agua("Agua"),
    Sin_elemento("sin_elemento");

    private String PartOfElementos;
    Elementos(String partOfElementos) {
        PartOfElementos = partOfElementos;
    }

    public String getPartOfElementos() {
        return PartOfElementos;
    }
}
