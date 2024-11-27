package com.github.Hanselmito.Entity.Enums;

public enum DebilElementoDraco {
    Draco("Draco"),
    SinDebilidad("SinDebilidad");

    private String PartOfDebilElementoDraco;
    DebilElementoDraco(String partOfDebilElemento) {
        PartOfDebilElementoDraco = partOfDebilElemento;
    }
    public String getPartOfDebilElementoDraco() {
        return PartOfDebilElementoDraco;
    }

}