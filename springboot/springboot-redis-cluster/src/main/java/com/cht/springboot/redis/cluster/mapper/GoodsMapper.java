package com.cht.springboot.redis.cluster.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.cht.springboot.redis.cluster.model.Goods;

public interface GoodsMapper {

   List<Goods> findAll();
   
   Goods findById(@Param("id") int id);
}
