package com.github.Hanselmito.Entity.Enums;

public enum EstadoNitro {
    nitro("nitro"),
    SinEfecto("SinEfecto");

    private String PartOfAlteraEstado;
    EstadoNitro(String partOfAlteraEstado) {
        PartOfAlteraEstado = partOfAlteraEstado;
    }
    public String getPartOfAlteraEstado() {
        return PartOfAlteraEstado;
    }
}
