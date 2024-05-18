package com.itbank.smartFarm.member;

import com.itbank.smartFarm.controller.MemberController;
import com.itbank.smartFarm.service.MemberService;
import com.itbank.smartFarm.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
public class MemberControllerTest {

    private MockMvc mockMvc;

    // MemberService를 Mock 객체로 생성
    @Mock
    private MemberService memberService;

    // MemberController에 Mock MemberService 주입
    @InjectMocks
    private MemberController memberController;

    // 각 테스트 실행 전 Mock 객체 초기화 및 MockMvc 설정
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    // login 엔드포인트 테스트
    @Test
    public void testLogin() throws Exception {
        MemberVO input = new MemberVO();
        input.setUserid("testUser");
        input.setUserpw("password");

        // MemberService의 login 메서드가 호출되면 input 객체 반환 설정
        when(memberService.login(any(MemberVO.class))).thenReturn(input);

        MockHttpSession session = new MockHttpSession();

        // login 엔드포인트 호출 및 결과 검증
        mockMvc.perform(post("/member/login")
                        .param("userid", "testUser")
                        .param("userpw", "password")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        // login 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberService, times(1)).login(any(MemberVO.class));

        // 세션에 user 속성이 설정되었는지 검증
        assert session.getAttribute("user").equals(input);
    }

    // logout 엔드포인트 테스트
    @Test
    public void testLogout() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", new MemberVO());

        // logout 엔드포인트 호출 및 결과 검증
        mockMvc.perform(get("/member/logout").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        // 세션에서 user 속성이 제거되었는지 검증
        assert session.getAttribute("user") == null;
    }

    // signUp 엔드포인트 테스트
    @Test
    public void testSignUp() throws Exception {
        // signUp 엔드포인트 호출 및 결과 검증
        mockMvc.perform(post("/member/signUp")
                        .param("userid", "newUser")
                        .param("userpw", "newPassword")
                        .param("email", "newUser@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        // MemberService의 signUp 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberService, times(1)).signUp(any(MemberVO.class));
    }

    // findId 엔드포인트 테스트
    @Test
    public void testFindId() throws Exception {
        // MemberService의 findId 메서드가 호출되면 "foundId" 반환 설정
        when(memberService.findId(any(MemberVO.class))).thenReturn("foundId");

        // findId 엔드포인트 호출 및 결과 검증
        mockMvc.perform(post("/member/findId")
                        .param("email", "user@example.com")
                        .param("phone", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userid", "foundId"));
    }

    // findPw 엔드포인트 테스트
    @Test
    public void testFindPw() throws Exception {
        // MemberService의 findPw 메서드가 호출되면 "newPassword" 반환 설정
        when(memberService.findPw(any(MemberVO.class))).thenReturn("newPassword");

        // findPw 엔드포인트 호출 및 결과 검증
        mockMvc.perform(post("/member/findPw")
                        .param("userid", "testUser")
                        .param("phone", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("newPw", "newPassword"));
    }

    // checkUserId 엔드포인트 테스트
    @Test
    public void testCheckUserId() throws Exception {
        // MemberService의 isUserIdExists 메서드가 호출되면 true 반환 설정
        when(memberService.isUserIdExists("testUser")).thenReturn(true);

        // checkUserId 엔드포인트 호출 및 결과 검증
        mockMvc.perform(post("/member/checkUserId")
                        .contentType("application/json")
                        .content("{\"userid\":\"testUser\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exists").value(true));
    }
}