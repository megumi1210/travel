package com.huike.travel.domain;

import java.util.Arrays;
import java.util.List;


/**
 * 分页结果的包装
 */
public class PageInfo<T> extends PageSerializable<T> {
  // 当前页
  private int pageNum;

  // 每页的数量
  private int pageSize;

  // 结果集的数量
  private int size;

  // 总页数
  private int pages;

  // 前一页
  private int prePage;
  // 后一页
  private int nextPage;

  // 是否为第一页
  private boolean isFirstPage = false;
  // 是否为最后一页
  private boolean isLastPage = false;
  // 是否有前一页
  private boolean hasPreviousPage = false;
  // 是否有下一页
  private boolean hasNextPage = false;
  // 导航页码数
  private int navigatePages;
  // 所有的导航页号集合
  private int[] navigatePageNums;
  // 导航页上的第一页
  private int navigateFirstPage;
  // 导航页上的最后一页
  private int navigateLastPage;

  public PageInfo() {}

  public PageInfo(List<T> list,PageParam pageParam,int total) {
    this(list, pageParam,total,8);
  }

  public PageInfo(List<T> list, PageParam pageParam,int total ,int navigatePages) {
    super(list);
    if(pageParam == null) pageParam = new PageParam();
    this.pageNum =  pageParam.getPageNum();
    this.pageSize = pageParam.getPageSize();
    this.total =total;
    this.pages = (total % pageSize == 0 ? total/pageSize : total/pageSize +1);
    this.size = list.size();
    this.navigatePages = navigatePages;

    // 计算导航页
    calcNavigatesNums();
    // 计算前后页，第一页，最后一页
    calcPage();
    // 判断页面边界
    judgePageBoundary();
  }


  public  static  <T> PageInfo<T> of(List<T> list ,PageParam pageParam,int total){ return  new PageInfo<>(list,pageParam,total);}

  public static <T> PageInfo<T> of(List<T> list, PageParam pageParam,int total,int navigatePages) {
    return new PageInfo<T>(list, pageParam,total,navigatePages);
  }

  /** 计算导航页 */
  private void calcNavigatesNums() {
    // 当总页数小于等于总导航数
    if (pages <= navigatePages) {
      navigatePageNums = new int[pages];
      for (int i = 0; i < pages; i++) {
        navigatePageNums[i] = i + 1;
      }
    } else { // 当总页数大于导航页码数时
      navigatePageNums = new int[navigatePages];
      int startNum = pageNum - navigatePages / 2;
      int endNum = pageNum + navigatePages / 2;

      if (startNum < 1) {
        startNum = 1;
        // 从第一页开始
        for (int i = 0; i < navigatePages; i++) {
          navigatePageNums[i] = startNum++;
        }
      } else if (endNum > pages) {
        endNum = pages;
        // 从最后一页开始
        for(int i = navigatePages -1 ;i >=0 ;i--){
           navigatePageNums[i] = endNum--;
        }
      } else {
        // 所有中间页
        for (int i = 0; i < navigatePages; i++) {
          navigatePageNums[i] = startNum++;
        }
      }
    }
  }

  /** 计算前后页,第一页，最后一页 */
  private void calcPage() {
    if (navigatePageNums != null && navigatePageNums.length > 0) {
      navigateFirstPage = navigatePageNums[0];
      navigateLastPage = navigatePageNums[navigatePageNums.length - 1];
    }

    if (pageNum > 1) {
      prePage = pageNum - 1;
    }
    if (pageNum < pages) {
      nextPage = pageNum + 1;
    }
  }


    /**
     * 判定页面边界
     */
    private void judgePageBoundary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages || pages == 0;
        hasPreviousPage = pageNum > 1;
        hasNextPage = pageNum < pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int[] getNavigatePageNums() {
        return navigatePageNums;
    }

    public void setNavigatePageNums(int[] navigatePageNums) {
        this.navigatePageNums = navigatePageNums;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "total=" + super.total+
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", pages=" + pages +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                ", navigatePages=" + navigatePages +
                ", navigatePageNums=" + Arrays.toString(navigatePageNums) +
                ", navigateFirstPage=" + navigateFirstPage +
                ", navigateLastPage=" + navigateLastPage +
                '}';
    }
}
