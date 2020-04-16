package com.roche.application.common.exception;

/**
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
public class InputValidationException extends BaseException {
    public InputValidationException(String message) {
        super(message);
    }

    public InputValidationException(Throwable cause) {
        super(cause);
    }

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
