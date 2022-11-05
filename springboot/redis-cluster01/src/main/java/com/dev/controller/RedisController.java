package com.dev.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.util.RedisHandleUtils;

@RestController
public class RedisController {

  @Autowired
  private final RedisHandleUtils redisHandleUtils = new RedisHandleUtils();
  
  @GetMapping("login")
  public void login() {
    //redisHandleUtils.makeTokenAndExpiredAt(loginUserInfo.getMemberId(), accessToken);
	  
	  
	//String aaa="22222";
	//String bbb="aaaaaa";

	String aaa;
	String bbb;
			
	Random rand=new Random(12);
	rand.setSeed(System.currentTimeMillis());
	//aaa=rand.toString();
	//bbb=rand.toString();
	
	aaa="test1";
	bbb="22222";
	  
    redisHandleUtils.makeTokenAndExpiredAt(aaa, bbb);
    
    //return 1;
  }
}
