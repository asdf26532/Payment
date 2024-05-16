package com.itbank.smartFarm.model;

import com.itbank.smartFarm.model.vo.MemberVO;
import com.itbank.smartFarm.model.vo.TestVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDAO {

    @Select("select * from test")
    TestVO test();

    @Select("select * from member where userid = #{userid} and userpw = #{userpw}")
    MemberVO selectOne(MemberVO input);

    @Insert("insert into member (name, address, email, userid, userpw, nick) " +
            "values (#{name}, #{address}, #{email}, #{userid}, #{userpw}, #{nick})")
    void insert(MemberVO input);
}
