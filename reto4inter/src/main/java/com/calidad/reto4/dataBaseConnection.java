package com.calidad.reto4;

import java.sql.*;
import java.util.logging.*;

public class dataBaseConnection {
    String url ="D:/mision mintic 2022/ciclo2_Java/codigosC2/reto4inter/src/main/resources/com/calidad/reto4.CalidadAgua.db";
    Connection connect;

    public void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:"+url);
        } catch (SQLException ex) {
            System.out.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
    }
    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(dataBaseConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}



