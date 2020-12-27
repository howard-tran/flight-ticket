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
import com.service.VietNamAirlineTicketService;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class updateSystem extends testCaseHandler {

  @Test
  public void updateAirline() throws Exception {
    this.runTestCase(
      () -> {
        String json = "";

        String filePath = App.class.getResource("/airline.json").getFile();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        
        while (true) {
          String t = br.readLine();
          if (t != null) {
            json += t;
          } else break;
        }

        AirlineData obj = new Gson().fromJson(json, AirlineData.class);
        
        this.run(PropertyHelper.getMongoDB(), "Airline", 
          collection -> {
            // clear before insert
            collection.deleteMany(new Document());

            // insert 
            for (Airline airline : obj.getAirlines()) {
              collection.insertOne(this.toBsonDocument(airline));
            }
            return null;
          });

        return null;
      }, "insert airline.json to dtb");
  }

  @Test
  public void updateAgent() throws Exception {
    this.runTestCase(
      () -> {
        String json = "";

        String filePath = App.class.getResource("/agent.json").getFile();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        
        while (true) {
          String t = br.readLine();
          if (t != null) {
            json += t;
          } else break;
        }

        AgentData obj = new Gson().fromJson(json, AgentData.class);
        
        this.run(PropertyHelper.getMongoDB(), "Agent", 
          collection -> {
            // clear before insert
            collection.deleteMany(new Document());

            // insert 
            for (Agent airline : obj.getAgents()) {
              collection.insertOne(this.toBsonDocument(airline));
            }
            return null;
          });

        return null;
      }, "insert agent.json to dtb");
  }
}
