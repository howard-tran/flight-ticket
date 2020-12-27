package com.dao;

import java.util.List;

import com.model.Ticket;

public interface ITicketDao extends IMongoDBQueryLogic {
  public String insertTicket(Ticket ticket) throws Exception;
  public Long getTicketCountByStatus(String status) throws Exception;
  public Integer updateTicket(Ticket ticket) throws Exception;
  public Ticket getTicketById(String id) throws Exception;
  public List<Ticket> getTicket(String status) throws Exception;
  public List<Ticket> getTicket() throws Exception;
  public List<Ticket> getTicketByAirlineStart(String airlineStart, String status) throws Exception;
  public List<Ticket> getTicketByAirlineEnd(String airlineEnd, String status) throws Exception;
  public List<Ticket> getTicketByRoute(String airlineStart, String airlineEnd, String status) throws Exception;
}
