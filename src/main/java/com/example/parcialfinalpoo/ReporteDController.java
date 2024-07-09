package com.example.parcialfinalpoo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.ResultSet;

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

}
