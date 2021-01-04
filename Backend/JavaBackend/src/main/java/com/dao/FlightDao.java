package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.helper.DatabaseSupplier;
import com.helper.PropertyHelper;
import com.helper.UnixHelper;
import com.model.Airplane;
import com.model.Flight;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository(DatabaseSupplier.MongoDB.FlightTicket.Flight)
public class FlightDao implements IFlightDao  {
  @Override
  public String insertFlight(Flight flight) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Flight", 
      collection -> {
        var json = new Gson().toJson(flight);
        var doc = Document.parse(json);

        collection.insertOne(doc);
        return doc.get("_id").toString();
      });
  }

  @Override
  public Integer updateFlight(Flight flight) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Flight",
      collection -> {
        var filter = new Document("_id", new ObjectId(flight.getId()));
        
        return (int)collection.updateOne(filter, this.toBsonDocument(flight))
          .getMatchedCount();
      });
  }

  @Override
  public Airplane getAirplaneModel(String id) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Airplane",
      collection -> {
        var filter = new Document("id", id);
        
        Document doc = collection.find(filter).first();
        if (doc == null) return null;

        return this.parse(doc, Airplane.class);
      });
  }

  @Override
  public Flight getFlight(String id) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Flight",
      collection -> {
        var filter = new Document("_id", new ObjectId(id));
        
        return this.parseWithId(collection.find(filter).first(), Flight.class);
      });
  }

  @Override
  public List<Flight> getFlight(int isExpired, String supplierId, String airlineStart, String airlineEnd,
      Long dateFlight) throws Exception {
    //
    return this.run(
      PropertyHelper.getMongoDB(), "Flight", 
      collection -> {
        var filter = new Document("isExpired", isExpired).append("supplierId", supplierId)
          .append("airlineStart", airlineStart).append("airlineEnd", airlineEnd);
        
        var res = new ArrayList<Flight>();
        for (var doc : collection.find(filter)) {
          Flight flight = this.parseWithId(doc, Flight.class);

          if (UnixHelper.equalUnixDate(dateFlight, flight.getFlightDate()))
            res.add(flight);
        }
        return res;
      });
  }

  @Override
  public List<Flight> getFlight(int isExpired, String airlineStart, String airlineEnd,
      Long dateFlight) throws Exception {
    //
    return this.run(
      PropertyHelper.getMongoDB(), "Flight", 
      collection -> {
        var filter = new Document("isExpired", isExpired).append("airlineStart", airlineStart)
          .append("airlineEnd", airlineEnd);
        
        var res = new ArrayList<Flight>();
        for (var doc : collection.find(filter)) {
          Flight flight = this.parseWithId(doc, Flight.class);

          if (UnixHelper.equalUnixDate(dateFlight, flight.getFlightDate()))
            res.add(flight);
        }
        return res;
      });
  }
}
