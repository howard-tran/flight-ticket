package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dao.ITicketDao;
import com.helper.DatabaseSupplier;
import com.helper.LogUtils;
import com.model.ListData;
import com.model.Ticket;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements LogService {
  private ITicketDao ticketDao;

  final int messageGetEach = 15;

  @Autowired
  TicketService(
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Ticket) ITicketDao ticketDao
  ) {
    this.ticketDao = ticketDao; 
  }

  private boolean equalUnixDate(Long unix1, Long unix2) {
    DateTime dt1 = new DateTime(unix1);
    DateTime dt2 = new DateTime(unix2);

    return dt1.getYear() == dt2.getYear()
      && dt1.getMonthOfYear() == dt2.getMonthOfYear()
      && dt1.getDayOfMonth() == dt2.getDayOfMonth();
  }

  private List<Ticket> filterByDate(List<Ticket> arr, Long datetime) {
    List<Ticket> res = new ArrayList<>();
    for (int i = 0; i < arr.size(); i++) {
      if (this.equalUnixDate(arr.get(i).getFlightDate(), datetime)) {
        res.add(arr.get(i));
      }
    }
    return res;
  }

  public Optional<ListData<List<Ticket>>> getTicket(int index, String status, Long datetime) {
    return this.runOptional(() -> {
      List<Ticket> list = this.ticketDao.getTicket(status);
      
      if (datetime != null) 
        list = this.filterByDate(list, datetime);

      if (index < list.size()) {
        if (index + messageGetEach <= list.size()) {
          return new ListData<List<Ticket>>(
            list.subList(index, index + messageGetEach), 
            list.size());
        } 
        else {
          return new ListData<List<Ticket>>(
            list.subList(index, list.size()), 
            list.size());
        } 
      }
      return new ListData<List<Ticket>>(new ArrayList<>(), list.size());
    });
  }

  public Optional<ListData<List<Ticket>>> getTicket(int index, Long datetime) {
    return this.runOptional(() -> {
      List<Ticket> list = this.ticketDao.getTicket();

      if (datetime != null) 
        list = this.filterByDate(list, datetime);

      if (index < list.size()) {
        if (index + messageGetEach <= list.size()) {
          return new ListData<List<Ticket>>(
            list.subList(index, index + messageGetEach), 
            list.size());
        } 
        else {
          return new ListData<List<Ticket>>(
            list.subList(index, list.size()), 
            list.size());
        } 
      }
      return new ListData<List<Ticket>>(new ArrayList<>(), list.size());
    });
  }

  public Optional<ListData<List<Ticket>>> getTicketByRoute(
    String airlineStart, String airlineEnd, int index, String status, Long datetime) {

    return this.runOptional(() -> {
      List<Ticket> list = this.ticketDao.getTicketByRoute(airlineStart, airlineEnd, status);

      if (datetime != null) 
        list = this.filterByDate(list, datetime);

      if (index < list.size()) {
        if (index + messageGetEach <= list.size()) {
          return new ListData<List<Ticket>>(
            list.subList(index, index + messageGetEach), 
            list.size());
        } 
        else {
          return new ListData<List<Ticket>>(
            list.subList(index, list.size()), 
            list.size());
        } 
      }
      return new ListData<List<Ticket>>(new ArrayList<>(), list.size());
    });
  }

  public Optional<ListData<List<Ticket>>> getTicketByAirlineStart(
    String airlineStart, int index, String status, Long datetime
  ) {
    return this.runOptional(() -> {
      List<Ticket> list = this.ticketDao.getTicketByAirlineStart(airlineStart, status);

      if (datetime != null) 
        list = this.filterByDate(list, datetime);

      if (index < list.size()) {
        if (index + messageGetEach <= list.size()) {
          return new ListData<List<Ticket>>(
            list.subList(index, index + messageGetEach), 
            list.size());
        } 
        else {
          return new ListData<List<Ticket>>(
            list.subList(index, list.size()), 
            list.size());
        } 
      }
      return new ListData<List<Ticket>>(new ArrayList<>(), list.size());
    });
  }

  public Optional<ListData<List<Ticket>>> getTicketByAirlineEnd(
    String airlineEnd, int index, String status, Long datetime
  ) {
    return this.runOptional(() -> {
      List<Ticket> list = this.ticketDao.getTicketByAirlineEnd(airlineEnd, status);

      if (datetime != null) 
        list = this.filterByDate(list, datetime);

      if (index < list.size()) {
        if (index + messageGetEach <= list.size()) {
          return new ListData<List<Ticket>>(
            list.subList(index, index + messageGetEach), 
            list.size());
        } 
        else {
          return new ListData<List<Ticket>>(
            list.subList(index, list.size()), 
            list.size());
        } 
      }
      return new ListData<List<Ticket>>(new ArrayList<>(), list.size());
    });
  }
}
