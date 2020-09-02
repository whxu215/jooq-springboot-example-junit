package com.github.jooq.example.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2019/05/10
 */
public class PasswordUtilTest {
    @Test
    public void should_return_true_with_6_digit_password() {
        Assert.assertTrue(PasswordUtil.isValidShortPassword("123456"));
    }

    @Test
    public void should_return_false_with_6_alphabet_digit_mixed_password() {
        Assert.assertFalse(PasswordUtil.isValidShortPassword("abc456"));
    }

    @Test
    public void should_return_false_with_non_6_digit_mixed_password() {
        Assert.assertFalse(PasswordUtil.isValidShortPassword("12345678"));
    }
}
