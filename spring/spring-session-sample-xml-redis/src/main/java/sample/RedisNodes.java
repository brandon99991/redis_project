package sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
//@ConfigurationProperties("spring.redis.cluster")
@Configuration
public class RedisNodes {

    @Value("${redis.master01}")
    private String redisMaster01;
	
    @Value("${redis.master02}")
    private String redisMaster02;

    @Value("${redis.master03}")
    private String redisMaster03;
    
    @Value("${redis.slave01}")
    private String redisSlave01;
	
    @Value("${redis.slave02}")
    private String redisSlave02;

    @Value("${redis.slave03}")
    private String redisSlave03;

    
    List<String> nodes = new ArrayList<String>();

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes() {
        this.nodes.add(redisMaster01);
        this.nodes.add(redisMaster02);
        this.nodes.add(redisMaster03);
        this.nodes.add(redisSlave01);        
        this.nodes.add(redisSlave02);
        this.nodes.add(redisSlave03);        
    }
}
