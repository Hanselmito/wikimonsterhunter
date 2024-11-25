package com.github.Hanselmito.Entity;

import com.github.Hanselmito.Entity.Enums.Tipo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Equipo {
    protected int id;
    protected String nombre;
    protected Tipo tipo;
    protected String habilidades;
    protected byte[] imagen;
    protected String materiales;
    protected Materiales id_materiales;
    protected List<Abilidades> List_Abilidades;

    public Equipo() {
    }

    public Equipo(int id, String nombre, Tipo tipo, String habilidades, byte[] imagen, String materiales, Materiales id_materiales) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.habilidades = habilidades;
        this.imagen = imagen;
        this.materiales = materiales;
        this.id_materiales = id_materiales;
        this.List_Abilidades = null;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public Materiales getId_materiales() {
        return id_materiales;
    }

    public void setId_materiales(Materiales id_materiales) {
        this.id_materiales = id_materiales;
    }

    public List<Abilidades> getList_Abilidades() {
        return List_Abilidades;
    }

    public void setList_Abilidades(List<Abilidades> list_Abilidades) {
        List_Abilidades = list_Abilidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return id == equipo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", habilidades='" + habilidades + '\'' +
                ", imagen=" + Arrays.toString(imagen) +
                ", materiales='" + materiales + '\'' +
                ", id_materiales=" + id_materiales +
                ", List_Abilidades=" + List_Abilidades +
                '}';
    }
}
