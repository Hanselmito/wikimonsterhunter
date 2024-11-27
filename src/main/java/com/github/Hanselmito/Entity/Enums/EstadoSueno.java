package com.github.Hanselmito.Entity.Enums;

public enum EstadoSueno {
    sueno("sueno"),
    SinEfecto("SinEfecto");

    private String PartOfAlteraEstado;
    EstadoSueno(String partOfAlteraEstado) {
        PartOfAlteraEstado = partOfAlteraEstado;
    }
    public String getPartOfAlteraEstado() {
        return PartOfAlteraEstado;
    }
}
