package com.umesh.warehouse.exception;

/**
 * @author ukushwaha
 */
public class ProductNotFoundException extends RuntimeException{


    public ProductNotFoundException(String message) {
        super(message);
    }
}
