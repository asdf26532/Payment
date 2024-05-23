package com.itbank;

import com.itbank.smartFarm.SmartFarmApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SmartFarmApplication.class)
@AutoConfigureMockMvc
@Transactional
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    // 공지사항 테스트
    @Test
    @DisplayName("공지사항 목록 조회 테스트")
    void testGetNoticeList() throws Exception {
        mockMvc.perform(get("/board/notice"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/notice"))
                .andExpect(model().attributeExists("notices"));
    }

    @Test
    @DisplayName("공지사항 상세 조회 테스트")
    void testGetNoticeDetail() throws Exception {
        mockMvc.perform(get("/board/notice_view/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("board/notice_view"))
                .andExpect(model().attributeExists("notice"))
                .andExpect(model().attributeExists("memberid"));
    }

    @Test
    @DisplayName("공지사항 작성 폼 접근 테스트")
    void testGetNoticeWriteForm() throws Exception {
        mockMvc.perform(get("/board/notice_write"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/notice_write"));
    }

    @Test
    @DisplayName("공지사항 작성 처리 테스트")
    void testPostNoticeWrite() throws Exception {
        mockMvc.perform(post("/board/notice_write")
                        .param("title", "Test Title")
                        .param("contents", "Test Contents"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/notice"));
    }

    @Test
    @DisplayName("공지사항 삭제 테스트")
    void testDeleteNotice() throws Exception {
        mockMvc.perform(post("/board/notice_delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/notice"));
    }

    // 장터 테스트
    @Test
    @DisplayName("장터 목록 조회 테스트")
    void testGetMarketList() throws Exception {
        mockMvc.perform(get("/board/freemarket"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/freemarket"))
                .andExpect(model().attributeExists("freemarkets"));
    }

    @Test
    @DisplayName("장터 상세 조회 테스트")
    void testGetMarketDetail() throws Exception {
        mockMvc.perform(get("/board/freemarket_view/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("board/freemarket_view"))
                .andExpect(model().attributeExists("freemarket"))
                .andExpect(model().attributeExists("memberid"));
    }

    @Test
    @DisplayName("장터 작성 폼 접근 테스트")
    void testGetMarketWriteForm() throws Exception {
        mockMvc.perform(get("/board/freemarket_write"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/freemarket_write"));
    }

    @Test
    @DisplayName("장터 작성 처리 테스트")
    void testPostMarketWrite() throws Exception {
        mockMvc.perform(post("/board/freemarket_write")
                        .param("title", "Test Title")
                        .param("contents", "Test Contents"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/freemarket"));
    }

    @Test
    @DisplayName("장터 삭제 테스트")
    void testDeleteMarket() throws Exception {
        mockMvc.perform(post("/board/freemarket_delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/freemarket"));
    }

    // 자유게시판 테스트
    @Test
    @DisplayName("자유게시판 목록 조회 테스트")
    void testGetFreeBoardList() throws Exception {
        mockMvc.perform(get("/board/freeBoard"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/freeBoard"))
                .andExpect(model().attributeExists("map"));
    }

    @DisplayName("자유게시판 작성 폼 접근 테스트")
    void testGetFreeBoardWriteForm() throws Exception {
        mockMvc.perform(get("/board/fBadd"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/fBadd"));
    }

    @Test
    @DisplayName("자유게시판 작성 처리 테스트")
    void testPostFreeBoardWrite() throws Exception {
        mockMvc.perform(post("/board/fBadd")
                        .param("title", "Test Title")
                        .param("contents", "Test Contents"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/freeBoard"));
    }

    @Test
    @DisplayName("자유게시판 상세 조회 테스트")
    void testGetFreeBoardDetail() throws Exception {
        mockMvc.perform(get("/board/fB_view/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("board/fB_view"))
                .andExpect(model().attributeExists("row"))
                .andExpect(model().attributeExists("replies"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @DisplayName("자유게시판 삭제 테스트")
    void testDeleteFreeBoard() throws Exception {
        mockMvc.perform(post("/board/deletefB/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/freeBoard"));
    }

    @Test
    @DisplayName("자유게시판 수정 폼 접근 테스트")
    void testGetFreeBoardUpdateForm() throws Exception {
        mockMvc.perform(get("/board/updatefB/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("board/fBadd"))
                .andExpect(model().attributeExists("row"));
    }

    @Test
    @DisplayName("자유게시판 수정 처리 테스트")
    void testPostFreeBoardUpdate() throws Exception {
        mockMvc.perform(post("/board/updatefB/{id}", 1)
                        .param("title", "Updated Test Title")
                        .param("contents", "Updated Test Contents"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/freeBoard"));
    }

    // 질문게시판 테스트
    @Test
    @DisplayName("질문게시판 목록 조회 테스트")
    void testGetQnAList() throws Exception {
        mockMvc.perform(get("/board/QnA"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/QnA"))
                .andExpect(model().attributeExists("map"));
    }

    @Test
    @DisplayName("질문게시판 작성 폼 접근 테스트")
    void testGetQnAWriteForm() throws Exception {
        mockMvc.perform(get("/board/QnAadd"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/QnAadd"));
    }

    @Test
    @DisplayName("질문게시판 작성 처리 테스트")
    void testPostQnAWrite() throws Exception {
        mockMvc.perform(post("/board/QnAadd")
                        .param("title", "Test Title")
                        .param("contents", "Test Contents"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/QnA"));
    }

    @Test
    @DisplayName("질문게시판 상세 조회 테스트")
    void testGetQnADetail() throws Exception {
        mockMvc.perform(get("/board/QnA_view/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("board/QnA_view"))
                .andExpect(model().attributeExists("row"))
                .andExpect(model().attributeExists("replies"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @DisplayName("질문게시판 삭제 테스트")
    void testDeleteQnA() throws Exception {
        mockMvc.perform(post("/board/deleteQnA/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/QnA"));
    }

    @Test
    @DisplayName("질문게시판 수정 폼 접근 테스트")
    void testGetQnAUpdateForm() throws Exception {
        mockMvc.perform(get("/board/updateQnA/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("board/QnAadd"))
                .andExpect(model().attributeExists("row"));
    }

    @Test
    @DisplayName("질문게시판 수정 처리 테스트")
    void testPostQnAUpdate() throws Exception {
        mockMvc.perform(post("/board/updateQnA/{id}", 1)
                        .param("title", "Updated Test Title")
                        .param("contents", "Updated Test Contents"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/QnA"));
    }

    // 댓글 테스트
    @Test
    @DisplayName("댓글 조회 테스트")
    void testGetReplies() throws Exception {
        mockMvc.perform(get("/board/replies/{board_id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("board/QnA_view"))
                .andExpect(model().attributeExists("replies"))
                .andExpect(model().attributeExists("board_id"));
    }

    @Test
    @DisplayName("댓글 추가 테스트")
    void testAddReply() throws Exception {
        mockMvc.perform(post("/board/replies")
                        .param("board_id", "1")
                        .param("contents", "Test Reply"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/QnA_view/1"));
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void testDeleteReply() throws Exception {
        mockMvc.perform(post("/board/deleteReply/{id}", 1)
                        .param("board_id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/QnA_view/1"));
    }
}
