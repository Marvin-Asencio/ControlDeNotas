package com.umg.curso.gestiondenotas;

import java.util.ArrayList;

/**
 *
 * @author Marvin Antonio
 */
public class Estudiante {

    private String nombre;
    private double[] calificaciones;

    private static ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public Estudiante(String nombre, double[] calificaciones) {
        this.nombre = nombre;
        this.calificaciones = calificaciones;
    }

    //MÉTODO PARA CALCULAR EL PROMEDIO DE LOS ALUMNOS:
    public double calcularPromedio() {
        double suma = 0;
        for (double calificacion : calificaciones) {
            suma += calificacion;
        }

        return suma / calificaciones.length;
    }

    //MÉTODO QUE MUESTRA LA INFORMACION DEL ESTUDIANTE:
    public String obtenerInfo() {
        return "Nombre: " + nombre + " - Promedio: " + String.format("%.2f", calcularPromedio());
    }

    //MÉTODO PARA AGREGAR UN ESTUDIANTE A LA LISTA:
    public static void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    //MÉTODO PARA OBTENER LOS DATOS DE LOS ESTUDIANTES:
    public static ArrayList<Estudiante> obtenerLista() {
        return estudiantes;
    }

    //MÉTODO PARA EL PROMEDIO GENERAL:
    public static double calcularPromedioGeneral() {
        if (estudiantes.isEmpty()) {
            return 0;
        }
        double sumaTotal = 0;
        for (Estudiante e : estudiantes) {
            sumaTotal += e.calcularPromedio();
        }

        return sumaTotal / estudiantes.size();
    }

    //MÉTODO PARA OBTENER LA NOTA MAS ALTA::
    public static String obtenerNotaMasAlta() {
        if (estudiantes.isEmpty()) {
            return "No hay estudiantes registrados.";
        }

        Estudiante mejor = estudiantes.get(0);

        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() > mejor.calcularPromedio()) {
                mejor = e;
            }
        }

        return "Nota más alta: " + String.format("%.2f", mejor.calcularPromedio()) + " (Estudiante: " + mejor.nombre + ")";
    }

    //MÉTODO PARA OBTENER LA NOTA MAS BAJA:
    public static String obtenerNotaMasBaja() {
        if (estudiantes.isEmpty()) {
            return "No hay estudiantes registrados.";
        }

        Estudiante peor = estudiantes.get(0);

        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() < peor.calcularPromedio()) {
                peor = e;
            }
        }

        return "Nota más baja: " + String.format("%.2f", peor.calcularPromedio()) + " (Estudiante: " + peor.nombre + ")";
    }

}
