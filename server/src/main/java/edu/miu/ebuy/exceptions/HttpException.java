package edu.miu.ebuy.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HttpException extends ApplicationException {

    HttpStatus httpStatus;


    public HttpException(HttpStatus httpStatus, String message, int errorCode) {
        super(message, errorCode);
        this.httpStatus = httpStatus;
    }

    HttpException(HttpStatus httpStatus,String message, Throwable cause, int errorCode) {
        super(message, cause, errorCode);
        this.httpStatus = httpStatus;
    }
}
