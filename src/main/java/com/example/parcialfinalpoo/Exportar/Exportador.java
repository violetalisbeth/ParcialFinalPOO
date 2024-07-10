package com.example.parcialfinalpoo.Exportar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Exportador { // 00007515: Creacion de clase Exportador
    public Exportador() {} // 00007515: Definicion de constructor por defecto

    public void exportarReporte(List<String> lista, String letraReporte) throws IOException { // 00007515: Creacion de funcion exportarReporte, recibe una lista de lineas a escribir y la letra del Reporte
        DateFormat formato = new SimpleDateFormat("dd-MM-yyyy-HH_mm_ss"); // 00007515: Crear un simple date format para darle el formato que se pidio en el pdf
        Date fecha = new Date(); // 00007515: Obtener la fecha actual
        String fechaActual = formato.format(fecha); // 00007515: Usar el formato para convertir la fecha a string
        String nombreDeArchivo =  "Reporte-" + letraReporte + "-" + fechaActual + ".txt"; // 00007515: Construir el nombre del archivo
        String directorio = "./src/main/java/com/example/parcialfinalpoo/Reportes/"; // 00007515: String para el directorio donde se va a guardar el reporte
        File archivo = new File(directorio + nombreDeArchivo); // 00007515: Crear un nuevo File usando el directorio y nombre de archivo
        FileWriter escritor = new FileWriter(archivo); // 00007515: Crear una variable escritor para poder escribir en el archivo linea por linea

        for (int i = 0; i < lista.size(); i++) { // 00007515: For para recorrer la lista de lineas una por una
            escritor.write(lista.get(i) + "\n"); // 00007515: Escribir cada linea en el archivo
        }

        escritor.close(); // 00007515: Cerrar el archivo
    }
}
