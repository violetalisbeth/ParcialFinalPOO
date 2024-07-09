package com.example.parcialfinalpoo;

public class Cliente {
    private int id;
    private String nombre;
    private int cantidadCompras;
    private double totalCompras;

    public Cliente(int id, String nombre, int CantidadCompras, double TotalCompras) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadCompras = CantidadCompras;
        this.totalCompras = TotalCompras;
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

    public int getCantidadCompras() {
        return cantidadCompras;
    }
    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }
    public double getTotalCompras() {
        return totalCompras;
    }
    public void setTotalCompras(double totalCompras) {
        this.totalCompras = totalCompras;
    }

}
