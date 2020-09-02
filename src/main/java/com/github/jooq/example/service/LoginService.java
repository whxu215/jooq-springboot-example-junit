package com.github.jooq.example.service;

import com.github.jooq.example.data.ApiResponse;
import com.github.jooq.example.data.ApiRetCode;
import com.github.jooq.example.exception.AppException;
import com.github.jooq.example.gen.tables.pojos.User;
import com.github.jooq.example.util.Constants;
import com.github.jooq.example.util.PasswordUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2020/09/01
 */
@Service
public class LoginService {
    // 登录失败redis key模板
    public static final String LOGIN_FAILED_TIMES_REDIS_KEY_TPL = "web_login_failed_times_%s";

    private UserService userService;

    private RedisTemplate<String, String> redisTemplate;

    public LoginService(UserService userService, RedisTemplate<String, String> redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 用户名密码登录
     * @param mobile
     * @param password
     * @return
     */
    public ApiResponse<String> login(String mobile, String password) {
        if (!checkLoginFailedLimit(mobile)) {
            return ApiResponse.fail(ApiRetCode.USER_LOGIN_LIMITED);
        }
        List<User> users = userService.findByMobile(mobile);
        if (users.size() == 0) {
            return ApiResponse.fail(ApiRetCode.USER_NOT_EXISTED);
        }
        User user = users.get(0);
        checkPassword(user, password);
        return ApiResponse.success(UUID.randomUUID().toString());
    }

    /**
     * 验证登录密码
     * @param user
     * @param password
     */
    private void checkPassword(User user, String password) {
        String thePassword = PasswordUtil.getSha1Password(password);
        if (!Objects.equals(thePassword, user.getPassword())) {
            incrLoginFailedTimes(user.getMobile());
            throw new AppException(ApiRetCode.PASSWORD_ERROR);
        }
    }

    /**
     * 验证登录失败次数
     * @param username
     * @return
     */
    private boolean checkLoginFailedLimit(String username) {
        String redisKey = String.format(LOGIN_FAILED_TIMES_REDIS_KEY_TPL, username);
        String value = redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        int loginFailedTimes = Integer.parseInt(value);
        if (loginFailedTimes < Constants.LoginConfig.LOGIN_FAILED_TIMES_LIMIT) {
            return true;
        }
        return false;
    }


    /**
     * 新增登录失败次数，LOCK_USER_LOGIN_PERIOD时间后过期
     * @param username
     */
    private void incrLoginFailedTimes(String username) {
        String redisKey = String.format(LOGIN_FAILED_TIMES_REDIS_KEY_TPL, username);
        boolean isKeyExisted = redisTemplate.hasKey(redisKey);
        redisTemplate.opsForValue().increment(redisKey, 1);
        if (!isKeyExisted) {
            redisTemplate.expire(redisKey, Constants.LoginConfig.LOCK_USER_LOGIN_PERIOD, TimeUnit.SECONDS);
        }
    }
}
