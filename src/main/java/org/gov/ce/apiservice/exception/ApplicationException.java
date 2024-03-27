package org.gov.ce.apiservice.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
        System.out.println("EXCEPTION: " + message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}