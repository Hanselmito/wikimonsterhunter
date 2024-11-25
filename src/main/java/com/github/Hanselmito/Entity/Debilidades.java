package com.github.Hanselmito.Entity;

import com.github.Hanselmito.Entity.Enums.DebilElemento;

import java.util.List;

public class Debilidades {
    protected DebilElemento elementoFuego = DebilElemento.Fuego;
    protected DebilElemento elementoAgua = DebilElemento.Agua;
    protected DebilElemento elementoRayo = DebilElemento.Rayo;
    protected DebilElemento elementoHielo = DebilElemento.Hielo;
    protected DebilElemento elementoDraco = DebilElemento.Draco;
    protected int efectividadFuego;
    protected int efectividadAgua;
    protected int efectividadRayo;
    protected int efectividadHielo;
    protected int efectividadDraco;
    protected Monstruos id_monstruo;
    protected List<Monstruos> List_Monstruos;

    public Debilidades() {
    }

    public Debilidades(DebilElemento elementoFuego, DebilElemento elementoAgua, DebilElemento elementoRayo, DebilElemento elementoHielo, DebilElemento elementoDraco, int efectividadFuego, int efectividadAgua, int efectividadRayo, int efectividadHielo, int efectividadDraco, Monstruos id_monstruo) {
        this.elementoFuego = elementoFuego;
        this.elementoAgua = elementoAgua;
        this.elementoRayo = elementoRayo;
        this.elementoHielo = elementoHielo;
        this.elementoDraco = elementoDraco;
        this.efectividadFuego = efectividadFuego;
        this.efectividadAgua = efectividadAgua;
        this.efectividadRayo = efectividadRayo;
        this.efectividadHielo = efectividadHielo;
        this.efectividadDraco = efectividadDraco;
        this.id_monstruo = id_monstruo;
        this.List_Monstruos = null;
    }

    public DebilElemento getElementoFuego() {
        return elementoFuego;
    }

    public void setElementoFuego(DebilElemento elementoFuego) {
        this.elementoFuego = elementoFuego;
    }

    public DebilElemento getElementoAgua() {
        return elementoAgua;
    }

    public void setElementoAgua(DebilElemento elementoAgua) {
        this.elementoAgua = elementoAgua;
    }

    public DebilElemento getElementoRayo() {
        return elementoRayo;
    }

    public void setElementoRayo(DebilElemento elementoRayo) {
        this.elementoRayo = elementoRayo;
    }

    public DebilElemento getElementoHielo() {
        return elementoHielo;
    }

    public void setElementoHielo(DebilElemento elementoHielo) {
        this.elementoHielo = elementoHielo;
    }

    public DebilElemento getElementoDraco() {
        return elementoDraco;
    }

    public void setElementoDraco(DebilElemento elementoDraco) {
        this.elementoDraco = elementoDraco;
    }

    public int getEfectividadFuego() {
        return efectividadFuego;
    }

    public void setEfectividadFuego(int efectividadFuego) {
        this.efectividadFuego = efectividadFuego;
    }

    public int getEfectividadAgua() {
        return efectividadAgua;
    }

    public void setEfectividadAgua(int efectividadAgua) {
        this.efectividadAgua = efectividadAgua;
    }

    public int getEfectividadRayo() {
        return efectividadRayo;
    }

    public void setEfectividadRayo(int efectividadRayo) {
        this.efectividadRayo = efectividadRayo;
    }

    public int getEfectividadHielo() {
        return efectividadHielo;
    }

    public void setEfectividadHielo(int efectividadHielo) {
        this.efectividadHielo = efectividadHielo;
    }

    public int getEfectividadDraco() {
        return efectividadDraco;
    }

    public void setEfectividadDraco(int efectividadDraco) {
        this.efectividadDraco = efectividadDraco;
    }

    public Monstruos getId_monstruo() {
        return id_monstruo;
    }

    public void setId_monstruo(Monstruos id_monstruo) {
        this.id_monstruo = id_monstruo;
    }

    public List<Monstruos> getList_Monstruos() {
        return List_Monstruos;
    }

    public void setList_Monstruos(List<Monstruos> list_Monstruos) {
        List_Monstruos = list_Monstruos;
    }

    @Override
    public String toString() {
        return "Debilidades{" +
                "elementoFuego=" + elementoFuego +
                ", elementoAgua=" + elementoAgua +
                ", elementoRayo=" + elementoRayo +
                ", elementoHielo=" + elementoHielo +
                ", elementoDraco=" + elementoDraco +
                ", efectividadFuego=" + efectividadFuego +
                ", efectividadAgua=" + efectividadAgua +
                ", efectividadRayo=" + efectividadRayo +
                ", efectividadHielo=" + efectividadHielo +
                ", efectividadDraco=" + efectividadDraco +
                ", id_monstruo=" + id_monstruo +
                ", List_Monstruos=" + List_Monstruos +
                '}';
    }
}
