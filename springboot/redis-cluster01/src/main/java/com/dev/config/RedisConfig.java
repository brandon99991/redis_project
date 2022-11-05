package com.dev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableRedisRepositories
@Profile({"local","stg","prd"})
public class RedisConfig {

    @Autowired
    RedisNodes redisNodes;

   // @Autowired
   public ObjectMapper objectMapper;    
    
   @Bean
   public RedisConnectionFactory redisConnectionFactory() {
       // clustering 구성 config
       RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
       redisNodes.getNodes().forEach(s -> {
           String[] url = s.split(":");
           redisClusterConfiguration.clusterNode(url[0],Integer.parseInt(url[1]));
       });
       return new LettuceConnectionFactory(redisClusterConfiguration);
   }      
   
   @Bean("redisTemplate")
    public RedisTemplate<String, String> redisTemplate() {
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        
        /* valueOperations 사용하는 경우 redis-cli 에서 keys * 조회시 키 값들의 앞에 \xac\xed\x00\x05t\x00\x04 붙는거 제거 */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        /* valueOperations 사용하는 경우 redis-cli 에서 get 'key' 조회 시 value 값에 \xac\xed\x00\x05t\x00\x04 붙는거 제거 */
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        /* hashOperations<String, Object, Object>를 사용하는 경우 두 번째 파라미터가 HashKey */
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        /* hashOperations<String, Object, Object>를 사용하는 경우 세 번째 파라미터가 HashValue */
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        /* 트랜잭션 ?*/
        // redisTemplate.setEnableTransactionSupport(true);

        /* redisTemplate은 redisConnectionFactory을 기반으로 돌아감 */
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}