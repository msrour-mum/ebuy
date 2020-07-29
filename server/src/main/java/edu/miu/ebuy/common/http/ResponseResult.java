package edu.miu.ebuy.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseResult implements Serializable {
         /**
         * Return HttpStatus
         */
        private ResponseStatus status;
        /**
         * data
         */
        private Object data;
}
