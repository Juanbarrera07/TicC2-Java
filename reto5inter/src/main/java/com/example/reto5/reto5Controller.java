package com.example.reto5;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;


public class reto5Controller {

    @FXML   private Label alertaText;

    //ingresar Datos
    @FXML    private TextField nombreText;
    @FXML    private TextField idText;
    @FXML    private TextField municipioText;
    @FXML    private TextField tipoAguaText;
    @FXML    private TextField tipoCuerpoText;
    @FXML    private TextField ircaText;
    @FXML    private TextField habitantesText;

    //salida de Datos
    @FXML    private TextArea ircaCUExit;
    @FXML    private TextField numeroRiesgoExit;
    @FXML    private TextArea nombreRiesgoExit;
    @FXML    private TextField promedioIRCAExit;
    @FXML    private TextArea afectacionExit;
    @FXML    private TextArea historialExit;

    //datos de paso
    DecimalFormat fdos = new DecimalFormat("0.00");
    ArrayList<Integer> idprueva = new ArrayList<>();
    String comprovar = "bien";
    int numcuerpos = 0;
    Double sumairca = 0.0;
    int total = 0;
    int stado = 0;

    @FXML
    void ingresarButton(ActionEvent event) {
        try {
            //conectar classes
            DensidadPoblacional dp = new DensidadPoblacional(nombreText.getText(),
                    municipioText.getText(),
                    tipoAguaText.getText(),
                    tipoCuerpoText.getText(),
                    Double.parseDouble(ircaText.getText()),
                    Integer.parseInt(habitantesText.getText()));
            CuerpoDeAgua cda = new CuerpoDeAgua(nombreText.getText(),
                    municipioText.getText(),
                    tipoAguaText.getText(),
                    tipoCuerpoText.getText(),
                    Double.parseDouble(ircaText.getText()),
                    Integer.parseInt(habitantesText.getText()));
            //no repetir id
            for (int i: idprueva){
                if (Objects.equals(i, Integer.parseInt(idText.getText()))) {
                    alertaText.setText("ID ya Existe");
                    comprovar = "mal";
                }
            }
            //continuar si id no existe
            if (comprovar.equals("bien")){
                //valores id nuevos
                idprueva.add(Integer.parseInt(idText.getText()));
                //vista valores totales ingresados
                historialExit.appendText(nombreText.getText()+" "
                        +idText.getText()+" "
                        +municipioText.getText()+" "
                        +tipoAguaText.getText()+" "
                        +tipoCuerpoText.getText()+" "
                        +ircaText.getText()+" "
                        +habitantesText.getText()+"\n");
                alertaText.setText("Datos Ingresados");
                //vista valores IRCA ingresados
                ircaCUExit.appendText(fdos.format(cda.getIRCA())+"\n");
                //vista numeros de cuerpos valores medio o menor
                if (cda.getIRCA() <= 35){
                    numcuerpos += 1;
                }
                numeroRiesgoExit.clear();
                numeroRiesgoExit.appendText(String.valueOf(numcuerpos));
                //vista nombre nivel de riesgo solo medio
                if (!Objects.equals(cda.nivel(cda.getIRCA(), cda.getNombre()), "")){
                    if  (Objects.equals(stado,3)){
                        stado = 1;
                        nombreRiesgoExit.clear();
                        nombreRiesgoExit.appendText(cda.nivel(cda.getIRCA(),cda.getNombre())+" ");
                    }else {
                        stado = 2;
                        nombreRiesgoExit.appendText(cda.nivel(cda.getIRCA(),cda.getNombre())+" ");
                    }
                }else if (!Objects.equals(stado,1)&&!Objects.equals(stado,2)){
                    if (Objects.equals(cda.nivel(cda.getIRCA(), cda.getNombre()), "")){
                        stado = 3;
                        nombreRiesgoExit.clear();
                        nombreRiesgoExit.appendText("NA");
                    }
                }
                System.out.println(stado);
                //vista promedio de los valores totales IRCA
                total += 1;
                sumairca += cda.getIRCA();
                promedioIRCAExit.clear();
                promedioIRCAExit.appendText(String.valueOf(fdos.format(sumairca/total)));
                //vista nivel de afeccion por num habitantes
                afectacionExit.appendText(String.valueOf(dp.afeccion(dp.getPoblacion()))+"\n");
            }else if (comprovar.equals("mal")){ //cambio del valor de id repetido para continuar
                comprovar = "bien";
            }
        //por si existe error en el codigo
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    //boton de salir
    @FXML
    void salirButton(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}