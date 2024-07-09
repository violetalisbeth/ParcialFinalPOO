package com.example.parcialfinalpoo;

import java.sql.*;

public class Conexion { //00097923 se crea clase conexion para establecer la conexion de la base de datos y reportes
    private Connection conn; // 00097923 Aqui se crea una variable de tipo connection

    protected Conexion(){ //00097923 Este es un constructor vacio

    }
    public void iniciarConexion() throws SQLException{ //00097923 se crea metodo para iniciar conexion a base de datos
        conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-NU1OAIM\\MSSQLSERVER01:4022;databaseName=BCNDataBase;encrypt=false;", //00097923 Se crea conexion a base de datos
                "sa", // 00097923 Aqui se coloca el nombre de usuario que maneja la base
                "Maell"); //00097923 Aqui se coloca la contrasena del usuario

    }
    public void cerrarConexion() throws SQLException{ //00097923 se crea metodo para cerrar onexion
        conn.close(); // 00097923 Se crea conexion
    }

    public ResultSet generarReporteD(String facilitador)throws SQLException{ //00021523 metodo para generacion de reporte D
        PreparedStatement ps = conn.prepareStatement("SELECT C.id, C.nombre, COUNT(CP.id) AS 'Cantidad_compras', SUM(CP.monto) AS 'Total_compras'" + //00021523 consulta a sql. seleccion de columnas a mostrar
                "FROM CLIENTE C " + //00021523 de tabla Cliente
                "INNER JOIN TARJETA T ON C.id = T.id_cliente " + //00021523 Donde id cliente y id_cliente(FK) en tarjeta sean el mismo
                "INNER JOIN FACILITADOR F ON T.id_facilitador = F.id " + //00021523 id facilitador y id_facilitador(FK) en tarjeta sea el mismo
                "INNER JOIN COMPRA CP ON T.id = CP.id_tarjeta " + //00021523 id de tarjeta coincida con id_tarjeta(FK) en compra
                "WHERE F.nombre = ? " + //00021523 WHERE condiciona a solo mostrar el facilitador de interes por medio de su nombre
                "GROUP BY C.id, C.nombre " + //00021523 agrupacion de los resultados en cliente id y nombre
                "ORDER BY COUNT(CP.id) DESC "); //00021523 Ordena lso resultados de forma descendente segun la cantidad de compras

        ps.setString(1, facilitador); //00021523 recibe parametro String donde iria el nombre de facilitador

        ResultSet rs = ps.executeQuery(); //00021523 se encarga de la ejecucion a la consulta sql
        return rs; //00021523 devuelve un ResultSet
    }
}
