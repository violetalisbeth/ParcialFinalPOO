package com.example.parcialfinalpoo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;

public class ReporteDController {

    @FXML
    private ListView<Cliente> lvClienteFacilitador;
    @FXML
    private TextField facilitador;

    @FXML
    private Button generarReporte;

    public void generarReporte(){
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();

        Conexion conexion = new Conexion();

        try{
            conexion.iniciarConexion();

            String facilitadorText = facilitador.getText();

            ResultSet rs = conexion.generarReporteD(facilitadorText);
            while (rs.next()) {
               Cliente cliente = new Cliente(
                       rs.getInt("id"),
                       rs.getString("nombre"),
                       rs.getInt("Cantidad de compras"),
                       rs.getDouble("Total gastado:")
                       );
               clientes.add(cliente);

            }

            rs.close();
            conexion.cerrarConexion();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }

        lvClienteFacilitador.setItems(clientes);
    }

}
