package com.thinkmicroservices.caching.service.account;

import java.util.List;

import com.thinkmicroservices.caching.model.Account;

public interface AccountCacheService {

    public Account save(Account account);
    public List<Account> findAll();
    public Account findById(int id);
    public Long delete(int id);
}