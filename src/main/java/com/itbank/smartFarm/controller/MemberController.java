package com.itbank.smartFarm.controller;

import com.itbank.smartFarm.model.vo.MemberVO;
import com.itbank.smartFarm.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    미구현 기능
    - 아이디,비밀번호 찾기
    - 해쉬처리
    - 회원정보 수정, 탈퇴
    - 회원가입시 아이디 중복체크 (비동기)
*/


@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService ms;

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public void login() {}

    // 로그인시 DB에 해당 아이디가 있는지 체크후 아이디를 세션으로 반환
    @PostMapping("/login")
    public String login(MemberVO input, HttpSession session) {
        session.setAttribute("user", ms.login(input));
        return "redirect:/";
    }

    // 로그아웃 버튼 클릭시 세션에서 'user'를 삭제후 홈으로 리다이렉트
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    // 회원가입 페이지로 이동
    @GetMapping("/signUp")
    public void signUp() {}

    // 가입란에 작성한 폼을 insert하여 추가하고 홈으로 리다이렉트
    // - 추후에 아이디 중복체크 (비동기)등을 추가예정
    @PostMapping("/signUp")
    public String signUp(MemberVO input) {
        ms.signUp(input);
        return "redirect:/";
    }

    // 나의정보 페이지로 이동
    @GetMapping("/myPage")
    public void myPage() {}

    // 회원정보 수정정보를 받아 update하고 로그아웃으로 리다이렉트
    @PostMapping("/myPage")
    public String myPage(MemberVO input) {

        return "redirect:/member/logout";
    }

}
