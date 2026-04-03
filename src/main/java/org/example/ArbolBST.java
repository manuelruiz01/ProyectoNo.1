package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class ArbolBST<T> {
    private Nodo<T> raiz;
    private Comparator<T> comparador;

    public ArbolBST(Comparator<T> comparador) {
        this.comparador = comparador;
        this.raiz = null;
    }

    public boolean insertar(T valor) {
        if (this.raiz == null) { //primera insersión del arbol (raiz)
            this.raiz = new Nodo<T>(valor);
            return true;
        } else {
            return this.insertar(valor, this.raiz) != null;
        }
    }

    private Nodo<T> insertar(T valor, Nodo<T> nodo) {
        if (nodo == null) {
            nodo = new Nodo<T>(valor);
            return nodo;
        } else if (this.comparador.compare(valor, nodo.dato) == 0) {
            return null; //repetido
        } else if (this.comparador.compare(valor, nodo.dato) < 0) { //menor a padre (izquierda)
            Nodo<T> aux = this.insertar(valor, nodo.izquierdo);
            if (aux != null) { //la insersión es válida
                nodo.izquierdo = aux;
            }

            return nodo;
        } else { //derecha (mayor a padre)
            Nodo<T> aux = this.insertar(valor, nodo.derecho);
            if (aux != null) {
                nodo.derecho = aux;
            }

            return nodo;
        }
    }

    public List<T> inorder(){
        Nodo<T> aux = this.raiz;
        Stack<Nodo<T>> stack = new Stack<>();
        List<T> resultado = new ArrayList<>();
        while (!stack.isEmpty() || aux != null) {
            while (aux != null){
                stack.push(aux);
                aux = aux.izquierdo;
            }
            aux = stack.pop();
            resultado.add(aux.dato);

            aux = aux.derecho;
        }

        return resultado;

    }

    public Boolean borrar (T valor){
        return true;
    }

    private static class Nodo<T> {
        public T dato;
        public Nodo<T> izquierdo;
        public Nodo<T> derecho;

        public Nodo(T dato) {
            this.dato = dato;
        }

    }
}
