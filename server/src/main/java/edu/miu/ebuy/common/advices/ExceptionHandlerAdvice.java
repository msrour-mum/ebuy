package edu.miu.ebuy.common.advices;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.common.http.ExceptionResult;
import edu.miu.ebuy.common.http.ResponseStatus;
import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.exceptions.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(annotations = BaseResponse.class)
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ExceptionResult handleException(Exception e){
        System.out.println(e);
        return new ExceptionResult(new ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                Errors.UNEXPECTED_ERROR,
                e.getMessage());
    }

    @ExceptionHandler(HttpException.class)
    public ExceptionResult handleException(HttpException e){
        return new ExceptionResult(new ResponseStatus(e.getHttpStatus().value(), e.getHttpStatus().getReasonPhrase()),
                e.getErrorCode(),
                e.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ExceptionResult handleException(ApplicationException e){
        return new ExceptionResult(new ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                e.getErrorCode(), e.getMessage());
    }

}
