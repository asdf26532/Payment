package com.itbank.smartFarm.vo;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersVO {
	private int id;
	private int memberId;
	private String status;
	private int totalPrice;
	private String createdAt;
	private List<OrderItemVO> orderItems;
	

}
