package com.github.Hanselmito.Entity.Enums;

public enum EstadoAturdimiento {
    aturdimiento("aturdimiento"),
    SinEfecto("SinEfecto");


    private String PartOfAlteraEstado;
    EstadoAturdimiento(String partOfAlteraEstado) {
        PartOfAlteraEstado = partOfAlteraEstado;
    }
    public String getPartOfAlteraEstado() {
        return PartOfAlteraEstado;
    }
}
