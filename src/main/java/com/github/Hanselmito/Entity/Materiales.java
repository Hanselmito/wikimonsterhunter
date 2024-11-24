package com.github.Hanselmito.Entity;

import java.util.Arrays;
import java.util.Objects;

public class Materiales {
    protected int id = 20;
    protected byte[] imagen;
    protected String nombre;
    protected String dropRate;
    protected String mediante;
    protected int cantidad;
    protected Monstruos id_monstruo;

    public Materiales() {
    }

    public Materiales(int id, byte[] imagen, String nombre, String dropRate, String mediante, int cantidad, Monstruos id_monstruo) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.dropRate = dropRate;
        this.mediante = mediante;
        this.cantidad = cantidad;
        this.id_monstruo = id_monstruo;
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

    public String getDropRate() {
        return dropRate;
    }

    public void setDropRate(String dropRate) {
        this.dropRate = dropRate;
    }

    public String getMediante() {
        return mediante;
    }

    public void setMediante(String mediante) {
        this.mediante = mediante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Monstruos getId_monstruo() {
        return id_monstruo;
    }

    public void setId_monstruo(Monstruos id_monstruo) {
        this.id_monstruo = id_monstruo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materiales that = (Materiales) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Materiales{" +
                "id=" + id +
                ", imagen=" + Arrays.toString(imagen) +
                ", nombre='" + nombre + '\'' +
                ", dropRate='" + dropRate + '\'' +
                ", mediante='" + mediante + '\'' +
                ", cantidad=" + cantidad +
                ", id_monstruo=" + id_monstruo +
                '}';
    }
}
