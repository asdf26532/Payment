 package com.itbank.smartFarm.vo;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CartVO {

	private int id;
	private int memberId;
	private List<CartItemVO> cartItems;
	private int totalPrice;
	
}