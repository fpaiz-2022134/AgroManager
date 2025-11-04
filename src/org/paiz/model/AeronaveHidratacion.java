package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IEjecutable;
import org.paiz.interfaces.IMedible;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase AeronaveHidratacion
 * Representa un dron para riego aéreo
 * @author Franco Paiz
 * @version 1.0
 */
public class AeronaveHidratacion extends Equipo
        implements IEjecutable, IMedible, IAuditable {

    private double duracionBateriaMin;
    private double volumenDepositoL;
    private double altitudOperacion;
    private String estadoAeronave;
    private List<String> bitacora;

    public AeronaveHidratacion(String codigo, String denominacion, String marca,
                               double consumo, String zona, double duracion,
                               double volumen) {
        super(codigo, denominacion, marca, consumo, zona);
        this.duracionBateriaMin = duracion;
        this.volumenDepositoL = volumen;
        this.altitudOperacion = 0.0;
        this.estadoAeronave = "EN_TIERRA";
        this.bitacora = new ArrayList<>();
        agregarRegistro("Aeronave de hidratación inicializada");
    }

    @Override
    public boolean realizarOperacion(String instruccion) {
        boolean exitoso = false;

        if (instruccion.equalsIgnoreCase("DESPEGAR")) {
            estadoAeronave = "EN_VUELO";
            altitudOperacion = 5.0 + Math.random() * 10.0;
            agregarRegistro(String.format("Despegue exitoso - Altitud: %.1fm",
                    altitudOperacion));
            exitoso = true;
        } else if (instruccion.equalsIgnoreCase("ATERRIZAR")) {
            estadoAeronave = "EN_TIERRA";
            altitudOperacion = 0.0;
            agregarRegistro("Aterrizaje completado");
            exitoso = true;
        } else if (instruccion.equalsIgnoreCase("ROCIAR")) {
            agregarRegistro("Rociado iniciado - Volumen: " + volumenDepositoL + "L");
            exitoso = true;
        }

        return exitoso;
    }

    @Override
    public String capturarDatos() {
        double bateriaRestante = duracionBateriaMin * (0.4 + Math.random() * 0.6);
        String datos = String.format("Batería: %.1f min - Altitud: %.1fm - " +
                        "Depósito: %.1fL - Estado: %s",
                bateriaRestante, altitudOperacion,
                volumenDepositoL, estadoAeronave);
        agregarRegistro("Telemetría capturada");
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
        return "Dron de Riego";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== AERONAVE DE HIDRATACIÓN ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Autonomía: %.1f min\n", duracionBateriaMin));
        resumen.append(String.format("Capacidad: %.1f L\n", volumenDepositoL));
        resumen.append(String.format("Altitud operación: %.1f m\n", altitudOperacion));
        resumen.append(String.format("Estado: %s\n", estadoAeronave));
        resumen.append(String.format("Funcionamiento: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Ejecución, Medición, Auditoría\n"));
        return resumen.toString();
    }

    public double getDuracionBateriaMin() {
        return duracionBateriaMin;
    }

    public double getVolumenDepositoL() {
        return volumenDepositoL;
    }

    public double getAltitudOperacion() {
        return altitudOperacion;
    }
}