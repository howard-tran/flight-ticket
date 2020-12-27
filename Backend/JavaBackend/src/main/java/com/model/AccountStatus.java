package com.model;

public enum AccountStatus {
  ACTIVE("ACTIVE"),
  DISABLED("DISABLED");
  
  private String _data;

  AccountStatus(String data) {
    this._data = data;
  }

  @Override
  public String toString() {
    return this._data;
  }
}
