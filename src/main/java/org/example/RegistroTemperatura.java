package org.example;

public class RegistroTemperatura {
    private int DNI;
    private double temperatura;

    public RegistroTemperatura(int DNI, double temperatura) {
        this.DNI = DNI;
        this.temperatura = temperatura;
    }

    public int getDNI() {
        return DNI;
    }

    public double getTemperatura() {
        return temperatura;
    }
}
