package com.github.Hanselmito.Entity;

import com.github.Hanselmito.Entity.Enums.*;

import java.util.List;


public class Estado {
    protected EstadoVeneno estadoVeneno = EstadoVeneno.SinEfecto;
    protected EstadoSueno estadoSueno = EstadoSueno.SinEfecto;
    protected EstadoParalisis estadoParalisis = EstadoParalisis.SinEfecto;
    protected EstadoNitro estadoNitro = EstadoNitro.SinEfecto;
    protected EstadoAturdimiento estadoAturdimiento = EstadoAturdimiento.SinEfecto;
    protected int efectividadVeneno;
    protected int efectividadSueno;
    protected int efectividadParalisis;
    protected int efectividadNitro;
    protected int efectividadAturdimiento;
    protected Monstruos id_monstruo;
    protected List<Monstruos> List_Monstruos;

    public Estado() {
    }

    public Estado(EstadoVeneno estadoVeneno, EstadoSueno estadoSueno, EstadoParalisis estadoParalisis, EstadoNitro estadoNitro, EstadoAturdimiento estadoAturdimiento, int efectividadVeneno, int efectividadSueno, int efectividadParalisis, int efectividadNitro, int efectividadAturdimiento, Monstruos id_monstruo, List<Monstruos> list_Monstruos) {
        this.estadoVeneno = estadoVeneno;
        this.estadoSueno = estadoSueno;
        this.estadoParalisis = estadoParalisis;
        this.estadoNitro = estadoNitro;
        this.estadoAturdimiento = estadoAturdimiento;
        this.efectividadVeneno = efectividadVeneno;
        this.efectividadSueno = efectividadSueno;
        this.efectividadParalisis = efectividadParalisis;
        this.efectividadNitro = efectividadNitro;
        this.efectividadAturdimiento = efectividadAturdimiento;
        this.id_monstruo = id_monstruo;
        this.List_Monstruos = null;
    }

    public EstadoVeneno getEstadoVeneno() {
        return estadoVeneno;
    }

    public void setEstadoVeneno(EstadoVeneno estadoVeneno) {
        this.estadoVeneno = estadoVeneno;
    }

    public EstadoSueno getEstadoSueno() {
        return estadoSueno;
    }

    public void setEstadoSueno(EstadoSueno estadoSueno) {
        this.estadoSueno = estadoSueno;
    }

    public EstadoParalisis getEstadoParalisis() {
        return estadoParalisis;
    }

    public void setEstadoParalisis(EstadoParalisis estadoParalisis) {
        this.estadoParalisis = estadoParalisis;
    }

    public EstadoNitro getEstadoNitro() {
        return estadoNitro;
    }

    public void setEstadoNitro(EstadoNitro estadoNitro) {
        this.estadoNitro = estadoNitro;
    }

    public EstadoAturdimiento getEstadoAturdimiento() {
        return estadoAturdimiento;
    }

    public void setEstadoAturdimiento(EstadoAturdimiento estadoAturdimiento) {
        this.estadoAturdimiento = estadoAturdimiento;
    }

    public int getEfectividadVeneno() {
        return efectividadVeneno;
    }

    public void setEfectividadVeneno(int efectividadVeneno) {
        this.efectividadVeneno = efectividadVeneno;
    }

    public int getEfectividadSueno() {
        return efectividadSueno;
    }

    public void setEfectividadSueno(int efectividadSueno) {
        this.efectividadSueno = efectividadSueno;
    }

    public int getEfectividadParalisis() {
        return efectividadParalisis;
    }

    public void setEfectividadParalisis(int efectividadParalisis) {
        this.efectividadParalisis = efectividadParalisis;
    }

    public int getEfectividadNitro() {
        return efectividadNitro;
    }

    public void setEfectividadNitro(int efectividadNitro) {
        this.efectividadNitro = efectividadNitro;
    }

    public int getEfectividadAturdimiento() {
        return efectividadAturdimiento;
    }

    public void setEfectividadAturdimiento(int efectividadAturdimiento) {
        this.efectividadAturdimiento = efectividadAturdimiento;
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
        return "Estado{" +
                "estadoVeneno=" + estadoVeneno +
                ", estadoSueno=" + estadoSueno +
                ", estadoParalisis=" + estadoParalisis +
                ", estadoNitro=" + estadoNitro +
                ", estadoAturdimiento=" + estadoAturdimiento +
                ", efectividadVeneno=" + efectividadVeneno +
                ", efectividadSueno=" + efectividadSueno +
                ", efectividadParalisis=" + efectividadParalisis +
                ", efectividadNitro=" + efectividadNitro +
                ", efectividadAturdimiento=" + efectividadAturdimiento +
                ", id_monstruo=" + id_monstruo +
                ", List_Monstruos=" + List_Monstruos +
                '}';
    }
}
