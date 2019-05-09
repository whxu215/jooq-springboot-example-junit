package com.github.jooq.example.util;

import java.util.regex.Pattern;

/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2019/05/10
 */
public class PasswordUtil {
    /**
     * 是否为合法的短密码
     * @param password
     * @return
     */
    public static boolean isValidShortPassword(String password) {
        return Pattern.matches("\\d{6}", password);
    }
}
