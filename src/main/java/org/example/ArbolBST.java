package org.example;

import java.util.Comparator;

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

    private static class Nodo<T> {
        public T dato;
        private Nodo<T> izquierdo;
        private Nodo<T> derecho;

        public Nodo(T dato) {
            super();
            this.dato = dato;
        }
    }
}
