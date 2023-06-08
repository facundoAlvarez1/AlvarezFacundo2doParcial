package org.example;

public class TemperaturaAltaException extends Exception{
    private int numeroTest;
    private String barrio;

    public TemperaturaAltaException(int numeroTest, String barrio) {
        this.numeroTest = numeroTest;
        this.barrio = barrio;
    }

    public int getNumeroTest() {
        return numeroTest;
    }

    public String getBarrio() {
        return barrio;
    }
}

