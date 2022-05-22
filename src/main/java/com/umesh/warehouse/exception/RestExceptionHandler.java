package com.umesh.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author ukushwaha
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Product does not exist.")
    @ExceptionHandler(ProductNotFoundException.class)
    public void handleProductNotFound(
            ProductNotFoundException ex) {

    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Product is out of stock")
    @ExceptionHandler(OurOfStockException.class)
    public void handleOutOfStock(
            OurOfStockException ex) {

    }


}
