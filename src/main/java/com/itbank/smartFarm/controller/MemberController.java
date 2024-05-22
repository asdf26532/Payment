package com.itbank.smartFarm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.itbank.smartFarm.Service.MemberService;
import com.itbank.smartFarm.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService ms;

    @GetMapping("/login")
	public void login() {}
	
    @PostMapping("/login")
    public ModelAndView login(MemberVO user, HttpSession session) {
        ModelAndView mav = new ModelAndView();
		
        user = ms.login(user);
		
		if (user != null) {
			session.setAttribute("user", user);
		}
		
		
		String msg = "로그인 되었습니다.";
		if (user== null) {
			msg = "로그인 실패하였습니다.";
		}

		mav.addObject("row", (user != null) ? 1 : 0);
		mav.addObject("path", "/");
		mav.addObject("msg", msg);

		mav.setViewName("/pay/Message");

		return mav;
	}


        

}
