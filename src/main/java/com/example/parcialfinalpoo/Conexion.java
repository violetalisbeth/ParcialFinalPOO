package com.example.parcialfinalpoo;

import java.sql.*;

public class Conexion { //00097923 se crea clase conexion para establecer la conexion de la base de datos y reportes
    private Connection conn; // 00097923 Aqui se crea una variable de tipo connection

    protected Conexion(){ //00097923 Este es un constructor vacio

    }
    public void iniciarConexion() throws SQLException{ //00097923 se crea funcion para iniciar conexion a base de datos
        conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-NU1OAIM\\MSSQLSERVER01:4022;databaseName=BCNDataBase;encrypt=false;", //00097923 Se crea conexion a base de datos
                "sa", // 00097923 Aqui se coloca el nombre de usuario que maneja la base
                "Maell"); //00097923 Aqui se coloca la contrasena del usuario

    }
    public void cerrarConexion() throws SQLException{ //00097923 se crea funcion para cerrar onexion
        conn.close(); // 00097923 Se crea conexion
    }

    public ResultSet generarReporteA(int id, String fechaInicial, String fechaFinal) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT c.nombre,com.id, com.descripcion, com.fecha, com.id_tarjeta, com.monto" //00097923 Aqui se coloca la consulta sql, en esta linea se selecciona lo que se va a mostrar
                +" FROM CLIENTE c " //00097923 Aqui se coloca la tabla de de donde vienen algunos elementos del SELECT
                +  "INNER JOIN COMPRAXCLIENTE cc ON cc.id_cliente = c.id " //00097923 Une CLIENTE con COMPRAXCLIENTE para obtener las compras por cliente
                + "INNER JOIN COMPRA com ON cc.id_compra = com.id " //00097923 Une COMPRA con COMPRAXCLIENTE para obtener detalles de las compras
                + "WHERE c.id = ? AND com.fecha BETWEEN ? AND ? " //00097923 Aqui se realiza un where que permite especificar las fechas en las que quiere los registros y el id de quien lo quiere
                + "GROUP BY c.nombre, com.id, com.descripcion, com.fecha, com.id_tarjeta, com.monto "); //00097923 aqui se agrupan los resultados que se seleccionaron
        ;
        ps.setInt(1, id); //00097923 Se coloca en el espacio de ? que estara el id
        ps.setString(2, fechaInicial); //00097923 Se coloca en el espacio de ? que estara la fecha inicial
        ps.setString(3, fechaFinal); //00097923 Se coloca en el espacio de ? que estara la fecha final



        ResultSet rs = ps.executeQuery(); //00097923 Aqui se ejecuta la consulta sql
        return rs; //00097923 retorna variable tipo resultset
    }


    public ResultSet generarReporteD(String facilitador)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT C.id, C.nombre, COUNT(CP.id) AS 'Cantidad_compras', SUM(CP.monto) AS 'Total_compras'" +
                "FROM CLIENTE C " +
                "INNER JOIN TARJETA T ON C.id = T.id_cliente " +
                "INNER JOIN FACILITADOR F ON T.id_facilitador = F.id " +
                "INNER JOIN COMPRA CP ON T.id = CP.id_tarjeta " +
                "WHERE F.nombre = ? " +
                "GROUP BY C.id, C.nombre " +
                "ORDER BY COUNT(CP.id) DESC ");

        ps.setString(1, facilitador);

        ResultSet rs = ps.executeQuery();
        return rs;
    }
}
