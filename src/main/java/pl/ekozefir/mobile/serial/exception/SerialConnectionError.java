/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ekozefir.mobile.serial.exception;

/**
 *
 * @author Michal Marasz
 */
public class SerialConnectionError extends RuntimeException {
    private final Exception cause;

    public SerialConnectionError(Exception cause) {
        this.cause = cause;
    }

    public Exception getCause() {
        return cause;
    }
    
}
