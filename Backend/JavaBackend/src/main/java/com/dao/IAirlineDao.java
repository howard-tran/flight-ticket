package com.dao;

import com.model.Airline;

public interface IAirlineDao extends IMongoDBQueryLogic {
  public Airline getAirline(String id) throws Exception;
}
