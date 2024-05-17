package com.itbank.smartFarm.model;

import com.itbank.smartFarm.model.vo.MemberVO;
import com.itbank.smartFarm.model.vo.TestVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberDAO {

    @Select("select * from test")
    TestVO test();

    @Select("select * from member where userid = #{userid} and userpw = #{userpw}")
    MemberVO selectOne(MemberVO input);

    @Insert("insert into member (name, address, email, userid, userpw, nick) " +
            "values (#{name}, #{address}, #{email}, #{userid}, #{userpw}, #{nick})")
    void insert(MemberVO input);

    @Update("update member set userpw = #{newpw}, email = #{email}, " +
            "address = #{address}, phone = #{phone} where id = #{id}")
    void update(MemberVO input);

    @Delete("delete from member where id = #{id}")
    void delete(MemberVO member);

    @Select("select userid from member where phone = #{phone}")
    String findId(MemberVO input);

    @Select("select * from member where userid = #{userid} and phone = #{phone}")
    MemberVO findPw(MemberVO input);

    @Update("update member set userpw = #{userpw} where id = #{id}")
    void newPw(MemberVO input);

    @Select("SELECT COUNT(*) FROM member WHERE userid = #{userid}")
    int countByUserId(String userid);
}
