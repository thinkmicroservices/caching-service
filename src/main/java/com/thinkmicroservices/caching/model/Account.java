/**
 * Account model
 */
package com.thinkmicroservices.caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Account")
/**
 * @author cwoodward
 */
public class Account implements Serializable {

    @Id
    private int id;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
}
