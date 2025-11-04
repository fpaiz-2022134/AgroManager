package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IMedible;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase CentralClimatica
 * Representa una estación meteorológica completa
 * @author Sistema de Gestión Agro-tecnológica
 * @version 1.0
 */
public class CentralClimatica extends Equipo implements IMedible, IAuditable {
    private double elevacionMetros;
    private List<String> variablesMedidas;
    private List<String> bitacora;

    public CentralClimatica(String codigo, String denominacion, String marca,
                            double consumo, String zona, double elevacion) {
        super(codigo, denominacion, marca, consumo, zona);
        this.elevacionMetros = elevacion;
        this.variablesMedidas = new ArrayList<>();
        this.bitacora = new ArrayList<>();
        inicializarVariables();
        agregarRegistro("Central climática inicializada");
    }

    private void inicializarVariables() {
        variablesMedidas.add("Temperatura");
        variablesMedidas.add("Humedad");
        variablesMedidas.add("Presión atmosférica");
        variablesMedidas.add("Velocidad del viento");
    }

    @Override
    public String capturarDatos() {
        StringBuilder datos = new StringBuilder("Datos meteorológicos:\n");
        datos.append(String.format("  - Temperatura: %.1f°C\n", 15 + Math.random() * 20));
        datos.append(String.format("  - Humedad: %.1f%%\n", 40 + Math.random() * 50));
        datos.append(String.format("  - Presión: %.1f hPa\n", 980 + Math.random() * 60));
        datos.append(String.format("  - Viento: %.1f km/h\n", Math.random() * 40));
        datos.append(String.format("  - Elevación: %.1fm", elevacionMetros));
        agregarRegistro("Captura completa de datos meteorológicos");
        return datos.toString();
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
        return "Estación Meteorológica";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== CENTRAL CLIMÁTICA ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Elevación: %.1f m\n", elevacionMetros));
        resumen.append("Variables medidas:\n");
        for (String variable : variablesMedidas) {
            resumen.append(String.format("  - %s\n", variable));
        }
        resumen.append(String.format("Estado: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Medición, Auditoría\n"));
        return resumen.toString();
    }

    public void incorporarVariable(String variable) {
        if (!variablesMedidas.contains(variable)) {
            variablesMedidas.add(variable);
            agregarRegistro("Nueva variable incorporada: " + variable);
        }
    }

    public double getElevacionMetros() {
        return elevacionMetros;
    }
}