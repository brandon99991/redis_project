package com.cht.springboot.redis.cluster.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cht.springboot.redis.cluster.mapper.GoodsMapper;
import com.cht.springboot.redis.cluster.model.Goods;
import com.cht.springboot.redis.cluster.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	@Cacheable(cacheNames="goodsList")
	public List<Goods> getList() {
		return goodsMapper.findAll();
	}

	@Override
	@Cacheable(cacheNames="goods",key="#id")
	public Goods getById(int id) {
		return goodsMapper.findById(id);
	}

}
