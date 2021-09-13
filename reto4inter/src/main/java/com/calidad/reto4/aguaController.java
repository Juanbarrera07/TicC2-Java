package com.calidad.reto4;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

public class aguaController {

    //alertas
    @FXML
    private Label ingresarText;
    @FXML
    private Label obtenerText;
    @FXML
    private Label editarText;

    //proceso celdas ingresar
    @FXML
    private TextField nombreText;
    @FXML
    private TextField idNum;
    @FXML
    private TextField municipioText;
    @FXML
    private TextField aguaText;
    @FXML
    private TextField cuerpoText;
    @FXML
    private TextField ircaNum;
    @FXML
    private TextField buscarText;
    @FXML
    private TextField nombreEEText;
    @FXML
    private TextField idEENum;
    @FXML
    private TextField municipioEEText;
    @FXML
    private TextField aguaEEText;
    @FXML
    private TextField cuerpoEEText;
    @FXML
    private TextField ircaEENum;

    //salidas
    @FXML
    private TextArea datosEntradaExit;
    @FXML
    private TextArea resultadoExit;

    //imagenes
    @FXML
    private ImageView ingresarArrow;
    @FXML
    private ImageView procesarArrow;
    @FXML
    private ImageView editelimArrow;

    //entornos
    @FXML
    private AnchorPane ingresarPanel;
    @FXML
    private AnchorPane procesarPanel;
    @FXML
    private AnchorPane editelimPanel;

    //pestaña ingresar
    @FXML
    public void onIngresarButton(MouseEvent event) {
        if (ingresarPanel.isVisible()){
            ingresarPanel.setVisible(false);
            ingresarArrow.setVisible(false);
            return;
        }
        ingresarPanel.setVisible(true);
        ingresarArrow.setVisible(true);

        procesarPanel.setVisible(false);
        procesarArrow.setVisible(false);
        editelimPanel.setVisible(false);
        editelimArrow.setVisible(false);
        nombreText.clear();
        idNum.clear();
        municipioText.clear();
        aguaText.clear();
        cuerpoText.clear();
        ircaNum.clear();
        ingresarText.setText(" ");
        obtenerText.setText(" ");
        editarText.setText(" ");
    }
    //prestaña procesar
    @FXML
    public void onProcesarButton(MouseEvent event) {
        if (procesarPanel.isVisible()){
            procesarPanel.setVisible(false);
            procesarArrow.setVisible(false);
            return;
        }
        procesarPanel.setVisible(true);
        procesarArrow.setVisible(true);

        editelimPanel.setVisible(false);
        editelimArrow.setVisible(false);
        ingresarPanel.setVisible(false);
        ingresarArrow.setVisible(false);
        datosEntradaExit.clear();
        resultadoExit.clear();
        ingresarText.setText(" ");
        obtenerText.setText(" ");
        editarText.setText(" ");
    }
    //prestaña eliminar / editar
    @FXML
    public void onEditElimButton(MouseEvent event) {
        if (editelimPanel.isVisible()){
            editelimPanel.setVisible(false);
            editelimArrow.setVisible(false);
            return;
        }
        editelimPanel.setVisible(true);
        editelimArrow.setVisible(true);

        procesarPanel.setVisible(false);
        procesarArrow.setVisible(false);
        ingresarPanel.setVisible(false);
        ingresarArrow.setVisible(false);
        nombreEEText.clear();
        idEENum.clear();
        municipioEEText.clear();
        aguaEEText.clear();
        cuerpoEEText.clear();
        ircaEENum.clear();
        ingresarText.setText(" ");
        obtenerText.setText(" ");
        editarText.setText(" ");
    }

    //procesos ingresar
    dataBaseConnection con = new dataBaseConnection();
    String comprovar = "bien";

    @FXML
    public void ingresarButton (MouseEvent event){
        try {
            con.connect();
            PreparedStatement pss = con.connect.prepareStatement("SELECT * FROM datosCalidadDelAgua WHERE ID = ?");
            try (pss){
                pss.setInt(1,Integer.parseInt(idNum.getText()));
                ResultSet result = pss.executeQuery();
                if(result.next()){
                    comprovar = "mal";
                }
            }
            con.close();
            if (comprovar.equals("bien")){
                con.connect();
                PreparedStatement ps = con.connect.prepareStatement("INSERT INTO datosCalidadDelAgua VALUES (?,?,?,?,?,?)");
                ps.setString(1,nombreText.getText());
                ps.setInt(2,Integer.parseInt(idNum.getText()));
                ps.setString(3,municipioText.getText());
                ps.setString(4,aguaText.getText());
                ps.setString(5,cuerpoText.getText());
                ps.setDouble(6,Double.parseDouble(ircaNum.getText()));
                ps.execute();
                con.close();
                ingresarText.setText("Datos Ingresados");
            } else if (comprovar.equals("mal")) {
                comprovar = "bien";
                ingresarText.setText("El ID Existe");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    //proceso obtener/procesar
    @FXML
    public void obtenerButton(MouseEvent event){
        try {
            datosEntradaExit.clear();
            con.connect();
            PreparedStatement ps = con.connect.prepareStatement("SELECT * FROM datosCalidadDelAgua");
            ResultSet result = ps.executeQuery();
            while (result.next()){
                datosEntradaExit.appendText(result.getString("Nombre")+" "
                        +result.getString("ID")+" "
                        +result.getString("Municipio")+" "
                        +result.getString("Tipo_agua")+" "
                        +result.getString("Tipo_cuerpo")+" "
                        +result.getString("IRCA")+"\n");
            }
            con.close();
            obtenerText.setText("Datos Obtenidos");
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    Double maxirca = 0.0;
    @FXML
    public void procesarButton(MouseEvent event){
        try {
            reto4 reto = new reto4();
            resultadoExit.clear();
            reto.procesos();
            DecimalFormat fdos = new DecimalFormat("0.00");
            con.connect();
            PreparedStatement ps = con.connect.prepareStatement("SELECT * FROM datosCalidadDelAgua");
            ResultSet result = ps.executeQuery();
            while (result.next()){
                resultadoExit.appendText(result.getString("Nombre")+" "+fdos.format(Double.parseDouble(result.getString("IRCA")))+"\n");
            }
            resultadoExit.appendText(String.valueOf(fdos.format(reto.numere))+"\n");
            for (String s: reto.motto){
                resultadoExit.appendText(s + " ");
            }
            resultadoExit.appendText("\n");
            resultadoExit.appendText(fdos.format(reto.maximo(reto.IRCA)));
            con.close();
            obtenerText.setText("Datos Procesados");
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    //proceso buscar
    @FXML
    public void buscarButton(MouseEvent event){
        try {
            datosEntradaExit.clear();
            con.connect();
            if (con != null) {
                PreparedStatement ps = con.connect.prepareStatement("SELECT * FROM datosCalidadDelAgua WHERE ID='"+buscarText.getText()+"'");
                try (ps){
                    ResultSet result = ps.executeQuery();
                    if (result.next()){
                        nombreEEText.setText(result.getString("Nombre"));
                        idEENum.setText(String.valueOf(result.getInt("ID")));
                        municipioEEText.setText(result.getString("Municipio"));
                        aguaEEText.setText(result.getString("Tipo_agua"));
                        cuerpoEEText.setText(result.getString("Tipo_cuerpo"));
                        ircaEENum.setText(String.valueOf(result.getString("IRCA")));
                        editarText.setText("Datos Encontrados");
                    } else {
                        nombreEEText.clear();
                        idEENum.clear();
                        municipioEEText.clear();
                        aguaEEText.clear();
                        cuerpoEEText.clear();
                        ircaEENum.clear();
                        editarText.setText("No Existen Datos");
                    }
                }
                con.close();
            }
        }catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    //proceso editar
    @FXML
    public void editarButton (MouseEvent event) {
        try {
            con.close();
            con.connect();
            PreparedStatement ps = con.connect.prepareStatement("UPDATE datosCalidadDelAgua " +
                    "SET Nombre ='"+nombreEEText.getText()+"',"+
                    "ID ='"+idEENum.getText()+"',"+
                    "Municipio ='"+municipioEEText.getText()+"',"+
                    "Tipo_agua ='"+aguaEEText.getText()+"',"+
                    "Tipo_cuerpo ='"+cuerpoEEText.getText()+"',"+
                    "IRCA ='"+ircaEENum.getText()+"' "+
                    "WHERE ID='"+buscarText.getText()+"' ");
            ps.executeUpdate();
            editarText.setText("Datos Modificados");
            nombreEEText.clear();
            idEENum.clear();
            municipioEEText.clear();
            aguaEEText.clear();
            cuerpoEEText.clear();
            ircaEENum.clear();
            con.close();
        }catch (Exception ex) {
            System.err.println(ex.getMessage());
            editarText.setText("El ID Existe");
        }
    }
    //proceso eliminar
    @FXML
    public void eliminarButton (MouseEvent event) {
        try {
            con.connect();
            PreparedStatement ps = con.connect.prepareStatement(
                    "DELETE FROM datosCalidadDelAgua WHERE ID='"+buscarText.getText()+"'");
            ps.executeUpdate();
            editarText.setText("Datos Eliminados");
            nombreEEText.clear();
            idEENum.clear();
            municipioEEText.clear();
            aguaEEText.clear();
            cuerpoEEText.clear();
            ircaEENum.clear();
            con.close();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    //boton de cerrar programa
    @FXML
    public void onExitButton(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }
}