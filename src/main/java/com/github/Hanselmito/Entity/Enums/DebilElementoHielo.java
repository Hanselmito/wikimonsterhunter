package com.github.Hanselmito.Entity.Enums;

public enum DebilElementoHielo {
    Hielo("Hielo"),
    SinDebilidad("SinDebilidad");

    private String PartOfDebilElementoHielo;
    DebilElementoHielo(String partOfDebilElemento) {
        PartOfDebilElementoHielo = partOfDebilElemento;
    }
    public String getPartOfDebilElementoHielo() {
        return PartOfDebilElementoHielo;
    }

}