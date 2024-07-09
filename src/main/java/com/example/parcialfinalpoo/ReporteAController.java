package com.example.parcialfinalpoo;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.example.parcialfinalpoo.Clases.Compra;
import com.example.parcialfinalpoo.Exportar.Exportador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReporteAController { //00097923 Clase de reporte A en javafx
    @FXML //00097923 para conectar con la interfaz
    TextField tfIDCliente; //00097923 Agregando tfIDCliente en javafx
    @FXML //00097923 para conectar con la interfaz
    DatePicker dpFechaInicial; //00097923 Agregando dpFechaInicial en javafx
    @FXML //00097923 para conectar con la interfaz
    DatePicker dpFechaFinal; //00097923 Agregando dpFechaFinal en javafx
    @FXML //00097923 para conectar con la interfaz
    Button btnGenerarReporte; //00097923 Agregando btnGenerarReporte en javafx
    @FXML //00097923 para conectar con la interfaz
    Button btnExportarReporte; //00097923 Agregando btnExportarReporte en javafx
    @FXML //00097923 para conectar con la interfaz
    ListView<Compra> lvReporte; //00097923 Agregando el ListView en javafx


    @FXML //00097923 para conectar con la interfaz
    public void initialize() { //00097923 metodo para inicializar list view
        lvReporte.setCellFactory(param -> new ListCell<Compra>() { //00097923 crea una list cell para compra
            @Override //00097923 sobreescribe  el metodo
            protected void updateItem(Compra item, boolean empty) { //00097923 crea un metodo updateitem
                super.updateItem(item, empty); //00097923 pasa parametros a clase padre
                if (item == null || empty) { // 00097923 evalua si el item es nulo o vacio
                    setText(null); //00097923 si cumple la condicion establece un texto nulo
                } else { //00097923 si no cumple la condicion inicial
                    setText("Nombre del cliente: " + item.getNombreCliente() + // 00097923 establece texto con nombre de cliente
                            "ID: " + item.getId() + ", Descripción: " + item.getDescripcion() + //00097923 establece el texto con id, descripcion
                            ", Fecha: " + item.getFecha() + //00097923 establece el texto con fecha
                            ", ID Tarjeta: " + item.getId_tarjeta() + //00097923 establece el texto con id tarjeta
                            ", Monto: " + item.getMonto()); //00097923 establece el texto con monto
                }
            }
        });
    }

    @FXML //00097923 para conecctar con la interfaz
    public void onGenerarReporteA(){ //00097923 Se define una funcion tipo lista para obtener el rango por fecha de compra

        ObservableList<Compra> resultados = FXCollections.observableArrayList();//00097923 Se declara la lista donde se guardaran los resulados de la consulta
        Conexion conexion = new Conexion(); //00097923 Se crea un objeto tipo conexion

        try{ // 00097923 se inicia un try catch
            conexion.iniciarConexion(); //00097923 se manda a llamar al metodo iniciar conexion
            ResultSet rs = conexion.generarReporteA(Integer.parseInt(tfIDCliente.getText()), //00097923 se introducen los parametros al metodo generar reporteA, en este caso id
                    dpFechaInicial.getValue().toString(), //00097923 se introduce la fecha inicial obtenida del DatePicker
                    dpFechaFinal.getValue().toString()); //00097923 se introduce la fecha final obtenida del DatePicker
            while (rs.next()){ //00097923 Se hace un uso de while para que recorra los resultados que da la consulta

                Compra compra = new Compra(rs.getString("nombre"), // 00097923 Se agrega la columna nombre de cliente dentro del objeto compra
                        rs.getInt("id"), // 00097923 Se agrega la columna id de compra dentro del objeto compra
                        rs.getString("descripcion"), //00097923 Se agrega la columna descripcion de compra
                        rs.getString("fecha"), //00097923 Se agrega la columna de la fecha de la compra
                        rs.getInt("id_tarjeta"), //00097923 Se agrega la columna del id de la tarjeta con la que se realizo la compra
                        rs.getDouble("monto")); // 00097923 Se agrega la columna del monto de la compra
                resultados.add(compra); //00097923 se añaden los resultados de compra a la lista


            }

            rs.close(); //00097923 se cierra el resultset
            conexion.cerrarConexion(); //00097923 se cierra la conexion a la base de datos
        }catch (SQLException e){ //00097923 se evalua en el catch si hay una excepcion
            throw new RuntimeException(e); //00097923 si hay una excepcion se ejecuta el runtime exception
        }
        lvReporte.setItems(resultados); //00097923 muestra en el list view lo que se guardo en la variable resultados

    }
    @FXML //00097923 para conectar con la interfaz
    public void exportarReporte() { //00097923 se crea metodo para exportar reporte
        Exportador exportador = new Exportador(); // 00097923 se crea un objeto de la clase exportador
        ArrayList<String> lineas = new ArrayList<>(); // 00097923 se crea una lista para guardar las lineas del texto
        lineas.add("Reporte de Compras Por Rango De Fecha"); // 00097923 es para agregarle un titulo a las compras

        if (lvReporte.getItems().isEmpty()) { //00097923 evalua si la list view esta vacia
            lineas.add("\tNo hay compras en la lista."); //00097923 se imprimira en el archivo si la list view esta vacia
        } else { //00097923 en el caso que no cumpla la condicion
            for (Compra compra : lvReporte.getItems()) { // 00097923 recorre linea por linea agregando la informacion
                String linea = "Nombre del cliente: " + compra.getNombreCliente() + //000979223 agrega el nombre del cliente
                        ", ID: " + compra.getId() + ", Descripcion: " + compra.getDescripcion() + //00097923 agrega descripcion de compras
                        ", Fecha: " + compra.getFecha() + ", ID Tarjeta: " + compra.getId_tarjeta() + //00097923 agrega id tarjeta
                        ", Monto: " + compra.getMonto(); // 00097923 agrega monto de compra
                lineas.add(linea); // 00097923 se agrega a el arraylist de lineas
            }
        }

        try { //00097923 inicia un try catch
            exportador.exportarReporte(lineas, "A"); // 00007515: Se intenta escribir el archivo
        } catch (IOException e) { //00097923 evalua si falla
            e.printStackTrace(); // 00007515: Si falla se imprime el error
        }
    }
}

