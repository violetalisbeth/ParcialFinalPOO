package com.example.parcialfinalpoo.Clases;

public class GastoMes {
    private int id;
    private int mes;
    private int anio;
    private double montoTotal;

    public GastoMes(int id, int mes, int anio, double montoTotal) {
        this.id = id;
        this.mes = mes;
        this.anio = anio;
        this.montoTotal = montoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
