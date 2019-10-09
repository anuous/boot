package com.anuous.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luoyuanguang
 *  redis 集群的连接配置
 */

@Configuration
@PropertySource("classpath:config/redisCuster.properties")
public class JedisCusterConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisCusterConfig.class);
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private String maxRedirects;
    @Value("${spring.redis.cluster.timeout}")
    private String timeout;
    @Value("${spring.redis.cluster.max-attempts}")
    private String maxAttempts;

    //@Bean
    public JedisCluster getJedisCuster(){
        if (StringUtils.isEmpty(nodes)){
            LOGGER.info("JedisCuster初始化失败，原因：集群ip:port配置为空（spring.redis.cluster.nodes）！");
            return null;
        }
        JedisCluster jedisCluster = null;
        String [] noteArray = nodes.split(",");
        Set<HostAndPort> servers = new HashSet<HostAndPort>();
        Integer port = null;
        for (String note:noteArray) {
            String[] ipPortPair = note.split(":");
            port = Integer.parseInt(ipPortPair[1]);
            servers.add(new HostAndPort(ipPortPair[0], port));
        }
        try {
            jedisCluster = new JedisCluster(servers, Integer.valueOf(timeout), Integer.valueOf(maxAttempts));
        }catch (Exception e){
            LOGGER.error("jedisCluster初始化失败，原因： ",e );
        }
        return jedisCluster;
    }

}
