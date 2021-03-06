package com.github.jooq.example.data;

import lombok.Getter;

/**
 * 接口返回码定义,请注释每个返回码定义.
 */
@Getter
public enum ApiRetCode {
  SUCCESS(200, "成功"),
  PARAMETER_EMPTY(10001, "参数错误"),
  INVALID_SHORT_PASSWORD(10002, "支付密码格式错误，必须为6位数字"),
  USER_EXISTED(10003, "该帐号已注册"),
  USER_NOT_EXISTED(10004, "该帐号已注册"),
  USER_LOGIN_LIMITED(10005, "账号登录次数超限，已锁定"),
  PASSWORD_ERROR(10006, "支密码错误"),
  ;


  /**
   * 返回码
   */
  private int code;
  /**
   * 描述
   */
  private String message;

  /**
   *
   * @param code
   * @param message
   */
  private ApiRetCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

}
