package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/*
 	Reply 테이블)
 	------------------------
	IDX      NOT NULL NUMBER
	B_IDX    NOT NULL NUMBER
	U_IDX    NOT NULL NUMBER
	CONTENTS          CLOB
	W_DATE            DATE


	Reply_view 뷰)
	------------------------
	USERID   NOT NULL VARCHAR2(20)
	NICK     NOT NULL VARCHAR2(20)
*/


@Getter
@Setter
public class ReplyVO {
    private int id, board_id, member_id;
    private String contents, userid, nick;
    private Date w_date;

}
