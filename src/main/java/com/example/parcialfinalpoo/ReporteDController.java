package com.example.parcialfinalpoo;

import com.example.parcialfinalpoo.Exportar.Exportador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ReporteDController {

    @FXML
    ListView<Cliente> lvClienteFacilitador;
    @FXML
    TextField tfFacilitador;
    @FXML
    Button btGenerarReporte;
    @FXML
     Button btExportarR;

    private ObservableList<Cliente> clientes;

    @FXML
    public void initialize () {
        clientes = FXCollections.observableArrayList();
        lvClienteFacilitador.setItems(clientes);

        lvClienteFacilitador.setCellFactory(param -> new ListCell<Cliente>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText("ID: " + item.getId() + ", Nombre: " + item.getNombre() +
                            ", Compras: " + item.getCantidadCompras() +
                            ", Total: " + item.getTotalCompras());
                }
            }
        });
    }

    @FXML
    public void onGenerarReporte(){
        clientes.clear();
        Conexion conexion = new Conexion();

        try{
            conexion.iniciarConexion();

            String facilitadorText = tfFacilitador.getText();
            ResultSet rs = conexion.generarReporteD(facilitadorText);
            while (rs.next()) {
               Cliente cliente = new Cliente(
                       rs.getInt("id"),
                       rs.getString("nombre"),
                       rs.getInt("Cantidad_compras"),
                       rs.getDouble("Total_compras")
                       );
               clientes.add(cliente);
            }

            rs.close();
            conexion.cerrarConexion();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void exportarReporte() {
        Exportador exportador = new Exportador(); // Se crea un objeto de la clase Exportador
        ArrayList<String> lineas = new ArrayList<>(); // Se crea una lista para guardar línea por línea el texto que se va a guardar
        lineas.add("Reporte de Clientes"); // Se añade el título del reporte

        if (clientes.isEmpty()) {
            lineas.add("\tNo hay clientes en la lista."); // Si la lista está vacía, se muestra que está vacía
        } else {
            for (Cliente cliente : clientes) {
                String linea = "ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() +
                        ", Compras: " + cliente.getCantidadCompras() + ", Total: " + cliente.getTotalCompras();
                lineas.add(linea); // Se agrega la información del cliente a la lista de líneas
            }
        }

        try {
            exportador.exportarReporte(lineas, "D"); // 00007515: Se intenta escribir el archivo
        } catch (IOException e) {
            e.printStackTrace(); // 00007515: Si falla se imprime el error
        }
    }
}
