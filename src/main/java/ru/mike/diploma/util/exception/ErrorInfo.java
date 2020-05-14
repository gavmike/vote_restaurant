package ru.mike.diploma.util.exception;

public  class ErrorInfo {
    private final ErrorType type;
    private final String detail;

    public ErrorInfo(ErrorType type, String detail) {
        this.type = type;
        this.detail = detail;
    }
}
