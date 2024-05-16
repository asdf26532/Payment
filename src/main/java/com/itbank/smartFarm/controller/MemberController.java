package com.itbank.smartFarm.controller;

import com.itbank.smartFarm.model.vo.MemberVO;
import com.itbank.smartFarm.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService ms;

    @GetMapping("/login")
    public void login() {}

    @PostMapping("/login")
    public String login(MemberVO input, HttpSession session) {
        session.setAttribute("user", ms.login(input));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/signUp")
    public void signUp() {}

    @PostMapping("/signUp")
    public String signUp(MemberVO input) {
        ms.signUp(input);
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public void myPage() {}

    @PostMapping("/myPage")
    public String myPage(MemberVO input) {

        return "redirect:/";
    }

}
