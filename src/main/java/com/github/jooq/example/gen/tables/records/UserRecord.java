/*
 * This file is generated by jOOQ.
 */
package com.github.jooq.example.gen.tables.records;


import com.github.jooq.example.gen.tables.User;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 管理员信息
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record6<Integer, String, String, Integer, Integer, LocalDateTime> {

    private static final long serialVersionUID = 1582074648;

    /**
     * Setter for <code>test.user.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>test.user.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>test.user.mobile</code>. 手机号
     */
    public void setMobile(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>test.user.mobile</code>. 手机号
     */
    public String getMobile() {
        return (String) get(1);
    }

    /**
     * Setter for <code>test.user.password</code>. 登陆密码
     */
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>test.user.password</code>. 登陆密码
     */
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>test.user.org_id</code>. 企业ID
     */
    public void setOrgId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>test.user.org_id</code>. 企业ID
     */
    public Integer getOrgId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>test.user.status</code>. 管理员状态：(0 可用，1 已锁定, 2 不可用)
     */
    public void setStatus(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>test.user.status</code>. 管理员状态：(0 可用，1 已锁定, 2 不可用)
     */
    public Integer getStatus() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>test.user.create_time</code>. 注册时间
     */
    public void setCreateTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>test.user.create_time</code>. 注册时间
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, Integer, Integer, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, String, Integer, Integer, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return User.USER.USER_ID;
    }

    @Override
    public Field<String> field2() {
        return User.USER.MOBILE;
    }

    @Override
    public Field<String> field3() {
        return User.USER.PASSWORD;
    }

    @Override
    public Field<Integer> field4() {
        return User.USER.ORG_ID;
    }

    @Override
    public Field<Integer> field5() {
        return User.USER.STATUS;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return User.USER.CREATE_TIME;
    }

    @Override
    public Integer component1() {
        return getUserId();
    }

    @Override
    public String component2() {
        return getMobile();
    }

    @Override
    public String component3() {
        return getPassword();
    }

    @Override
    public Integer component4() {
        return getOrgId();
    }

    @Override
    public Integer component5() {
        return getStatus();
    }

    @Override
    public LocalDateTime component6() {
        return getCreateTime();
    }

    @Override
    public Integer value1() {
        return getUserId();
    }

    @Override
    public String value2() {
        return getMobile();
    }

    @Override
    public String value3() {
        return getPassword();
    }

    @Override
    public Integer value4() {
        return getOrgId();
    }

    @Override
    public Integer value5() {
        return getStatus();
    }

    @Override
    public LocalDateTime value6() {
        return getCreateTime();
    }

    @Override
    public UserRecord value1(Integer value) {
        setUserId(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setMobile(value);
        return this;
    }

    @Override
    public UserRecord value3(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UserRecord value4(Integer value) {
        setOrgId(value);
        return this;
    }

    @Override
    public UserRecord value5(Integer value) {
        setStatus(value);
        return this;
    }

    @Override
    public UserRecord value6(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    @Override
    public UserRecord values(Integer value1, String value2, String value3, Integer value4, Integer value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Integer userId, String mobile, String password, Integer orgId, Integer status, LocalDateTime createTime) {
        super(User.USER);

        set(0, userId);
        set(1, mobile);
        set(2, password);
        set(3, orgId);
        set(4, status);
        set(5, createTime);
    }
}
