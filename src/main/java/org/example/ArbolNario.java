package org.example;

import java.util.*;

public class ArbolNario<T> {
    private NaryNode<T> root;
    private int elementos;


    public ArbolNario(T rootValue) {
        this.root = new NaryNode<>(rootValue);
        this.elementos = 1;

    }


    //Insercion
    public void insertar(T nombreDistrito,T nombreZona, T nombreAvenida, T interseccion ) {
        NaryNode<T> distrito = crear(this.root,nombreDistrito);
        NaryNode<T> zona = crear(distrito,nombreZona);
        NaryNode<T> avenida = crear(zona,nombreAvenida);
        avenida.children.add(new NaryNode<>(interseccion));
    }

    public NaryNode<T> crear(NaryNode<T> padre, T valor) { //Busca en los hijos del nodo padre para crear solo una vez un elemento dentro de otro al haber varias intersecciones en una division urbana
        for(NaryNode<T>hijo: padre.children){
            if(hijo.value.equals(valor)){
                return hijo;
            }
        }
        NaryNode<T> nuevo = new NaryNode<>(valor);
        padre.children.add(nuevo);
        this.elementos++;
        return nuevo;

    }

    //Recorrido por Niveles
    public List<List<T>> levelOrder(){
        List<List<T>> result = new ArrayList<>();
        Queue<NaryNode<T>> queue = new LinkedList<>();
        queue.offer(this.root);

        while(!queue.isEmpty()){
            List<T> level = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++){
                NaryNode<T> node = queue.poll();
                level.add(node.value);

                for(NaryNode<T>child: node.children){
                    queue.offer(child);
                }

            }
            result.add(level);

        }
        return result;

    }

    //Profundidad
    public int profunidadMaxima(){
        return profundidad(this.root);
    }

    private int profundidad(NaryNode<T> node){
        if(node == null) return 0;
        if(node.children.isEmpty()) return 1;
        int profundidadMaxima = 0;
        for(NaryNode<T> child: node.children){
            int profundidadHijo = profundidad(child);
            profundidadMaxima = Math.max(profundidadMaxima, profundidadHijo);
        }

        return profundidadMaxima+1;

    }

    //Cantidad de Hojas
    public String HojasPorDistrito(){
        StringBuilder resultado = new StringBuilder();

        for(NaryNode<T> child: this.root.children){
            int hojas = contarHojas(child);
            resultado.append("Cantidad de intersecciones en " +child.value+":"+hojas+"\n");
        }
        return resultado.toString();
    }

    public int CantidadNodosInternos(){
        return this.elementos-contarHojas(this.root);
    }

    private int contarHojas(NaryNode<T> node){
        if(node == null) return 0;
        if(node.children.isEmpty()) return 1;
        int hojas = 0;
        for(NaryNode<T> child: node.children){
            hojas += contarHojas(child);
        }
        return hojas;

    }



    private static class NaryNode<T> {
        public T value;
        public List<NaryNode<T>> children;

        public NaryNode(T value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }








}
