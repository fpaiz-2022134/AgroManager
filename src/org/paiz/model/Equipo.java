package org.paiz.model;

/**
 * Clase abstracta Equipo
 * Representa la base para todos los dispositivos del sistema agro-tecnológico
 * Implementa Comparable para permitir ordenamiento por consumo eléctrico
 * @author Sistema de Gestión Agro-tecnológica
 * @version 1.0
 */
public abstract class Equipo implements Comparable<Equipo> {
    // Atributos protected para acceso desde clases hijas
    protected String codigo;
    protected String denominacion;
    protected String marca;
    protected double consumoWatts;
    protected String zona;
    protected boolean enFuncionamiento;

    /**
     * Constructor de Equipo
     * @param codigo identificador único del equipo
     * @param denominacion nombre del equipo
     * @param marca fabricante del equipo
     * @param consumoWatts consumo eléctrico en watts
     * @param zona ubicación del equipo
     */
    public Equipo(String codigo, String denominacion, String marca,
                  double consumoWatts, String zona) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.marca = marca;
        this.consumoWatts = consumoWatts;
        this.zona = zona;
        this.enFuncionamiento = true;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getMarca() {
        return marca;
    }

    public double getConsumoWatts() {
        return consumoWatts;
    }

    public String getZona() {
        return zona;
    }

    public boolean isEnFuncionamiento() {
        return enFuncionamiento;
    }

    public void setEnFuncionamiento(boolean estado) {
        this.enFuncionamiento = estado;
    }

    /**
     * Método abstracto para obtener la categoría del equipo
     * @return String con la categoría
     */
    public abstract String obtenerCategoria();

    /**
     * Método abstracto para generar resumen detallado
     * @return String con información completa del equipo
     */
    public abstract String generarResumen();

    /**
     * Override de toString para representación básica
     * @return String con información básica del equipo
     */
    @Override
    public String toString() {
        return String.format("%s - %s [%s] - %.2fW",
                codigo, denominacion, marca, consumoWatts);
    }

    /**
     * Override de equals para comparación por código
     * @param obj objeto a comparar
     * @return boolean indicando si son iguales
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return codigo.equals(equipo.codigo);
    }

    /**
     * Implementación de compareTo para ordenamiento por consumo eléctrico
     * @param otro equipo a comparar
     * @return int negativo, cero o positivo según la comparación
     */
    @Override
    public int compareTo(Equipo otro) {
        return Double.compare(this.consumoWatts, otro.consumoWatts);
    }
}
