package com.itbank.smartFarm.model;

import com.itbank.smartFarm.vo.MemberVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberDAO {


    @Select("select * from member where userid = #{userid} and userpw = #{userpw}")
    MemberVO selectOne(MemberVO input);

  
}
