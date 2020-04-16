package com.roche.application.common.exception;

/**
 * Created on 16.04.20.
 *
 * @author waldemarkipka
 */
public class ProcessingException extends BaseException {
    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(Throwable cause) {
        super(cause);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
