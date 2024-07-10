package com.example.parcialfinalpoo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class InsertarClienteController { //00021523 clase de javafx

    @FXML //00021523 conexion a interfaz
    TextField tfClienteNombre; //00021523 text field para nombre del cliente
    @FXML //00021523 conexion a interfaz
    TextField tfTelefonoCliente; //00021523 text field para telefono del cliente
    @FXML //00021523 conexion a interfaz
    TextField tfDireccionCliente; //00021523 text field para direccion del cliente

    @FXML
    void insertarCliente(){ //00021523 metodo de insercion de cliente
        Conexion conexion = new Conexion(); //00021523 llama al metodo iniciar conexion

        try {
            conexion.iniciarConexion(); //00021523 inicia conexion a la base

            int filasAfectadas = conexion.insertarCliente(tfClienteNombre.getText(), //00021523 inserta en la base al cliente usando los valores de los textfield
                    tfDireccionCliente.getText(), //00021523 inserta direccion
                    tfTelefonoCliente.getText()); //00021523 inserta telefono

            if (filasAfectadas > 0) { //00021523 si alguna fila fue afectada se toma como insercion exitosa
                mostrarMensaje("Insertado","Cliente agregado"); //00021523 muestra mensaje que indica que el cliente se agrego
            }

            conexion.cerrarConexion(); //00021523 cierra la conexion a la base
        } catch (SQLException e) { //00021523 evalua excepcion
            mostrarMensaje("Error", "Error al insertar el cliente"); //00021523 muestra mensaje de error
            throw new RuntimeException(e); //00021523 si hay excepcion se ejecuta un run time exception
        }

    }
    private void mostrarMensaje(String titulo, String mensaje) { //00021523 metodo para mostrar mensaje de alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //00021523 nueva alerta INFORMACION
        alert.setTitle(titulo); //00021523 titulo para la alerta
        alert.setHeaderText(null); //00021523 contenido del mensaje sin encabezado
        alert.setContentText(mensaje); //00021523 texto del mensaje
        alert.show(); //00021523 muestra la alerta
    }

}
