package org.example;

public class Simulacion {
    public int cantidadIntersecciones;
    public int cantidadEventos;
    public ManejoDeDatos administrador = new ManejoDeDatos();

    public Simulacion(int cantidadIntersecciones, int cantidadEventos) {
        this.cantidadIntersecciones = cantidadIntersecciones;
        this.cantidadEventos = cantidadEventos;
    }

    //public void generarDatos()

    public void ArbolesBinarios(){
        administrador.cargarArbolBSTporId();

    }

}
