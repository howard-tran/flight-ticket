package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

import com.google.gson.Gson;
import com.model.AirlineData;
import com.model.Ticket;
import com.service.VietNameAirlineTicketService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class unitTest1 extends testCasePrint {

@Test
  public void testcase1() throws Exception {
    this.run(
      () -> {
        var tickets = VietNameAirlineTicketService.requestForTickets("fuck");
        for (int i = 0; i < tickets.size(); i++) {
          System.out.println(tickets.get(i).toString()); 
        }

        return null;
      });
  }
}
