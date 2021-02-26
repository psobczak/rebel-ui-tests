package pl.sobczak.rebel.config.exceptions;

public class ProductNotFoundException extends IllegalArgumentException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
