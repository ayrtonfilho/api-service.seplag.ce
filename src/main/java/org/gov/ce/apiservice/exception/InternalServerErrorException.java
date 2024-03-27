package org.gov.ce.apiservice.exception;

import java.sql.SQLException;

public class InternalServerErrorException extends SQLException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
