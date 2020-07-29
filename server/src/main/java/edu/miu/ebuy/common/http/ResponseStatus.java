package edu.miu.ebuy.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseStatus {

    private int code;
    private String reason;
}
