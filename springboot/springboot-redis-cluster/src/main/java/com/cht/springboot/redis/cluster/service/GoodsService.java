package com.cht.springboot.redis.cluster.service;

import java.util.List;

import com.cht.springboot.redis.cluster.model.Goods;

public interface GoodsService {
	
	List<Goods> getList();
	 
	 Goods getById(int id);

}
