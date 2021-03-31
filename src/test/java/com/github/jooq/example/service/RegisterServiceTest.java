package com.github.jooq.example.service;

import com.github.jooq.example.data.ApiResponse;
import com.github.jooq.example.data.ApiRetCode;
import com.github.jooq.example.gen.tables.pojos.User;
import com.github.jooq.example.gen.tables.records.UserRecord;
import com.github.jooq.example.proto.RegisterProto;
import com.github.jooq.example.service.RegisterService;
import com.github.jooq.example.service.UserService;
import com.github.jooq.example.util.PasswordUtil;
import org.jooq.DSLContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


/**
 * 注册服务测试
 *
 * @author xupanpan9
 * @date 2019/05/10
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PasswordUtil.class)
public class RegisterServiceTest {
    /**
     * 前置条件：用户名为空，登录密码不为空
     * 期望结果：注册失败，返回参数错误 ApiRetCode.PARAMETER_EMPTY
     */
    @Test
    public void should_response_fail_with_empty_username() {
        RegisterService registerService = new RegisterService(mock(UserService.class), mock(DSLContext.class));
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("").password("123456").build();
        ApiResponse<String> response = registerService.register(registerReq);

        assertThat(response.getCode(), is(ApiRetCode.PARAMETER_EMPTY.getCode()));
    }

    /**
     * 前置条件：登录密码非6位数字
     * 期望结果：注册失败，返回登录密码格式错误 ApiRetCode.INVALID_SHORT_PASSWORD
     */
    @Test
    public void should_response_password_invalid_with_non_6_digits_pwd() {
        RegisterService registerService = new RegisterService(mock(UserService.class), mock(DSLContext.class));
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("test").password("aaa456").build();
        ApiResponse<String> response = registerService.register(registerReq);

        assertThat(response.getCode(), is(ApiRetCode.INVALID_SHORT_PASSWORD.getCode()));
    }

    /**
     * 前置条件：用已存在的登录帐号注册
     * 期望结果：注册失败，返回帐号已存在 ApiRetCode.USER_EXISTED
     */
    @Test
    public void should_response_user_existed_with_existed_username() {
        List<User> users = mock(List.class);
        when(users.size()).thenReturn(1);

        UserService userService = mock(UserService.class);
        when(userService.findByMobile(anyString())).thenReturn(users);

        RegisterService registerService = new RegisterService(userService, mock(DSLContext.class));
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("test").password("123456").build();
        ApiResponse<String> response = registerService.register(registerReq);

        assertThat(response.getCode(), is(ApiRetCode.USER_EXISTED.getCode()));
    }

    /**
     * mock/verify
     * 前置条件：使用正常不存在的帐号注册，密码格式正确
     * 期望结果：注册成功
     */
    @Test
    public void should_response_success_with_valid_input() {
        List<User> users = mock(List.class);
        when(users.size()).thenReturn(0);

        UserService userService = mock(UserService.class);
        when(userService.findByMobile(anyString())).thenReturn(users);

        RegisterService registerService = new RegisterService(userService, mock(DSLContext.class));
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("test").password("123456").build();
        ApiResponse<String> response = registerService.register(registerReq);

        UserRecord user = new UserRecord();
        user.setMobile(registerReq.getUsername());
        user.setPassword(PasswordUtil.getSha1Password(registerReq.getPassword()));

        // verify if the method userService.createUser is invoked, and the arguments is right.
        verify(userService).createUser(user);

        assertThat(response.getCode(), is(ApiRetCode.SUCCESS.getCode()));
    }

    /**
     * mock static
     * 前置条件：登录密码非6位数字
     * 期望结果：注册失败，返回登录密码格式错误 ApiRetCode.INVALID_SHORT_PASSWORD
     */
    @Test
    public void should_response_INVALID_SHORT_PASSWORD_with_invalid_password() {
        PowerMockito.mockStatic(PasswordUtil.class);
        PowerMockito.when(PasswordUtil.isValidShortPassword(anyString())).thenReturn(false);

        RegisterService registerService = new RegisterService(mock(UserService.class), mock(DSLContext.class));
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("test").password("123456").build();
        ApiResponse<String> response = registerService.register(registerReq);
        Assert.assertEquals(response.getCode(), ApiRetCode.INVALID_SHORT_PASSWORD.getCode());
    }
}
