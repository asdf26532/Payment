package com.itbank.smartFarm.model;

import com.itbank.smartFarm.model.vo.MemberVO;
import com.itbank.smartFarm.model.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDAO {

    @Select("select * from test")
    TestVO test();

    @Select("select * from member where userid = #{userid} and userpw = #{userpw}")
    MemberVO selectOne(MemberVO input);
}
