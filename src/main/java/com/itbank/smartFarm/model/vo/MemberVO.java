package com.itbank.smartFarm.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberVO {

    private int id;
    private String name, address, email, phone;
    private String userid, userpw, newpw, nick;
}
