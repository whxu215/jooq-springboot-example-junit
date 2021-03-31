package com.github.jooq.example.service;

import com.github.jooq.example.data.Page;
import com.github.jooq.example.gen.tables.pojos.User;
import com.github.jooq.example.gen.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.jooq.example.gen.tables.User.USER;

@Service
public class UserService extends BaseService {
  public UserService(DSLContext dslContext) {
    super(dslContext);
  }

  public User createUser(UserRecord user) {
    UserRecord newUser = dslContext.insertInto(USER)
        .set(USER.MOBILE, user.getMobile())
        .set(USER.PASSWORD, user.getPassword())
        .set(USER.ORG_ID, 1)
        .set(USER.STATUS, 1)
        .set(USER.CREATE_TIME, user.getCreateTime())
        .returning(USER.USER_ID)
        .fetchOne();
    user.setUserId(newUser.getUserId());
    return user.into(User.class);
  }

  public List<User> findByMobile(String mobile) {
    return dslContext.selectFrom(USER).where(
        USER.MOBILE.like("%" + mobile + "%")).fetch().map(f -> f.into(User.class));
  }

  public Page<List<User>> findByMobileWithPage(String mobile, int currentPage, int pageSize) {
    SelectConditionStep<UserRecord> conditionStep = dslContext.selectFrom(USER)
        .where(USER.MOBILE.like(mobile + "%"));
    return selectPage(conditionStep, User.class, currentPage, pageSize);
  }
}
