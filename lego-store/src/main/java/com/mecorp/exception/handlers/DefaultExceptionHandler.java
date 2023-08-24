package com.mecorp.exception.handlers;

import com.mecorp.exception.HttpErrorModel;
import com.mecorp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<HttpErrorModel> handleNotFoundException(NotFoundException exception) {
        HttpErrorModel httpErrorModel = new HttpErrorModel();
        httpErrorModel.setMessage(exception.getMessage());
        httpErrorModel.setStatusCode(HttpStatus.NOT_FOUND);
        httpErrorModel.setTimestamp(LocalDate.now().toString());

        return new ResponseEntity<>(httpErrorModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<HttpErrorModel> handleException(Exception exception) {
        HttpErrorModel httpErrorModel = new HttpErrorModel();
        httpErrorModel.setMessage(exception.getMessage());
        httpErrorModel.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        httpErrorModel.setTimestamp(LocalDate.now().toString());

        return new ResponseEntity<>(httpErrorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
