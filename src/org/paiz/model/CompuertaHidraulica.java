package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IEjecutable;
import org.paiz.interfaces.IMedible;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase CompuertaHidraulica
 * Representa una válvula de riego controlable
 * @author Franco Paiz
 * @version 1.0
 */
public class CompuertaHidraulica extends Equipo implements IEjecutable, IAuditable {
    private double flujoMaximoLH;
    private String posicionCompuerta;
    private List<String> bitacora;

    public CompuertaHidraulica(String codigo, String denominacion, String marca,
                               double consumo, String zona, double flujo) {
        super(codigo, denominacion, marca, consumo, zona);
        this.flujoMaximoLH = flujo;
        this.posicionCompuerta = "CERRADA";
        this.bitacora = new ArrayList<>();
        agregarRegistro("Compuerta hidráulica inicializada");
    }

    @Override
    public boolean realizarOperacion(String instruccion) {
        boolean exitoso = false;

        if (instruccion.equalsIgnoreCase("ABRIR")) {
            posicionCompuerta = "ABIERTA";
            agregarRegistro("Compuerta abierta - Flujo máximo: " + flujoMaximoLH + " L/h");
            exitoso = true;
        } else if (instruccion.equalsIgnoreCase("CERRAR")) {
            posicionCompuerta = "CERRADA";
            agregarRegistro("Compuerta cerrada");
            exitoso = true;
        } else if (instruccion.startsWith("PARCIAL")) {
            posicionCompuerta = "PARCIAL";
            agregarRegistro("Compuerta en posición parcial");
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
        return "Sistema de Riego";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== COMPUERTA HIDRÁULICA ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Flujo máximo: %.2f L/h\n", flujoMaximoLH));
        resumen.append(String.format("Posición: %s\n", posicionCompuerta));
        resumen.append(String.format("Estado: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Ejecución, Auditoría\n"));
        return resumen.toString();
    }

    public double getFlujoMaximoLH() {
        return flujoMaximoLH;
    }

    public String getPosicionCompuerta() {
        return posicionCompuerta;
    }
}