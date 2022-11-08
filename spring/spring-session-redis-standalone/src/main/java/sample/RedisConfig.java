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
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.SessionEventHttpSessionListenerAdapter;

import io.lettuce.core.ClientOptions;

//import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionListener;

@Configuration
//@EnableRedisHttpSession
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 180)      // Redis Session Timeout (seconds)
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    
    @Value("${spring.redis.port}")
    private int port;

	@Bean
	public SessionEventHttpSessionListenerAdapter session() {
	        List<HttpSessionListener> listeners = new ArrayList<HttpSessionListener>();
	        listeners.add(new SessionListener());
	        return new SessionEventHttpSessionListenerAdapter(listeners);
	}	
    
	// tag::cookie-serializer[]
	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("RSESSIONID"); // <1>
		serializer.setCookiePath("/"); // <2>
		serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); // <3>
		return serializer;
	}
	// end::cookie-serializer[]    
    
	
	
    /*
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
    	
        //LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
    	LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(host, port);
        return lettuceConnectionFactory;
    }	
    */

	
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