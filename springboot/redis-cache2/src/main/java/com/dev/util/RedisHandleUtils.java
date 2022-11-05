package com.dev.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisHandleUtils {

    static final String LOGIN_KEY = "LoginMember";
    static final String MEMBER_INFO_KEY = "MemberInfo";

//  @Autowired
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    
    public void makeTokenAndExpiredAt(String memberId, String accessToken) {
    	// key, value, expirationTime, expirttime단위?
    	redisTemplate.opsForValue().set(LOGIN_KEY + ":" + memberId, accessToken, 4320, TimeUnit.MINUTES);
        
    }
}
