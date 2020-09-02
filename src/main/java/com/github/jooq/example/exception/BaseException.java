package com.github.jooq.example.exception;

import com.github.jooq.example.data.ApiRetCode;
import lombok.Getter;

import java.text.MessageFormat;

/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2020/09/01
 */
@Getter
public class BaseException extends RuntimeException {
    private final int errorCode;
    private final String errorMessage;
    private String extErrorMessage;

    public BaseException(int errorCode) {
        super();
        this.errorCode = errorCode;
        errorMessage = "";
    }

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(ApiRetCode result, Exception exception) {
        super(result.getMessage(), exception);
        errorCode = result.getCode();
        errorMessage = exception.getMessage();
    }

    public BaseException(ApiRetCode result) {
        super(result.getMessage());
        this.errorCode = result.getCode();
        this.errorMessage = result.getMessage();
    }

    public BaseException(ApiRetCode result, String... params) {
        super(result.getMessage());
        this.errorCode = result.getCode();
        this.errorMessage = MessageFormat.format(result.getMessage(), params);
    }

}
