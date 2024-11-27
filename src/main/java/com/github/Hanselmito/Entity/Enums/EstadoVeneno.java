package com.github.Hanselmito.Entity.Enums;

public enum EstadoVeneno {
    veneno("veneno"),
    SinEfecto("SinEfecto");

    private String PartOfAlteraEstado;
    EstadoVeneno(String partOfAlteraEstado) {
        PartOfAlteraEstado = partOfAlteraEstado;
    }
    public String getPartOfAlteraEstado() {
        return PartOfAlteraEstado;
    }
}
