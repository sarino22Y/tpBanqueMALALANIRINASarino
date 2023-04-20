/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquemalalanirinasarino.exception;

import jakarta.ejb.ApplicationException;

/**
 *
 * @author Sarino
 */
/**
 * ConcurrentAccessException lancée en cas d'accès concurrent sans gravité.
 */
@ApplicationException(rollback = false)
public class ConcurrentAccessException extends RuntimeException{

    public ConcurrentAccessException(String message) {
        super(message);
    }
}
