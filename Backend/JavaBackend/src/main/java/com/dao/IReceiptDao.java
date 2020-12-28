package com.dao;

import java.util.List;

import com.model.Receipt;

public interface IReceiptDao extends IMongoDBQueryLogic {
  public List<Receipt> getReceipt(String accountId) throws Exception;
  public Integer updateReceipt(Receipt receipt) throws Exception;
  public String insertReceipt(Receipt receipt) throws Exception;
}
