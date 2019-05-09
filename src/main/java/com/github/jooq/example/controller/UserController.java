package com.github.jooq.example.controller;

import com.github.jooq.example.data.Page;
import com.github.jooq.example.gen.tables.pojos.User;
import com.github.jooq.example.gen.tables.records.UserRecord;
import com.github.jooq.example.proto.RegisterProto.RegisterReq;
import com.github.jooq.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "/USER")
@RestController
@RequestMapping("/")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation("注册")
  @PostMapping("/register")
  public User register(@RequestBody RegisterReq registerReq) {
    UserRecord user = new UserRecord();
    user.setMobile(registerReq.getUsername());
    user.setPassword(registerReq.getPassword());

    return userService.createUser(user);
  }

  @ApiOperation("查询")
  @GetMapping("/findByUsername")
  public List<User> findByUsername(@ApiParam("用户名") @RequestParam String username) {
    return userService.findByMobile(username);
  }

  @ApiOperation("分页")
  @GetMapping("/findByUsernamePaging")
  public Page<List<User>> findByUsernamePaging(
      @ApiParam("用户名") @RequestParam String username,
      @ApiParam(value = "当前页", defaultValue = "1") @RequestParam int currentPage,
      @ApiParam(value = "每页记录数", defaultValue = "2") @RequestParam int pageSize) {
    return userService.findByMobileWithPage(username, currentPage, pageSize);
  }
}
