package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ReplyVO {
    private int id;
    private int board_id;
    private int member_id;
    private String contents;
    private Date w_date;
    private String nick;
}