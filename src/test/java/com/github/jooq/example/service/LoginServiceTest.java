package com.github.jooq.example.service;

import com.github.jooq.example.data.ApiRetCode;
import com.github.jooq.example.exception.AppException;
import com.github.jooq.example.gen.tables.pojos.User;
import com.github.jooq.example.util.Constants;
import com.github.jooq.example.util.PasswordUtil;
import com.google.common.collect.Lists;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static com.github.jooq.example.service.LoginService.LOGIN_FAILED_TIMES_REDIS_KEY_TPL;
import static org.mockito.Mockito.*;

/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2020/09/02
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(LoginService.class)
public class LoginServiceTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    /**
     * Exception Test
     * Mock Private
     * 支付密码错误时抛异常
     */
    @Test
    public void should_throw_exception_with_wrong_password() throws Exception {
        expectedEx.expect(AppException.class);
        expectedEx.expectMessage(ApiRetCode.PASSWORD_ERROR.getMessage());

        User user = defaultUser();
        UserService userService = mock(UserService.class);
        when(userService.findByMobile(user.getMobile())).thenReturn(Lists.newArrayList(user));

        LoginService loginService = new LoginService(userService, redisTemplate);
        // spy target object
        loginService = PowerMockito.spy(loginService);
        PowerMockito.doReturn(true).when(loginService, "checkLoginFailedLimit", user.getMobile());
        PowerMockito.doAnswer(t -> t).when(loginService, "incrLoginFailedTimes", user.getMobile());
        loginService.login(user.getMobile(), "666666");
    }

    /**
     * Exception Test
     * Mock Private
     * 支付密码错误时记录登录错误次数
     */
    @Test
    public void should_incr_login_error_times_with_wrong_password() throws Exception {
        User user = defaultUser();
        UserService userService = mock(UserService.class);
        when(userService.findByMobile(user.getMobile())).thenReturn(Lists.newArrayList(user));

        LoginService loginService = new LoginService(userService, redisTemplate);
        // spy target object
        loginService = PowerMockito.spy(loginService);
        PowerMockito.doReturn(true).when(loginService, "checkLoginFailedLimit", user.getMobile());
        //PowerMockito.doAnswer(t -> t).when(loginService, "incrLoginFailedTimes", user.getMobile());
        String redisKey = String.format(LOGIN_FAILED_TIMES_REDIS_KEY_TPL, user.getMobile());
        when(redisTemplate.hasKey(redisKey)).thenReturn(false);
        ValueOperations<String, String> valueOperations = mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        try {
            loginService.login(user.getMobile(), "666666");
        } catch (AppException e) {
            // ignore
        }

        // 验证支付密码失败时失败次数增加1
        verify(valueOperations).increment(redisKey, 1);
        // 验证如果失败次数redis key不存在时，设置过期时间
        verify(redisTemplate).expire(redisKey, Constants.LoginConfig.LOCK_USER_LOGIN_PERIOD, TimeUnit.SECONDS);
    }

    private User defaultUser() {
        User user = new User();
        user.setMobile("13800000000");
        user.setPassword(PasswordUtil.getSha1Password("123456"));
        return user;
    }
}
