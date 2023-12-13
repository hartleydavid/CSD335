/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorting;

/**
 *
 * @author Thomas.Abbott
 */
public class UnderflowException extends Exception {
    public UnderflowException(String msg) {
        super(msg);
    }
    
    public UnderflowException() {
        super("Empty Priority Queue");
    }
}
