package com.ra.demo.service.account;

import com.ra.demo.model.Account;
import com.ra.demo.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create(Account account) {
        accountRepository.save(account);
        return true;
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Account account, Long id) {
        Account accountUpdate = accountRepository.findById(id).orElse(null);
        if (accountUpdate != null) {
            accountUpdate.setFullName(account.getFullName());
            accountUpdate.setAddress(account.getAddress());
            accountUpdate.setPhone(account.getPhone());
            accountUpdate.setDateOfBirth(account.getDateOfBirth());
            accountUpdate.setStatus(account.isStatus());
            accountRepository.save(accountUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
