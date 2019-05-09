package com.github.jooq.example.data;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 查询分页结果
 *
 * @param <T> - 任意类
 */
@Getter
@Setter
@NoArgsConstructor
public class Page<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  public static int DEFAULT_CURRENT_PAGE = 1;

  public static int DEFAULT_PAGE_SIZE = 20;

  public static int DEFAULT_MAX_PAGE_SIZE = 1000;

  /**
   * 当前页数
   */
  private int currentPage;

  /**
   * 每页记录数
   */
  private int pageSize;

  /**
   * 总记录数
   */
  private long totalCount;

  /**
   * 总页数
   */
  private int totalPage;

  /**
   * 分页数据
   */
  private T dataList;

  /**
   * @param currentPage - 当前页数
   * @param pageSize - 每页记录数
   * @param totalCount - 总记录数
   * @param dataList - 当前页数据
   */
  public Page(int currentPage, int pageSize, long totalCount, T dataList) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.totalCount = totalCount;
    this.totalPage = (int) (totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0));
    this.dataList = dataList;
  }

  public Page(int currentPage, int pageSize) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
  }

  public int getCurrentPage() {
    if (currentPage == 0) {
      return DEFAULT_CURRENT_PAGE;
    }
    return currentPage;
  }

  public int getPageSize() {
    if (pageSize == 0) {
      return DEFAULT_PAGE_SIZE;
    }
    return pageSize;
  }
}
