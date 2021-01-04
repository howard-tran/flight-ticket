package com.service;

import java.util.List;
import java.util.Optional;

import com.dao.IAccountDao;
import com.helper.DatabaseSupplier;
import com.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements LogService {
  private IAccountDao accountDao;

  @Autowired
  AccountService(@Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Account) IAccountDao accountDao) {
    this.accountDao = accountDao;
  }

  public Optional<String> addAccount(Account account) {
    return this.runOptional(
      () -> {
        var ac  = new Object() {
          Account x = null;
        };
        
        this.testRun(
          () -> {
            ac.x = this.accountDao.getAccountByUsername
              (account.getUsername(), account.getAgencyId());
          });

        if (ac.x == null) {

          this.testRun(
            () -> {
              ac.x = this.accountDao.getAccountByEmail
                (account.getEmail(), account.getAgencyId());
            });
          
          if (ac.x == null) {
            return this.accountDao.insertAccount(account);
          }
          return "email";
        }
        return "username";
      }
    );
  }

  public Optional<List<Account>> getAllStaff(String agentId) {
    return this.runOptional(
      () -> {
        return this.accountDao.getAllStaff(agentId);
      }
    );
  }

  public Optional<Account> getAccount(String id, String agentId) {
    return this.runOptional(
      () -> {
        return this.accountDao.getAccount(id, agentId);
      }
    );
  }
  
  public Optional<String> checkAccount(Account account) {
    return this.runOptional(
      () -> {
        var ac = this.accountDao.getAccountByUsername
          (account.getUsername(), account.getAgencyId());

        if (
          ac != null && 
          ac.getPassword().equals(account.getPassword())
        ) return ac.getId();
        return "";
      }
    );
  }
}
