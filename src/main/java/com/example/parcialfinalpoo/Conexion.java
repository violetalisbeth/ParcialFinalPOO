package com.example.parcialfinalpoo;

import java.sql.*;

public class Conexion { //00097923 se crea clase conexion para establecer la conexion de la base de datos y reportes
    private Connection conn; // 00097923 Aqui se crea una variable de tipo connection

    protected Conexion(){ //00097923 Este es un constructor vacio

    }
    public void iniciarConexion() throws SQLException{ //00097923 se crea metodo para iniciar conexion a base de datos
        conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-NU1OAIM\\\\MSSQLSERVER01:4022;databaseName=BCNDataBase;encrypt=false;", //00097923 Se crea conexion a base de datos
                "sa", // 00097923 Aqui se coloca el nombre de usuario que maneja la base
                "Maell"); //00097923 Aqui se coloca la contrasena del usuario

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

    public ResultSet generarReporteB(int id) throws SQLException {
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
    public int insertarTarjeta(String numeroTarjeta, String fechaExpiracion, String tipo, int id_facilitador, int id_cliente) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO TARJETA VALUES(?,?,?,?,?)");
        ps.setString(1, numeroTarjeta);
        ps.setString(2, fechaExpiracion);
        ps.setString(3, tipo);
        ps.setInt(4, id_facilitador);
        ps.setInt(5, id_cliente);

        return ps.executeUpdate();

    }

    public int insertarCliente(String nombre, String direccion, String telefono) throws SQLException{ //00021523 metodo para insertar clientes
        PreparedStatement ps = conn.prepareStatement("INSERT INTO CLIENTE VALUES(?,?,?)"); //00021523 insercion a la tabla
        ps.setString(1, nombre); //00021523 toma primer parametro (?) para colocar el nombre
        ps.setString(2, direccion); //00021523 toma segundo parametro(?) para direccion
        ps.setString(3, telefono); //00021523 toma tercer parametro(?) para telefono

        return ps.executeUpdate();

    }
}
