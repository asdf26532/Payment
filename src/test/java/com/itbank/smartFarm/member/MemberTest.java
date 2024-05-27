package com.itbank.smartFarm.member;

import com.itbank.smartFarm.model.MemberDAO;
import com.itbank.smartFarm.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberDAO dao;


    @Test
    public void loginTest() {
        MemberVO mem = new MemberVO();
        mem.setUserid("test1");
        mem.setUserpw("1111");
        System.out.println(dao.selectOne(mem));
    }
}
