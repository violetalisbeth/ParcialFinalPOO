package com.example.parcialfinalpoo;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ReporteAController {
    @FXML
    TextField tfIDCliente;
    @FXML
    DatePicker dpFechaInicial;
    @FXML
    DatePicker dpFechaFinal;
    @FXML
    Button btnGenerarReporte;
    @FXML
    Button btnExportarReporte;
    @FXML
    ListView<Compra> lvReporte;

    @FXML
    public void onGenerarReporteA(){ //00097923 Se define una funcion tipo lista para obtener el rango por fecha de compra

        ObservableList<Compra> resultados = FXCollections.observableArrayList();//00097923 Se declara la lista donde se guardaran los resulados de la consulta
        Conexion conexion = new Conexion();

        try{
            conexion.iniciarConexion();
            ResultSet rs = conexion.generarReporteA(Integer.parseInt(tfIDCliente.getText()),
                    dpFechaInicial.getValue().toString(),
                    dpFechaFinal.getValue().toString());
            while (rs.next()){ //00097923 Se hace un uso de while para que recorra los resultados que da la consulta

                Compra compra = new Compra(rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getInt("id_tarjeta"),
                        rs.getDouble("monto"));
                resultados.add(compra);


            }

            rs.close();
            conexion.cerrarConexion();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        lvReporte.setItems(resultados);

    }
}

