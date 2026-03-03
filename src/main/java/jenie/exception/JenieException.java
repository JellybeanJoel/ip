package jenie.exception;

/**
 * Signals an error specific to the Jenie application's logic or user input.
 */
public class JenieException extends Exception{
    /**
     * Constructs a new JenieException with the specified detail message.
     *
     * @param message The detail message explaining the error.
     */
    public JenieException(String message) {
        super(message);
    }
}
