package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberVO {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private String createdAt;
    private String updatedAt;

}
