package edu.miu.ebuy.common.advices;

import edu.miu.ebuy.common.http.*;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations = BaseResponse.class)
public class ResponseHandlerAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if(MediaType.APPLICATION_JSON.equals(selectedContentType) || MediaType.APPLICATION_JSON_UTF8.equals(selectedContentType)){ // Judging that the Content-Type of the response is body in JSON format
            if(body instanceof ResponseResult || body instanceof ExceptionResult) { // If the object returned by the response is a unified responder, the body is returned directly.
                return body;
            } else {
                // Only the normal returned result will enter the judgment process, so the normal successful status code will be returned.
                return new ResponseResult(new ResponseStatus(HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase()),body);
            }
        }
        // Non-JSON format body can be returned directly
        return body;
    }
}
