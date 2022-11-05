package com.cht.springboot.redis.cluster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes= {SpringbootRedisClusterApplication.class})
@RunWith(SpringRunner.class)
public class SpringbootRedisClusterApplicationTests {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	//启动集群的时候要根据实际id启动，不然java程序连接就直接导致拒绝连接，连接失败等问题

	@Test
	public void testRedisCluster() {
		for(int i=0;i<100;i++) {
			String val=String.valueOf(i);
			redisTemplate.opsForValue().set(val, val);
		}
		
		for(int i=0;i<100;i++) {
			String key=String.valueOf(i);
			String val=redisTemplate.opsForValue().get(key);
			System.out.println("-----------------"+val);
		}
	}

}
