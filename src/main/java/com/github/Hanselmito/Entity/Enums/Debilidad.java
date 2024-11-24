package com.github.Hanselmito.Entity.Enums;

public enum Debilidad {
    Draco("Draco"),
    Fuego("Fuego"),
    Rayo("Rayo"),
    Hielo("Hielo"),
    Agua("Agua");

    private String PartOfDebilidad;
    Debilidad(String partOfDebilidad) {
        PartOfDebilidad = partOfDebilidad;
    }
    private String getPartOfDebilidad() {
        return PartOfDebilidad;
    }
}
