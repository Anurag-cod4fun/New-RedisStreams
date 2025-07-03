package com.example.demotwo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.redis.streams.Producer;
import com.redis.streams.command.serial.SerialTopicConfig;
import com.redis.streams.command.serial.TopicManager;
import com.redis.streams.command.serial.TopicProducer;
import com.redis.streams.exception.InvalidTopicException;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPooled;

@Configuration
public class RedisJedisStreamConfig {

    @Value("${redis.host:localhost}")
    private String redisHost;

    @Value("${redis.port:6379}")
    private int redisPort;

    @Value("${redis.password:}")
    private String redisPassword;

    @Value("${redis.timeout:3000}")
    private int timeout;

    @Value("${redis.database:0}")
    private int database;
    
    @Bean
    public JedisPooled jedisPooled() {
        return new JedisPooled(
                new HostAndPort(redisHost, redisPort),
                DefaultJedisClientConfig.builder()
                        .password(redisPassword != null && !redisPassword.isEmpty() ? redisPassword : null)
                        .database(database)
                        .timeoutMillis(timeout)
                        .build()
        );
    }
    
//    SerialTopicConfig config = new SerialTopicConfig("my-topic");
//    JedisPooled jedis = jedisPooled();
//    TopicManager topicManager = TopicManager.createTopic(jedis, config);
    
    @Bean
    public TopicManager topicManager(JedisPooled jedisPooled) throws InvalidTopicException {
        SerialTopicConfig config = new SerialTopicConfig("my-topic");
        return TopicManager.createTopic(jedisPooled, config);
    }
    
    @Bean
    public Producer producer(JedisPooled jedisPooled) {
        return new TopicProducer(jedisPooled, "my-topic");
    }

//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(maxTotal);
//        poolConfig.setMaxIdle(maxIdle);
//        poolConfig.setMinIdle(minIdle);
//        poolConfig.setBlockWhenExhausted(true);
//        poolConfig.setMaxWait(Duration.ofMillis(timeout));
//        return poolConfig;
//    }
//
//    @Bean
//    public JedisPool jedisPool(JedisPoolConfig poolConfig) {
//        if (redisPassword != null && !redisPassword.isEmpty()) {
//            return new JedisPool(poolConfig, redisHost, redisPort, timeout, redisPassword, database);
//        } else {
//            return new JedisPool(poolConfig, redisHost, redisPort, timeout, null, database);
//        }
//    }
}
