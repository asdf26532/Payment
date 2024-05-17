package com.itbank.smartFarm.aop;

import com.itbank.smartFarm.model.vo.MemberVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PasswordHashAspect {

    @Before("execution(* com.itbank.smartFarm.service.MemberService.login(..)) && args(input)")
    public void hashPasswordBeforeLogin(MemberVO input) {
        input.setUserpw(PasswordEncoder.encode(input.getUserpw()));
    }

    @Before("execution(* com.itbank.smartFarm.service.MemberService.signUp(..)) && args(input)")
    public void hashPasswordBeforeSignUp(MemberVO input) {
        input.setUserpw(PasswordEncoder.encode(input.getUserpw()));
    }

    @Before("execution(* com.itbank.smartFarm.service.MemberService.update(..)) && args(input)")
    public void hashPasswordBeforeUpdate(MemberVO input) {
        input.setUserpw(PasswordEncoder.encode(input.getUserpw()));
    }
}

