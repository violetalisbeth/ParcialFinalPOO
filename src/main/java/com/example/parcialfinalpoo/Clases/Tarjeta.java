package com.example.parcialfinalpoo.Clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Tarjeta {
    private int id; // 00007515: id de la tarjeta
    private String numero; // 00007515: numero de la tarjeta
    private String fechaExpiracion; // 00007515: fecha de expiracion de la tarjeta
    private String tipo; // 00007515: Tipo de la tarjeta
    private String facilitador; // 00007515: facilitador de la tarjeta
    private int idCliente; // 00007515: id del cliente dueno de la tarejta

    public Tarjeta(int id, String numero, String fechaExpiracion, String tipo, String facilitador, int idCliente) { // 00007515: Aqui se crea el constructo de Tarjeta
        this.id = id;
        this.numero = numero;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
        this.facilitador = facilitador;
        this.idCliente = idCliente;
    }

    public int getId() { // 00007515: Definicion de la funcion getId para obtener el id de la tarjeta
        return id; // 00007515: Retorno del valor de id
    }

    public void setId(int id) { // 00007515: Definicion de la funcion setId, recibe como parametro id
        this.id = id; // 00007515: Asigna el valor de id al valor que se le manda a la funcion
    }

    public String getNumero() { // 00007515: Definicion dela funcion getNumero para obtener el numero de la tarjeta
        return ocultarNumero(); // 00007515: Retorna el numero de la tarjeta, pero oculto
    }

    public void setNumero(String numero) { // 00007515: Definicion de la funcion setNumero, recibe como parametro numero
        this.numero = numero; // 00007515: Asigna el valor de numero
    }

    public String getFechaExpiracion() { // 00007515: Definicion de la funcion getFechaExpiracion
        return fechaExpiracion; // 00007515: Retorna la fecha de expiracion de la tarjeta
    }

    public void setFechaExpiracion(String fechaExpiracion) { // 00007515: Definicion de la funcion setFechaExpiracion, recibe como parametro la fecha de expiracion
        this.fechaExpiracion = fechaExpiracion; // 00007515: retorna el valor de fecha de expiracion
    }

    public String getFacilitador() {
        return facilitador; // 00007515: Retorna el valor de facilitador
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador; // 00007515: Asigna el valor de facilitador
    }

    public int getIdCliente() {
        return idCliente; // 00007515: Retorna el valor de idCliente
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente; // 00007515: Asigna el valor de idCliente
    }

    public String getTipo() {
        return tipo; // 00007515: Retorna el valor de tipo
    }

    public void setTipo(String tipo) {
        this.tipo = tipo; // 00007515: Asigna el valor de tipo
    }

    private String ocultarNumero() {
        String primerosDigitos = numero.substring(0,numero.length()-4); // 00007515: Obtiene los primeros 12 digitos de la tarjeta, incluyendo espacios o guiones
        String textoOculto = numero.replace(primerosDigitos, "XXXX XXXX XXXX "); // 00007515: Oculta los primeros digitos y los reemplaza por X
        return textoOculto; // 00007515: Retorna el texto oculto
    }

    @Override
    public String toString() {
        return "\t- " + this.getNumero() + " " + this.facilitador.toUpperCase(); // 00007515: Imprime una descripcion de la tarjeta
    }
}
