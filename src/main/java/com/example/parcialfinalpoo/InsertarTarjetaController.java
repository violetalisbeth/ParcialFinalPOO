package com.example.parcialfinalpoo;

import com.example.parcialfinalpoo.Clases.Tarjeta;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class InsertarTarjetaController { //00097923 clase para insertar tarjeta
    @FXML  //00097923 para conectar con la interfaz
    TextField tfNumeroTarjeta; //00097923 agregando tfNumeroTarjeta
    @FXML  //00097923 para conectar con la interfaz
    DatePicker dpFechaExpiracion; //00097923 agregando dpFechaExpiracion
    @FXML  //00097923 para conectar con la interfaz
    TextField tfTipo; //00097923 agregando tfTipo
    @FXML  //00097923 para conectar con la interfaz
    TextField tfIDFacilitador; //00097923 agregando tfIDFacilitador a javafx
    @FXML  //00097923 para conectar con la interfaz
    TextField tfIDCliente; //00097923 agregando tfIDCliente


    @FXML  //00097923 para conectar con la interfaz
    void insertarTarjeta(){ //00097923 metodo para insertar tarjeta
        Conexion conexion = new Conexion(); //00097923 se manda a llamar al metodo iniciar conexion

        try { // iniciando un try catch
            conexion.iniciarConexion(); //00097923 e manda a llamar al metodo iniciar conexion


            int filasAfectadas = conexion.insertarTarjeta(tfNumeroTarjeta.getText(), //00097923 guarda en una variable int lo que devuelva el metodo insertar tarjeta
                    dpFechaExpiracion.getValue().toString(), //00097923 toma como parametro la fecha escrita en la interfaz
                    tfTipo.getText(), //00097923 toma como parametro el tipo de tarjeta escrito en la interfaz
                    Integer.parseInt(tfIDFacilitador.getText()), //00097923 toma como parametro el id del facilitador escrito en la interfaz
                    Integer.parseInt(tfIDCliente.getText())); //00097923 toma como parametro el id del cliente escrito en la interfaz

            if (filasAfectadas > 0) { //00097923 evalua si han habido inserciones
                mostrarMensaje("Insertado","Tarjeta insertada"); //00097923 llama al metodo mostrar mensaje y pasa parametros de lo que va a mostrar si se cumple
            }

            conexion.cerrarConexion(); //00097923 cierra la conexion
        } catch (SQLException e) { //00097923 evalua en el catch si existe un error
            mostrarMensaje("Error", "Error al insertar tarjeta"); //00097923 si existe un error muestra este mensaje
            throw new RuntimeException(e); //00097923 si hay una excepcion se ejecuta el runtime exception
        }

    }
    private void mostrarMensaje(String titulo, String mensaje) { //00097923 se crea metodo para mostrar mensaje
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //00097923 se crea un objeto tipo alerta
        alert.setTitle(titulo); //00097923 agrega titulo a la alerta
        alert.setHeaderText(null); //00097923 hace que la alerta no tenga header
        alert.setContentText(mensaje); //00097923 escribe el mensaje en la alerta
        alert.show(); //00097923 muestra la alerta
    }


}
