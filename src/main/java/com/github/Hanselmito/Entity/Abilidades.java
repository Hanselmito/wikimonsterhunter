package com.github.Hanselmito.Entity;

import java.util.List;
import java.util.Objects;

public class Abilidades {
    protected int id;
    protected String nombre;
    protected int nivel;
    protected String descripcion;
    protected String set;
    protected List<Equipo> List_Equipo;

    public Abilidades() {
    }

    public Abilidades(int id, String nombre, int nivel, String descripcion, String set) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.set = set;
        this.List_Equipo = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public List<Equipo> getList_Equipo() {
        return List_Equipo;
    }

    public void setList_Equipo(List<Equipo> list_Equipo) {
        List_Equipo = list_Equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abilidades that = (Abilidades) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Abilidades{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", descripcion='" + descripcion + '\'' +
                ", set='" + set + '\'' +
                ", List_Equipo=" + List_Equipo +
                '}';
    }
}
