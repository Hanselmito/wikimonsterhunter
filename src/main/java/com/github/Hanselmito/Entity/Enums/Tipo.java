package com.github.Hanselmito.Entity.Enums;

public enum Tipo {
    ArmaduraAlfa("ArmaduraAlfa"),
    ArmaduraBeta("ArmaduraBeta");

    private String PartOfTipo;
    Tipo(String partOfTipo) {
        PartOfTipo = partOfTipo;
    }

    public String getPartOfTipo() {
        return PartOfTipo;
    }
}
