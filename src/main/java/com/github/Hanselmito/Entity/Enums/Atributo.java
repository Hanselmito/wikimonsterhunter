package com.github.Hanselmito.Entity.Enums;

public enum Atributo {
    Draco("Draco"),
    Fuego("Fuego"),
    Rayo("Rayo"),
    Hielo("Hielo"),
    Agua("Agua"),
    Sueno("Sueno"),
    Paralisis("Paralisis"),
    Nitro("Nitro"),
    Veneno("Veneno");

    private String PartOfAtributo;
    Atributo(String partOfAtributo) {
        PartOfAtributo = partOfAtributo;
    }

    public String getPartOfAtributo() {
        return PartOfAtributo;
    }
}
