package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneradorCiudad {
    public void generarDatos(int interseccionesTotales) {
        Random randominterseccion = new Random();
        Random randomSensor = new Random();
        Random randomRiesgo = new Random();
        Random randomCongestion = new Random();
        int distritos = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.125));
        int zonas = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.25));
        int avenidasPosibles = (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.375));
        int interseccionesPorAvenida = randominterseccion.nextInt(1, (int)Math.ceil(Math.pow((double)interseccionesTotales, (double)0.25)));

        int avenidas = avenidasPosibles +  (int)Math.ceil(Math.pow((double)(interseccionesTotales - distritos*zonas*avenidasPosibles*interseccionesPorAvenida), (double)0.375));

        List<String> intersecciones = new ArrayList();
        List<Integer> ids = new ArrayList();


        for(int i = 1; i <= interseccionesTotales; ++i) {
            ids.add(i);
        }

        //int cont = 0;
        Collections.shuffle(ids);
        int cantidad = interseccionesTotales;

        for(int i = 1; i <= distritos; ++i) {
            for(int j = 1; j <= zonas; ++j) {
                for(int k = 1; k <= avenidas; ++k) {
                    for(int l = 1; l <= interseccionesPorAvenida && cantidad != 0; ++l) {
                        int sensor = randomSensor.nextInt(1, 10);
                        int riesgo = randomRiesgo.nextInt(1, 100);
                        int congestion = randomCongestion.nextInt(1, 100);
                        String id = Integer.toString((Integer)ids.get(cantidad - 1));
                        intersecciones.add(id + ",Distrito" + String.valueOf(i) + ",Zona" + String.valueOf(j) + "," + String.valueOf(k) + "avenida," + String.valueOf(riesgo) + "," + String.valueOf(congestion) + "," + String.valueOf(sensor));
                        --cantidad;
                    }
                }
            }
        }

        for(String linea : intersecciones) {
            System.out.println(linea);
        }

        generarArchivo("C:\\Users\\manue\\Desktop\\ciudad5.csv", intersecciones);
    }

    public void generarArchivo(String ruta, List<String> ciudad) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
                for(String linea : ciudad) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
