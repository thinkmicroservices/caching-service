/**
 * Account Controller
 */
package com.thinkmicroservices.caching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.thinkmicroservices.caching.model.Account;

import com.thinkmicroservices.caching.service.account.AccountCacheService;

@RestController
@RequestMapping("/account")
@EnableCaching
@Slf4j
/**
 * @author cwoodward
 */
public class AccountController {

    @Autowired

    private AccountCacheService accountCacheService;

    @PostMapping
    public Account save(@RequestBody Account account) {
        log.debug("save({})", account);
        return accountCacheService.save(account);
    }

    @GetMapping
    public List<Account> getAll() {
        log.debug("getAll");
        return accountCacheService.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Account") //,unless = "#{constraint_expression}")
    public Account findById(@PathVariable int id) {
        log.debug("findById({})");
        return accountCacheService.findById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Account")
    public Long delete(@PathVariable int id) {
        log.debug("delete ({})", id);
        return accountCacheService.delete(id);
    }
}
