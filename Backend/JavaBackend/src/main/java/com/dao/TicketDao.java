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

        Document res = collection.find(filter).first();
        if (res == null) return null;

        return this.parseWithId(res, Ticket.class);
      });
  }

  @Override
  public List<Ticket> getTicket(String flightId) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Ticket",
      collection -> {
        var filter = new Document("flightId", flightId);
        
        var res = new ArrayList<Ticket>();
        for (var doc : collection.find(filter)) {
          res.add(this.parseWithId(doc, Ticket.class));
        }
        return res;
      });
  }

  @Override
  public List<Ticket> getTicket(String flightId, String seatType) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Ticket",
      collection -> {
        var filter = new Document("flightId", flightId).append("seatType", seatType);
        
        var res = new ArrayList<Ticket>();
        for (var doc : collection.find(filter)) {
          res.add(this.parseWithId(doc, Ticket.class));
        }
        return res;
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
}
