package org.example;

import java.time.LocalDateTime;

public class Interseccion {
    private int id;
    private String distrito;
    private String zona;
    private String avenida;



    private int nivelCongestion; //1-10
    private int nivelRiesgo; //1-10
    private LocalDateTime actualizacionReporte;
    private boolean activa;
    private int sensores;
    private String nombre;



    public Interseccion(int id, String distrito, String zona, String avenida, int nivelRiesgo, int nivelCongestion, int sensores){
        this.id = id;
        this.distrito = distrito;
        this.zona = zona;
        this.avenida = avenida;
        this.sensores = sensores;
        this.activa = true;
        this.nivelCongestion = nivelCongestion;
        this.nivelRiesgo = nivelRiesgo;
        this.actualizacionReporte = LocalDateTime.now();
        this.nombre = "Interseccion" + Integer.toString(id);

    }

    public String getNombre() {
        return nombre;
    }

    public String getDistrito() {
        return distrito;
    }
    public String getZona() {
        return zona;
    }
    public String getAvenida() {
        return avenida;
    }

    public int getId() {return id;}
    public int getNivelCongestion() {return nivelCongestion;}
    public int getNivelRiesgo() {return nivelRiesgo;}
    public LocalDateTime getActualizacionReporte() {return actualizacionReporte;}

    public void setNivelCongestion(int nivelCongestion) {
        this.nivelCongestion = nivelCongestion;
        this.actualizacionReporte = LocalDateTime.now();
    }

    public void setActiva(boolean activa){
        this.activa = activa;
        this.actualizacionReporte = LocalDateTime.now();
    }

    //Este objeto es lo que va dentro del BST y el AVL





}
