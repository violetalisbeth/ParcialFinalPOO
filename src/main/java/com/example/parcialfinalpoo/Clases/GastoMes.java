package com.example.parcialfinalpoo.Clases;

public class GastoMes {//00351519 Se crea clase GastoMes para almacenar informacion de reporteB
    private int id;//00351519 id del cliente
    private int mes;//00351519 mes cuando se realizaron las compras
    private int anio;//00351519 anio cuando se realizaron las compras
    private double montoTotal;//00351519 total de dinero gastado

    public GastoMes(int id, int mes, int anio, double montoTotal) {//00351519 se crea el constructor
        this.id = id;//00351519 se pasa el id del cliente
        this.mes = mes;//00351519 se pasa el mes
        this.anio = anio;//00351519 se pasa el anio
        this.montoTotal = montoTotal;//00351519 se pasa el monto total gastado ese mes
    }

    public int getId() {
        return id;
    }//00351519 retorna el valor del id

    public void setId(int id) {
        this.id = id;
    }//00351519 asigna el valor al id

    public int getMes() {
        return mes;
    }//00351519 retorna el valor del mes

    public void setMes(int mes) {
        this.mes = mes;
    }//00351519 asigna el valor a la variable mes

    public int getAnio() {
        return anio;
    }//00351519 retorna el valor del anio

    public void setAnio(int anio) {
        this.anio = anio;
    }//00351519 asigna el valor a la variable anio

    public double getMontoTotal() {
        return montoTotal;
    }//00351519 retorna el valor del monto total

    public void setMontoTotal(double montoTotal) {//00351519 metodo set para asigna valor al monto total
        this.montoTotal = montoTotal;//00351519 asigna el valor al monto total
    }
}
