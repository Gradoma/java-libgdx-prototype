package by.gradomski.game.exception;

public class IncorrectConfigTypeException extends Exception {
    static final long serialVersionUID = 1L;

    public IncorrectConfigTypeException() {
    }

    public IncorrectConfigTypeException(String message) {
        super(message);
    }

    public IncorrectConfigTypeException(String message, Exception e) {
        super(message, e);
    }

    public IncorrectConfigTypeException(Exception e) {
        super(e);
    }
}
