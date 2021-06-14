package com.assignment.chitter.exception;

/*
    author brian.dsouza
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    User Define Exception Handling
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DatabaseHandlingException extends Exception{

    private static final long serialVersionUID = 1L;

    public DatabaseHandlingException(String message){
        super(message);
    }
}