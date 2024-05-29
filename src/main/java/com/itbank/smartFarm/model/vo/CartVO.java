package com.itbank.smartFarm.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartVO {
	private int order_id, member_id, delivery_id, orderitems_id, total_order_price, total_item_count;
	private Date order_date;
	private String status;


}
