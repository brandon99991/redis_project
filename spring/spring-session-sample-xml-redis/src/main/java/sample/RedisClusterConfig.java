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
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisClusterConfig {
	
    //@Value("${spring.redis.cluster}")
    //@Value("#{'${spring.redis.cluster}'.split(',')}")
    //private List<String> redisNodes;

    @Value("#{'${spring.redis.master}'.split(',')}")
    private List<String> redisMasterNodes;
	
    @Value("#{'${spring.redis.slave}'.split(',')}")
    private List<String> redisSlaveNodes;
    
	
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
      LettuceClientConfiguration lettuceClientConfiguration =
          LettuceClientConfiguration.builder()
              .commandTimeout(Duration.ofSeconds(3000))
              .clientOptions(ClientOptions.builder().build())
              .build();
      
      RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
      
      /*
      redisNodes.forEach(s -> {
          String[] url = s.split(":");
          clusterConfiguration.clusterNode(url[0],Integer.parseInt(url[1]));
          System.out.println(url[0] + ":" +url[1]);
      });
      */

      System.out.println("[MasterNodes]");
      redisMasterNodes.forEach(s1 -> {
          String[] url = s1.split(":");
          clusterConfiguration.clusterNode(url[0],Integer.parseInt(url[1]));
          System.out.println(url[0] + ":" +url[1]);
      });
      
      System.out.println("[SlaveNodes]");      
      redisSlaveNodes.forEach(s2 -> {
          String[] url = s2.split(":");
          clusterConfiguration.clusterNode(url[0],Integer.parseInt(url[1]));
          System.out.println(url[0] + ":" +url[1]);
      });
      
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