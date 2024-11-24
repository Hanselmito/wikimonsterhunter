package com.github.Hanselmito.Entity.Enums;

public enum DebilElemento {
    Fuego(Debilidad.Fuego),
    Agua(Debilidad.Agua),
    Rayo(Debilidad.Rayo),
    Hielo(Debilidad.Hielo),
    Draco(Debilidad.Draco);

    private final Debilidad debilidad;

    DebilElemento(Debilidad debilidad) {
        this.debilidad = debilidad;
    }

    public Debilidad getDebilidad() {
        return debilidad;
    }
}