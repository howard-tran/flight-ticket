package com.model;

public enum ReceiptStatus {
  PENDING("PENDING"),
  PAID("PAID"),
  DESTROY("DESTROY");

  private String _data;

  ReceiptStatus(String data) {
    this._data = data;
  }

  @Override
  public String toString() {
    return this._data;
  }
}
