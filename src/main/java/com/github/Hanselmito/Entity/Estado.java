package com.github.Hanselmito.Entity;

import com.github.Hanselmito.Entity.Enums.AlteraEstado;


public class Estado {
    protected AlteraEstado estadoVeneno = AlteraEstado.estadoVeneno;
    protected AlteraEstado estadoSueno = AlteraEstado.estadoSueno;
    protected AlteraEstado estadoParalisis = AlteraEstado.estadoParalisis;
    protected AlteraEstado estadoNitro = AlteraEstado.estadoNitro;
    protected AlteraEstado estadoAturdimiento = AlteraEstado.estadoAturdimiento;
    protected int efectividadVeneno;
    protected int efectividadSueno;
    protected int efectividadParalisis;
    protected int efectividadNitro;
    protected int efectividadAturdimiento;
    protected Monstruos id_monstruo;

    public Estado() {
    }

    public Estado(AlteraEstado estadoVeneno, AlteraEstado estadoSueno, AlteraEstado estadoParalisis, AlteraEstado estadoNitro, AlteraEstado estadoAturdimiento, int efectividadVeneno, int efectividadSueno, int efectividadParalisis, int efectividadNitro, int efectividadAturdimiento, Monstruos id_monstruo) {
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
    }

    public AlteraEstado getEstadoVeneno() {
        return estadoVeneno;
    }

    public void setEstadoVeneno(AlteraEstado estadoVeneno) {
        this.estadoVeneno = estadoVeneno;
    }

    public AlteraEstado getEstadoSueno() {
        return estadoSueno;
    }

    public void setEstadoSueno(AlteraEstado estadoSueno) {
        this.estadoSueno = estadoSueno;
    }

    public AlteraEstado getEstadoParalisis() {
        return estadoParalisis;
    }

    public void setEstadoParalisis(AlteraEstado estadoParalisis) {
        this.estadoParalisis = estadoParalisis;
    }

    public AlteraEstado getEstadoNitro() {
        return estadoNitro;
    }

    public void setEstadoNitro(AlteraEstado estadoNitro) {
        this.estadoNitro = estadoNitro;
    }

    public AlteraEstado getEstadoAturdimiento() {
        return estadoAturdimiento;
    }

    public void setEstadoAturdimiento(AlteraEstado estadoAturdimiento) {
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
                '}';
    }
}
