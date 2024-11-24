package com.github.Hanselmito.Entity.Enums;

public enum AlteraEstado {
    estadoVeneno("estadoVeneno"),
    estadoSueno("estadoSueno"),
    estadoParalisis("estadoParalisis"),
    estadoNitro("estadoNitro"),
    estadoAturdimiento("estadoAturdimiento");

    private String PartOfAlteraEstado;
    AlteraEstado(String partOfAlteraEstado) {
        PartOfAlteraEstado = partOfAlteraEstado;
    }
    public String getPartOfAlteraEstado() {
        return PartOfAlteraEstado;
    }
}
