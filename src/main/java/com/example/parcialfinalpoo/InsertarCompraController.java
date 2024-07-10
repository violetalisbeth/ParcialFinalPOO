package com.example.parcialfinalpoo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class InsertarCompraController {//00351519 controller para insertar una compra

    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    DatePicker dpFechaCompra;//00351519 DatePicker para seleccionar la fecha
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    TextField txtMonto;//00351519 textfield para el monto de la compra
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    TextField txtDescripcion;//00351519 textfield para la descripcion de la compra
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    TextField txtIDTarjeta;//00351519 textfield para el id de la tarjeta

    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    void insertarCompra(){//00351519 metodo para insertar la compra a la tabla
        Conexion conexion = new Conexion(); //00351519 Se crea un objeto de conexion

        try {//00351519 se inicia un try... catch para capturar cualquier error
            conexion.iniciarConexion();//00351519 Llama metodo para iniciar conexion

            int filasAfectadas = conexion.insertarCompra(dpFechaCompra.getValue().toString(),//00351519 inserta fecha de compra a la base de datos
                    Double.parseDouble(txtMonto.getText()),//00351519 inserta monto a la base de datos
                    txtDescripcion.getText(),Integer.parseInt(txtIDTarjeta.getText()));//00351519 inserta la descripcion y el id de tarjeta a la base de datos

            if (filasAfectadas > 0) {//00351519 condicion if que se cumple si el numero de filas afectas es mayor que cero
                mostrarMensaje("Insertado","Compra agregada");//00351519 si la condicion se cumple, se muestra el mensaje de exito
            }

            conexion.cerrarConexion();//00351519 llama el metodo para cerrar la conexion
        } catch (SQLException e) {//00351519 captura si hay una excepcion
            mostrarMensaje("Error", "Error al insertar la compra");//00351519 es hay un error, se muestra la ventana de mensaje de error
            throw new RuntimeException(e);//00351519 relanza una excepcion de tipo RuntimeException
        }

    }
    private void mostrarMensaje(String titulo, String mensaje) {//00351519 funcion para mostrar mensaje de exito o de error
        Alert alert = new Alert(Alert.AlertType.INFORMATION);//00351519 se crea un objeto de tipo INFORMATION para mostrar un mensaje de informacion
        alert.setTitle(titulo);//00351519 establece el titulo de la ventana emergente
        alert.setHeaderText(null);//00351519 establece el encabezado de la ventana emergente
        alert.setContentText(mensaje);//00351519 establece el contenido de texto del mensaje
        alert.show();//00351519 muestra la ventana emergente
    }

}
