package com.example.parcialfinalpoo;

import com.example.parcialfinalpoo.Clases.Tarjeta;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class InsertarTarjetaController {
    @FXML
    TextField tfNumeroTarjeta;
    @FXML
    DatePicker dpFechaExpiracion;
    @FXML
    TextField tfTipo;
    @FXML
    TextField tfIDFacilitador;
    @FXML
    TextField tfIDCliente;


    @FXML
    void insertarTarjeta(){
        Conexion conexion = new Conexion(); //00097923 se manda a llamar al metodo iniciar conexion

        try {
            conexion.iniciarConexion();


            int filasAfectadas = conexion.insertarTarjeta(tfNumeroTarjeta.getText(),
                    dpFechaExpiracion.getValue().toString(),
                    tfTipo.getText(),
                    Integer.parseInt(tfIDFacilitador.getText()),
                    Integer.parseInt(tfIDCliente.getText()));

            if (filasAfectadas > 0) {
                mostrarMensaje("Insertado","Tarjeta insertada");
            }

            conexion.cerrarConexion();
        } catch (SQLException e) {
            mostrarMensaje("Error", "Error al insertar el cliente");
            throw new RuntimeException(e);
        }

    }
    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }


}
