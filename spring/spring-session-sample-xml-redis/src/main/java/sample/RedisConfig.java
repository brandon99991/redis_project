package sample;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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
	
   // @Value("${spring.redis.host}")
    private String host = "192.168.35.201";
    
   // @Value("${spring.redis.port}")
    private int port = 8002;	

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        //LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
    	LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(host, port);
        return lettuceConnectionFactory;
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