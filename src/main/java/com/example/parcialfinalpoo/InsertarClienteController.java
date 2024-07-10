package com.example.parcialfinalpoo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class InsertarClienteController {

    @FXML
    TextField tfClienteNombre;
    @FXML
    TextField tfTelefonoCliente;
    @FXML
    TextField tfDireccionCliente;

    @FXML
    void insertarCliente(){
        Conexion conexion = new Conexion(); //00097923 se manda a llamar al metodo iniciar conexion

        try {
            conexion.iniciarConexion();

            int filasAfectadas = conexion.insertarCliente(tfClienteNombre.getText(),
                    tfDireccionCliente.getText(),
                    tfTelefonoCliente.getText());

            if (filasAfectadas > 0) {
                mostrarMensaje("Insertado","Cliente agregado");
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
