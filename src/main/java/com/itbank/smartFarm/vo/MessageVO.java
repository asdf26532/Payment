package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;

@Getter @Setter @ToString
public class MessageVO {
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private String nick;
    private Timestamp timestamp;
}
