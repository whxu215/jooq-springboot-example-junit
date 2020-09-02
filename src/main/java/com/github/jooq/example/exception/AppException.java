package com.github.jooq.example.exception;

import com.github.jooq.example.data.ApiRetCode;

/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2020/09/01
 */
public class AppException extends BaseException {

    public AppException(ApiRetCode result) {
        super(result);
    }

    public AppException(ApiRetCode result, String... params) {
        super(result, params);
    }

    public AppException(ApiRetCode message, RuntimeException ex) {
        super(message, ex);
    }

}
