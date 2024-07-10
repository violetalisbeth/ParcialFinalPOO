package com.example.parcialfinalpoo;

import com.example.parcialfinalpoo.Clases.Tarjeta;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrarBaseController { // 00007515: Clase BorrarBaseController

    @FXML // 00007515: Conectar la interfaz
    private TextField idCliente; // 00007515: TextField de id cliente

    @FXML // 00007515: Conectar la interfaz
    private TextField idTarjeta; // 00007515: TextField de idTarjeta

    @FXML // 00007515: Conectar la Interfaz
    private TextField idCompra; // 00007515: TextField de idTarjeta

    @FXML// 00007515: Conectar la Interfaz
    public void borrarCliente() { // 00007515: Funcion borrar cliente
        Conexion conexion = new Conexion(); // 00007515: Se crea un nuevo o bjeto de conexion
        int id = Integer.parseInt(idCliente.getText()); // 00007515: Leer el id y convertirlo a entero
        try {//00351519 se inicia un try... catch para capturar cualquier error
            conexion.iniciarConexion(); // 00007515: Iniciar la conexion con la base
            int filasAfectadas = conexion.borrarCliente(id); // 00007515: Tratar de borrar el cliente
            conexion.cerrarConexion(); // 00007515: Cerrar conexion
            if (filasAfectadas > 0) { // 00007515: si alguna fila fue afectada se toma como insercion exitosa
                mostrarMensaje("Borrado","Cliente Borrado"); // 00007515: muestra mensaje que indica que el cliente se borro
            }
        } catch(SQLException e) {//00351519 captura si hay una excepcion
            e.printStackTrace(); // 00007515: Se imprime el error si falla
        }
    }

    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    public void borrarTarjeta() { // 00007515: Funcion borrar tarjeta
        Conexion conexion = new Conexion(); // 00007515: Se crea un nuevo objeto de conexion
        int id = Integer.parseInt(idTarjeta.getText()); // 00007515: Leer el id y convertirlo en entero
        try {
            conexion.iniciarConexion(); // 00007515: Iniciar conexion
            int filasAfectadas = conexion.borrarTarjeta(id); // 00007515: Tratar de borrar la tarjeta
            conexion.cerrarConexion(); // 00007515: Cerrar Conexion
            if (filasAfectadas > 0) { // 00007515: si alguna fila fue afectada se toma como insercion exitosa
                mostrarMensaje("Borrado","Cliente Borrado"); // 00007515: muestra mensaje que indica que el cliente se borro
            }
        } catch(SQLException e) {//00351519 captura si hay una excepcion
            e.printStackTrace(); // 00007515: Se imprime el error si falla
        }
    }

    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    public void borrarCompra() { // 00007515: Funcion borrar Compra
        Conexion conexion = new Conexion(); // 00007515: Se crea un nuevo objeto de conexion
        int id = Integer.parseInt(idCompra.getText()); // 00007515: Leer el id y convertirlo a entero
        try {//00351519 se inicia un try... catch para capturar cualquier error
            conexion.iniciarConexion(); // 00007515: Iniciar la conexion con la base
            int filasAfectadas = conexion.borrarCompra(id); // 00007515: Tratar de borrar la compra
            conexion.cerrarConexion(); // 00007515: Cerrar la conexion
            if (filasAfectadas > 0) { // 00007515: si alguna fila fue afectada se toma como insercion exitosa
                mostrarMensaje("Borrado","Cliente Borrado"); // 00007515: muestra mensaje que indica que el cliente se borro
            }
        } catch(SQLException e) {//00351519 captura si hay una excepcion
            e.printStackTrace(); // 00007515: Se imprime el error si falla
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
