package com.github.Hanselmito.Entity.Enums;

public enum DebilElementoAgua {
    elementoAgua("Agua"),
    SinDebilidad("SinDebilidad");

    private String PartOfDebilElementoAgua;
    DebilElementoAgua(String partOfDebilElemento) {
        PartOfDebilElementoAgua = partOfDebilElemento;
    }
    public String getPartOfDebilElementoAgua() {
        return PartOfDebilElementoAgua;
    }

}