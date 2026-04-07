package org.example;

import java.util.*;

public class ArbolAVL<T> {
    private final Comparator<T> comparator;
    private int rotaciones = 0;

    private Node<T> node; //raiz
    private int size; //cantidad total de nodos

    private int comparacionesBusqueda = 0;
    private int comparacionesEliminacion = 0;
    private int comparacionesInsercion = 0;

    public ArbolAVL(Comparator<T> comparator) {
        this.comparator = comparator;
        this.node = null;
        this.size = 0;
    }


    //Insercion
    public void insert(T value) {
        this.node = insert(node, value);
    }

    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            this.size++;
            return new Node<>(value);
        }

        int compare = comparator.compare(value, root.value);

        if (compare < 0) {
            comparacionesInsercion++;
            root.left = insert(root.left, value);
        }
        else if (compare > 0) {
            comparacionesInsercion++;
            root.right = insert(root.right, value);
        } else {
            return root;
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int balance = getBalance(root);

        if (balance < -1 && comparator.compare(value, root.left.value) > 0) {
            comparacionesInsercion++;
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        if (balance > 1 && comparator.compare(value, root.right.value) < 0) {
            comparacionesInsercion++;
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        if (balance < -1) {
            return rotateRight(root);
        }

        if (balance > 1) {
            return rotateLeft(root);
        }

        return root;
    }

    //Rotaciones
    private Node<T> rotateLeft(Node<T> root) {
        if (root == null) return null;

        Node<T> newRoot = root.right; //nuevo root
        root.right = newRoot.left;    // los hijos derechos del sucesor pasan al predecesor
        newRoot.left = root;          // el sucesor se vuelve padre del predecesor

        updateHeight(root);
        updateHeight(newRoot);
        rotaciones++;
        return newRoot;
    }

    private Node<T> rotateRight(Node<T> root) {
        if (root == null) return null;

        Node<T> newRoot = root.left; //nuevo root
        root.left = newRoot.right;  // los hijos derechos del sucesor pasan al predecesor
        newRoot.right = root;       // el sucesor se vuelve padre del predecesor

        updateHeight(root);
        updateHeight(newRoot);
        rotaciones++;
        return newRoot;
    }

    //Balance
    private int getBalance(Node<T> root) {
        if (root == null)
            return 0;

        return getHeight(root.right) - getHeight(root.left);
    }

    //Altura
    private void updateHeight(Node<T> root) {
        if (root == null) return;

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private int getHeight(Node<T> root) {
        if (root == null)
            return 0;

        return root.height;
    }

    public int height() {
        if (this.node == null)
            return 0;
        return this.node.height;
    }

    //Tamaño
    public int size() {
        return this.size;
    }

    //Recorrido inorder
    public List<T> inorder() {
        List<T> result = new ArrayList<>();
        if (this.node == null) {
            return result;
        }
        return inorder(this.node, result);
    }

    private List<T> inorder(Node<T> node, List<T> result) {
        if (node == null) {
            return result;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
        return result;
    }



    //Recorrido preorder
    public List<T> preorder() {
        List<T> result = new ArrayList<>();
        if (this.node == null) {
            return result;
        }
        return preorder(this.node, result);
    }

    private List<T> preorder(Node<T> node, List<T> result) {
        if (node == null) {
            return result;
        }

        result.add(node.value);
        inorder(node.left, result);
        inorder(node.right, result);
        return result;
    }


    //Recorrido postorder
    public List<T> postorder() {
        List<T> result = new ArrayList<>();
        if (this.node == null) {
            return result;
        }
        return postorder(this.node, result);
    }

    private List<T> postorder(Node<T> node, List<T> result) {
        if (node == null) {
            return result;
        }

        inorder(node.left, result);
        inorder(node.right, result);
        result.add(node.value);
        return result;
    }

    //Recorrido level order
    public List<List<T>>levelOrder(){
        if(this.node == null){
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(this.node);
        while(!queue.isEmpty()){
            List<T> nivel = new ArrayList<>();

            int size = queue.size();
            for(int i = 0; i < size; i++){
                Node<T> aux = queue.poll();
                nivel.add(aux.value);
                if(aux.left != null) queue.offer(aux.left);
                if(aux.right != null) queue.offer(aux.right);
            }
            result.add(nivel);
        }
        return result;
    }

    //Valor minimo
    public T minimo(Node<T> nodo) {
        if (nodo == null) {
            return null;
        }

        Node<T> actual = nodo;
        while (actual.left != null) {
            actual = actual.left;
        }
        return actual.value;
    }


    //Eliminacion
    public boolean delete(T value) {
        if(delete(this.node, value) != null){
            return true;
        }else {
            return false;
        }
    }


    private Node<T> delete(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        int compare = comparator.compare(value, node.value);

        if (compare < 0) {
            node.left = delete(node.left, value);
            comparacionesEliminacion++;
        }
        else if (compare > 0) {
            node.right = delete(node.right, value);
            comparacionesEliminacion++;
        }
        else {
            if (node.left == null) {
                this.size--;
                return node.right;
            }
            if (node.right == null) {
                this.size--;
                return node.left;
            }

            T min = minimo(node.right);
            node.value = min;
            node.right = delete(node.right, min);
        }

        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

            int balance = getBalance(node);

            if (balance < -1 && getBalance(node.left) <= 0) {
                return rotateRight(node);
            }

            if (balance < -1 && getBalance(node.left) > 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            if (balance > 1 && getBalance(node.right) >= 0) {
                return rotateLeft(node);
            }

            if (balance > 1 && getBalance(node.right) < 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    //Busqueda
    public boolean search(T value) {
        if(search(this.node, value) != null){
            return true;
        }else{
            return false;

        }
    }

    private Node<T> search(Node<T> root, T value) {
        if (root == null) {
            return null;
        }

        int compare = comparator.compare(value, root.value);

        if (compare == 0) {
            comparacionesBusqueda++;
            return root;
        }

        if (compare < 0) {
            comparacionesBusqueda++;
            return search(root.left, value);
        }

        return search(root.right, value);
    }

    public int factorBalance(){
        int alturaIzquierda = this.node.left.height;
        int alturaDerecha = this.node.right.height;
        return  alturaIzquierda - alturaDerecha ;

    }

    //Getter de Rotaciones y comparaciones totales
    public int rotaciones() {
        return this.rotaciones;
    }
    public int getComparacionesInsercion() {
        return this.comparacionesInsercion;
    }
    public int getComparacionesEliminacion() {return this.comparacionesEliminacion;}
    public int getComparacionesBusqueda() {return this.comparacionesBusqueda;}


    //Nodos
    private static class Node<T> {
        protected T value;
        protected Node<T> left;
        protected Node<T> right;
        protected int height;

        public Node(T value) {
            this.value = value;
            this.height = 1;
        }
    }
}


