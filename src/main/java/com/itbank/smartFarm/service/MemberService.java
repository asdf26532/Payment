package com.itbank.smartFarm.service;

import com.itbank.smartFarm.model.MemberDAO;
import com.itbank.smartFarm.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberDAO dao;

    public MemberVO login(MemberVO input) {
        return dao.selectOne(input);
    }

    
}
