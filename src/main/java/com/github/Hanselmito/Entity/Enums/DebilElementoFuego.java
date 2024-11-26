package com.github.Hanselmito.Entity.Enums;

public enum DebilElementoFuego {
    elementoFuego("Fuego"),
    SinDebilidad("SinDebilidad");

    private String PartOfDebilElementoFuego;
    DebilElementoFuego(String partOfDebilElemento) {
        PartOfDebilElementoFuego = partOfDebilElemento;
    }
    public String getPartOfDebilElementoFuego() {
        return PartOfDebilElementoFuego;
    }

}