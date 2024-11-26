package com.github.Hanselmito.Entity.Enums;

public enum DebilElementoRayo {
    elementoRayo("Rayo"),
    SinDebilidad("SinDebilidad");

    private String PartOfDebilElementoRayo;
    DebilElementoRayo(String partOfDebilElemento) {
        PartOfDebilElementoRayo = partOfDebilElemento;
    }
    public String getPartOfDebilElementoRayo() {
        return PartOfDebilElementoRayo;
    }

}