package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dao.IFlightDao;
import com.dao.ITicketDao;
import com.helper.DatabaseSupplier;
import com.model.ListData;
import com.model.Ticket;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements LogService {
  private ITicketDao ticketDao;
  private IFlightDao flightDao;

  final int messageGetEach = 15;

  @Autowired
  TicketService(
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Ticket) ITicketDao ticketDao,
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Flight) IFlightDao flightDao
  ) {
    this.ticketDao = ticketDao; 
    this.flightDao = flightDao;
  }
}
