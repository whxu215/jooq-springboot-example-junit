package com.github.jooq.example.util;

/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2020/09/01
 */
public class Constants {
    // SHA-1签名密钥
    public static final String SHA1_SECRET_KEY = "REFEWJJF98KLOJOKK";

    public static final class LoginConfig {
        // 登录失败次数限制，在LOCK_USER_LOGIN_PERIOD时间内如果登录失败次数超了，则在LOCK_USER_LOGIN_PERIOD时间内不让用户登录
        public static final int LOGIN_FAILED_TIMES_LIMIT = 10;
        // 登录失败次数超了之后会冻结用户登录操作的时间(单位：秒)
        public static final int LOCK_USER_LOGIN_PERIOD = 3600;
    }
}
