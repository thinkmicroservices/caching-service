/**
 * RedisConfig - setup the Redis configuration
 */
package com.thinkmicroservices.caching.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableRedisRepositories
@Slf4j
/**
 * @author cwoodward
 */
public class RedisConfig {
    /* Jedis connection factory properties */
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value("${spring.redis.password}")
    private String redisPassword;
    @Value("${spring.redis.database}")
    private int redisDatabase;
    @Value("${redis.cluster.enabled}")
    private boolean redisClusterEnabled;
    @Value("${spring.redis.cluster.nodes}")
    Collection<String> redisClusterNodes;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        /* configure single instance */
        if (redisClusterEnabled) {
            log.info("REDIS Connection Factory - CLUSTERED");
            redisClusterNodes.forEach(clusterNode -> {
                log.info( " - {}",clusterNode);
            });
            RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(redisClusterNodes);
            clusterConfiguration.setPassword(redisPassword);
            return new JedisConnectionFactory(clusterConfiguration);
        } else {
            log.info("REDIS Connection Factory - NON-CLUSTERED");
            
            RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
            configuration.setHostName(redisHost);
            configuration.setPort(redisPort);
            configuration.setPassword(redisPassword);
            configuration.setDatabase(redisDatabase);

            return new JedisConnectionFactory(configuration);
        }
    }

    @Bean
    public RedisTemplate<String, Object> template() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();

        return template;
    }

}
