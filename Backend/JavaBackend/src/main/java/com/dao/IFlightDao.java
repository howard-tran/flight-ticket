package com.dao;

import java.util.List;

import com.model.Flight;

public interface IFlightDao extends IMongoDBQueryLogic {
  public String insertFlight(Flight flight) throws Exception;
  public Integer updateFlight(Flight flight) throws Exception;
  public Flight getFlight(String id) throws Exception;
  public List<Flight> getFlight(int isExpired, String supplierId, 
    String airlineStart, String airlineEnd, Long dateFlight) throws Exception;
  
  public List<Flight> getFlight(int isExpired, String airlineStart, String airlineEnd, Long dateFlight) throws Exception;
}
