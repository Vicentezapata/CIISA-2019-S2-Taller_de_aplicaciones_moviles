package com.example.gymfitnees;

public class Calculos {
    public Double calcularIMC(String estatura, String peso){
        Double estatura_d = Double.valueOf(estatura);
        Double peso_d = Double.valueOf(peso);
        Double imc = null;
        imc = peso_d/(estatura_d*estatura_d);
        return imc;
    }
}
