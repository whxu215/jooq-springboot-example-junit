package com.github.jooq.example.service;

import com.github.jooq.example.data.Page;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
  protected DSLContext dslContext;

  protected BaseService(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  public <R1 extends Record> Page<List<R1>> selectPage(
      SelectConditionStep<R1> conditionStep, int currentPage,
      int pageSize) {
    int theCurrentPage = adjustCurrentPage(currentPage);
    int thePageSize = adjustPageSize(pageSize);

    int totalCount = dslContext.fetchCount(conditionStep);
    List<R1> resultList = Lists.newArrayList();
    if (totalCount > 0) {
      conditionStep.offset((theCurrentPage - 1) * thePageSize);
      conditionStep.limit(pageSize);
      resultList = conditionStep.fetch();
    }
    return new Page<>(theCurrentPage, thePageSize, totalCount, resultList);
  }

  public <R1 extends Record, R2 extends Serializable> Page<List<R2>> selectPage(
      SelectConditionStep<R1> conditionStep, Class<R2> resultClass, int currentPage,
      int pageSize) {
    Page<List<R1>> resultPage = selectPage(conditionStep, currentPage, pageSize);
    return new Page<>(resultPage.getCurrentPage(), resultPage.getPageSize(),
        resultPage.getTotalCount(),
        resultPage.getDataList().stream().map(f -> f.into(resultClass))
            .collect(Collectors.toList()));
  }

  private int adjustPageSize(int pageSize) {
    if (pageSize > 0 && pageSize <= Page.DEFAULT_MAX_PAGE_SIZE) {
      return pageSize;
    }
    if (pageSize <= 0) {
      return Page.DEFAULT_PAGE_SIZE;
    } else {
      return Page.DEFAULT_MAX_PAGE_SIZE;
    }
  }

  private int adjustCurrentPage(int currentPage) {
    return currentPage <= 0 ? Page.DEFAULT_CURRENT_PAGE : currentPage;
  }
}
