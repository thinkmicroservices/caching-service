/**
 * Account Data Access Object
 */
package com.thinkmicroservices.caching.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.thinkmicroservices.caching.model.Account;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
/**
 * @author cwoodward
 */
public class AccountDAO {

    public static final String HASH_KEY = "Account";

    @Autowired
    private RedisTemplate template;

    /**
     *
     * @param account
     * @return
     */
    public Account save(Account account) {
        log.debug("Account Save");
        template.opsForHash().put(HASH_KEY, account.getId(), account);
        return account;
    }

    /**
     *
     * @return
     */
    public List<Account> findAll() {
        log.debug("Account findAll");
        return template.opsForHash().values(HASH_KEY);
    }

    /**
     *
     * @param id
     * @return
     */
    public Account findById(int id) {
        log.debug("Account FindById {}", id);
        return (Account) template.opsForHash().get(HASH_KEY, id);
    }

    public Long delete(int id) {
        log.debug("Account delete {}", id);
        return template.opsForHash().delete(HASH_KEY, id);

    }
}
