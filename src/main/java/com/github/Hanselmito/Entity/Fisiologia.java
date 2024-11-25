package com.github.Hanselmito.Entity;

import java.util.Arrays;
import java.util.List;

public class Fisiologia {
    protected byte[] imagen;
    protected String puntos_debiles;
    protected int corte;
    protected int impacto;
    protected int disparo;
    protected String partes_rompibles;
    protected Monstruos id_monstruo;
    protected List<Monstruos> List_Monstruos;

    public Fisiologia() {
    }

    public Fisiologia(byte[] imagen, String puntos_debiles, int corte, int impacto, int disparo, String partes_rompibles, Monstruos id_monstruo) {
        this.imagen = imagen;
        this.puntos_debiles = puntos_debiles;
        this.corte = corte;
        this.impacto = impacto;
        this.disparo = disparo;
        this.partes_rompibles = partes_rompibles;
        this.id_monstruo = id_monstruo;
        this.List_Monstruos = null;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getPuntos_debiles() {
        return puntos_debiles;
    }

    public void setPuntos_debiles(String puntos_debiles) {
        this.puntos_debiles = puntos_debiles;
    }

    public int getCorte() {
        return corte;
    }

    public void setCorte(int corte) {
        this.corte = corte;
    }

    public int getImpacto() {
        return impacto;
    }

    public void setImpacto(int impacto) {
        this.impacto = impacto;
    }

    public int getDisparo() {
        return disparo;
    }

    public void setDisparo(int disparo) {
        this.disparo = disparo;
    }

    public String getPartes_rompibles() {
        return partes_rompibles;
    }

    public void setPartes_rompibles(String partes_rompibles) {
        this.partes_rompibles = partes_rompibles;
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
        return "Fisiologia{" +
                "imagen=" + Arrays.toString(imagen) +
                ", puntos_debiles='" + puntos_debiles + '\'' +
                ", corte=" + corte +
                ", impacto=" + impacto +
                ", disparo=" + disparo +
                ", partes_rompibles='" + partes_rompibles + '\'' +
                ", id_monstruo=" + id_monstruo +
                ", List_Monstruos=" + List_Monstruos +
                '}';
    }
}
