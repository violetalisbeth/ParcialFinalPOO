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
        return id;  //0021523 retorno id
    }
    public void setId(int id) { //0021523 setear id
        this.id = id;  //0021523 setea id como id
    }
    public String getNombre() { //0021523 obtener nombre
        return nombre; //0021523 retorno nombre
    }
    public void setNombre(String nombre) { //0021523 setear nombre
        this.nombre = nombre; //0021523 setea nombre como nombre
    }

    public int getCantidadCompras() { //0021523 obtener cantidad
        return cantidadCompras; //0021523 retorna cantidad
    }
    public void setCantidadCompras(int cantidadCompras) { //0021523 setear cantidad
        this.cantidadCompras = cantidadCompras; //0021523 setea cantidad como cantidad
    }
    public double getTotalCompras() { //0021523 obtener total gastado
        return totalCompras; //0021523 retorna gasto en compras
    }
    public void setTotalCompras(double totalCompras) { //0021523 setear total gastado
        this.totalCompras = totalCompras; //0021523 setea total de compras como total de compras
    }

}
