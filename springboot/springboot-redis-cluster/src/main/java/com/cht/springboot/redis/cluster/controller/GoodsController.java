package com.cht.springboot.redis.cluster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cht.springboot.redis.cluster.model.Goods;
import com.cht.springboot.redis.cluster.service.GoodsService;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {
 
	@Autowired
	private GoodsService goodsService;
	
	@GetMapping("/getList")
	public List<Goods> getList(){
		return goodsService.getList();
	}
	
	@GetMapping("/getById")
	public Goods getById(@RequestParam("id") int id){
		return goodsService.getById(id);
	}
}
