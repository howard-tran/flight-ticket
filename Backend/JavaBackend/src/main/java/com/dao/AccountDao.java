package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.helper.DatabaseSupplier;
import com.helper.PropertyHelper;
import com.model.Account;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository(DatabaseSupplier.MongoDB.FlightTicket.Account)
public class AccountDao implements IAccountDao {
  @Override
  public Account getAccount(String id, String agentId) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Account",
      collection -> {
        var condition1 = new Document("_id", new ObjectId(id));
        var condition2 = new Document("agentId", agentId);

        var condition = Document.parse(String.format("{$and: [%s, %s]}", 
          condition1.toString(), condition2.toString()));

        return this.parse(collection.find(condition).first(), Account.class);
      });
  }

  @Override
  public List<Account> getAllAccount(String agentId) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Account", 
      collection -> {
        var res = new ArrayList<Account>();
        for (var doc : collection.find()) {
          res.add(this.parseWithId(doc, Account.class));
        }
        return res;
      });
  }

  @Override
  public String insertAccount(Account account) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Account", 
      collection -> {
        var json = new Gson().toJson(account);
        var doc = Document.parse(json);

        collection.insertOne(doc);
        return doc.get("_id").toString();
      });
  }
}
