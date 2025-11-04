package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IEjecutable;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase ModuloNutrientes
 * Representa un sistema de fertilización automática
 * @author Franco Paiz
 * @version 1.0
 */
public class ModuloNutrientes extends Equipo implements IEjecutable, IAuditable {
    private double capacidadLitros;
    private List<String> formulasDisponibles;
    private double dosisProgramada;
    private List<String> bitacora;

    public ModuloNutrientes(String codigo, String denominacion, String marca,
                            double consumo, String zona, double capacidad) {
        super(codigo, denominacion, marca, consumo, zona);
        this.capacidadLitros = capacidad;
        this.formulasDisponibles = new ArrayList<>();
        this.dosisProgramada = 0.0;
        this.bitacora = new ArrayList<>();
        inicializarFormulas();
        agregarRegistro("Módulo de nutrientes inicializado");
    }

    private void inicializarFormulas() {
        formulasDisponibles.add("NPK 20-20-20");
        formulasDisponibles.add("Nitrógeno concentrado");
        formulasDisponibles.add("Fósforo soluble");
    }

    @Override
    public boolean realizarOperacion(String instruccion) {
        boolean exitoso = false;

        if (instruccion.startsWith("APLICAR")) {
            dosisProgramada = 5.0 + Math.random() * 15.0;
            agregarRegistro(String.format("Aplicación iniciada - Dosis: %.2f L",
                    dosisProgramada));
            exitoso = true;
        } else if (instruccion.equalsIgnoreCase("DETENER")) {
            agregarRegistro("Aplicación detenida");
            exitoso = true;
        } else if (instruccion.startsWith("PROGRAMAR")) {
            agregarRegistro("Nueva dosificación programada");
            exitoso = true;
        }

        return exitoso;
    }

    @Override
    public void agregarRegistro(String entrada) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        bitacora.add(timestamp + " - " + entrada);
    }

    @Override
    public List<String> consultarBitacora() {
        return new ArrayList<>(bitacora);
    }

    @Override
    public String obtenerCategoria() {
        return "Sistema de Fertilización";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== MÓDULO DE NUTRIENTES ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Capacidad: %.2f L\n", capacidadLitros));
        resumen.append("Fórmulas disponibles:\n");
        for (String formula : formulasDisponibles) {
            resumen.append(String.format("  - %s\n", formula));
        }
        resumen.append(String.format("Estado: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Ejecución, Auditoría\n"));
        return resumen.toString();
    }

    public void incorporarFormula(String formula) {
        if (!formulasDisponibles.contains(formula)) {
            formulasDisponibles.add(formula);
            agregarRegistro("Nueva fórmula incorporada: " + formula);
        }
    }

    public double getCapacidadLitros() {
        return capacidadLitros;
    }
}