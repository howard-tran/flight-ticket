package com.controller;

import com.App;
import com.model.Receipt;
import com.service.ReceiptService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(App.service + "/receipt")
@RestController
public class ReceiptController {
  private ReceiptService receiptService;
  
  @PostMapping("/addReceipt")
  public Response<Object> addReceipt(
    @RequestBody Receipt receipt
  ) {
    var res = this.receiptService.addReceipt(receipt);

    if (res == null) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @GetMapping("/getReceipt")
  public Response<Object> getReceipt(
    @RequestParam(name = "accountId", required = true) String accountId,
    @RequestParam(name = "index", required = true) int index
  ) {
    var res = this.receiptService.getReceipt(accountId, index);

    if (res == null) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }
}
