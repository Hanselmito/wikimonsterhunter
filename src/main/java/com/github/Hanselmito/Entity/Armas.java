package com.github.Hanselmito.Entity;

import com.github.Hanselmito.Entity.Enums.Atributo;

import java.util.Arrays;
import java.util.Objects;

public class Armas {
    protected int id;
    protected byte[] imagen;
    protected String nombre;
    protected int ataque = 100;
    protected Atributo atributo;
    protected String afilado;
    protected String afinidad;
    protected int defensa = 11;
    protected int ranuras = 3;
    protected String materiales;
    protected Materiales id_materiales;

    public Armas() {
    }

    public Armas(int id, byte[] imagen, String nombre, int ataque, Atributo atributo, String afilado, String afinidad, int defensa, int ranuras, String materiales, Materiales id_materiales) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.ataque = ataque;
        this.atributo = atributo;
        this.afilado = afilado;
        this.afinidad = afinidad;
        this.defensa = defensa;
        this.ranuras = ranuras;
        this.materiales = materiales;
        this.id_materiales = id_materiales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public String getAfilado() {
        return afilado;
    }

    public void setAfilado(String afilado) {
        this.afilado = afilado;
    }

    public String getAfinidad() {
        return afinidad;
    }

    public void setAfinidad(String afinidad) {
        this.afinidad = afinidad;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getRanuras() {
        return ranuras;
    }

    public void setRanuras(int ranuras) {
        this.ranuras = ranuras;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Armas armas = (Armas) o;
        return id == armas.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Armas{" +
                "id=" + id +
                ", imagen=" + Arrays.toString(imagen) +
                ", nombre='" + nombre + '\'' +
                ", ataque=" + ataque +
                ", atributo=" + atributo +
                ", afilado='" + afilado + '\'' +
                ", afinidad='" + afinidad + '\'' +
                ", defensa=" + defensa +
                ", ranuras=" + ranuras +
                ", materiales='" + materiales + '\'' +
                ", id_materiales=" + id_materiales +
                '}';
    }
}
