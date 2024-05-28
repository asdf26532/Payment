package com.itbank.smartFarm.model.vo;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersVO {
	private int id, member_id, delivery_id, orderitems_id;
	private Date order_date;
	private String status;
}
