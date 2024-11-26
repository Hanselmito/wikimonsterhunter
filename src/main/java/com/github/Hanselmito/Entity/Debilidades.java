package com.github.Hanselmito.Entity;

import com.github.Hanselmito.Entity.Enums.*;

import java.util.List;

public class Debilidades {
    protected DebilElementoFuego elementoFuego;
    protected DebilElementoAgua elementoAgua;
    protected DebilElementoRayo elementoRayo;
    protected DebilElementoHielo elementoHielo;
    protected DebilElementoDraco elementoDraco;
    protected int efectividadFuego;
    protected int efectividadAgua;
    protected int efectividadRayo;
    protected int efectividadHielo;
    protected int efectividadDraco;
    protected Monstruos id_monstruo;
    protected List<Monstruos> List_Monstruos;

    public Debilidades() {
    }

    public Debilidades(DebilElementoFuego elementoFuego, DebilElementoAgua elementoAgua, DebilElementoRayo elementoRayo, DebilElementoHielo elementoHielo, DebilElementoDraco elementoDraco, int efectividadFuego, int efectividadAgua, int efectividadRayo, int efectividadHielo, int efectividadDraco, Monstruos id_monstruo) {
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

    public DebilElementoFuego getElementoFuego() {
        return elementoFuego;
    }

    public void setElementoFuego(boolean elementoFuego) {
        if (elementoFuego) {
            this.elementoFuego = DebilElementoFuego.elementoFuego;
        } else {
            this.elementoFuego = DebilElementoFuego.SinDebilidad;
        }
    }

    public DebilElementoAgua getElementoAgua() {
        return elementoAgua;
    }

    public void setElementoAgua(boolean elementoAgua) {
        if (elementoAgua) {
            this.elementoAgua = DebilElementoAgua.elementoAgua;
        } else {
            this.elementoAgua = DebilElementoAgua.SinDebilidad;
        }
    }

    public DebilElementoRayo getElementoRayo() {
        return elementoRayo;
    }

    public void setElementoRayo(boolean elementoRayo) {
        if (elementoRayo) {
            this.elementoRayo = DebilElementoRayo.elementoRayo;
        } else {
            this.elementoRayo = DebilElementoRayo.SinDebilidad;
        }
    }

    public DebilElementoHielo getElementoHielo() {
        return elementoHielo;
    }

    public void setElementoHielo(boolean elementoHielo) {
        if (elementoHielo) {
            this.elementoHielo = DebilElementoHielo.elementoHielo;
        } else {
            this.elementoHielo = DebilElementoHielo.SinDebilidad;
        }
    }

    public DebilElementoDraco getElementoDraco() {
        return elementoDraco;
    }

    public void setElementoDraco(boolean elementoDraco) {
        if (elementoDraco) {
            this.elementoDraco = DebilElementoDraco.elementoDraco;
        } else {
            this.elementoDraco = DebilElementoDraco.SinDebilidad;
        }
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
