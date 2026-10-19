package com.travelbrain.modelo;

/**
 * Entidad Destino de la plataforma TravelBrain.
 * Representa un destino turistico que el usuario puede explorar.
 */
public class Destino {

    private int id;
    private String nombre;
    private String pais;
    private String descripcion;
    private String mejorEpoca;

    public Destino() {
    }

    public Destino(String nombre, String pais, String descripcion, String mejorEpoca) {
        this.nombre = nombre;
        this.pais = pais;
        this.descripcion = descripcion;
        this.mejorEpoca = mejorEpoca;
    }

    public Destino(int id, String nombre, String pais, String descripcion, String mejorEpoca) {
        this(nombre, pais, descripcion, mejorEpoca);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMejorEpoca() {
        return mejorEpoca;
    }

    public void setMejorEpoca(String mejorEpoca) {
        this.mejorEpoca = mejorEpoca;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - %s | Mejor epoca: %s",
                id, nombre, pais, descripcion, mejorEpoca);
    }
}
