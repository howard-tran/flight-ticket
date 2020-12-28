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

  // option + index != nul, guaranteed
  private Optional<ListData<List<Ticket>>> handleTicketParams(
    int index, String airlineStart, String airlineEnd, Long datetime, String option
  ) {
    if (airlineStart != null && airlineEnd != null && datetime != null) {
      return this.resultOnTicketStatus(option, status -> {
        return this.ticketService.getTicketByRoute(airlineStart, airlineEnd, index, status, datetime);
      });
    }//
    else if (airlineStart != null && airlineEnd != null) {
      return this.resultOnTicketStatus(option, status -> {
        return this.ticketService.getTicketByRoute(airlineStart, airlineEnd, index, status, null);
      });
    }
    else if (airlineStart != null && datetime != null) {
      return this.resultOnTicketStatus(option, status -> {
        return this.ticketService.getTicketByAirlineStart(airlineStart, index, status, datetime);
      });
    }
    else if (airlineEnd != null && datetime != null) {
      return this.resultOnTicketStatus(option, status -> {
        return this.ticketService.getTicketByAirlineEnd(airlineEnd, index, status, datetime);
      });
    }
    else if (airlineStart != null) {
      return this.resultOnTicketStatus(option, status -> {
        return this.ticketService.getTicketByAirlineStart(airlineStart, index, status, null);
      });
    }
    else if (airlineEnd != null) {
      return this.resultOnTicketStatus(option, status -> {
        return this.ticketService.getTicketByAirlineEnd(airlineEnd, index, status, null);
      });
    }
    else if (datetime != null) {
      return this.resultOnTicketStatus(option, status -> {
        return this.ticketService.getTicket(index, status, datetime);
      });
    }
    else {
      return this.resultOnTicketStatus(option, status -> {
        return this.ticketService.getTicket(index, status, null);
      });
    }
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
    @RequestParam(name = "option", required = true) String option,
    @RequestParam(name = "airlineStart") String airlineStart,
    @RequestParam(name = "airlineEnd") String airlineEnd,
    @RequestParam(name="dateTime") Long datetime
  ) {
    var res = handleTicketParams(index, parseStringFromParams(airlineStart), 
      parseStringFromParams(airlineEnd), datetime, option);

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
