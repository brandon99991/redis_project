package com.cht.springboot.redis.cluster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.cht.springboot.redis.cluster.mapper")
@EnableCaching   //启用缓存
public class SpringbootRedisClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisClusterApplication.class, args);
	}

}
