package com.example.mintic;

import java.util.ArrayList;

public class CuerpoDeAgua extends ObjetoGeografico {
    public CuerpoDeAgua(int entrada) {
        super(entrada);
    }
    public int getEntrada(){
        return super.getEntrada();
    }

    public String nivel(int i, ArrayList<String> NN) {
        return NN.get(i);
    }
}
