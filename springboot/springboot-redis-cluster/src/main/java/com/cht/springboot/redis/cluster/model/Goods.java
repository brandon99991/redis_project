package com.cht.springboot.redis.cluster.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Goods implements Serializable{
	private int id;
	private String name;
	private double price;
	private String publisher;
}
