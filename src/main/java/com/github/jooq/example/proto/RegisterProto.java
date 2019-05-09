package com.github.jooq.example.proto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RegisterProto {

  @Getter
  @Setter
  @Builder
  public static class RegisterReq {

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
  }
}
