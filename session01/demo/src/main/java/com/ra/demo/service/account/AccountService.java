package com.ra.demo.service.account;

import com.ra.demo.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    boolean create(Account account);
    Account findById(Long id);
    boolean update(Account account,Long id);
    void delete(Long id);
}
