package com.controller;

import java.util.List;
import java.util.Optional;

import com.App;
import com.helper.IFunction2;
import com.model.ListData;
import com.model.Ticket;
import com.model.TicketProfile;
import com.model.TicketRequestStatus;
import com.model.TicketStatus;
import com.service.LogService;
import com.service.TicketProfileService;
import com.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(App.service + "/ticket")
@RestController
public class TicketController implements LogService {

  @Autowired
  private TicketService ticketService;

  @Autowired
  private TicketProfileService ticketProfileService;

  private <T> T resultOnTicketStatus(String option, IFunction2<String, T> func) {
    if (option.equals(TicketRequestStatus.NEW.toString())) {
      return (T)this.run(() -> {
        return func.run(TicketStatus.NEW_TICKET.toString());
      });
      //
    } else if (option.equals(TicketRequestStatus.PENDING.toString())) {
      return (T)this.run(() -> {
        return func.run(TicketStatus.PENDING_TICKET.toString());
      });
        //
    } else if (option.equals(TicketRequestStatus.EXPIRED.toString())) {
      return (T)this.run(() -> {
        return func.run(TicketStatus.EXPIRED_TICKET.toString());
      });
    }
    return null;
  }

  private String parseStringFromParams(String value) {
    if (value == "") {
      return null;
    }
    return value;
  }

  @GetMapping("/get")
  public Response<Object> get(
    @RequestParam(name = "index", required = true) int index, 
    @RequestParam(name = "airlineStart") String airlineStart,
    @RequestParam(name = "airlineEnd") String airlineEnd,
    @RequestParam(name = "seatClass") String seatClass,
    @RequestParam(name = "dateFlight") Long dateFlight,
    @RequestParam(name="customerCount") int customerCount
  ) {
    var res = this.ticketService.searchFlight(airlineStart, airlineEnd, seatClass, 
      dateFlight, customerCount, index);

    if (res == null) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @GetMapping("/getFilterSupplier")
  public Response<Object> get(
    @RequestParam(name = "index", required = true) int index, 
    @RequestParam(name = "supplierId") String supplierId,
    @RequestParam(name = "airlineStart") String airlineStart,
    @RequestParam(name = "airlineEnd") String airlineEnd,
    @RequestParam(name = "seatClass") String seatClass,
    @RequestParam(name = "dateFlight") Long dateFlight,
    @RequestParam(name="customerCount") int customerCount
  ) {
    var res = this.ticketService.searchFlight(supplierId, airlineStart, airlineEnd, seatClass, 
      dateFlight, customerCount, index);

    if (res == null) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @GetMapping("/getAirline")
  public Response<Object> getAirline(
    @RequestParam(name = "id", required = true) String id 
  ) {
    var res = this.ticketService.getAirline(id);

    if (res == null) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @GetMapping("/getAirplaneModel")
  public Response<Object> getAirplaneModel(
    @RequestParam(name = "id", required = true) String id 
  ) {
    var res = this.ticketService.getAirplane(id);

    if (res == null) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @PostMapping("/addProfile")
  public Response<Object> addProfile(
    @RequestBody TicketProfile ticketProfile
  ) {
    var res = this.ticketProfileService.addTicketProfile(ticketProfile);

    if (res == null) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @GetMapping("/getProfile")
  public Response<Object> getProfile(
    @RequestParam(name = "ticketId") String ticketId
  ) {
    var res = this.ticketProfileService.getTicketProfile(ticketId);

    if (res == null) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }
}
