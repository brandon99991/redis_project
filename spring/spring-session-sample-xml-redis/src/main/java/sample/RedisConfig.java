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

//import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import java.time.Duration;

@Configuration
//@Profile("embedded-redis")
public class RedisConfig {
    
	/*
	private static final String DOCKER_IMAGE = "redis:7.0.4-alpine";

	@Bean
	public GenericContainer redisContainer() {
		GenericContainer redisContainer = new GenericContainer(DOCKER_IMAGE).withExposedPorts(6379);
		redisContainer.start();
		return redisContainer;
	}
	
	@Bean
	@Primary
	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(redisContainer().getHost(), redisContainer().getFirstMappedPort());
	}
	*/

    @Value("${spring.redis.host}")
    private String host;
    //private String host = "192.168.35.201";
    
    @Value("${spring.redis.port}")
    private int port;
    //private int port = 8002;	

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        //LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
    	LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(host, port);
        return lettuceConnectionFactory;
    }	

    /*
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
      LettuceClientConfiguration lettuceClientConfiguration =
          LettuceClientConfiguration.builder()
              .commandTimeout(Duration.ofSeconds(3000))
              .clientOptions(ClientOptions.builder().build())
              .build();
      
      RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
      redisStandaloneConfiguration.setHostName(host);
      redisStandaloneConfiguration.setPort(port);
      return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
    }
    */
    
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