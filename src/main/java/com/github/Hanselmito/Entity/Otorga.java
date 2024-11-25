package com.github.Hanselmito.Entity;

import java.util.Objects;

public class Otorga {
    protected Equipo id_Equipo;
    protected Abilidades id_Abilidades;

    public Otorga() {
    }

    public Otorga(Equipo id_Equipo, Abilidades id_Abilidades) {
        this.id_Equipo = id_Equipo;
        this.id_Abilidades = id_Abilidades;
    }

    public Equipo getId_Equipo() {
        return id_Equipo;
    }

    public void setId_Equipo(Equipo id_Equipo) {
        this.id_Equipo = id_Equipo;
    }

    public Abilidades getId_Abilidades() {
        return id_Abilidades;
    }

    public void setId_Abilidades(Abilidades id_Abilidades) {
        this.id_Abilidades = id_Abilidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Otorga otorga = (Otorga) o;
        return Objects.equals(id_Equipo, otorga.id_Equipo) && Objects.equals(id_Abilidades, otorga.id_Abilidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Equipo, id_Abilidades);
    }

    @Override
    public String toString() {
        return "Otorga{" +
                "id_Equipo=" + id_Equipo +
                ", id_Abilidades=" + id_Abilidades +
                '}';
    }
}
