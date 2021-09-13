package com.calidad.reto4;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class reto4 {

    dataBaseConnection con = new dataBaseConnection();
    CuerpoDeAgua cda = new CuerpoDeAgua("NA");
    ArrayList<String> motto = new ArrayList<>();
    ArrayList<Double> IRCA = new ArrayList<>();
    double numere = 0;

    public void procesos() throws SQLException {
        con.connect();
        PreparedStatement ps = con.connect.prepareStatement("SELECT * FROM datosCalidadDelAgua");
        ResultSet result = ps.executeQuery();
        motto.clear();
        motto.add(cda.getEntrada());
        while (result.next()){
            IRCA.add(result.getDouble("IRCA"));
            if (Double.parseDouble(result.getString("IRCA")) >= 50) {
                numere = numere + 1;
            }
            if (Double.parseDouble(result.getString("IRCA")) > 35 && Double.parseDouble(result.getString("IRCA")) <= 80){
                motto.add(cda.nivel(result.getString("Nombre")));
            }
            if (motto.size() > 1) {
                motto.remove(0);
            }
        }
        con.close();
    }

    public Double maximo (ArrayList<Double> max) {
        return Collections.max(max);
    }
}
