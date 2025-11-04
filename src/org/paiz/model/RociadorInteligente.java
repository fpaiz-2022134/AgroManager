package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IEjecutable;
import org.paiz.interfaces.IMedible;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase RociadorInteligente
 * Representa un aspersor con capacidades de medición y acción
 * @author Franco Paiz
 * @version 1.0
 */
public class RociadorInteligente extends Equipo
        implements IEjecutable, IMedible, IAuditable {

    private double alcanceMetros;
    private double presionBar;
    private String modoRociador;
    private List<String> bitacora;

    public RociadorInteligente(String codigo, String denominacion, String marca,
                               double consumo, String zona, double alcance) {
        super(codigo, denominacion, marca, consumo, zona);
        this.alcanceMetros = alcance;
        this.presionBar = 3.5;
        this.modoRociador = "APAGADO";
        this.bitacora = new ArrayList<>();
        agregarRegistro("Rociador inteligente inicializado");
    }

    @Override
    public boolean realizarOperacion(String instruccion) {
        boolean exitoso = false;

        if (instruccion.equalsIgnoreCase("ACTIVAR")) {
            modoRociador = "ACTIVO";
            agregarRegistro("Rociador activado - Alcance: " + alcanceMetros + "m");
            exitoso = true;
        } else if (instruccion.equalsIgnoreCase("DESACTIVAR")) {
            modoRociador = "APAGADO";
            agregarRegistro("Rociador desactivado");
            exitoso = true;
        } else if (instruccion.startsWith("AJUSTAR")) {
            modoRociador = "AJUSTE";
            agregarRegistro("Ajustando parámetros de rociado");
            exitoso = true;
        }

        return exitoso;
    }

    @Override
    public String capturarDatos() {
        presionBar = 2.5 + Math.random() * 2.0;
        String datos = String.format("Presión actual: %.2f bar - Alcance: %.1fm - Modo: %s",
                presionBar, alcanceMetros, modoRociador);
        agregarRegistro("Datos de operación capturados");
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
        return "Sistema de Riego Inteligente";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== ROCIADOR INTELIGENTE ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Alcance: %.1f m\n", alcanceMetros));
        resumen.append(String.format("Presión: %.2f bar\n", presionBar));
        resumen.append(String.format("Modo: %s\n", modoRociador));
        resumen.append(String.format("Estado: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Ejecución, Medición, Auditoría\n"));
        return resumen.toString();
    }

    public double getAlcanceMetros() {
        return alcanceMetros;
    }

    public double getPresionBar() {
        return presionBar;
    }
}