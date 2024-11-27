package com.github.Hanselmito.Entity.Enums;

public enum DebilElementoAgua {
    Agua("Agua"),
    SinDebilidad("SinDebilidad");

    private String PartOfDebilElementoAgua;
    DebilElementoAgua(String partOfDebilElemento) {
        PartOfDebilElementoAgua = partOfDebilElemento;
    }
    public String getPartOfDebilElementoAgua() {
        return PartOfDebilElementoAgua;
    }

}