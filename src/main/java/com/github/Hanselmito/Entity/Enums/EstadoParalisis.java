package com.github.Hanselmito.Entity.Enums;

public enum EstadoParalisis {
    paralisis("paralisis"),
    SinEfecto("SinEfecto");

    private String PartOfAlteraEstado;
    EstadoParalisis(String partOfAlteraEstado) {
        PartOfAlteraEstado = partOfAlteraEstado;
    }
    public String getPartOfAlteraEstado() {
        return PartOfAlteraEstado;
    }
}
