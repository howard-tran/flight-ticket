package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

import com.google.gson.Gson;
import com.helper.PropertyHelper;
import com.model.Agent;
import com.model.AgentData;
import com.model.Airline;
import com.model.AirlineData;
import com.model.Ticket;
import com.model.TicketStatus;
import com.service.AirplaneStatic;
import com.service.RouteStatic;
import com.service.TicketService;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class unitTest1 extends testCaseHandler {

  @Autowired
  private TicketService ticketService;

  @Test
  public void testcase1() throws Exception {
    this.runTestCase(
      () -> {
        int a = AirplaneStatic.getInstance().getAirplaneData("VN-VA").size();
        int b = RouteStatic.getInstance().getRouteData("VN-VA").size();

        System.out.println(a);
        System.out.println(b);
        return null;
      });
  }

  @Test
  public void testCase2() throws Exception {
    
  }
}
