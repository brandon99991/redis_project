package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.ClientOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import java.time.Duration;

@Configuration
//@Profile("embedded-redis")
public class RedisClusterConfig {
	
	/*
    @Autowired
    RedisNodes redisNodes;
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisNodes.getNodes().forEach(s -> {
            String[] url = s.split(":");
            redisClusterConfiguration.clusterNode(url[0],Integer.parseInt(url[1]));
        });
        return new LettuceConnectionFactory(redisClusterConfiguration);
    }    
    */
	
    private String host1= "192.168.35.211";
    private int port1 = 6101;		
    private String host2= "192.168.35.212";
    private int port2 = 6101;		
    private String host3= "192.168.35.213";
    private int port3 = 6101;		
    private String host4= "192.168.35.211";
    private int port4 = 6201;		
    private String host5= "192.168.35.212";
    private int port5 = 6201;		
    private String host6= "192.168.35.213";
    private int port6 = 6201;		
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
      LettuceClientConfiguration lettuceClientConfiguration =
          LettuceClientConfiguration.builder()
              .commandTimeout(Duration.ofSeconds(3000))
              .clientOptions(ClientOptions.builder().build())
              .build();
      
      RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
      //clusterConfiguration.clusterNode(host, port);
      clusterConfiguration.clusterNode(host1, port1);
      clusterConfiguration.clusterNode(host2, port2);
      clusterConfiguration.clusterNode(host3, port3);
      clusterConfiguration.clusterNode(host4, port4);
      clusterConfiguration.clusterNode(host5, port5);
      clusterConfiguration.clusterNode(host6, port6);
      
      new LettuceConnectionFactory(clusterConfiguration);
      return new LettuceConnectionFactory(clusterConfiguration, lettuceClientConfiguration);
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());        
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }    
    
    /*
    @Bean
    public RedisTemplate<String, Object> redisTemplate2() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(MyData.class));
        return redisTemplate;
    } 
    */       
}