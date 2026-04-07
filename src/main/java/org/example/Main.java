package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*GeneradorCiudad generadorCiudad = new GeneradorCiudad();
        generadorCiudad.generarDatos(1000);*/


        ManejoDeDatos manejoDeDatos = new ManejoDeDatos();
        manejoDeDatos.cargarDatos("C:\\Users\\manue\\Desktop\\ciudad6.csv");
        manejoDeDatos.cargarArbolBSTporId();
        manejoDeDatos.cargarArbolNario();
        manejoDeDatos.cargarArbolAVLporId();

        manejoDeDatos.cargarArbolBSTporCongestion();

        manejoDeDatos.recorridosBST();
        manejoDeDatos.recorridoArbolNario();

        manejoDeDatos.buscarInterseccionesPorIdBST(1);

        manejoDeDatos.buscarInterseccionesPorCongestionBST(19,137);



    }
}