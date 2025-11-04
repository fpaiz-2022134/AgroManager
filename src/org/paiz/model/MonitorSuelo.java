package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IMedible;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase MonitorSuelo
 * Representa un sensor de suelo con capacidades de medición y auditoría
 * @author Franco Paiz
 * @version 1.0
 */
public class MonitorSuelo extends Equipo implements IMedible, IAuditable {
    private double profundidadCm;
    private String categoriaMonitor;
    private double valorActual;
    private List<String> bitacora;

    /**
     * Constructor de MonitorSuelo
     */
    public MonitorSuelo(String codigo, String denominacion, String marca,
                        double consumo, String zona, double profundidadCm,
                        String categoriaMonitor) {
        super(codigo, denominacion, marca, consumo, zona);
        this.profundidadCm = profundidadCm;
        this.categoriaMonitor = categoriaMonitor;
        this.valorActual = 0.0;
        this.bitacora = new ArrayList<>();
        agregarRegistro("Monitor de suelo inicializado");
    }

    @Override
    public String capturarDatos() {
        // Simula lectura de sensor
        valorActual = 15.0 + Math.random() * 25.0;
        String lectura = String.format("Humedad del suelo: %.2f%% a %.1fcm de profundidad",
                valorActual, profundidadCm);
        agregarRegistro("Lectura capturada: " + valorActual);
        return lectura;
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
        return "Sensor de Suelo";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== MONITOR DE SUELO ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Profundidad: %.1f cm\n", profundidadCm));
        resumen.append(String.format("Tipo: %s\n", categoriaMonitor));
        resumen.append(String.format("Estado: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Medición, Auditoría\n"));
        return resumen.toString();
    }

    public double getProfundidadCm() {
        return profundidadCm;
    }

    public String getCategoriaMonitor() {
        return categoriaMonitor;
    }
}