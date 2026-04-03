package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataController {

    public void cargarDatos(String ruta) {
        List<Interseccion> intersecciones = new ArrayList();

        try {
            String linea;
            try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                while((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    int id = Integer.parseInt(datos[0]);
                    String distrito = datos[1];
                    String zona = datos[2];
                    String avenida = datos[3];
                    int nivelRiesgo = Integer.parseInt(datos[4]);
                    int nivelCongestion = Integer.parseInt(datos[5]);
                    int sensores = Integer.parseInt(datos[6]);
                    Interseccion interseccion = new Interseccion(id, distrito, zona, avenida, nivelRiesgo, nivelCongestion, sensores);
                    intersecciones.add(interseccion);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        Comparator<Interseccion> ordenPorId = (a,b) -> Integer.compare(a.getId(),b.getId());
        ArbolBST arbolBST = new ArbolBST(ordenPorId);

        //Insertar segun ID
        for (Interseccion interseccion: intersecciones) {
            arbolBST.insertar(interseccion);

        }

        List<Interseccion> interseccionesBST = arbolBST.inorder();
        for (Interseccion interseccion: interseccionesBST) {
            System.out.printf(interseccion.getId() + " ");
        }
        System.out.println();*/


        ArbolNario arbolNario = new ArbolNario("Ciudad");

        //Insertar en el Arbol n-ario
        for(Interseccion interseccion: intersecciones) {
            arbolNario.insertar(interseccion.getDistrito(),interseccion.getZona(),interseccion.getAvenida(),interseccion.getNombre());

        }

        List<List<String>>recorridoPorNivel= arbolNario.levelOrder();
        for(List<String> nivel : recorridoPorNivel) {
            for(String elemento : nivel) {
                System.out.printf(elemento + " ");
            }
            System.out.println();
        }

        System.out.println("Profundidad maxima: " + arbolNario.profunidadMaxima());
        System.out.println(arbolNario.HojasPorDistrito());


    }

}
