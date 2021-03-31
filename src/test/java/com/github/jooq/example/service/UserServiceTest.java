package com.github.jooq.example.service;

import com.github.jooq.example.gen.tables.pojos.User;
import com.github.jooq.example.gen.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.InsertResultStep;
import org.jooq.InsertSetMoreStep;
import org.jooq.InsertSetStep;
import org.jooq.TableField;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.github.jooq.example.gen.tables.User.USER;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class comments
 *
 * @author xupanpan9
 * @date 2020/09/01
 */
public class UserServiceTest {
    /**
     * Mockito.mock
     * Mockito.verify
     */
    @Test
    public void test_create_user_success() {
        UserRecord userRecord = new UserRecord();
        userRecord.setMobile("13800000000");
        userRecord.setPassword("123456");
        userRecord.setOrgId(1);
        userRecord.setStatus(1);
        userRecord.setCreateTime(LocalDateTime.now());

        DSLContext dslContext = mock(DSLContext.class);
        InsertSetStep<UserRecord> insertSetStep = mock(InsertSetStep.class);
        InsertSetMoreStep<UserRecord> moreStep = mock(InsertSetMoreStep.class);
        when(dslContext.insertInto(USER)).thenReturn(insertSetStep);
        when(insertSetStep.set(USER.MOBILE, userRecord.getMobile())).thenReturn(moreStep);
        when(moreStep.set(any(TableField.class), anyString())).thenReturn(moreStep);
        when(moreStep.set(any(TableField.class), anyInt())).thenReturn(moreStep);
        when(moreStep.set(any(TableField.class), any(LocalDateTime.class))).thenReturn(moreStep);


        InsertResultStep<UserRecord> insertResultStep = mock(InsertResultStep.class);
        when(moreStep.returning(any(TableField.class))).thenReturn(insertResultStep);
        UserRecord newUserRecord = new UserRecord();
        newUserRecord.setUserId(1);
        when(insertResultStep.fetchOne()).thenReturn(newUserRecord);

        UserService userService = new UserService(dslContext);

        User user = userService.createUser(userRecord);
        Assert.assertTrue(user.getUserId() > 0);
        Assert.assertEquals(user.getMobile(), userRecord.getMobile());
        Assert.assertEquals(user.getPassword(), userRecord.getPassword());


        verify(dslContext).insertInto(USER);
        verify(insertSetStep).set(USER.MOBILE, userRecord.getMobile());
        verify(moreStep).set(USER.PASSWORD, userRecord.getPassword());
        verify(moreStep).set(USER.ORG_ID, 1);
        verify(moreStep).set(USER.STATUS, 1);

    }
}
