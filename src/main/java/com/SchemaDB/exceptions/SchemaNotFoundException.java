package com.SchemaDB.exceptions;

public class SchemaNotFoundException extends Exception {

    public SchemaNotFoundException(String message) {
        super(message);
    }

    public SchemaNotFoundException(Throwable e) {
        super(e);
    }
}
