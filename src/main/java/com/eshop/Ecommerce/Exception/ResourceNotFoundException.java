package com.eshop.Ecommerce.Exception;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;
    public ResourceNotFoundException(){

    }
    public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not fount with %s : %s", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not fount with %s : %s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }
}
