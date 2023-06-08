package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TemperaturaTest {
    private Map<Integer, RegistroTemperatura> tablaTemperaturas;

    public TemperaturaTest() {
        this.tablaTemperaturas = new HashMap<>();
    }

    public void testear(Persona persona) throws TemperaturaAltaException {
        int numeroKit = persona.getNumeroKit();
        double temperatura = obtenerTemperaturaAleatoria(); // Simulación de temperatura aleatoria
        RegistroTemperatura registro = new RegistroTemperatura(numeroKit, temperatura);
        tablaTemperaturas.put(numeroKit, registro);

        if (temperatura > 37.5) {
            throw new TemperaturaAltaException(numeroKit, persona.getBarrio());
        }
    }

    public double obtenerTemperatura(int numeroKit) {
        RegistroTemperatura registro = tablaTemperaturas.get(numeroKit);
        return registro != null ? registro.getTemperatura() : -1;
    }


    public void aislar(Persona persona) throws TemperaturaAltaException {
        double temperatura = obtenerTemperatura(persona.getNumeroKit());
        if (temperatura >= 38) {
            throw new TemperaturaAltaException(persona.getNumeroKit(), persona.getBarrio());
        }
    }

    private double obtenerTemperaturaAleatoria() {
        // Simulación de generación de temperatura aleatoria
        return 36.5 + Math.random() * 2.0; // Temperatura entre 36.5 y 38.5
    }
}

