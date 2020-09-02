package com.github.jooq.example.service;

import com.github.jooq.example.data.ApiResponse;
import com.github.jooq.example.data.ApiRetCode;
import com.github.jooq.example.gen.tables.records.UserRecord;
import com.github.jooq.example.proto.RegisterProto;
import com.github.jooq.example.util.PasswordUtil;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Class comments
 *
 * @author xupanpan
 * @date 2019/05/09
 */
@Service
public class RegisterService extends BaseService {

    private UserService userService;

    public RegisterService(UserService userService, DSLContext dslContext) {
        super(dslContext);
        this.userService = userService;
    }

    /**
     * 用户注册
     *
     * @param registerReq
     */
    public ApiResponse<String> register(RegisterProto.RegisterReq registerReq) {
        if (StringUtils.isEmpty(registerReq.getUsername())) {
            return ApiResponse.fail(ApiRetCode.PARAMETER_EMPTY);
        }
        if (StringUtils.isEmpty(registerReq.getPassword())) {
            return ApiResponse.fail(ApiRetCode.PARAMETER_EMPTY);
        }
        if (!PasswordUtil.isValidShortPassword(registerReq.getPassword())) {
            return ApiResponse.fail(ApiRetCode.INVALID_SHORT_PASSWORD);
        }
        if (userService.findByMobile(registerReq.getUsername()).size() > 0) {
            return ApiResponse.fail(ApiRetCode.USER_EXISTED);
        }
        UserRecord user = new UserRecord();
        user.setMobile(registerReq.getUsername());
        user.setPassword(PasswordUtil.getSha1Password(registerReq.getPassword()));

        userService.createUser(user);
        return ApiResponse.success("注册成功");
    }
}
