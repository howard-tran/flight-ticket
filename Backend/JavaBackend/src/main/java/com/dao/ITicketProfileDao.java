package com.dao;

import java.util.List;

import com.model.TicketProfile;

public interface ITicketProfileDao extends IMongoDBQueryLogic {
  public String insertTicketProfile(TicketProfile ticketProfile) throws Exception;
  public List<TicketProfile> get(String ticketId) throws Exception;
}
