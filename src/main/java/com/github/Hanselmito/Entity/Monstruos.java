package com.github.Hanselmito.Entity;

import com.github.Hanselmito.Entity.Enums.Clase;
import com.github.Hanselmito.Entity.Enums.Debilidad;
import com.github.Hanselmito.Entity.Enums.Elementos;
import com.github.Hanselmito.Entity.Enums.Estados;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Monstruos {
    protected int id = 20;
    protected String nombre;
    protected String titulos;
    protected Clase clase;
    protected Elementos elementos = Elementos.Sin_elemento;
    protected Estados estados;
    protected Debilidad debilidad;
    protected String habitats;
    protected String tamano;
    protected String parientes;
    protected byte[] imagen;
    protected List<Materiales> List_Materiales;

    public Monstruos() {
    }

    public Monstruos(int id, String nombre, String titulos, Clase clase, Elementos elementos, Estados estados, Debilidad debilidad, String habitats, String tamano, String parientes, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.titulos = titulos;
        this.clase = clase;
        this.elementos = elementos;
        this.estados = estados;
        this.debilidad = debilidad;
        this.habitats = habitats;
        this.tamano = tamano;
        this.parientes = parientes;
        this.imagen = imagen;
        List_Materiales = null;
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

    public String getTitulos() {
        return titulos;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Elementos getElementos() {
        return elementos;
    }

    public void setElementos(Elementos elementos) {
        this.elementos = elementos;
    }

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public Debilidad getDebilidad() {
        return debilidad;
    }

    public void setDebilidad(Debilidad debilidad) {
        this.debilidad = debilidad;
    }

    public String getHabitats() {
        return habitats;
    }

    public void setHabitats(String habitats) {
        this.habitats = habitats;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getParientes() {
        return parientes;
    }

    public void setParientes(String parientes) {
        this.parientes = parientes;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public List<Materiales> getList_Materiales() {
        return List_Materiales;
    }

    public void setList_Materiales(List<Materiales> list_Materiales) {
        List_Materiales = list_Materiales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monstruos monstruos = (Monstruos) o;
        return id == monstruos.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Monstruos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", titulos='" + titulos + '\'' +
                ", clase=" + clase +
                ", elementos=" + elementos +
                ", estados=" + estados +
                ", debilidad=" + debilidad +
                ", habitats='" + habitats + '\'' +
                ", tamano='" + tamano + '\'' +
                ", parientes='" + parientes + '\'' +
                ", imagen=" + Arrays.toString(imagen) +
                ", List_Materiales=" + List_Materiales +
                '}';
    }
}
