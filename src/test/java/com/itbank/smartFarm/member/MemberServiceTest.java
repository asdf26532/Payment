package com.itbank.smartFarm.member;

import com.itbank.smartFarm.model.MemberDAO;
import com.itbank.smartFarm.service.MemberService;
import com.itbank.smartFarm.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    // DAO를 Mock 객체로 생성
    @Mock
    private MemberDAO memberDAO;

    // MemberService에 Mock DAO 주입
    @InjectMocks
    private MemberService memberService;

    // 각 테스트 실행 전 Mock 객체 초기화
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // login 메서드 테스트
    @Test
    public void testLogin() {
        MemberVO input = new MemberVO();
        input.setUserid("testUser");
        input.setUserpw("password");

        // DAO의 selectOne 메서드가 호출되면 input 객체 반환 설정
        when(memberDAO.selectOne(any(MemberVO.class))).thenReturn(input);

        // login 메서드 호출 및 결과 검증
        MemberVO result = memberService.login(input);
        assertEquals(input, result);

        // selectOne 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberDAO, times(1)).selectOne(any(MemberVO.class));
    }

    // signUp 메서드 테스트
    @Test
    public void testSignUp() {
        MemberVO input = new MemberVO();
        memberService.signUp(input);

        // DAO의 insert 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberDAO, times(1)).insert(input);
    }

    // update 메서드 테스트
    @Test
    public void testUpdate() {
        MemberVO input = new MemberVO();
        memberService.update(input);

        // DAO의 update 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberDAO, times(1)).update(input);
    }

    // delete 메서드 테스트
    @Test
    public void testDelete() {
        MemberVO input = new MemberVO();
        memberService.delete(input);

        // DAO의 delete 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberDAO, times(1)).delete(input);
    }

    // findId 메서드 테스트
    @Test
    public void testFindId() {
        MemberVO input = new MemberVO();

        // DAO의 findId 메서드가 호출되면 "foundId" 반환 설정
        when(memberDAO.findId(any(MemberVO.class))).thenReturn("foundId");

        // findId 메서드 호출 및 결과 검증
        String result = memberService.findId(input);
        assertEquals("foundId", result);

        // findId 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberDAO, times(1)).findId(any(MemberVO.class));
    }

    // findPw 메서드 테스트
    @Test
    public void testFindPw() {
        MemberVO input = new MemberVO();
        MemberVO member = new MemberVO();
        member.setUserid("testUser");

        // DAO의 findPw 메서드가 호출되면 member 객체 반환 설정
        when(memberDAO.findPw(any(MemberVO.class))).thenReturn(member);

        // findPw 메서드 호출 및 결과 검증
        String newPw = memberService.findPw(input);
        assertNotNull(newPw);

        // findPw와 newPw 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberDAO, times(1)).findPw(any(MemberVO.class));
        verify(memberDAO, times(1)).newPw(any(MemberVO.class));
    }

    // isUserIdExists 메서드 테스트
    @Test
    public void testIsUserIdExists() {
        // DAO의 countByUserId 메서드가 호출되면 1 반환 설정
        when(memberDAO.countByUserId("testUser")).thenReturn(1);

        // isUserIdExists 메서드 호출 및 결과 검증
        boolean exists = memberService.isUserIdExists("testUser");
        assertTrue(exists);

        // countByUserId 메서드가 정확히 한 번 호출되었는지 검증
        verify(memberDAO, times(1)).countByUserId("testUser");
    }
}