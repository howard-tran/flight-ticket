package com.model;

import java.util.Objects;

public class ListData<T> {
  private T array;
  private int dtbSize;

  public ListData() {
  }

  public ListData(T array, int dtbSize) {
    this.array = array;
    this.dtbSize = dtbSize;
  }

  public T getArray() {
    return this.array;
  }

  public void setArray(T array) {
    this.array = array;
  }

  public int getDtbSize() {
    return this.dtbSize;
  }

  public void setDtbSize(int dtbSize) {
    this.dtbSize = dtbSize;
  }

  public ListData array(T array) {
    this.array = array;
    return this;
  }

  public ListData dtbSize(int dtbSize) {
    this.dtbSize = dtbSize;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ListData)) {
            return false;
        }
        ListData listData = (ListData) o;
        return Objects.equals(array, listData.array) && dtbSize == listData.dtbSize;
  }

  @Override
  public int hashCode() {
    return Objects.hash(array, dtbSize);
  }

  @Override
  public String toString() {
    return "{" +
      " array='" + getArray() + "'" +
      ", dtbSize='" + getDtbSize() + "'" +
      "}";
  }
}
