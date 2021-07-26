package com.SchemaDB.exceptions;

public class StoreException extends Exception {

    public StoreException(String message) {
        super(message);
    }

    public StoreException(Throwable e) {
        super(e);
    }
}
