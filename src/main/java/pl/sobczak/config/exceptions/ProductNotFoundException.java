package pl.sobczak.config.exceptions;

public class ProductNotFoundException extends IllegalArgumentException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
