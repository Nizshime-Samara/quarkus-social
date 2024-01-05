package org.estudos.nizshime.camada_de_dominio.exception;

import java.util.Objects;

public class ErrorMessage {

    private String field;

    private String message;

    public ErrorMessage(String string) {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
