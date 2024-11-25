package com.github.Hanselmito.Entity.Enums;

public enum DebilElemento {
    elementoFuego("elementoFuego"),
    elementoAgua("elementoAgua"),
    elementoRayo("elementoRayo"),
    elementoHielo("elementoHielo"),
    elementoDraco("elementoDraco");

    private String PartOfDebilElemento;
    DebilElemento(String partOfDebilElemento) {
        PartOfDebilElemento = partOfDebilElemento;
    }
    public String getPartOfDebilElemento() {
        return PartOfDebilElemento;
    }

}