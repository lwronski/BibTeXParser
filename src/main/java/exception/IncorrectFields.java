package exception;

public class IncorrectFields extends RuntimeException {
    /**
     * IncorrectFields is throw when user attempts to factory invalid record field
     *
     * @param message passed to message exception
     */
    public IncorrectFields(String message) {
        super(message);
    }
}
