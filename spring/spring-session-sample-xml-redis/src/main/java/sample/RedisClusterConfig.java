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
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.SessionRepositoryFilter;

import io.lettuce.core.ClientOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableRedisHttpSession
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 100)      // Redis Session Timeout (seconds)
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 6000, redisNamespace = "ueboot_shiro")
public class RedisClusterConfig extends AbstractHttpSessionApplicationInitializer {
	
    //@Autowired(required = false)
	//@Autowired
	//RedisHttpSessionConfiguration redisHttpSessionConfiguration;
	//public void setmaxInactiveIntervalInSeconds(@Autowired RedisHttpSessionConfiguration config){
	//	config.setMaxInactiveIntervalInSeconds(100);
	//}
	
    //SessionRepositoryFilter sessionRepositoryFilter = new SessionRepositoryFilter(??????) 
    //RedisHttpSessionConfiguration redisHttpSessionConfiguration = new RedisHttpSessionConfiguration();
	
	//public RedisClusterConfig(){
    //	super(RedisHttpSessionConfiguration.class);
    //	RedisHttpSessionConfiguration.setMaxInactiveIntervalInSeconds(3000);
    //}
	
	public RedisClusterConfig(){
		
    }
	
	

    @Value("#{'${spring.redis.master}'.split(',')}")
    private List<String> redisMasterNodes;
	
    @Value("#{'${spring.redis.slave}'.split(',')}")
    private List<String> redisSlaveNodes;
    
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
    
	
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
      LettuceClientConfiguration lettuceClientConfiguration =
          LettuceClientConfiguration.builder()
              .commandTimeout(Duration.ofSeconds(3000))
              .clientOptions(ClientOptions.builder().build())
              .build();
      
      RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();

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