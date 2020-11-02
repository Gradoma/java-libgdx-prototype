package by.gradomski.game.exception;

public class BuilderException extends Exception {
    static final long serialVersionUID = 1L;

    public BuilderException() {
    }

    public BuilderException(String message) {
        super(message);
    }

    public BuilderException(String message, Exception e) {
        super(message, e);
    }

    public BuilderException(Exception e) {
        super(e);
    }
}
