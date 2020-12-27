package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.helper.DatabaseSupplier;
import com.helper.PropertyHelper;
import com.model.TicketProfile;

import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository(DatabaseSupplier.MongoDB.FlightTicket.TicketProfile)
public class TicketProfileDao implements ITicketProfileDao {
  @Override
  public List<TicketProfile> get(String ticketId) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "TicketProfile",
      collection -> {
        var filter = new Document("ticketId", ticketId);
        var res = new ArrayList<TicketProfile>();

        for (var doc : collection.find(filter)) {
          res.add(this.parseWithId(doc, TicketProfile.class));
        }
        return res;
      });
  }

  @Override
  public String insertTicketProfile(TicketProfile ticketProfile) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "TicketProfile",
      collection -> {
        var json = new Gson().toJson(ticketProfile);
        var doc = Document.parse(json);

        collection.insertOne(doc);
        return doc.get("_id").toString();
      });
  }
}
