package com.itbank.smartFarm.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int id, type, member_id;
	private String title, contents, category, nick;
	private int soldout, v_count, secret;
	private Date w_date;
}
