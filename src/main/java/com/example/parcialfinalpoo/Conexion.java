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

    public ResultSet generarReporteC(int id) throws SQLException {//00351519 metodo para generar reporte C
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

    public int insertarTarjeta(String numeroTarjeta, String fechaExpiracion, String tipo, int id_facilitador, int id_cliente) throws SQLException{ //00097923 metodo para insertar tarjetas
        PreparedStatement ps = conn.prepareStatement("INSERT INTO TARJETA VALUES(?,?,?,?,?)"); //00097923 insercion a table
        ps.setString(1, numeroTarjeta); //00097923 toma primer parametro (?) para colocar el numero de tarjeta
        ps.setString(2, fechaExpiracion); //00097923 toma segundo parametro (?) para colocar fecha de expiracion
        ps.setString(3, tipo); //00097923 toma tercer parametro (?) para colocar tipo de tarjeta
        ps.setInt(4, id_facilitador); //00097923 toma cuarto parametro (?) para colocar id facilitador
        ps.setInt(5, id_cliente); //00097923 toma quinto parametro (?) para colocar el id cliente
        return ps.executeUpdate();//00351519 ejecuta la consulta sql y retorna variable tipo ResultSet

    }

    public int insertarCliente(String nombre, String direccion, String telefono) throws SQLException{ //00021523 metodo para insertar clientes
        PreparedStatement ps = conn.prepareStatement("INSERT INTO CLIENTE VALUES(?,?,?)"); //00021523 insercion a la tabla
        ps.setString(1, nombre); //00021523 toma primer parametro (?) para colocar el nombre
        ps.setString(2, direccion); //00021523 toma segundo parametro(?) para direccion
        ps.setString(3, telefono); //00021523 toma tercer parametro(?) para telefono

        return ps.executeUpdate();//00351519 ejecuta la consulta sql y retorna variable tipo ResultSet

    }

    public int insertarCompra(String FechaCompra, double monto, String Descripcion, int idTarjeta) throws SQLException{//00351519 metodo para insertar una compra
        PreparedStatement ps = conn.prepareStatement("INSERT INTO COMPRA VALUES(?,?,?,?)");//00351519  Se agrega la consulta sql que se realizara en la base de datos que insertara datos a la tabla COMPRA
        ps.setString(1, FechaCompra);//00351519 parametro fecha de la compra que estara en el ?
        ps.setDouble(2, monto);//00351519 parametro monto que estara en el ?
        ps.setString(3, Descripcion);//00351519 parametro descripcion que estara en el ?
        ps.setInt(4, idTarjeta);//00351519 parametro id de la tarjeta que estara en el ?

        return ps.executeUpdate();//00351519 ejecuta la consulta sql y retorna variable tipo ResultSet

    }
}