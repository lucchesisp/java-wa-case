package br.com.xpto.config.exceptions;

public class ExceptionDto {

    private String field;
    private String message;

    public ExceptionDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
