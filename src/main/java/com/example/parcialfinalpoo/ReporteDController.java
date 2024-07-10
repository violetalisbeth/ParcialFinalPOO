package com.example.parcialfinalpoo;

import com.example.parcialfinalpoo.Clases.Cliente;
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

public class ReporteDController { // 00021523 clase de javafx

    @FXML // 00021523 conexion a interfaz
    ListView<Cliente> lvClienteFacilitador; // 00021523 ListView en javafx
    @FXML // 00021523 conexion a interfaz
    TextField tfFacilitador; // 00021523 Text Field que recibe el nombre del facilitador
    @FXML // 00021523 conexion a interfaz
    Button btGenerarReporte;  // 00021523 Boton para generar reporte
    @FXML // 00021523 conexion a interfaz
     Button btExportarR; // 00021523 boton para exportar reporte

    private ObservableList<Cliente> clientes; // 00021523 lista observable de clientes

    @FXML // 00021523 conexion a interfaz
    public void initialize () {  // 00021523 metodo para inicializar vista
        clientes = FXCollections.observableArrayList();  // 00021523 inicializa una lista observable
        lvClienteFacilitador.setItems(clientes);  // 00021523 dentro del ListView recibe la lista observable de clientes

        lvClienteFacilitador.setCellFactory(param -> new ListCell<Cliente>() { // 00021523 creacion de un List cell para clientes
            @Override // 00021523 sobreescribir el metodo
            protected void updateItem(Cliente item, boolean empty) {  // 00021523 creacion de metodo para updateItem
                super.updateItem(item, empty); // 00021523 parametros de clase padre
                if (item == null || empty) { // 00021523 condicional para item vacio o nulo
                    setText(null); // 00021523 texto nulo si no hexiste item
                } else { // 00021523 si la condicion anterior no se cumple, entonces:
                    setText("ID: " + item.getId() + ", Nombre: " + item.getNombre() + // 00021523 muestra informacion del cliente en cada celda
                            ", Compras: " + item.getCantidadCompras() + // 00021523 muestra la cantidad de compras
                            ", Total: " + item.getTotalCompras()); // 00021523 muestra el total de compras
                }
            }
        });
    }

    @FXML // 00021523 conexion a la interfaz
    public void onGenerarReporte(){ // 00021523 al hacer clic en boton de generear reporte hace llamado a este metodo
        clientes.clear(); // 00021523 limpia lista de clientes
        Conexion conexion = new Conexion(); // 00021523 creacion de una nueva conexion

        try{//00351519 se inicia un try... catch para capturar cualquier error
            conexion.iniciarConexion(); // 00021523 inicia conexion

            String facilitadorText = tfFacilitador.getText(); // 00021523 para obtener el texto que se ingreso en el text field
            ResultSet rs = conexion.generarReporteD(facilitadorText); // 00021523 ejecucion de la consulta sql
            while (rs.next()) { // 00021523 itera en los resultados de una consulta con bucle while
               Cliente cliente = new Cliente( // 00021523 se inicia un nuevo Cliente
                       rs.getInt("id"), // 00021523 agrega columna id dentro de Cliente
                       rs.getString("nombre"), // 00021523 agrega columna nombre dentro de cliente
                       rs.getInt("Cantidad_compras"), // 00021523 columna de cantidad de compras dentro de cliente
                       rs.getDouble("Total_compras") // 00021523 columna total de comrpas dentro de cliente
                       );
               clientes.add(cliente); // 00021523 se agregan los resultados a la lista
            }

            rs.close(); // 00021523 cierra la consulta
            conexion.cerrarConexion(); // 00021523 cierra la conexion
        }catch(Exception e) { // 00021523 evalua excepcion
            throw new RuntimeException(e); // 00021523 ejecucoin de runtime Exception si hay una excepcion
        }
    }

    @FXML // 00021523 conexion a interfaz
    public void exportarReporte() { // 00021523 metodo para exportar reporte
        Exportador exportador = new Exportador(); // 00021523 crea un objeto de la clase Exportador
        ArrayList<String> lineas = new ArrayList<>(); // 00021523 crea una lista y guarda linea a linea el texto para guardarlo
        lineas.add("Reporte de Clientes"); // 00021523 da titlo al reporte

        if (clientes.isEmpty()) { // 00021523 condicion si la list view esta vacia
            lineas.add("\tNo hay clientes en la lista."); // 00021523 muestra que está vacía
        } else { // 00021523 si la condicion anterior no se cumple
            for (Cliente cliente : clientes) { // 00021523 itera sobre cada elemento para anadir la informacion a la lista
                String linea = "ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() + // 00021523 agrega nombre y id del cliente
                        ", Compras: " + cliente.getCantidadCompras() + ", Total: " + cliente.getTotalCompras(); // 00021523 agreag cantidad de las compras y el total
                lineas.add(linea); // 00021523 agrega la información del cliente a la lista de líneas
            }
        }

        try { // 00021523 inicio de try catch
            exportador.exportarReporte(lineas, "D"); // 00021523 Se intenta escribir el archivo
        } catch (IOException e) { // 00021523 evalua excepcion
            e.printStackTrace(); // 00021523 Si falla se imprime el error
        }
    }
}
