package com.itbank.smartFarm.service;

import com.itbank.smartFarm.aop.PasswordEncoder;
import com.itbank.smartFarm.model.MemberDAO;
import com.itbank.smartFarm.model.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    private MemberDAO dao;

    public MemberVO login(MemberVO input) {
        String hash = input.getUserpw();
        System.out.println(hash);
        return dao.selectOne(input);
    }

    public void signUp(MemberVO input) {
        dao.insert(input);
    }

    public void update(MemberVO input) {
        dao.update(input);
    }

    public void delete(MemberVO member) {
        dao.delete(member);
    }

    @Transactional(readOnly = true)
    public String findId(MemberVO input) {
        return dao.findId(input);
    }

    public String findPw(MemberVO input) {
        MemberVO member = dao.findPw(input);
        if(member != null){
            String newPw = UUID.randomUUID().toString().substring(0, 8);

            // 해쉬처리
            member.setUserpw(PasswordEncoder.encode(newPw));
            dao.newPw(member);
            return newPw;
        }
        return null;
    }

    public boolean isUserIdExists(String userid) {
        return dao.countByUserId(userid) > 0;
    }
}
