package edu.miu.ebuy.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ExceptionResult  implements Serializable {

    /**
     * Return HttpStatus
     */
    private ResponseStatus status;

    /**
     * Custom error code
     */
    private int errCode;

    /**
     * error
     */
    private String error;


}
