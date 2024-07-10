package com.example.parcialfinalpoo;

import com.example.parcialfinalpoo.Clases.Tarjeta;
import com.example.parcialfinalpoo.Exportar.Exportador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteCController {
    private final List<Tarjeta> tarjetasCredito = new ArrayList<>(); // 00007515: Lista de tarjetas de credito
    private final List<Tarjeta> tarjetasDebito = new ArrayList<>(); // 00007515: Lista de tarjetas de debito
    private ObservableList<Tarjeta> contenidoCredito; // 00007515: Lista observable de tarjetas de credito
    private ObservableList<Tarjeta> contenidoDebito; // 00007515: Lista observable de tarjetas de debito

    @FXML // 00007515: Conecta con la interfaz
    private ListView<Tarjeta> listaCredito; // 00007515: ListView de tarjetas de credito en el archivo fxml

    @FXML // 00007515: Conecta con la interfaz
    private ListView<Tarjeta> listaDebito; // 00007515: ListView de tarjetas de debito en el archivo fxml

    @FXML // 00007515: Conecta con la interfaz
    private TextField idUsuario; // 00007515: TextField para escribir el id de usuario en el archivo fxml

    @FXML
    public void initialize() { // 00007515: Metodo para inicializar la pantalla
        contenidoCredito = FXCollections.observableArrayList(tarjetasCredito); // 00007515: Aqui se guarda el contenido en la lista observable de credito
        contenidoDebito = FXCollections.observableArrayList(tarjetasDebito); // 00007515: Aqui se guarda el contenido en la lista observable de debito
        listaCredito.setItems(contenidoCredito); // 00007515: Asigna el contenido de la lista
        listaDebito.setItems(contenidoDebito); // 00007515: Asigna el contenido de la lista
        listaCredito.setPlaceholder(new Label("No se encontraron tarjetas de credito.")); // 00007515: Pone un Label para cuando la lista de tarjetas este vacia
        listaDebito.setPlaceholder(new Label("No se encontraron tarjetas de debito")); // 00007515: Pone un label para cuando la lista de debito este vacia
        listaCredito.setCellFactory(param -> new ListCell<Tarjeta>() { // 00007515: Se crea un nuevo cell factory para configurar el listview
            @Override
            protected void updateItem(Tarjeta item, boolean empty) { // 00007515: Se crea funcion para actualizar las filas
                super.updateItem(item, empty); // 00007515: Se llama el metodo de la clase padre

                if (item == null || empty) { // 00007515: Si la lista esta vacia o el item nulo, no se pone texto
                    setText(null); // 00007515: Se pone el texto como nulo
                } else {
                    setText(item.toString()); // 00007515: Aqui se asigna el texto usando el toString de la clase Tarjeta
                }
            }
        });

        listaDebito.setCellFactory(param -> new ListCell<Tarjeta>() { // 00007515: Se crea funcion para actualizar las filas
            @Override
            protected void updateItem(Tarjeta item, boolean empty) { // 00007515: Se llama el metodo de la clase padre
                super.updateItem(item, empty); // 00007515: Si la lista esta vacia o el item nulo, no se pone texto

                if (item == null || empty) { // 00007515: Si la lista esta vacia o el item nulo, no se pone texto
                    setText(null); // 00007515: Se pone el texto como nulo
                } else {
                    setText(item.toString()); // 00007515: Aqui se asigna el texto usando el toString de la clase Tarjeta
                }
            }
        });
    }

    @FXML
    public void generarReporte() {
        Conexion conexion = new Conexion(); // 00007515: Se crea un nuevo objeto de conexion
        tarjetasCredito.clear(); // 00007515: Se vacia la lista de credito
        tarjetasDebito.clear(); // 00007515: Se vacia la lista de debito
        listaCredito.setItems(null); // 00007515: Se vacia la lista de credito
        listaDebito.setItems(null); // 00007515: Se vacia la lista de debito
        try {
            int id = Integer.parseInt(idUsuario.getText()); // 00007515: Se convierte el id en el textfield a entero
            conexion.iniciarConexion(); // 00007515: Aqui se inicia la conexion
            ResultSet resultados = conexion.generarReporteC(id); // 00007515: Aqui se ejecuta el generarReporteB de la clase Conexion
            while (resultados.next()) { // 00007515: Aqui se verifica que hayan resultados
                int idTarjeta = Integer.parseInt(resultados.getString("id")); // 00007515: Se saca el id de la tarjeta
                String numero = resultados.getString("numero"); // 00007515: Se saca el numero de tarjeta
                String fechaExpiracion = resultados.getString("fecha_expiracion"); // 00007515: Se saca la fecha de expiracion de tarjeta
                String tipo = resultados.getString("tipo"); // 00007515: Se saca el tipo de tarjeta
                String facilitador = resultados.getString("nombre"); // 00007515: Se saca el facilitador de la tarjeta
                int idCliente = Integer.parseInt(resultados.getString("id_cliente")); // 00007515: Se saca el id de cliente
                Tarjeta tarjeta = new Tarjeta(idTarjeta, numero, fechaExpiracion, tipo, facilitador, idCliente); // 00007515: Se crea una nueva tarjeta

                if (tarjeta.getTipo().matches("DEBITO")) {
                    tarjetasDebito.add(tarjeta); // 00007515: Si la tarjeta es debito se mete en la lista de debito
                } else {
                    tarjetasCredito.add(tarjeta); // 00007515: Si la tarjeta es de credito se mete en la lista de credito
                }
            }

            listaCredito.setItems(FXCollections.observableArrayList(tarjetasCredito)); // 00007515: Se actualiza la lista de credito
            listaDebito.setItems(FXCollections.observableArrayList(tarjetasDebito)); // 00007515: Se actualiza la lista de debito
        } catch(SQLException e) {
            e.printStackTrace(); // 00007515: Se imprime el error si falla
        }
    }

    @FXML
    public void exportarReporte() {
        Exportador exportador = new Exportador(); // 00007515: Se crea un objeto de la clase Exportador
        ArrayList<String> lineas = new ArrayList<>(); // 00007515: Se crea una lista para guardar linea por linea el texto que se va a guardar
        lineas.add("Tarjetas de Credito"); // 00007515: Se anade el titulo para credito
        if (tarjetasCredito.isEmpty()) {
            lineas.add("\tEl usuario no tiene tarjetas de credito."); // 00007515: Si la lista esta vacia, se muestra que esta vacia
        } else {
            for (int i = 0; i < tarjetasCredito.size(); i++) {
                String tarjeta = tarjetasCredito.get(i).toString(); // 00007515: Para cada tarjeta, se usa el toString para sacar la informacion
                lineas.add(tarjeta); // 00007515: Se agrega la tarjeta a la lista de lineas
            }
        }
    }
}
