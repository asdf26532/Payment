package com.itbank.smartFarm.vo;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersVO {
	private int id, count, member_id, delivery_id, orderitem_id;
	private Date order_date;
	private String status;
	
	public OrdersVO(int memberid, int orderitem_id, int delivery_id2) {
		this.member_id=memberid;
		this.delivery_id=delivery_id2;
		this.orderitem_id=orderitem_id;
	}
}
