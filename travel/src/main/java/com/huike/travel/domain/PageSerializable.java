package com.huike.travel.domain;

import java.io.Serializable;
import java.util.List;

public class PageSerializable<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  // 总记录数
  protected int total;
  // 结果集
  protected List<T> list;

  public  PageSerializable(){

  }

  public PageSerializable(List<T> list) {
    this.list = list;
  }

  public static <T> PageSerializable<T> of(List<T> list) {
    return new PageSerializable<T>(list);
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "PageSerializable{" + "total=" + total + ", list=" + list + '}';
  }
}
