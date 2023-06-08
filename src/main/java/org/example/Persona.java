package org.example;

public class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private String barrio;
    private String DNI;
    private String ocupacion;
    private int numeroKit;
    public Persona(String nombre, String apellido, int edad, String barrio, String DNI, String ocupacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.barrio = barrio;
        this.DNI = DNI;
        this.ocupacion = ocupacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getDNI() {
        return DNI;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public int getNumeroKit() {
        return numeroKit;
    }

    public void setNumeroKit(int numeroKit) {
        this.numeroKit = numeroKit;
    }


}
