package com.roche.application.rest.data.response;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
public class ErrorContainer {

    @Schema(description = "The error message")
    @NotBlank(message = "The error message is null!")
    @NotNull
    private String error;

    /**
     * Used for JSON serialisation!
     */
    public ErrorContainer() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
