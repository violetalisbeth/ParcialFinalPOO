package com.example.parcialfinalpoo.Clases;

public class Cliente { //0021523 creacion clase compra
    private int id; //0021523 id cliente
    private String nombre; //0021523 nombre cliente
    private int cantidadCompras; //0021523 cantidad de compras realizadas por el cliente
    private double totalCompras; //0021523 total gastado en las compras

    public Cliente(int id, String nombre, int CantidadCompras, double TotalCompras) { //0021523 constructor
        this.id = id; //0021523 id a constructor
        this.nombre = nombre; //0021523 nombre a constructor
        this.cantidadCompras = CantidadCompras; //0021523 cantidad a constructor
        this.totalCompras = TotalCompras; //0021523 total a constructor
    }

    public int getId() { //0021523 obtener id
        return id;
    }
    public void setId(int id) { //0021523 setear id
        this.id = id;
    }
    public String getNombre() { //0021523 obtener nombre
        return nombre;
    }
    public void setNombre(String nombre) { //0021523 setear nombre
        this.nombre = nombre;
    }

    public int getCantidadCompras() { //0021523 obtener cantidad
        return cantidadCompras;
    }
    public void setCantidadCompras(int cantidadCompras) { //0021523 setear cantidad
        this.cantidadCompras = cantidadCompras;
    }
    public double getTotalCompras() { //0021523 obtener total gastado
        return totalCompras;
    }
    public void setTotalCompras(double totalCompras) { //0021523 setear total gastado
        this.totalCompras = totalCompras;
    }

}
