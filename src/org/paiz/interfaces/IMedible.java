package org.paiz.interfaces;

/**
 * Interfaz IMedible
 * Define el contrato para dispositivos que capturan datos
 * @author Franco Paiz
 * @version 1.0
 */
public interface IMedible {
    /**
     * Captura y retorna datos del dispositivo
     * @return String con los datos capturados
     */
    String capturarDatos();
}