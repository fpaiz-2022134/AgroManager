package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IEjecutable;
import org.paiz.interfaces.IMedible;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase UnidadBombeo
 * Representa una bomba de agua con medición y control
 * @author Sistema de Gestión Agro-tecnológica
 * @version 1.0
 */
public class UnidadBombeo extends Equipo
        implements IEjecutable, IMedible, IAuditable {

    private double potenciaCaballos;
    private double caudalLH;
    private String estadoBombeo;
    private List<String> bitacora;

    public UnidadBombeo(String codigo, String denominacion, String marca,
                        double consumo, String zona, double potencia, double caudal) {
        super(codigo, denominacion, marca, consumo, zona);
        this.potenciaCaballos = potencia;
        this.caudalLH = caudal;
        this.estadoBombeo = "DETENIDA";
        this.bitacora = new ArrayList<>();
        agregarRegistro("Unidad de bombeo inicializada");
    }

    @Override
    public boolean realizarOperacion(String instruccion) {
        boolean exitoso = false;

        if (instruccion.equalsIgnoreCase("ENCENDER")) {
            estadoBombeo = "BOMBEANDO";
            agregarRegistro("Bomba encendida - Caudal: " + caudalLH + " L/h");
            exitoso = true;
        } else if (instruccion.equalsIgnoreCase("APAGAR")) {
            estadoBombeo = "DETENIDA";
            agregarRegistro("Bomba apagada");
            exitoso = true;
        } else if (instruccion.startsWith("VELOCIDAD")) {
            estadoBombeo = "AJUSTANDO";
            agregarRegistro("Ajustando velocidad de bombeo");
            exitoso = true;
        }

        return exitoso;
    }

    @Override
    public String capturarDatos() {
        double caudalActual = caudalLH * (0.8 + Math.random() * 0.4);
        String datos = String.format("Caudal actual: %.2f L/h - Potencia: %.1f HP - Estado: %s",
                caudalActual, potenciaCaballos, estadoBombeo);
        agregarRegistro("Lectura de caudal realizada");
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
        return "Sistema de Bombeo";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== UNIDAD DE BOMBEO ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Potencia: %.1f HP\n", potenciaCaballos));
        resumen.append(String.format("Caudal máximo: %.2f L/h\n", caudalLH));
        resumen.append(String.format("Estado: %s\n", estadoBombeo));
        resumen.append(String.format("Funcionamiento: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Ejecución, Medición, Auditoría\n"));
        return resumen.toString();
    }

    public double getPotenciaCaballos() {
        return potenciaCaballos;
    }

    public double getCaudalLH() {
        return caudalLH;
    }
}
