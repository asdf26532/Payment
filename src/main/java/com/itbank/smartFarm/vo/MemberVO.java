package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO {
	private int id, phone;
	private String name, address, email, userid, userpw, nick;
}

