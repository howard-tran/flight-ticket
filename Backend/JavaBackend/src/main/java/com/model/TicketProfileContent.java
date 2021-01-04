package com.model;

public enum TicketProfileContent {
  BABYTICKET("BABYTICKET"),
  MINORTICKET("MINORTICKET"),
  ADULTTICKET("ADULTTICKET");

  private String _data;

  TicketProfileContent(String data) {
    this._data = data;
  }

  @Override
  public String toString() {
    return this._data;
  }
}
