package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemVO {
	private int id;
	private int orderId;
	private int itemId;
	private String itemName;
	private int quantity;
	private int price;

}
