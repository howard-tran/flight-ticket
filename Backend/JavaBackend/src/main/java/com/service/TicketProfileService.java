package com.service;

import java.util.List;
import java.util.Optional;

import com.dao.ITicketProfileDao;
import com.model.ListData;
import com.model.TicketProfile;
import com.helper.DatabaseSupplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TicketProfileService implements LogService {
  private ITicketProfileDao ticketProfileDao;

  @Autowired
  TicketProfileService(
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.TicketProfile) ITicketProfileDao ticketProfileDao
  ) {
    this.ticketProfileDao = ticketProfileDao;
  }

  public Optional<ListData<List<TicketProfile>>> getTicketProfile(String ticketId) {
    return this.runOptional(() -> {
      List<TicketProfile> list = this.ticketProfileDao.get(ticketId);
      return new ListData<List<TicketProfile>>(list, list.size());
    });
  }

  public Optional<String> addTicketProfile(TicketProfile ticketProfile) {
    return this.runOptional(() -> {
      return this.ticketProfileDao.insertTicketProfile(ticketProfile);
    });
  }
}