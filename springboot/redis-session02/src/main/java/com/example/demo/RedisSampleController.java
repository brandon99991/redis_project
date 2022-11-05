package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession; 

import com.example.demo.RedisSampleService;
 
@RestController
public class RedisSampleController {
    
    @Autowired
    private RedisSampleService redisSampleService;

    //@PostMapping(value = "/setRedisStringValue")
    //public void setRedisStringValue(String key, String value) {
    //	redisSampleService.setRedisStringValue(key, value);
    //}
    
    @GetMapping(value = "/setRedisStringValue")
    public void setRedisStringValue(String key, String value) {
    	redisSampleService.setRedisStringValue(key, value);
    }
    
    
  //  @PostMapping(value = "/getRedisStringValue")
  //  public void getRedisStringValue(String key) {
  //      redisSampleService.getRedisStringValue(key);
  //  }
    
    @GetMapping(value="/getRedisStringValue")
    public String getRedisStringValue(String key) {
        //redisSampleService.getRedisStringValue(key);
        
        return redisSampleService.getRedisStringValue(key);
    }   
    
    @GetMapping("/getSessionId")
    public String getSessionId(HttpSession session) {
        return session.getId();
    }    
    
}