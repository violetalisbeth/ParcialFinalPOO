package com.example.parcialfinalpoo.Clases;


public class Compra { //00097923 Se crea la clase compra
    private String nombreCliente; //00097923 nombre del cliente
    private int id; //00097923 id de la compra
    private String descripcion; //00097923 descripcion de la compra
    private String fecha; //00097923 fecha de la compra
    private int id_tarjeta; //00097923 id de la tarjeta con el que se realizó la compra
    private Double monto; // 00097923 monto de la compra

    public Compra(String nombreCliente,  int id, String descripcion, String fecha, int id_tarjeta, Double monto) { //00097923 creacion de constructor
        this.nombreCliente = nombreCliente; //00097923 paso de nombre cliente a  constructor
        this.id = id; //00097923 paso de id a constructor
        this.descripcion = descripcion; //00097923 paso de descripcion a constructor
        this.fecha = fecha; //00097923 paso de fecha
        this.id_tarjeta = id_tarjeta; // 00097923 paso de id tarjeta
        this.monto = monto; //00097923 paso de monto
    }

    public String getNombreCliente() { return nombreCliente;} //00097923 retorno del nombre del cliente

    public void setNombreCliente(String nombreCliente) {this.nombreCliente = nombreCliente;}//00097923 asigna valor al nombre del cliente

    public int getId() { return id;} //00097923 retorno de valor de id

    public void setId(int id) {this.id = id;} //00097923 asigna valor de id

    public String getDescripcion() {return descripcion;} //00097923 retorna descripcion

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;} //00097923 asigna valor a descripcion

    public String getFecha() {return fecha;} //00097923 retorna fecha

    public void setFecha(String fecha) {this.fecha = fecha;} //00097923 asigna fecha

    public int getId_tarjeta() {return id_tarjeta;} //00097923  retorna id tarjeta

    public void setId_tarjeta(int id_tarjeta) {this.id_tarjeta = id_tarjeta;} //00097923 asigna valor a id tarjeta

    public Double getMonto() {return monto;} //00097923 retorna el monto

    public void setMonto(Double monto) {this.monto = monto;}//00097923 asigna valor de monto
}