package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroPersonas {
    private List<Persona> personas;
    private int cantidadKitsDisponibles = 0;
    private Map<Integer, Double> temperaturasRegistradas;
    private int contadorKits;
    private int numeroKit;
    private int numeroTester;


    public RegistroPersonas(int cantidadKitsDisponibles) {
        this.personas = new ArrayList<>();
        this.cantidadKitsDisponibles = cantidadKitsDisponibles;
        this.contadorKits = 1;
        this.temperaturasRegistradas = new HashMap<Integer, Double>();
    }

    public void registrarPersona(String nombre, String apellido, int edad, String barrio, String DNI, String ocupacion,double temperatura) throws SinKitsException {
        if (cantidadKitsDisponibles <= 0) {
            throw new SinKitsException("No hay kits disponibles. ¿Hay más kits disponibles?");
        }
        if (buscarPersonaPorDNI(DNI) != null) {
            throw new IllegalArgumentException("El DNI ya está registrado.");
        }
        Persona persona = new Persona(nombre, apellido, edad, barrio, DNI, ocupacion);
        persona.setNumeroKit(generarNumeroKit());
        personas.add(persona);
        temperaturasRegistradas.put(contadorKits,temperatura);
        (cantidadKitsDisponibles)--;
        (contadorKits)++;
        for (Persona p : personas) {
            if (p.getNumeroKit() == 0) {
                p.setNumeroKit(contadorKits);
                contadorKits++;
            }
        }
    }

    public void mostrarPersonas() {
        for (Persona persona : personas) {
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Apellido: " + persona.getApellido());
            System.out.println("Edad: " + persona.getEdad());
            System.out.println("Barrio: " + persona.getBarrio());
            System.out.println("DNI: " + persona.getDNI());
            System.out.println("Ocupación: " + persona.getOcupacion());
            System.out.println("Número de kit: " + persona.getNumeroKit());
            System.out.println("----------------------------------");
        }
    }

    private Persona buscarPersonaPorDNI(String DNI) {
        for (Persona persona : personas) {
            if (persona.getDNI().equals(DNI)) {
                return persona;
            }
        }
        return null;
    }

    private int generarNumeroKit() {
        return personas.size() + 1;
    }

    public int getCantidadKitsDisponibles() {
        return cantidadKitsDisponibles;
    }

    public void setCantidadKitsDisponibles(int cantidadKitsDisponibles) {
        this.cantidadKitsDisponibles = cantidadKitsDisponibles;
    }

    public Map<Integer, Persona> getPersonasRegistradas() {
        Map<Integer, Persona> personasRegistradas = new HashMap<>();
        for (Persona persona : personas) {
            personasRegistradas.put(persona.getNumeroKit(), persona);
        }
        return personasRegistradas;
    }
}
