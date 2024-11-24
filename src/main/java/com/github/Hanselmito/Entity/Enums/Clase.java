package com.github.Hanselmito.Entity.Enums;

public enum Clase {
    Dragon_Anciano("Dragon_Anciano"),
    Wyvern_de_Colmillos("Wyvern_de_Colmillos"),
    Wyvern_Volador("Wyvern_Volador"),
    Wyvern_Brutal("Wyvern_Brutal"),
    Bestia_de_Colmillos("Bestia_de_Colmillos"),
    Leviatan("Leviatan"),
    Wyvern_Pajaro("Wyvern_Pajaro");

    private String PartOfClase;
    Clase(String partOfClase) {
        PartOfClase = partOfClase;
    }
    public String getPartOfClase() {
        return PartOfClase;
    }
}
