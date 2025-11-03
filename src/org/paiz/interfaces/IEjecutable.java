package org.paiz.interfaces;

/**
 * Interfaz IEjecutable
 * Define el contrato para dispositivos que pueden ejecutar operaciones
 * @author Franco Paiz
 * @version 1.0
 */
public interface IEjecutable {
    /**
     * Realiza una operación según la instrucción proporcionada
     * @param instruccion comando a ejecutar
     * @return boolean indicando si la operación fue exitosa
     */
    boolean realizarOperacion(String instruccion);
}