package hw08.exceptions;

public class AppArrayDataException extends Exception {
    public AppArrayDataException(String cell) {
        super("Incorrect data in cell " + cell);
    }
}
