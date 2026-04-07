package org.example;

public class Evento {
    private int id;
    private int prioridad;
    private String tipo;
    private int idInterseccion;


    public Evento(int id, int prioridad, String tipo, int idInterseccion) {
        this.id = id;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.idInterseccion = idInterseccion;
    }

    public int getId() { return id; }
    public int getPrioridad() { return prioridad; }
    public String getTipo() { return tipo; }
    public int getIdInterseccion() { return idInterseccion; }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "E" + id + " " + tipo + "p= " + prioridad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;
        Evento evento = (Evento) o;
        return id == evento.id;
    }
}