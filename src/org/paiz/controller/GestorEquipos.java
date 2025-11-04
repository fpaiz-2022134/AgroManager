package org.paiz.controller;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IEjecutable;
import org.paiz.interfaces.IMedible;
import org.paiz.model.Equipo;
import org.paiz.model.RepositorioEquipos;

import java.util.List;

/**
 * Clase GestorEquipos (CONTROLADOR en patrón MVC)
 * Maneja la lógica de negocio y coordina entre modelo y vista
 * @author Franco Paiz
 * @version 1.0
 */
public class GestorEquipos {
    private RepositorioEquipos repositorio;

    /**
     * Constructor que inicializa el repositorio
     */
    public GestorEquipos() {
        this.repositorio = new RepositorioEquipos();
    }

    /**
     * Enumera todos los equipos del sistema
     * @return List con todos los equipos
     */
    public List<Equipo> enumerarTodosEquipos() {
        return repositorio.recuperarEquipos();
    }

    /**
     * Localiza un equipo específico por código
     * @param codigo el código a buscar
     * @return Equipo encontrado o null
     */
    public Equipo localizarEquipoPorCodigo(String codigo) {
        return repositorio.localizarPorCodigo(codigo);
    }

    /**
     * Localiza equipos por denominación
     * @param denominacion texto a buscar
     * @return List con equipos encontrados
     */
    public List<Equipo> localizarEquipoPorDenominacion(String denominacion) {
        return repositorio.localizarPorDenominacion(denominacion);
    }

    /**
     * Organiza equipos por consumo eléctrico
     * Utiliza polimorfismo: el método compareTo de cada equipo
     * @return List ordenada de equipos
     */
    public List<Equipo> organizarEquiposPorConsumo() {
        return repositorio.organizarPorConsumo();
    }

    /**
     * Genera un reporte estadístico del sistema
     * @return String con estadísticas generales
     */
    public String generarReporteEstadistico() {
        List<Equipo> equipos = repositorio.recuperarEquipos();
        int totalEquipos = equipos.size();

        // Contar por capacidades usando polimorfismo
        int medibles = 0;
        int ejecutables = 0;
        int auditables = 0;
        double consumoTotal = 0.0;

        for (Equipo equipo : equipos) {
            if (equipo instanceof IMedible) medibles++;
            if (equipo instanceof IEjecutable) ejecutables++;
            if (equipo instanceof IAuditable) auditables++;
            consumoTotal += equipo.getConsumoWatts();
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("========================================\n");
        reporte.append("    REPORTE ESTADÍSTICO DEL SISTEMA\n");
        reporte.append("========================================\n\n");
        reporte.append(String.format("Total de equipos: %d\n", totalEquipos));
        reporte.append(String.format("Equipos con medición: %d\n", medibles));
        reporte.append(String.format("Equipos accionables: %d\n", ejecutables));
        reporte.append(String.format("Equipos auditables: %d\n", auditables));
        reporte.append(String.format("Consumo total: %.2f W\n", consumoTotal));
        reporte.append(String.format("Consumo promedio: %.2f W\n",
                totalEquipos > 0 ? consumoTotal / totalEquipos : 0));
        reporte.append("========================================\n");

        return reporte.toString();
    }

    /**
     * Inicializa la plataforma cargando datos iniciales
     * Cumple con el requisito de carga inicial (init)
     */
    public void inicializarPlataforma() {
        repositorio.cargarDatosIniciales();
    }
}
