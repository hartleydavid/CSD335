/*
 * 
 */
package assign4;

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
