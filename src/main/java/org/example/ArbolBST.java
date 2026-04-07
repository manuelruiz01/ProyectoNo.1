package org.example;

import java.util.*;

public class ArbolBST<T> {
    private Nodo<T> raiz;
    private Comparator<T> comparador;
    private long comparacionesInsercion = 0;
    private long comparacionesBusqueda = 0;
    private long comparacionesEliminacion = 0;

    public ArbolBST(Comparator<T> comparador) {
        this.comparador = comparador;
        this.raiz = null;
    }

    public boolean insertar(T valor) {
        if (this.raiz == null) { //primera insersión del arbol (raiz)
            this.raiz = new Nodo<T>(valor);
            return true;
        } else {
            return insertar(valor, this.raiz) != null;
        }
    }

    private Nodo<T> insertar(T valor, Nodo<T> nodo) {
        if (nodo == null) {
            nodo = new Nodo<>(valor);
            return nodo;
        } else if (this.comparador.compare(valor, nodo.dato) == 0) {
            comparacionesInsercion++;
            Nodo<T> aux = this.insertar(valor, nodo.izquierdo);
            if (aux != null) {
                nodo.izquierdo = aux;
            }
            return nodo; //si es repetido lo inserta a la izquierda
        } else if (this.comparador.compare(valor, nodo.dato) < 0) { //menor a padre (izquierda)
            Nodo<T> aux = this.insertar(valor, nodo.izquierdo);
            if (aux != null) { //la insersión es válida
                nodo.izquierdo = aux;
            }
            comparacionesInsercion++;
            return nodo;
        } else { //derecha (mayor a padre)
            Nodo<T> aux = this.insertar(valor, nodo.derecho);
            if (aux != null) {
                nodo.derecho = aux;
            }
            comparacionesInsercion++;

            return nodo;
        }
    }

    //Recorrido inorder
    public List<T> inorder() {
        List<T> result = new ArrayList<>();
        if (this.raiz == null) {
            return result;
        }
        return inorder(this.raiz, result);
    }

    private List<T> inorder(Nodo<T> nodo, List<T> result) {
        if (nodo == null) {
            return result;
        }

        inorder(nodo.izquierdo, result);
        result.add(nodo.dato);
        inorder(nodo.derecho, result);
        return result;
    }



    //Recorrido preorder
    public List<T> preorder() {
        List<T> result = new ArrayList<>();
        if (this.raiz == null) {
            return result;
        }
        return preorder(this.raiz, result);
    }

    private List<T> preorder(Nodo<T> nodo, List<T> result) {
        if (nodo == null) {
            return result;
        }

        result.add(nodo.dato);
        inorder(nodo.izquierdo, result);
        inorder(nodo.derecho, result);
        return result;
    }


    //Recorrido postorder
    public List<T> postorder() {
        List<T> result = new ArrayList<>();
        if (this.raiz == null) {
            return result;
        }
        return postorder(this.raiz, result);
    }

    private List<T> postorder(Nodo<T> nodo, List<T> result) {
        if (nodo == null) {
            return result;
        }

        inorder(nodo.izquierdo, result);
        inorder(nodo.derecho, result);
        result.add(nodo.dato);
        return result;
    }

    //Recorrido level order
    public List<List<T>> levelOrder(){
        if(this.raiz == null){
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        Queue<Nodo<T>> queue = new LinkedList<>();
        queue.offer(this.raiz);
        while(!queue.isEmpty()){
            List<T> nivel = new ArrayList<>();

            int size = queue.size();
            for(int i = 0; i < size; i++){
                Nodo<T> nodo = queue.poll();
                nivel.add(nodo.dato);
                if(nodo.izquierdo != null) queue.offer(nodo.izquierdo);
                if(nodo.derecho != null) queue.offer(nodo.derecho);
            }
            result.add(nivel);
        }
        return result;
    }

    //Minimo
    public T minimo(Nodo<T> nodo) {
        if (nodo == null) {
            return null;
        }

        Nodo<T> actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.dato;
    }

    //Buscar
    private Nodo<T> buscar (Nodo<T> nodo, T valor){
        if(nodo == null){
            return null;
        }

        if(this.comparador.compare(nodo.dato, valor) == 0){ //Encontrado
            comparacionesBusqueda++;
            return nodo;
        }

        if(this.comparador.compare(nodo.dato, valor) < 0){ //no se ha encontrado pero su valor es menor que su padre
            comparacionesBusqueda++;
            return buscar(nodo.derecho, valor);
        }

        return buscar(nodo.izquierdo, valor);
    }

    public T encontrado(T valor){
        if(buscar(this.raiz, valor) != null){
            return buscar(this.raiz, valor).dato;
        }else{
            return null;

        }

    }



    //Eliminar

    private Nodo<T> eliminar(Nodo<T> nodo, T valor) {
        if (nodo == null) {
            return null;
        }

        if (comparador.compare(valor, nodo.dato) == 0) { //se ha encontrado
            if (nodo.izquierdo == null && nodo.derecho == null) { //nodo hoja
                return null; //nuevo valor de nodo (solo se borra)
            }
            if (nodo.derecho == null ) { //solo hay hijo izquierdo
                return nodo.izquierdo; //nuevo valor de nodo (su hijo)
            }

            if (nodo.izquierdo == null ) { //solo hay hijo derecho
                return nodo.derecho;  //nuevo valor de nodo (su hijo)
            }
            // Intercambio entre sucesor inorder y nodo para su eliminación
            T min = minimo(nodo.derecho);  // Sucesor inorder o valor mas bajo del hijo derecho
            nodo.dato = min; //dato recibe min
            nodo.derecho = eliminar(nodo.derecho, min); //eliminación de nodo
            this.comparacionesEliminacion++;
            return nodo;

        }
        if (comparador.compare(valor, nodo.dato) < 0) { //no se ha encontrado pero su valor es menor que su padre
            this.comparacionesEliminacion++;
            nodo.izquierdo = eliminar(nodo.izquierdo, valor);//recorrido a la izquierda
            return nodo;
        }
        //no se ha encontrado pero su valor es mayor que su padre
        this.comparacionesEliminacion++;
        nodo.derecho = eliminar(nodo.derecho, valor); //recorrido a la derecha
        return nodo;
    }


    public boolean eliminado (T valor){
        if(eliminar(this.raiz, valor) != null){
            return true;
        }else {
            return false;
        }
    }

    //Altura
    public int height() {
        return height(raiz);
    }

    private int height(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(height(nodo.izquierdo), height(nodo.derecho));
    }

    //Getters

    public long contadorComparacionesInsert (){
        return comparacionesInsercion;
    }

    public long contadorComparacionesDelete (){
        return comparacionesEliminacion;
    }

    public long contadorComparacionesSearch (){
        return comparacionesBusqueda;
    }

    //Nodos
    private static class Nodo<T> {
        public T dato;
        public Nodo<T> izquierdo;
        public Nodo<T> derecho;

        public Nodo(T dato) {
            this.dato = dato;
        }

    }
}
