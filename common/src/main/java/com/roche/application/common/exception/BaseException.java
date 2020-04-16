package com.roche.application.common.exception;

/**
 * Base class for all application exceptions.
 * <p>
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
public class BaseException extends RuntimeException {

    public BaseException(final String message) {
        super(message);
    }

    public BaseException(final Throwable cause) {
        super(cause);
    }

    public BaseException(final String message, final Throwable cause) {
        super(cause);
    }

}

