package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*GeneradorCiudad generadorCiudad = new GeneradorCiudad();
        generadorCiudad.generarDatos(1000);*/

        DataController dataController = new DataController();
        dataController.cargarDatos("C:\\Users\\manue\\Desktop\\ciudad5.csv");

    }
}