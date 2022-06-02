/**
 * CachingServiceApplication
 */
package com.thinkmicroservices.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan
@Slf4j
/**
 * @author cwoodward
 */
public class CachingServiceApplication {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("Starting CachingServiceApplication");
        SpringApplication.run(CachingServiceApplication.class, args);
    }

}
