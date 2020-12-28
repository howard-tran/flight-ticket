package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.helper.DatabaseSupplier;
import com.helper.PropertyHelper;
import com.model.Ticket;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository(DatabaseSupplier.MongoDB.FlightTicket.Ticket)
public class TicketDao implements ITicketDao {

  @Override
  public Ticket getTicketById(String id) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Ticket",
      collection -> {
        var filter = new Document("_id", new ObjectId(id));
        return this.parseWithId(collection.find(filter).first(), Ticket.class);
      });
  }

  @Override
  public Integer updateTicket(Ticket ticket) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Ticket",
      collection -> {
        var filter = new Document("_id", new ObjectId(ticket.getId()));
        
        return (int)collection.updateOne(filter, this.toBsonDocument(ticket))
          .getMatchedCount();
      });
  }

  @Override
  public String insertTicket(Ticket ticket) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Ticket",
      collection -> {
        var json = new Gson().toJson(ticket);
        var doc = Document.parse(json);

        collection.insertOne(doc);
        return doc.get("_id").toString();
      });
  }

  @Override
  public Long getTicketCountByStatus(String status) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Ticket",
      collection -> {
        return collection.countDocuments(new Document("status", status));
      });
  }

  @Override
  public List<Ticket> getTicket(String status) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Ticket", 
      collection -> {
        var res = new ArrayList<Ticket>();
        for (var doc : collection.find(new Document("status", status))) {
          res.add(this.parseWithId(doc, Ticket.class));
        }
        return res;
      });
  }

  @Override
  public List<Ticket> getTicket() throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Ticket", 
      collection -> {
        var res = new ArrayList<Ticket>();
        for (var doc : collection.find()) {
          res.add(this.parseWithId(doc, Ticket.class));
        }
        return res;
      });
  }

  @Override
  public List<Ticket> getTicketByRoute(String airlineStart, String airlineEnd, String status) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Ticket", 
      collection -> {
        var filter = new Document("status", status).append("airlineStart", airlineStart)
          .append("airlineEnd", airlineEnd);

        var res = new ArrayList<Ticket>();
        for (var doc : collection.find(filter)) {
          res.add(this.parseWithId(doc, Ticket.class));
        }
        return res;
      });
  }

  @Override
  public List<Ticket> getTicketByAirlineStart(String airlineStart, String status) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Ticket",
      collection -> {
        var filter  = new Document("status", status).append("airlineStart", airlineStart);
        var res = new ArrayList<Ticket>();

        for (var doc : collection.find(filter)) {
          res.add(this.parseWithId(doc, Ticket.class));
        }
        return res;
      });
  }

  @Override
  public List<Ticket> getTicketByAirlineEnd(String airlineEnd, String status) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Ticket",
      collection -> {
        var filter  = new Document("status", status).append("airlineEnd", airlineEnd);
        var res = new ArrayList<Ticket>();

        for (var doc : collection.find(filter)) {
          res.add(this.parseWithId(doc, Ticket.class));
        }
        return res;
      });
  }
}
