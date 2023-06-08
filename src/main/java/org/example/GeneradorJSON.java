package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class GeneradorJSON {
    public void generarJSON(RegistroPersonas registroPersonas, TemperaturaTest temperaturaTest) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();

        ArrayNode personasSanasNode = rootNode.putArray("sanos");
        ArrayNode casosSospechososNode = rootNode.putArray("aislar");

        // Obtener las personas registradas
        Map<Integer, Persona> personasRegistradas = registroPersonas.getPersonasRegistradas();
        // Recorrer las personas y agregarlas al arreglo correspondiente
        for (Persona persona : personasRegistradas.values()) {
            int numeroKit = persona.getNumeroKit();
            double temperatura = temperaturaTest.obtenerTemperatura(numeroKit);
            String barrio = persona.getBarrio();

            ObjectNode personaNode = objectMapper.createObjectNode();
            personaNode.put("nombre", persona.getNombre());
            personaNode.put("apellido", persona.getApellido());
            personaNode.put("edad", persona.getEdad());
            personaNode.put("barrio", persona.getBarrio());
            personaNode.put("DNI", persona.getDNI());
            personaNode.put("ocupacion", persona.getOcupacion());

            if (temperatura <= 38.0) {
                personasSanasNode.add(personaNode);
            } else {
                ObjectNode casoSospechosoNode = objectMapper.createObjectNode();
                casoSospechosoNode.put("kit", numeroKit);
                casoSospechosoNode.put("temperatura", temperatura);
                casoSospechosoNode.put("barrio", barrio);

                casosSospechososNode.add(casoSospechosoNode);
            }
        }

        try {
            objectMapper.writeValue(new File("datos.json"), rootNode);
            System.out.println("JSON generado y guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar y guardar el JSON.");
            e.printStackTrace();
        }
    }
}
