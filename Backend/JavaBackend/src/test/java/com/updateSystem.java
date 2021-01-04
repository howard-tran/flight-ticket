package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.helper.IFunction;
import com.helper.IFunction2;
import com.helper.IFunction3;
import com.helper.IFunction4;
import com.helper.PropertyHelper;
import com.model.Agent;
import com.model.AgentData;
import com.model.Airline;
import com.model.AirlineData;
import com.model.Airplane;
import com.model.Route;
import com.model.TicketSupplier;
import com.service.RouteStatic;
import com.model.Ticket;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class updateSystem extends testCaseHandler {

  @Test // update airline
  public void updateSystem1() throws Exception {
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

  @Test // update agent
  public void updateSystem2() throws Exception {
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

  @Test // update supplier
  public void updateSystem3() throws Exception {
    this.runTestCase(
      () -> {
        String json = "";

        String filePath = App.class.getResource("/supplier.json").getFile();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        
        while (true) {
          String t = br.readLine();
          if (t != null) {
            json += t;
          } else break;
        }
        Type listType = new TypeToken<List<TicketSupplier>>() {}.getType();
        List<TicketSupplier> obj = new Gson().fromJson(json, listType);
        
        this.run(PropertyHelper.getMongoDB(), "Supplier", 
          collection -> {
            // clear before insert
            collection.deleteMany(new Document());

            // insert 
            for (TicketSupplier airline : obj) {
              collection.insertOne(this.toBsonDocument(airline));
            }
            return null;
          });

        return null;
      }, "insert supplier.json to dtb");
  }

  @Test // update airplane
  public void updateSystem4() throws Exception {

    IFunction<Integer> randomStartAirplaneId = () -> {
      return new Random().nextInt(501) + 100; 
    };

    IFunction<String> randomAirplaneModel = () -> {
      String arr[] = {"Boeing 737", "Airbus A321", "Airbus A320"};
      return arr[new Random().nextInt(arr.length)];
    };

    this.runTestCase(
      () -> {
        List<TicketSupplier> listSupplier = new ArrayList<>();
        // get supplier list
        this.run(PropertyHelper.getMongoDB(), "Supplier", 
          collection -> {
            for (var doc : collection.find()) {
              listSupplier.add(this.parse(doc, TicketSupplier.class));
            }
            return null;
          });
        
        this.run(PropertyHelper.getMongoDB(), "Airplane",
          collection -> {
            collection.deleteMany(new Document());

            for (int i = 0; i < listSupplier.size(); i++) {
              final int airplaneSize = 1000;

              int x = randomStartAirplaneId.run() + airplaneSize;
              for (Integer j = x - airplaneSize; j <= x; j++) {
                var airplaneId = listSupplier.get(i).getId() + j.toString();

                var airplane = new Airplane(airplaneId, 
                  listSupplier.get(i).getId(), randomAirplaneModel.run());
                  
                collection.insertOne(this.toBsonDocument(airplane));
              }
            }

            return null;
          });

        return null;
      }, "insert airplane to dtb");
  }

  @Test // update route
  public void updateSystem5() throws Exception {
    //
    List<TicketSupplier> listSupplier = new ArrayList<>();
    List<Airline> listAirline = new ArrayList<>();

    IFunction4<String, Integer, Integer, List<Route>> generateRoute_t = (supplier, currentSupplier, totalSupplier) -> {
      List<Route> res = new ArrayList<>(), resFinal = new ArrayList<>();
  
      for (int i = 0; i < listAirline.size() - 1; i++) {
        for (int j = i + 1; j < listAirline.size(); j++) {
          var airlineStart = listAirline.get(i).getId();
          var airlineEnd = listAirline.get(j).getId();
  
          var route = new Route(airlineStart, airlineEnd, supplier); 
          res.add(route);
        }
      }
  
      int size_t = res.size(); 
      
      for (int i = 0; i < size_t; i++) {
        var airlineStart = res.get(i).getAirlineEnd();
        var airlineEnd = res.get(i).getAirlineStart();
  
        var route = new Route(airlineStart, airlineEnd, supplier); 
        res.add(route);
      }

      return res;
    };
  
    IFunction<List<Route>> generateRoute = () -> {
      List<Route> res = new ArrayList<>();
      for (int i = 0; i < listSupplier.size(); i++) {
        res.addAll(generateRoute_t.run(listSupplier.get(i).getId(), i, listSupplier.size()));
      }
      return res;
    };

    this.runTestCase(
      () -> {
        // get airline list
        this.run(PropertyHelper.getMongoDB(), "Airline",
          collection -> {
            for (var doc : collection.find()) {
              listAirline.add(this.parse(doc, Airline.class));
            }
            return null;
          });

        // get supplier list
        this.run(PropertyHelper.getMongoDB(), "Supplier", 
          collection -> {
            for (var doc : collection.find()) {
              listSupplier.add(this.parse(doc, TicketSupplier.class));
            }
            return null;
          });
        
        this.run(PropertyHelper.getMongoDB(), "Route",
          collection -> {
            List<Route> routeList = generateRoute.run();

            collection.deleteMany(new Document());

            for (var route : routeList) {
              collection.insertOne(this.toBsonDocument(route));
            }

            return null;
          });
        
        return null;
      }, "insert route to dtb");
  }
}
