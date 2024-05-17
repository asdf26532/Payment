package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BoardVO {

    private String title, contents;
    private int id_number, member_id;
    private int type_number;
    private String category;
    private int soldout, secret;
    private int v_count;
    private Date w_date;
    private String nick;

}
