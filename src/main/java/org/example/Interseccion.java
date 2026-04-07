package org.example;

import java.time.LocalDateTime;

public class Interseccion {
    private int id;
    private String distrito;
    private String zona;
    private String avenida;



    private int nivelCongestion; //1-100
    private int nivelRiesgo; //1-100
    private long  actualizacionReporte;
    private boolean activa;
    private int sensores;
    private String nombre;

    public Interseccion(int id, String distrito, String zona, String avenida, int nivelRiesgo, int nivelCongestion, int sensores, int activa){
        this.id = id;
        this.distrito = distrito;
        this.zona = zona;
        this.avenida = avenida;
        this.sensores = sensores;
        if(activa==1)
            this.activa = true;
        else
            this.activa = false;
        this.nivelCongestion = nivelCongestion;
        this.nivelRiesgo = nivelRiesgo;
        this.actualizacionReporte = System.currentTimeMillis();
        this.nombre = "Interseccion" + Integer.toString(id);

    }

    public String getNombre() {
        return nombre;
    }
    public boolean isActiva() {return activa;}

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
    public long getActualizacionReporte() {return actualizacionReporte;}

    public void setNivelCongestion(int nivelCongestion) {
        this.nivelCongestion =  Math.min(100,this.nivelCongestion + nivelCongestion);
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = Math.min(100,this.nivelRiesgo + nivelRiesgo);
    }

    public void setActiva(boolean activa){
        this.activa = activa;
    }

    public void actualizarReporte() {
        this.actualizacionReporte = System.currentTimeMillis();
    }

    //Este objeto es lo que va dentro del BST y el AVL





}
