package com.model;

public enum TicketSeatType {
  BUSINESS("BUSINESS"),
  PRENIUM("PRENIUM"),
  ECONOMY("ECONOMY");

  private String _data;

  TicketSeatType(String data) {
    this._data = data;
  }

  @Override
  public String toString() {
    return this._data;
  }
}
