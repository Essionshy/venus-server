package com.tingyu.venus.exception;

import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/11/10 22:14
 * @Version venus-server
 */
@Data
public class ResultException  extends RuntimeException{

    private int code;

    private String message;

    public ResultException(String message) {
        super(message);
        this.message = message;
    }

    public ResultException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
