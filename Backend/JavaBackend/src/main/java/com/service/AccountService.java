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
    return this.run(
      () -> {
        return this.accountDao.insertAccount(account);
      }
    );
  }

  public Optional<List<Account>> getAllAccount(String agentId) {
    return this.run(
      () -> {
        return this.accountDao.getAllAccount(agentId);
      }
    );
  }

  public Optional<Account> getAccount(String id, String agentId) {
    return this.run(
      () -> {
        return this.accountDao.getAccount(id, agentId);
      }
    );
  }

  public Optional<String> checkAccount(Account account) {
    return this.run(
      () -> {
        var ac = this.accountDao.getAccount(account.getId(), account.getAgencyId());
        if (
          ac.getUsername().equals(account.getUsername())
          && ac.getPassword().equals(account.getPassword())
        ) {
          return account.getId();
        }
        return "";
      }
    );
  }
}
