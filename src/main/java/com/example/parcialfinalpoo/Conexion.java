package com.example.parcialfinalpoo;

import java.sql.*;

public class Conexion { //00097923 se crea clase conexion para establecer la conexion de la base de datos y reportes
    private Connection conn; // 00097923 Aqui se crea una variable de tipo connection

    protected Conexion(){ //00097923 Este es un constructor vacio

    }
    public void iniciarConexion() throws SQLException{ //00097923 se crea metodo para iniciar conexion a base de datos
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BCNDataBase;encrypt=false;integratedSecurity=true;", //00097923 Se crea conexion a base de datos
                "admin", // 00097923 Aqui se coloca el nombre de usuario que maneja la base
                "admin"); //00097923 Aqui se coloca la contrasena del usuario

    }
    public void cerrarConexion() throws SQLException{ //00097923 se crea metodo para cerrar onexion
        conn.close(); // 00097923 Se crea conexion
    }

    public ResultSet generarReporteA(int id, String fechaInicial, String fechaFinal) throws SQLException{ //00097923 se crea metodo para generar el reporte a
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

    public ResultSet generarReporteB(int id, int mes, int anio) throws SQLException {//00351519 Metodo para generar reporte B
        PreparedStatement ps = conn.prepareStatement("SELECT CC.id_cliente, MONTH(C.fecha) AS 'MES', YEAR(C.fecha) AS 'ANIO' ,SUM(monto) AS 'TOTAL DINERO POR MES'\n" +//00351519 Se agrega la consulta sql que se realizara en la base de datos
                "\tFROM COMPRA C \n" +//00351519 Selecciona de la tabla COMPRA
                "\tINNER JOIN COMPRAXCLIENTE CC ON C.id = CC.id_compra\n" +//00351519 une la tabla COMPRA con la tabla COMPRAXCLIENTE cuando el id sea el mismo
                "\tWHERE CC.id_cliente = ? AND MONTH(C.fecha) = ? AND YEAR(C.fecha) = ?\n" +//00351519 realiza consulta cuando se cumplan las condiciones
                "\tGROUP BY CC.id_cliente, MONTH(C.fecha), YEAR(C.fecha);");//00351519 Agrupa resultados por el id cliente, mes y anio de compra

        ps.setInt(1, id);//00351519 parametro id cliente que estara en el ?
        ps.setInt(2, mes);//00351519 parametro mes que estara en el ?
        ps.setInt(3, anio);//00351519 parametro anio que estara en el ?

        ResultSet rs = ps.executeQuery();//00351519 ejecuta la consulta sql
        return rs;//00351519 retorna variable tipo ResultSet
    }

    public ResultSet generarReporteC(int id) throws SQLException {
        //   WHERE c.id = 1;
        PreparedStatement ps = conn.prepareStatement("SELECT t.id, t.numero, t.fecha_expiracion, t.tipo, f.nombre, c.id AS id_cliente" // 00007515: Aqui se coloca la consulta sql, en esta linea se selecciona lo que se va a mostrar
                + " FROM TARJETA t " // 00007515: Aqui se coloca la tabla de de donde vienen algunos elementos del SELECT
                + "INNER JOIN CLIENTE c ON t.id_cliente = c.id " // 00007515: Une CLIENTE con TARJETA para obtener la informacion del cliente
                + "INNER JOIN FACILITADOR f ON t.id_facilitador = f.id " // 00007515: Une TARJETA con FACILITADOR para obtener el nombre de facilitador
                + "WHERE c.id = ? ") // 00007515 Aqui se realiza un where que permite especificar el id del cliente para filtrar las tarjetas por cliente
                ;
        ps.setInt(1, id); // 00007515 Se coloca en el espacio de ? que estara el id

        ResultSet rs = ps.executeQuery(); // 00007515: Aqui se ejecuta la consulta sql
        return rs; // 00007515: Aqui se retorna el resultado
    }
}
