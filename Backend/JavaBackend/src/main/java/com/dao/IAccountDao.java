package com.dao;

import java.util.List;

import com.model.Account;

public interface IAccountDao extends IMongoDBQueryLogic {
  public String insertAccount(Account account) throws Exception;
  public List<Account> getAllStaff(String agentId) throws Exception;
  public Account getAccount(String id, String agentId) throws Exception;
  public Account getAccountByUsername(String username, String agentId) throws Exception;
  public Account getAccountByEmail(String email, String agentId) throws Exception;
}
