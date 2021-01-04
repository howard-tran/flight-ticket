package com.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dao.IAirlineDao;
import com.dao.IFlightDao;
import com.dao.ITicketDao;
import com.helper.DatabaseSupplier;
import com.model.Airline;
import com.model.Flight;
import com.model.Ticket;
import com.model.ListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements LogService {
  private ITicketDao ticketDao;
  private IFlightDao flightDao;
  private IAirlineDao airlineDao;

  @Autowired
  TicketService(
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Ticket) ITicketDao ticketDao,
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Flight) IFlightDao flightDao,
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Airline) IAirlineDao airlineDao
  ) {
    this.ticketDao = ticketDao; 
    this.flightDao = flightDao;
    this.airlineDao = airlineDao;
  }

  public Optional<Airline> getAirline(String id) {
    return this.runOptional(
      () -> {
        return this.airlineDao.getAirline(id);
      }
    );
  }

  public Optional<ListData<List<Flight>>> searchFlight(String supplierId, String airlineStart, String airlineEnd, 
    String seatClass, Long dateFlight, int customerCount, int index) {

    final int getSize = 30;

    return this.runOptional(
      () -> {
        List<Flight> list = new ArrayList<>(), 
          checkList = this.flightDao.getFlight(0, supplierId, airlineStart, 
            airlineEnd, dateFlight);
        
        for (int i = 0; i < checkList.size(); i++) {
          List<Ticket> tickets = this.ticketDao.getTicket(checkList.get(i).getId(), seatClass);

          if (tickets.size() == 0)
            continue;
            
          if (tickets.get(0).getCount() >= customerCount) {
            list.add(checkList.get(i));
          }
        }

        if (index < list.size()) {
          if (index + getSize <= list.size()) {
            return new ListData<>(list.subList(index, index + getSize), list.size());
          } 
          else return new ListData<>(list.subList(index, list.size()), list.size());
        }
        return new ListData<>(new ArrayList<>(), 0);
      });
  } 

  public Optional<ListData<List<Flight>>> searchFlight(String airlineStart, String airlineEnd, 
    String seatClass, Long dateFlight, int customerCount, int index) {

    final int getSize = 30;

    return this.runOptional(
      () -> {
        List<Flight> list = new ArrayList<>(), 
          checkList = this.flightDao.getFlight(0, airlineStart, 
            airlineEnd, dateFlight);

        for (int i = 0; i < checkList.size(); i++) {
          List<Ticket> tickets = this.ticketDao.getTicket(checkList.get(i).getId(), seatClass);

          if (tickets.size() == 0)
            continue;

          if (tickets.get(0).getCount() >= customerCount) {
            list.add(checkList.get(i));
          }
        }

        if (index < list.size()) {
          if (index + getSize <= list.size()) {
            return new ListData<>(list.subList(index, index + getSize), list.size());
          } 
          else return new ListData<>(list.subList(index, list.size()), list.size());
        }
        return new ListData<>(new ArrayList<>(), 0);
      });
  }
}
