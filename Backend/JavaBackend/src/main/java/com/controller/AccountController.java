package com.controller;

import com.App;
import com.model.Account;
import com.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(App.service + "/account")
@RestController
public class AccountController {
  @Autowired
  private AccountService accountService;

  @GetMapping("/get")
  public Response<Object> getAccount(
    @RequestParam(name = "agentid", required = true) String agentId
  ) {
    var res = this.accountService.getAllAccount(agentId);

    if (res.isEmpty()) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @PostMapping("/addmanager")
  public Response<Object> addManager(
    @RequestBody Account account
  ) {
    // decentralization for system admin

    // =========
    var res = this.accountService.addAccount(account);

    if (res.isEmpty()) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @PostMapping("/addstaff")
  public Response<Object> addStaff(
    @RequestBody Account account
  ) {
    // decentralization for agent manager

    // =========
    var res = this.accountService.addAccount(account);

    if (res.isEmpty()) {
      return ResponseHandler.error(null);
    } else {
      return ResponseHandler.ok(res.get());
    }
  }

  @PostMapping("/signin")
  public Response<Object> signIn(
    @RequestBody Account account
  ) {
    var res = this.accountService.checkAccount(account);

    if (res.isEmpty()) {
      return ResponseHandler.error(null);
    } else {
      if (res.get().length() > 0) {
        return ResponseHandler.ok(res.get());
      }
      return ResponseHandler.unAuthorized(null);
    }
  }
}
