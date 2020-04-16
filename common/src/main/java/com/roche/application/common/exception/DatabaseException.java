package com.roche.application.common.exception;

/**
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
public class DatabaseException extends BaseException {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
