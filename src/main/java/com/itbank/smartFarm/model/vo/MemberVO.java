package com.itbank.smartFarm.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberVO {

    private int id, phone;
    private String name, address, email;
    private String userid, userpw, newpw, nick;
}
