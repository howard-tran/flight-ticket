package com.model;

public enum TicketType {
  ADULT("ADULT"), // 13 ->
  MINOR("MINOR"), // 3 -> 12
  BABY("BABY"); // 0 -> 2
  
  private String _data;

  TicketType(String data) {
    this._data = data;
  }

  @Override
  public String toString() {
    return this._data;
  }
}
