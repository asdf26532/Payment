package com.itbank.smartFarm.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int id, type, memberid;
	private String title, contents, category, nick;
	private int soldout, v_count, secret;
	private Date w_date;
}
