package com.example.parcialfinalpoo;

import com.sun.nio.sctp.AbstractNotificationHandler;

public class Compra {
    private int id;
    private String descripcion;
    private String fecha;
    private int id_tarjeta;
    private Double monto;

    public Compra(int id, String descripcion, String fecha, int id_tarjeta, Double monto) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.id_tarjeta = id_tarjeta;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}