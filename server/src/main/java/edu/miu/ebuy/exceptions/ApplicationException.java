package edu.miu.ebuy.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApplicationException extends Exception{

    private int errorCode;
    private String message;

    public ApplicationException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    ApplicationException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.message = message;
        this.errorCode = errorCode;
    }

    public HttpException httpError(HttpStatus httpStatus)
    {
        return new HttpException(httpStatus, this.getMessage(), this.getErrorCode());
    }
}
