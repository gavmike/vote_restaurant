package ru.mike.diploma.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.mike.diploma.util.ValidationUtil;
import ru.mike.diploma.util.exception.ErrorInfo;
import ru.mike.diploma.util.exception.ErrorType;
import ru.mike.diploma.util.exception.IllegalRequestException;
import ru.mike.diploma.util.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import static ru.mike.diploma.util.exception.ErrorType.*;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
 private static Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);


    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e, false, DATA_NOT_FOUND);
    }


    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        Throwable throwable = ValidationUtil.getRootCause(e);
        if (throwable.toString().contains("users_unique_email_idx"))
            return new ErrorInfo(DATA_ERROR, "User with this email already exists");
        else return logAndGetErrorInfo(req, e, true, DATA_ERROR);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler({IllegalRequestException.class,  ValidationException.class, MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class, BindException.class})
    public ErrorInfo illegalRequestDataError(HttpServletRequest req, Exception e) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (e instanceof BindException) {
            String message = ((BindException) e).getFieldError().getDefaultMessage();
            log.error(VALIDATION_ERROR + " at request " + req.getRequestURL(), rootCause);
            return new ErrorInfo(VALIDATION_ERROR, message);
        }
        if (e instanceof MethodArgumentNotValidException) {
            String message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            log.error(VALIDATION_ERROR + " at request " + req.getRequestURL(), rootCause);
            return new ErrorInfo(VALIDATION_ERROR, message);
        }

        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true,APP_ERROR);
    }



    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return new ErrorInfo(errorType, rootCause.getLocalizedMessage());
    }
}
