package com.dao;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.helper.DatabaseSupplier;
import com.helper.PropertyHelper;
import com.model.Receipt;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository(DatabaseSupplier.MongoDB.FlightTicket.Receipt)
public class ReceiptDao implements IReceiptDao {
  @Override
  public String insertReceipt(Receipt receipt) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Receipt", 
      collection -> {
        var json = new Gson().toJson(receipt);
        var doc = Document.parse(json);

        collection.insertOne(doc);
        return doc.get("_id").toString();
      });
  }  

  @Override
  public Integer updateReceipt(Receipt receipt) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Receipt",
      collection -> {
        var filter = new Document("_id", new ObjectId(receipt.getId()));
        
        return (int)collection.updateOne(filter, this.toBsonDocument(receipt))
          .getMatchedCount();
      });
  }

  @Override
  public List<Receipt> getReceipt(String accountId) throws Exception {
    return this.run(
      PropertyHelper.getMongoDB(), "Receipt", 
      collection -> {
        var filter = new Document("userid", accountId);

        var res = new ArrayList<Receipt>();
        for (var doc : collection.find(filter)) {
          res.add(this.parseWithId(doc, Receipt.class));
        }
        return res;
      });
  }
}
