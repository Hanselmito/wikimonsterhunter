package com.github.Hanselmito.Entity.Enums;

public enum DebilElementoFuego {
    Fuego("Fuego"),
    SinDebilidad("SinDebilidad");

    private String PartOfDebilElementoFuego;
    DebilElementoFuego(String partOfDebilElemento) {
        PartOfDebilElementoFuego = partOfDebilElemento;
    }
    public String getPartOfDebilElementoFuego() {
        return PartOfDebilElementoFuego;
    }

}