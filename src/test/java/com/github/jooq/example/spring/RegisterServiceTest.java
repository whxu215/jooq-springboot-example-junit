package com.github.jooq.example.spring;

import com.github.jooq.example.data.ApiResponse;
import com.github.jooq.example.data.ApiRetCode;
import com.github.jooq.example.gen.tables.pojos.User;
import com.github.jooq.example.gen.tables.records.UserRecord;
import com.github.jooq.example.proto.RegisterProto;
import com.github.jooq.example.service.RegisterService;
import com.github.jooq.example.service.UserService;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2019/05/10
 */
public class RegisterServiceTest {
    @Test
    public void should_response_fail_with_empty_username() {
        RegisterService registerService = new RegisterService(mock(UserService.class));
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("").password("123456").build();
        ApiResponse<String> response = registerService.register(registerReq);

        assertThat(response.getCode(), is(ApiRetCode.PARAMETER_EMPTY.getCode()));
    }

    @Test
    public void should_response_password_invalid_with_non_6_digits_pwd() {
        RegisterService registerService = new RegisterService(mock(UserService.class));
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("test").password("aaa456").build();
        ApiResponse<String> response = registerService.register(registerReq);

        assertThat(response.getCode(), is(ApiRetCode.INVALID_SHORT_PASSWORD.getCode()));
    }

    @Test
    public void should_response_user_existed_with_existed_username() {
        List<User> users = mock(List.class);
        when(users.size()).thenReturn(1);

        UserService userService = mock(UserService.class);
        when(userService.findByMobile(anyString())).thenReturn(users);

        RegisterService registerService = new RegisterService(userService);
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("test").password("123456").build();
        ApiResponse<String> response = registerService.register(registerReq);

        assertThat(response.getCode(), is(ApiRetCode.USER_EXISTED.getCode()));
    }

    @Test
    public void should_response_success_with_valid_input() {
        List<User> users = mock(List.class);
        when(users.size()).thenReturn(0);

        UserService userService = mock(UserService.class);
        when(userService.findByMobile(anyString())).thenReturn(users);

        RegisterService registerService = new RegisterService(userService);
        RegisterProto.RegisterReq registerReq = RegisterProto.RegisterReq.builder().username("test").password("123456").build();
        ApiResponse<String> response = registerService.register(registerReq);

        UserRecord user = new UserRecord();
        user.setMobile(registerReq.getUsername());
        user.setPassword(registerReq.getPassword());

        // verify if the method userService.createUser is invoked, and the arguments is right.
        verify(userService).createUser(user);

        assertThat(response.getCode(), is(ApiRetCode.SUCCESS.getCode()));
    }
}
