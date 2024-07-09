package com.example.parcialfinalpoo;

import java.sql.*;

import com.example.parcialfinalpoo.Clases.Compra;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ReporteAController { //00097923 Clase de reporte A en javafx
    @FXML //00097923 para conecctar con la interfaz
    TextField tfIDCliente; //00097923 Agregando tfIDCliente en javafx
    @FXML //00097923 para conecctar con la interfaz
    DatePicker dpFechaInicial; //00097923 Agregando dpFechaInicial en javafx
    @FXML //00097923 para conecctar con la interfaz
    DatePicker dpFechaFinal; //00097923 Agregando dpFechaFinal en javafx
    @FXML //00097923 para conecctar con la interfaz
    Button btnGenerarReporte; //00097923 Agregando btnGenerarReporte en javafx
    @FXML //00097923 para conecctar con la interfaz
    Button btnExportarReporte; //00097923 Agregando btnExportarReporte en javafx
    @FXML //00097923 para conecctar con la interfaz
    ListView<Compra> lvReporte; //00097923 Agregando el ListView en javafx

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

                Compra compra = new Compra(rs.getInt("id"), // 00097923 Se agrega la columna id de compra dentro del objeto compra
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
}

