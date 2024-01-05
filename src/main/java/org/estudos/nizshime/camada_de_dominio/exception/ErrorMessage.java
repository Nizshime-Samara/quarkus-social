package org.estudos.nizshime.camada_de_dominio.exception;

public class ErrorMessage {

    private String field;

    private String message;

    public ErrorMessage(String string) {
    }

    public ErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
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
