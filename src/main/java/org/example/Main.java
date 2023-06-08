package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws SinKitsException {
        RegistroPersonas registroPersonas = new RegistroPersonas(10); // Cambia el número de kits disponibles según tus necesidades
        TemperaturaTest temperaturaTest = new TemperaturaTest();
        registroPersonas.setCantidadKitsDisponibles(20);


        // Crear y registrar personas
        Persona persona1 = new Persona("Juan", "Perez", 30, "Barrio A", "123456758", "Empleado");
        Persona persona2 = new Persona("Maria", "Gomez", 40, "Barrio B", "876543241", "Estudiante");
        Persona persona3 = new Persona("Maria", "Gomez", 40, "Barrio B", "876143241", "Estudiante");
        Persona persona4 = new Persona("Maria", "Gomez", 40, "Barrio B", "8765423281", "Estudiante");
        Persona persona5 = new Persona("Maria", "Gomez", 40, "Barrio B", "876543261", "Estudiante");

        // Agregar personas al registro
        registroPersonas.registrarPersona(persona1.getNombre(), persona1.getApellido(), persona1.getEdad(), persona1.getBarrio(), persona1.getDNI(), persona1.getOcupacion(),36.777);
        registroPersonas.registrarPersona(persona2.getNombre(), persona2.getApellido(), persona2.getEdad(), persona2.getBarrio(), persona2.getDNI(), persona2.getOcupacion(),38.45);
        registroPersonas.registrarPersona(persona3.getNombre(), persona3.getApellido(), persona3.getEdad(), persona3.getBarrio(), persona3.getDNI(), persona3.getOcupacion(),35.45);
        registroPersonas.registrarPersona(persona4.getNombre(), persona4.getApellido(), persona4.getEdad(), persona4.getBarrio(), persona4.getDNI(), persona4.getOcupacion(),36.45);
        registroPersonas.registrarPersona(persona5.getNombre(), persona5.getApellido(), persona5.getEdad(), persona5.getBarrio(), persona5.getDNI(), persona5.getOcupacion(),35.35);

        registroPersonas.mostrarPersonas();

        // Realizar test de temperatura
        try {
            temperaturaTest.testear(persona1);
            temperaturaTest.testear(persona2);
            temperaturaTest.testear(persona3);
            temperaturaTest.testear(persona4);
            temperaturaTest.testear(persona5);

            temperaturaTest.aislar(persona1);
            temperaturaTest.aislar(persona2);
            temperaturaTest.aislar(persona3);
        } catch (TemperaturaAltaException e) {
            int numeroTest = e.getNumeroTest();
            String barrio = e.getBarrio();
            System.out.println("Temperatura alta registrada en el test " + numeroTest + " del barrio " + barrio);
            guardarDatosEnArchivo(numeroTest, barrio);
            System.out.println("archivo guardado con exitos");
        }

        // Obtener temperatura por número de kit
        double temperatura1 = temperaturaTest.obtenerTemperatura(persona1.getNumeroKit());
        double temperatura2 = temperaturaTest.obtenerTemperatura(persona2.getNumeroKit());
        double temperatura3 = temperaturaTest.obtenerTemperatura(persona3.getNumeroKit());
        double temperatura4 = temperaturaTest.obtenerTemperatura(persona4.getNumeroKit());
        double temperatura5 = temperaturaTest.obtenerTemperatura(persona5.getNumeroKit());

        System.out.println("La temperatura registrada para el kit " + persona1.getNumeroKit() + " es: " + temperatura1 + " grados");
        System.out.println("La temperatura registrada para el kit " + persona2.getNumeroKit() + " es: " + temperatura2 + " grados");
        System.out.println("La temperatura registrada para el kit " + persona3.getNumeroKit() + " es: " + temperatura3 + " grados");
        System.out.println("La temperatura registrada para el kit " + persona4.getNumeroKit() + " es: " + temperatura4 + " grados");
        System.out.println("La temperatura registrada para el kit " + persona5.getNumeroKit() + " es: " + temperatura5 + " grados");

        GeneradorJSON generadorJSON = new GeneradorJSON();
        generadorJSON.generarJSON(registroPersonas, temperaturaTest);


    }


    private static void guardarDatosEnArchivo(int numeroTest, String barrio) {
        String nombreArchivo = "urgente.dat";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nombreArchivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(numeroTest);
            objectOutputStream.writeObject(barrio);

            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Datos guardados en el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo " + nombreArchivo);
            e.printStackTrace();
        }
    }
}

