package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ManejoDeDatos {
    public List<Interseccion> intersecciones = new ArrayList<>();

    //BST
    public ArbolBST<Interseccion> arbolBSTPorId;
    public ArbolBST<Interseccion> arbolBSTPorCongestion;
    public ArbolBST<Interseccion> arbolBSTPorRiesgo;
    public ArbolBST<Interseccion> arbolBSTPorTiempoReporte;


    //AVL
    public ArbolAVL<Interseccion> arbolAVLPorId;

    //Nario
    public ArbolNario<String> arbolNarioCiudad;

    //Cola de prioridad
    private Comparator<Evento> comparador = (a,b) ->Integer.compare(b.getPrioridad(), a.getPrioridad());
    public ColaDePrioridad<Evento> cola = new ColaDePrioridad<>(comparador);

    //Criterios de insercion
    public Comparator<Interseccion> ordenPorId = (a,b) -> Integer.compare(a.getId(),b.getId());

    public Comparator<Interseccion> ordenPorCongestion = (a,b) -> {
        int orden = Integer.compare(a.getNivelCongestion(),b.getNivelCongestion());
        if(orden == 0) return Integer.compare(a.getId(),b.getId());;
        return orden;
    };

    public Comparator<Interseccion> ordenPorRiesgo = (a,b) -> {
        int orden = Integer.compare(a.getNivelRiesgo(),b.getNivelRiesgo());
        if(orden == 0) return Integer.compare(a.getId(),b.getId());;
        return orden;
    };

    public Comparator<Interseccion> ordenPorTiempoReporte = (a,b) -> {
        int orden = Long.compare(a.getActualizacionReporte(),b.getActualizacionReporte());
        if(orden == 0) return Integer.compare(a.getId(),b.getId());;
        return orden;
    };



    public ManejoDeDatos(){}


    public void cargarDatos(String ruta) {
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
                    int activa  = Integer.parseInt(datos[7]);
                    Interseccion interseccion = new Interseccion(id, distrito, zona, avenida, nivelRiesgo, nivelCongestion, sensores, activa);
                    intersecciones.add(interseccion);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //BST
    public void cargarArbolBSTporId(){
        arbolBSTPorId = new ArbolBST(ordenPorId);
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorId.insertar(interseccion);
        }
    }

    public void cargarArbolBSTporCongestion(){
        arbolBSTPorCongestion= new ArbolBST(ordenPorCongestion);
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorCongestion.insertar(interseccion);
        }
    }

    public void cargarArbolBSTporRiesgo(){
        arbolBSTPorCongestion= new ArbolBST(ordenPorRiesgo);
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorRiesgo.insertar(interseccion);
        }
    }

    public void cargarArbolBSTporTiempoReporte(){
        arbolBSTPorTiempoReporte= new ArbolBST(ordenPorTiempoReporte);
        for (Interseccion interseccion: intersecciones) {
            arbolBSTPorTiempoReporte.insertar(interseccion);
        }
    }



    //Mostrar Recorridos
    private void recorridosBST(ArbolBST<Interseccion> arbolBST){
        System.out.println("Recorrido Inorder ");
        for (Interseccion interseccion: arbolBST.inorder()) {
            System.out.print(interseccion.getId() + " ");
        }
        System.out.println("Recorrido Preorder ");
        for (Interseccion interseccion: arbolBST.preorder()) {
            System.out.print(interseccion.getId() +  " ");
        }
        System.out.println("Recorrido Postorder ");
        for (Interseccion interseccion: arbolBST.postorder()) {
            System.out.print(interseccion.getId() +  " ");
        }
        System.out.println();
        System.out.println("Recorrido LevelOrder ");
        for (List<Interseccion>nivel: arbolBST.levelOrder()) {
            for (Interseccion interseccion: nivel) {
                System.out.print(interseccion.getId() +  " ");
            }
        }

    }

    public void recorridosBST(){
        System.out.println("Recorridos ArbolBST indexado por Ids ");
        recorridosBST(arbolBSTPorId);

        System.out.println("Recorridos ArbolBST indexado por Nivel de congestion ");
        recorridosBST(arbolBSTPorCongestion);

        System.out.println("Recorridos ArbolBST indexado por Nivel de Riesgo");
        recorridosBST(arbolBSTPorRiesgo);

        System.out.println("Recorridos ArbolBST indexado por Tiempo de reporte");
        recorridosBST(arbolBSTPorTiempoReporte);

    }

    //Buscar
    public void buscarInterseccionesPorIdBST(int valor){
        if(arbolBSTPorId.encontrado(new Interseccion(valor, "", "", "", 0, 0, 0,0)) != null){
            System.out.println("Interseccion " + valor + " encontrada");
        }else{
            System.out.println("Interseccion " + valor + " no encontrada");
        }

    }

    public void buscarInterseccionesPorCongestionBST(int congestion, int id){
        if(arbolBSTPorCongestion.encontrado(new Interseccion(id, "", "", "", 0, congestion, 0,0)) != null){
            System.out.println("Interseccion " + id + " encontrada");
        }else{
            System.out.println("Interseccion " + id + " no encontrada");
        }

    }

   //Eliminar
   public void eliminarInterseccionesPorIdBST(int valor){
       if(arbolBSTPorId.eliminado(new Interseccion(valor, "", "", "", 0, 0, 0,0))){
           System.out.println("Interseccion " + valor + " eliminada");
       }else{
           System.out.println("Interseccion " + valor + " no eliminada");
       }

   }


    //AVL
    public void cargarArbolAVLporId(){
        arbolAVLPorId = new ArbolAVL(ordenPorId);
        for (Interseccion interseccion: intersecciones) {
            if(interseccion.isActiva())
                arbolAVLPorId.insert(interseccion);
        }
    }

    //Mostrar Recorridos
    private void recorridosAVL(ArbolAVL<Interseccion> arbolAVL){
        System.out.println("Recorrido Inorder ");
        for (Interseccion interseccion: arbolAVL.inorder()) {
            System.out.print(interseccion.getId() + " ");
        }
        System.out.println("Recorrido Preorder ");
        for (Interseccion interseccion: arbolAVL.preorder()) {
            System.out.print(interseccion.getId() +  " ");
        }
        System.out.println("Recorrido Postorder ");
        for (Interseccion interseccion: arbolAVL.postorder()) {
            System.out.print(interseccion.getId() +  " ");
        }
        System.out.println();
    }

    public void recorridosAVL(){
        System.out.println("Recorridos ArbolBST indexado por Ids ");
        recorridosAVL(arbolAVLPorId);

    }

    //Buscar
    public void buscarInterseccionesPorIdAVL(int valor){
        if(arbolAVLPorId.search(new Interseccion(valor, "", "", "", 0, 0, 0,0))){
            System.out.println("Interseccion " + valor + " encontrada");
        }else{
            System.out.println("Interseccion " + valor + " no encontrada");
        }

    }

    //Eliminar
    public void eliminarInterseccionesPorIdAVL(int valor){
        if(arbolAVLPorId.delete(new Interseccion(valor, "", "", "", 0, 0, 0,0))){
            System.out.println("Interseccion " + valor + " encontrada");
        }else{
            System.out.println("Interseccion " + valor + " no encontrada");
        }
    }



    //N - Ario
    public void cargarArbolNario(){
        arbolNarioCiudad = new ArbolNario("Ciudad");
        for(Interseccion interseccion: intersecciones) {
            arbolNarioCiudad.insertar(interseccion.getDistrito(),interseccion.getZona(),interseccion.getAvenida(),interseccion.getNombre());
        }

    }

    //Mostrar Recorrido level order
    public void recorridoArbolNario(){
        List<List<String>>recorridoPorNivel= arbolNarioCiudad.levelOrder();
        System.out.println("Recorrido arbol nario");
        for(List<String> nivel : recorridoPorNivel) {
            for(String elemento : nivel) {
                System.out.printf(elemento + " ");
            }
            System.out.println();
        }
    }

    //Estadisticas de todos los arboles
    public List<String> estadisticasArboles(){
        List<String> estadisticas = new ArrayList<>();

        StringBuilder  estadisticasArbolNario = new StringBuilder();
        estadisticasArbolNario.append(" Estadisticas arbol Nario: Profundidad maxima: " + arbolNarioCiudad.profunidadMaxima() + " Intersecciones por distrito: " + arbolNarioCiudad.HojasPorDistrito());
        estadisticas.add(estadisticasArbolNario.toString());
        return estadisticas;

    }

    //Cola
    public Evento generarEvento(int id){
        String[] tipos = {"accidente", "trafico", "lluvia", "semaforo descompuesto", "reparaciones", "manifestacion"};
        Random random = new Random();
        int prioridad = random.nextInt(1,101);
        String tipo = tipos[random.nextInt(tipos.length)];
        int interseccion = random.nextInt(1,intersecciones.size());
        return new Evento(id, prioridad, tipo, interseccion);
    }

    public void cargarCola(int cantidadEventos){
        for(int i = 0; i < cantidadEventos; i++){
            cola.insertar(generarEvento(i));
        }
    }

    public void procesarEvento(){
        Evento evento = cola.extraer();
        Interseccion interseccion = arbolBSTPorId.encontrado(new Interseccion(evento.getIdInterseccion(), "", "", "", 0, 0, 0,0));

        if(interseccion == null){return;}

        arbolBSTPorCongestion.eliminado(interseccion);

        switch (evento.getTipo()){
            case "accidente":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 30);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 25);
                break;

            case "trafico":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 15);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 1);
                break;
            case "lluvia":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 15);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 25);
                break;

            case "semaforo descompuesto":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 10);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 25);
                break;

            case "reparaciones":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 10);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 20);
                break;

            case "manifestacion":
                interseccion.setNivelCongestion(interseccion.getNivelCongestion() + 25);
                interseccion.setNivelRiesgo(interseccion.getNivelRiesgo() + 5);
                break;

        }
        interseccion.actualizarReporte();
        arbolBSTPorCongestion.insertar(interseccion);

    }

    public void actualizarPrioridad(){
        Evento evento = cola.peek();
        Evento eventoNuevo = new Evento(evento.getId(),evento.getPrioridad()-10, evento.getTipo(), evento.getId());
        cola.actualizarPrioridad(evento, eventoNuevo);
    }

    //Pasa de ser un Max-Heap a un Min-Heap, de manera que ahora los eventos de menor prioridad se procesan primero
    public void InvertirPrioridad(){
        Comparator<Evento> comparadorNuevo = (a,b) ->Integer.compare(a.getPrioridad(),b.getPrioridad());
        cola.cambiarComparador(comparadorNuevo);
    }



}
