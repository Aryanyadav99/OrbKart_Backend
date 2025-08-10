package com.eshop.Ecommerce.Exception;

public class APIException extends RuntimeException {
    //used when there are already a category present and you cannot add other like that
    private static final long serialVersionUID = 1L;

    public APIException(){

    }

    public APIException(String message) {
        super(message);
    }
}
