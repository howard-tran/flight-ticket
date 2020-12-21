package com.model;

public enum AccountType {
  MANAGER("MANAGER"),
  STAFF("STAFF"),
  CUSTOMER("CUSTOMER");

  private String _data;

  AccountType(String data) {
    this._data = data;
  }

  @Override
  public String toString() {
    return this._data;
  }
}
