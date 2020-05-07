package ru.mike.diploma.util.exception;

public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException(String msg){
        super(msg);
    }
}
