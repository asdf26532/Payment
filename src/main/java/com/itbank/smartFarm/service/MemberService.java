package com.itbank.smartFarm.service;

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

    @Transactional(readOnly = true)
    public MemberVO login(MemberVO input) {
        return dao.selectOne(input);
    }

    public void signUp(MemberVO input) {
        dao.insert(input);
    }

    public void update(MemberVO input, MemberVO member) {
        if(member.getUserpw().equals(input.getUserpw())){
            input.setId(member.getId());
            dao.update(input);
        }
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
            member.setUserpw(newPw);
            dao.newPw(member);
            return newPw;
        }
        return null;
    }

    public boolean isUserIdExists(String userid) {
        return dao.countByUserId(userid) > 0;
    }
}
