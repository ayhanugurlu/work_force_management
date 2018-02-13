package com.au.example.wfm.handler;

import com.au.example.wfm.exception.InvalidUserNameOrPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ayhan.Ugurlu on 13/02/2018
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @Value("${error.code.header.key}")
    private String errorCodeHeaderKey;


    @Autowired
    private Tracer tracer;

    @ExceptionHandler(InvalidUserNameOrPassword.class)
    public ResponseEntity<ErrResponse> handleValidationException(HttpServletRequest request, InvalidUserNameOrPassword e) {

        String errorMessage = e.getMessage();
        String[] eCodes = {InvalidUserNameOrPassword.ERR_CODE};

        logger.error(errorMessage);
        ErrResponse response = new ErrResponse(tracer.getCurrentSpan().getTraceId(), errorMessage);


        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .header(errorCodeHeaderKey, eCodes)
                .body(response);
    }
}