package com.thinkmicroservices.caching.service.account;
import java.util.List;

import com.thinkmicroservices.caching.model.Account;
import com.thinkmicroservices.caching.respository.AccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("redis")
/* Provide an AccountCacheService implementation with Redis */
public class RedisAccountCacheServiceImpl implements AccountCacheService{

    @Autowired
    private AccountDAO dao;

    @Override
    public Account save(Account account) {
        return dao.save(account);
    }

    @Override
    public List<Account> findAll() {
        return dao.findAll();
    }

    @Override
    public Account findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Long delete(int id) {
    
        return dao.delete(id);
    }
    
}