package com.dao;

import java.util.List;

import com.model.Account;

public interface IAccountDao extends IDbQueryLogic {
  public String insertAccount(Account account) throws Exception;
  public List<Account> getAllAccount(String agentId) throws Exception;
  public Account getAccount(String id, String agentId) throws Exception;
}
