package com.example.parcialfinalpoo;

import com.example.parcialfinalpoo.Clases.GastoMes;
import com.example.parcialfinalpoo.Exportar.Exportador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReporteBController {//00351519 Clase para el controller del Reporte B
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    TextField txtIDCliente;//00351519 Textfield para escribir el id del cliente
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    TextField txtMes;//00351519 Textfield para escribir el mes del que se quiere sacar reporte
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    TextField txtAnio;//00351519 Textfield para escribir el anio del que se quiere sacar reporte
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    Button btnGenerarReporte;//00351519 Boton para generar el reporte B
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    Button btnExportarReporte;//00351519 Boton para exportar el reporte B a un archivo txt
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    ListView<GastoMes> lvReporte;//00351519 ListView donde se mostrara la tabla de la consulta sql
    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    public void initialize() {//00351519 metodo que inicializa el ListView
        lvReporte.setCellFactory(param -> new ListCell<GastoMes>() { //00351519: crea un Cell Factoy para la clase GastoMes
            @Override//00351519 Sobreescribe el metodo updateItem
            protected void updateItem(GastoMes item, boolean empty) { //00351519crea metodo updateItem para actualizar filas
                super.updateItem(item, empty);//00351519 Llama metodo de la clase padre y pasa parametros

                if (item == null || empty) { //00351519 condicion que evalua si la lista esta vacia o el item nulo
                    setText(null);//00351519 Se pone el texto como nulo
                    lvReporte.setPlaceholder(new Label("no hubo ninguna compra"));//00351519 Se muestra el mensaje en la ListView
                } else {//00351519 codigo que ejecutara si no se cumple condicion inicial
                    setText("ID: " + item.getId() + ", MES: " + item.getMes() +//00351519 asigna como texto el id y mes
                            ", ANIO: " + item.getAnio() +//00351519 asigna como texto el anio
                            ", GASTO MENSUAL: " + item.getMontoTotal());//00351519 asigna como texto el gasto mensual
                }

            }
        });
    }

    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    public void ongenerarReporteB() {//00351519 Metodo que generara el gasto mensual de un cliente
        ObservableList<GastoMes> resultados =FXCollections.observableArrayList();//00351519 Lista que guarda resultados de consulta sql
        Conexion conexion = new Conexion();//00351519 Se crea un objeto de conexion

        try {//00351519 se inicia un try... catch para capturar cualquier error
            int id = Integer.parseInt(txtIDCliente.getText());//00351519 se obtiene el id del cliente del textfield txtIDCliente
            int idMes = Integer.parseInt(txtMes.getText());//00351519 se obtiene el numero del mes del textfield txtMes
            int idAnio = Integer.parseInt(txtAnio.getText());//00351519 se obtiene el numero del anio del textfield txtAnio
            conexion.iniciarConexion();//00351519 Llama metodo para iniciar conexion
            ResultSet rs = conexion.generarReporteB(id, idMes, idAnio);//00351519 ejecuta la consulta del reporte y se almacena resultados en ResultSet rs
            while (rs.next()) {//00351519  intruccion que recorre los resultados de la consulta
                GastoMes gastoMes = new GastoMes(rs.getInt("id_cliente"),//00351519 se almacena el id en el objeto gastoMes
                        rs.getInt("MES"),//00351519 se almacena el mes en el objeto gastoMes
                        rs.getInt("ANIO"),//00351519 se almacena el anio en el objeto gastoMes
                        rs.getDouble("TOTAL DINERO POR MES"));//00351519 se almacena el gasto mensual en el objeto gastoMes
                resultados.add(gastoMes);//00351519 Agrega resultado a la lista
            }

            rs.close();//00351519 cierra ResultSet rs
            conexion.cerrarConexion();//00351519 llama el metodo para cerrar la conexion
        } catch(SQLException e) {//00351519 captura si hay una excepcion
            throw new RuntimeException(e);//00351519 relanza una excepcion de tipo RuntimeException
        }
        lvReporte.setItems(resultados);//00351519 muestra los resultados de la consulta que se encuentran en la lista
    }

    @FXML//00351519 Para vincular el codigo Java con el objeto de la interfaz
    public void exportarReporteB() {//00351519 metodo para exportar el gasto mensual de un cliente a un archivo txt
        Exportador exportador = new Exportador();//00351519 Crea un objeto de la clase Exportador
        ArrayList<String> lineas = new ArrayList<>();//00351519 ArrayList para guardar linea por linea el reporte al archivo txt
        lineas.add("Total Gastado en el mes");//00351519 agrega encabezado para archivo txt
        if (lvReporte.getItems().isEmpty()){//00351519 condicion para evaluar si la lista esta vacia
            lineas.add("\tEl cliente no realizo compras ese mes");//00351519 si se cumple la condicion, se almacena este mensaje en el archivo txt
        }else {//00351519 codigo que ejecutara si no se cumple condicion inicial
            for (GastoMes gastoMes : lvReporte.getItems()){//00351519 estructura for que recorre contenido de la lista
                String line  = "ID: " + gastoMes.getId() + ", MES: " + gastoMes.getMes() +//00351519 agrega el id del cliente y el mes a un String line
                        ", ANIO: " + gastoMes.getAnio() +//00351519 agrega el anio
                        ", GASTO MENSUAL: " + gastoMes.getMontoTotal();//00351519 agrega el gasto mensual
                lineas.add(line);//00351519 agrega el String line al archivo txt
            }
        }
        try {//00351519 se inicia un try... catch para capturar cualquier error
            exportador.exportarReporte(lineas, "B");//00351519 exporta el archivo con un identificador llamado 'B'
        } catch (IOException e) {//00351519 captura si hay una excepcion
            e.printStackTrace();//00351519 Imprime la excepcion
        }
    }
}

