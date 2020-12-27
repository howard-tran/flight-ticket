package com.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dao.IReceiptDao;
import com.helper.DatabaseSupplier;
import com.model.ListData;
import com.model.Receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService implements LogService {
  private IReceiptDao receiptDao;

  private final int receiptGetEach = 15;

  @Autowired
  ReceiptService(
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Receipt) IReceiptDao receiptDao
  ) {
    this.receiptDao = receiptDao;
  }

  public Optional<String> addReceipt(Receipt receipt) {
    return this.runOptional(() -> {
      return this.receiptDao.insertReceipt(receipt);
    });
  }

  public Optional<Integer> updateReceipt(Receipt receipt) {
    return this.runOptional(() -> {
      return this.receiptDao.updateReceipt(receipt);
    });
  }

  public Optional<ListData<List<Receipt>>> getReceipt(String accountId, int index) {
    return this.runOptional(() -> {
      List<Receipt> list = this.receiptDao.getReceipt(accountId);

      if (index < list.size()) {
        if (index + receiptGetEach <= list.size()) {
          return new ListData<List<Receipt>>(
            list.subList(index, index + receiptGetEach), 
            list.size());
        } 
        else {
          return new ListData<List<Receipt>>(
            list.subList(index, list.size()), 
            list.size());
        } 
      }
      return new ListData<List<Receipt>>(new ArrayList<>(), list.size());
    });
  }
}
