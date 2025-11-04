package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IMedible;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase DetectorHumedad
 * Representa un detector de humedad ambiental
 * @author Franco Paiz
 * @version 1.0
 */
public class DetectorHumedad extends Equipo implements IMedible, IAuditable {
    private String intervaloDeteccion;
    private double exactitud;
    private List<String> bitacora;

    public DetectorHumedad(String codigo, String denominacion, String marca,
                           double consumo, String zona, String intervalo,
                           double exactitud) {
        super(codigo, denominacion, marca, consumo, zona);
        this.intervaloDeteccion = intervalo;
        this.exactitud = exactitud;
        this.bitacora = new ArrayList<>();
        agregarRegistro("Detector de humedad inicializado");
    }

    @Override
    public String capturarDatos() {
        double lectura = 30.0 + Math.random() * 60.0;
        String datos = String.format("Humedad relativa: %.2f%% (±%.2f%%)",
                lectura, exactitud);
        agregarRegistro("Medición realizada: " + lectura);
        return datos;
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
        return "Detector de Humedad";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== DETECTOR DE HUMEDAD ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Intervalo: %s\n", intervaloDeteccion));
        resumen.append(String.format("Exactitud: ±%.2f%%\n", exactitud));
        resumen.append(String.format("Estado: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Medición, Auditoría\n"));
        return resumen.toString();
    }

    public String getIntervaloDeteccion() {
        return intervaloDeteccion;
    }

    public double getExactitud() {
        return exactitud;
    }
}
