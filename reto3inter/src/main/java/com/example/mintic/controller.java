package com.example.mintic;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class controller {

    ArrayList<String> Nombre = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String> Municipio = new ArrayList<>();
    ArrayList<String> Tagua = new ArrayList<>();
    ArrayList<String> TCagua = new ArrayList<>();
    ArrayList<Double> IRCA = new ArrayList<>();
    ArrayList<String> motto = new ArrayList<>();
    float numere = 0;
    int entra = 0;
    String comprovar = "bien";
    CuerpoDeAgua cda = new CuerpoDeAgua(entra);

    @FXML
    private TextField nombreText;

    @FXML
    private TextField idNum;

    @FXML
    private TextField municipioText;

    @FXML
    private TextField tipoaguaText;

    @FXML
    private TextField tipocuerpoText;

    @FXML
    private TextField ircaNum;

    @FXML
    private TextArea ingresadoExit;
    @FXML
    private TextArea resultadoExit;

    @FXML
    private Label contadorText;

    @FXML
    private Label estadoText;


    public void ingresarButtonOnAction(ActionEvent event) {

        for (int i: id){
            if (Objects.equals(i, Integer.parseInt(idNum.getText()))) {
                contadorText.setText("ID ya Existe");
                comprovar = "mal";
            }
        }

        if (comprovar.equals("bien")){
            entra++;
            contadorText.setText(String.valueOf(entra));
            this.Nombre.add(nombreText.getText());
            this.id.add(Integer.parseInt(idNum.getText()));
            this.Municipio.add(municipioText.getText());
            this.Tagua.add(tipoaguaText.getText());
            this.TCagua.add(tipocuerpoText.getText());
            this.IRCA.add(Double.parseDouble(ircaNum.getText()));
            ingresadoExit.appendText(nombreText.getText()+" "
                    +idNum.getText()+" "
                    +municipioText.getText()+" "
                    +tipoaguaText.getText()+" "
                    +tipocuerpoText.getText()+" "
                    +ircaNum.getText()+"\n");
        }else if (comprovar.equals("mal")){
            comprovar = "bien";
        }

    }

    public void procesarButtonOnAction(ActionEvent event) {
        estadoText.setText("Listo");

        resultadoExit.clear();
        for (double z : IRCA){
            if (z >= 50){
                numere = numere+1;
            }
        }
        motto.clear();
        motto.add("NA");
        for (int i = 0; i < IRCA.size(); i++) {
            if (IRCA.get(i) > 35 && IRCA.get(i) <= 80) {
                motto.add(cda.nivel(i, Nombre));
            }
        }
        if (motto.size() > 1) {
            motto.remove(0);
        }

        DecimalFormat fdos = new DecimalFormat("0.00");
        for (int i = 0; i < Nombre.size(); i++) {
            resultadoExit.appendText(Nombre.get(i) + " " + fdos.format(IRCA.get(i))+"\n");
        }
        resultadoExit.appendText(String.valueOf(fdos.format(numere))+"\n");

        for (String s : motto) {
            resultadoExit.appendText(s + " ");
        }
        resultadoExit.appendText("\n");
        resultadoExit.appendText(String.valueOf(fdos.format(Collections.max(IRCA))));
    }
}