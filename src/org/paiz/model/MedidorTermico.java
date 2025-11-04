package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IMedible;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase MedidorTermico
 * Representa un sensor de temperatura
 * @author Franco Paiz
 * @version 1.0
 */
public class MedidorTermico extends Equipo implements IMedible, IAuditable {
    private String rangoTermico;
    private String escala;
    private List<String> bitacora;

    public MedidorTermico(String codigo, String denominacion, String marca,
                          double consumo, String zona, String rango) {
        super(codigo, denominacion, marca, consumo, zona);
        this.rangoTermico = rango;
        this.escala = "Celsius";
        this.bitacora = new ArrayList<>();
        agregarRegistro("Medidor térmico inicializado");
    }

    @Override
    public String capturarDatos() {
        double temperatura = 10.0 + Math.random() * 30.0;
        String lectura = String.format("Temperatura: %.2f°%s (Rango: %s)",
                temperatura, escala, rangoTermico);
        agregarRegistro("Temperatura registrada: " + temperatura);
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
        return "Medidor Térmico";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== MEDIDOR TÉRMICO ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Rango: %s\n", rangoTermico));
        resumen.append(String.format("Escala: %s\n", escala));
        resumen.append(String.format("Estado: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Medición, Auditoría\n"));
        return resumen.toString();
    }

    public String getRangoTermico() {
        return rangoTermico;
    }

    public String getEscala() {
        return escala;
    }
}
